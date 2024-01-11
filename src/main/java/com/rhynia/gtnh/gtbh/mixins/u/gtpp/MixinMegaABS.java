package com.rhynia.gtnh.gtbh.mixins.u.gtpp;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gregtech.api.enums.HeatingCoilLevel;
import gtPlusPlus.xmod.gregtech.common.tileentities.machines.multi.production.mega.GregTechMetaTileEntity_MegaAlloyBlastSmelter;

@Mixin(value = GregTechMetaTileEntity_MegaAlloyBlastSmelter.class, priority = 2000, remap = false)
public class MixinMegaABS {

    /**
     * Modify coil discount to be 5% faster each coil, in pow calculation.
     *
     * @since 1.0.0
     */
    @Inject(method = "getCoilDiscount", at = @At("HEAD"), cancellable = true)
    private void bh$getCoilDiscount(@NotNull HeatingCoilLevel lvl, @NotNull CallbackInfoReturnable<Double> cir) {
        cir.setReturnValue(1.0 - Math.pow(0.95, lvl.getTier()));
    }

    /**
     * Modify parallel limit to 1024.
     *
     * @since 1.0.0
     */
    @ModifyConstant(method = "createProcessingLogic", constant = @Constant(intValue = 256))
    private int bh$parallelModify(int parallel) {
        return 1024;
    }

}
