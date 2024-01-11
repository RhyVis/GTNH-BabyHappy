package com.rhynia.gtnh.gtbh.mixins.l.tt;

import java.math.BigInteger;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.github.technus.tectech.thing.metaTileEntity.multi.GT_MetaTileEntity_EM_EyeOfHarmony;
import com.gtnewhorizon.gtnhmixins.LateMixin;

@LateMixin
@Mixin(value = GT_MetaTileEntity_EM_EyeOfHarmony.class, remap = false)
public class MixinEOH {

    @Shadow
    private long currentCircuitMultiplier;

    @ModifyArg(
        method = "processRecipe",
        at = @At(
            value = "INVOKE",
            target = "Lcom/github/technus/tectech/thing/metaTileEntity/multi/GT_MetaTileEntity_EM_EyeOfHarmony;addEUToGlobalEnergyMap(Ljava/lang/String;Ljava/math/BigInteger;)Z"),
        index = 1)
    private BigInteger bh$i(BigInteger k) {
        return k.multiply(BigInteger.valueOf((long) Math.pow(0.44, currentCircuitMultiplier)));
    }
}
