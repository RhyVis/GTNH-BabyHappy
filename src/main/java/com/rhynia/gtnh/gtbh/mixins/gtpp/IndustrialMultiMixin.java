package com.rhynia.gtnh.gtbh.mixins.gtpp;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gtPlusPlus.xmod.gregtech.common.tileentities.machines.multi.processing.GregtechMetaTileEntity_IndustrialMultiMachine;

@SuppressWarnings("UnusedMixin")
@Mixin(value = GregtechMetaTileEntity_IndustrialMultiMachine.class, priority = 2000, remap = false)
public class IndustrialMultiMixin {

    /**
     * Increase 9in1 parallel to 8 each voltage tier.
     *
     * @since 1.0.1
     */
    @Inject(method = "getMaxParallelRecipes", at = @At("RETURN"), cancellable = true)
    private void bh$getMaxParallelRecipes(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(cir.getReturnValue() * 4);
    }
}
