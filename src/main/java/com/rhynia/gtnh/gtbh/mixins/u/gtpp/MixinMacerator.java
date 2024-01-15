package com.rhynia.gtnh.gtbh.mixins.u.gtpp;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;

import gregtech.api.logic.ProcessingLogic;
import gtPlusPlus.xmod.gregtech.common.tileentities.machines.multi.processing.GregtechMetaTileEntity_IndustrialMacerator;

@SuppressWarnings("SpellCheckingInspection")
@Mixin(value = GregtechMetaTileEntity_IndustrialMacerator.class, priority = 2000, remap = false)
public class MixinMacerator {

    @ModifyConstant(method = "createProcessingLogic", constant = @Constant(floatValue = 1.0F / 1.6F))
    private float bh$rSpeedBounus(float c) {
        return 1.0F / 3.0F;
    }

    @ModifyReturnValue(method = "createProcessingLogic", at = @At("RETURN"))
    private ProcessingLogic bh$injectEUModifier(ProcessingLogic o) {
        return o.setEuModifier(1.0F / 4.0F);
    }

    @ModifyReturnValue(method = "getMaxParallelRecipes", at = @At("RETURN"))
    private int bh$rParallel(int o) {
        return o * 8;
    }
}
