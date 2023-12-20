package com.rhynia.gtnh.gtbh.mixins.gt;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gregtech.api.logic.ProcessingLogic;
import gregtech.common.tileentities.machines.multi.GT_MetaTileEntity_PlasmaForge;

@Mixin(value = GT_MetaTileEntity_PlasmaForge.class, priority = 2000, remap = false)
public class MixinDTPF {

    @Shadow
    private double discount;

    @Inject(method = "createProcessingLogic", at = @At("RETURN"), cancellable = true)
    private void bh$injectSpeedModifier(CallbackInfoReturnable<ProcessingLogic> cir) {
        cir.setReturnValue(
            cir.getReturnValue()
                .setSpeedBonus(Math.max(1F / 4F, (float) discount)));
    }
}
