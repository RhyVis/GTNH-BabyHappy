package com.rhynia.gtnh.gtbh.mixins.tt;

import static com.rhynia.gtnh.gtbh.util.ConfigValues.EOH_CostPowBase;
import static com.rhynia.gtnh.gtbh.util.ConfigValues.EOH_OutputEUMultiplier;

import java.math.BigInteger;

import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.github.technus.tectech.thing.metaTileEntity.multi.GT_MetaTileEntity_EM_EyeOfHarmony;

@Mixin(value = GT_MetaTileEntity_EM_EyeOfHarmony.class, remap = false)
public class MixinEOH {

    @Shadow
    private BigInteger usedEU;

    @Shadow
    private long currentCircuitMultiplier;

    @Shadow
    private BigInteger outputEU_BigInt;

    /**
     * A test to reduce energy cost.
     *
     * @param original Original EU cost calculated
     * @since 1.0.10
     */
    @Redirect(
        method = "processRecipe",
        at = @At(
            value = "FIELD",
            target = "Lcom/github/technus/tectech/thing/metaTileEntity/multi/GT_MetaTileEntity_EM_EyeOfHarmony;usedEU:Ljava/math/BigInteger;",
            opcode = Opcodes.PUTFIELD))
    private void bh$euModifier(GT_MetaTileEntity_EM_EyeOfHarmony instance, BigInteger original) {
        usedEU = original.multiply(BigInteger.valueOf((long) Math.pow(EOH_CostPowBase, currentCircuitMultiplier)));
    }

    /**
     * A test to modify energy output.
     *
     * @param original Original EU output calculated
     * @since 1.0.10
     */
    @Redirect(
        method = "processRecipe",
        at = @At(
            value = "FIELD",
            target = "Lcom/github/technus/tectech/thing/metaTileEntity/multi/GT_MetaTileEntity_EM_EyeOfHarmony;outputEU_BigInt:Ljava/math/BigInteger;",
            ordinal = 0,
            opcode = Opcodes.PUTFIELD))
    private void bh$outputModifier(GT_MetaTileEntity_EM_EyeOfHarmony instance, BigInteger original) {
        outputEU_BigInt = original.multiply(BigInteger.valueOf(EOH_OutputEUMultiplier));
    }
}
