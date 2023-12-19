package com.rhynia.gtnh.gtbh.mixins.gtpp;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;

import gtPlusPlus.xmod.gregtech.common.tileentities.machines.multi.processing.GregtechMetaTileEntity_IndustrialMultiMachine;

@Mixin(value = GregtechMetaTileEntity_IndustrialMultiMachine.class, priority = 2000, remap = false)
public class IndustrialMultiMixin {

    /**
     * Increase 9in1 parallel to 8 each voltage tier.
     *
     * @since 1.0.1
     */
    @ModifyReturnValue(method = "getMaxParallelRecipes", at = @At("RETURN"))
    private int bh$getMaxParallelRecipes(int original) {
        return original * 4;
    }
}
