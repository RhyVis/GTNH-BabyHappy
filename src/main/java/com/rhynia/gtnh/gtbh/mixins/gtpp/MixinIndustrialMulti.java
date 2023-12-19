package com.rhynia.gtnh.gtbh.mixins.gtpp;

import java.util.List;

import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;

import gtPlusPlus.xmod.gregtech.common.tileentities.machines.multi.processing.GregtechMetaTileEntity_IndustrialMultiMachine;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;

@Mixin(value = GregtechMetaTileEntity_IndustrialMultiMachine.class, priority = 2000, remap = false)
public class MixinIndustrialMulti {

    /**
     * Increase 9in1 speed to 500% (+400%).
     *
     * @since 1.0.7
     */
    @ModifyConstant(method = "createProcessingLogic", constant = @Constant(floatValue = 1F / 3.5F))
    private float bh$speedModify(float speed) {
        return 1F / 5.0F;
    }

    /**
     * Decrease 9in1 energy consumption to 50%.
     *
     * @since 1.0.7
     */
    @ModifyConstant(method = "createProcessingLogic", constant = @Constant(floatValue = 0.8F))
    private float bh$energyModify(float eu) {
        return 0.5F;
    }

    /**
     * Increase 9in1 parallel to 8 each voltage tier.
     *
     * @since 1.0.1
     */
    @ModifyReturnValue(method = "getMaxParallelRecipes", at = @At("RETURN"))
    private int bh$getMaxParallelRecipes(int original) {
        return original * 4;
    }

    /**
     * I believe it AFFECTS BALANCE.
     *
     * @since 1.0.7
     */
    @Inject(method = "getWailaBody", at = @At("TAIL"))
    private void bh$tauntMessage(ItemStack itemStack, List<String> currentTip, IWailaDataAccessor accessor,
        IWailaConfigHandler config, CallbackInfo ci) {
        currentTip.add("Nine in One - Insane");
    }
}
