package com.rhynia.gtnh.gtbh.mixins.gt;

import javax.annotation.Nonnull;

import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import gregtech.api.enums.MaterialsUEVplus;
import gregtech.api.logic.ProcessingLogic;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_ExtendedPowerMultiBlockBase;
import gregtech.api.recipe.check.CheckRecipeResult;
import gregtech.api.recipe.check.CheckRecipeResultRegistry;
import gregtech.api.util.GT_OverclockCalculator;
import gregtech.api.util.GT_ParallelHelper;
import gregtech.api.util.GT_Recipe;
import gregtech.common.tileentities.machines.multi.GT_MetaTileEntity_PlasmaForge;

@Mixin(value = GT_MetaTileEntity_PlasmaForge.class, remap = false)
public abstract class DTPFMixin extends GT_MetaTileEntity_ExtendedPowerMultiBlockBase<GT_MetaTileEntity_PlasmaForge> {

    protected DTPFMixin(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    @Shadow
    private int mHeatingCapacity;
    @Final
    @Shadow
    private static final FluidStack[] valid_fuels = { MaterialsUEVplus.ExcitedDTEC.getFluid(1L),
        MaterialsUEVplus.ExcitedDTRC.getFluid(1L), MaterialsUEVplus.ExcitedDTPC.getFluid(1L),
        MaterialsUEVplus.ExcitedDTCC.getFluid(1L), MaterialsUEVplus.ExcitedDTSC.getFluid(1L) };
    @Shadow
    private long running_time;
    @Final
    @Shadow
    private static final double max_efficiency_time_in_ticks = 3600d * 8d * 20d;
    @Final
    @Shadow
    private static final double maximum_discount = 0.5d;
    @Shadow
    private double discount;

    /**
     * DTPF time discount.
     *
     * @author Rhynia
     * @reason Fetched strange errors during inject
     * @since 1.0.6
     */
    @Overwrite
    @Override
    protected ProcessingLogic createProcessingLogic() {
        return new ProcessingLogic() {

            @Nonnull
            @Override
            protected GT_OverclockCalculator createOverclockCalculator(@Nonnull GT_Recipe recipe) {
                return super.createOverclockCalculator(recipe).setRecipeHeat(recipe.mSpecialValue)
                    .setMachineHeat(mHeatingCapacity);
            }

            @NotNull
            @Override
            protected GT_ParallelHelper createParallelHelper(@Nonnull GT_Recipe recipe) {
                return super.createParallelHelper(bh$recipeAfterDiscount(recipe));
            }

            @Override
            protected @Nonnull CheckRecipeResult validateRecipe(@Nonnull GT_Recipe recipe) {
                return recipe.mSpecialValue <= mHeatingCapacity ? CheckRecipeResultRegistry.SUCCESSFUL
                    : CheckRecipeResultRegistry.insufficientHeat(recipe.mSpecialValue);
            }
        };

    }

    @Unique
    protected GT_Recipe bh$recipeAfterDiscount(@Nonnull GT_Recipe recipe) {
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
