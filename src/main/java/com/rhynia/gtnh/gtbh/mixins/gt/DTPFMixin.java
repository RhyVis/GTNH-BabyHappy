package com.rhynia.gtnh.gtbh.mixins.gt;

import javax.annotation.Nonnull;

import net.minecraftforge.fluids.FluidStack;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import gregtech.api.util.GT_Recipe;
import gregtech.common.tileentities.machines.multi.GT_MetaTileEntity_PlasmaForge;

@Mixin(value = GT_MetaTileEntity_PlasmaForge.class, priority = 2000, remap = false)
public class DTPFMixin {

    @Shadow
    private long running_time;
    @Final
    @Shadow
    private static double max_efficiency_time_in_ticks;
    @Shadow
    private double discount;
    @Final
    @Shadow
    private static double maximum_discount;
    @Final
    @Shadow
    private static FluidStack[] valid_fuels;

    /**
     * DTPF running will also give discount on recipe time, the minimum is 1 tick.
     * 
     * @author Rhynia
     * @reason Add time discount to DTPF
     * @since 1.0.3
     */
    @Nonnull
    @Overwrite
    protected GT_Recipe recipeAfterDiscount(@Nonnull GT_Recipe recipe) {
        GT_Recipe tRecipe = recipe.copy();
        outside: for (int i = 0; i < recipe.mFluidInputs.length; i++) {
            for (FluidStack fuel : valid_fuels) {
                if (tRecipe.mFluidInputs[i].isFluidEqual(fuel)) {
                    double time_percentage = running_time / max_efficiency_time_in_ticks;
                    time_percentage = Math.min(time_percentage, 1.0d);
                    discount = 1 - time_percentage * 0.5;
                    discount = Math.max(maximum_discount, discount);
                    tRecipe.mFluidInputs[i].amount = (int) Math.round(tRecipe.mFluidInputs[i].amount * discount);
                    break outside;
                }
            }
        }
        double time_discount_pre = running_time / max_efficiency_time_in_ticks;
        time_discount_pre = Math.min(time_discount_pre, 1.0d);
        tRecipe.mDuration = (int) Math.max(1, tRecipe.mDuration * (1 - time_discount_pre));
        return tRecipe;
    }
}
