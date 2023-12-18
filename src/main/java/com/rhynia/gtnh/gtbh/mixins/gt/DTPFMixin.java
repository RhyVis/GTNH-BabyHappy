package com.rhynia.gtnh.gtbh.mixins.gt;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gregtech.api.util.GT_Recipe;
import gregtech.common.tileentities.machines.multi.GT_MetaTileEntity_PlasmaForge;

@Mixin(value = GT_MetaTileEntity_PlasmaForge.class, priority = 2000, remap = false)
public class DTPFMixin {

    @Shadow
    private long running_time;
    @Final
    @Shadow
    private static double max_efficiency_time_in_ticks;

    @Inject(method = "recipeAfterDiscount", at = @At("RETURN"), cancellable = true)
    protected void bh$recipeAfterDiscount(GT_Recipe recipe, CallbackInfoReturnable<GT_Recipe> cir) {
        double time_discount_pre = running_time / max_efficiency_time_in_ticks;
        time_discount_pre = Math.min(time_discount_pre, 1.0);
        GT_Recipe kRecipe = cir.getReturnValue()
            .copy();
        kRecipe.mDuration = (int) Math.max(1, kRecipe.mDuration * (1.0 - time_discount_pre));
        cir.setReturnValue(kRecipe);
    }
}
