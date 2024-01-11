package com.rhynia.gtnh.gtbh.mixins.bw;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.bartimaeusnek.bartworks.common.tileentities.multis.GT_TileEntity_CircuitAssemblyLine;

import gregtech.api.logic.ProcessingLogic;

@Mixin(value = GT_TileEntity_CircuitAssemblyLine.class, priority = 2000, remap = false)
public class MixinCAL {

    /**
     * A speed test for CAL.
     * !Not a default option!
     *
     * @since 1.0.10
     */
    @Inject(method = "createProcessingLogic", at = @At("RETURN"), cancellable = true)
    private void bh$injectSpeedModifier(CallbackInfoReturnable<ProcessingLogic> cir) {
        cir.setReturnValue(
            cir.getReturnValue()
                .setSpeedBonus(1F / 3F));
    }
}
