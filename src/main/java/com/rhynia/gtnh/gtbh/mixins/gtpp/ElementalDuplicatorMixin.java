package com.rhynia.gtnh.gtbh.mixins.gtpp;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gtPlusPlus.xmod.gregtech.common.tileentities.machines.multi.production.GregtechMTE_ElementalDuplicator;

@Mixin(value = GregtechMTE_ElementalDuplicator.class, priority = 2000, remap = false)
public class ElementalDuplicatorMixin {

    /**
     * Modify speed to be 300% (+200%).
     *
     * @since 1.0.2
     */
    @ModifyConstant(method = "createProcessingLogic", constant = @Constant(floatValue = 1F / 2F))
    private float bh$speedModify(float constant) {
        return 1F / 3F;
    }

    /**
     * Modify parallel limit to 32 each voltage.
     *
     * @since 1.0.2
     */
    @Inject(method = "getMaxParallelRecipes", at = @At("RETURN"), cancellable = true)
    private void bh$parallelModify(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(cir.getReturnValue() * 4);
    }

}
