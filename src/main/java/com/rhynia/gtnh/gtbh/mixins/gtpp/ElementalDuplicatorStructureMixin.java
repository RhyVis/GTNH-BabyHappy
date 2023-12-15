package com.rhynia.gtnh.gtbh.mixins.gtpp;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import gtPlusPlus.xmod.gregtech.common.tileentities.machines.multi.production.GregtechMTE_ElementalDuplicator;

@Mixin(value = GregtechMTE_ElementalDuplicator.class, priority = 2000, remap = false)
public class ElementalDuplicatorStructureMixin {

    /**
     * Structure check always return true.
     *
     * @since 1.0.2
     */
    @Inject(method = "checkMachine", at = @At("RETURN"), cancellable = true)
    private void bh$parallelModify(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }

}
