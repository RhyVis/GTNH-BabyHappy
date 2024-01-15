package com.rhynia.gtnh.gtbh.mixins.u.bw;

import net.minecraftforge.fluids.FluidStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.github.bartimaeusnek.crossmod.galacticgreg.GT_TileEntity_VoidMiner_Base;

@Mixin(value = GT_TileEntity_VoidMiner_Base.class, priority = 2000, remap = false)
public class MixinVoidMiner {

    @Shadow
    private int multiplier;

    @Inject(method = "getNobleGasInputAndSetMultiplier", at = @At(value = "RETURN", ordinal = 0))
    private void bh$injectMultiplier(CallbackInfoReturnable<FluidStack> cir) {
        this.multiplier = this.multiplier * 16;
    }
}
