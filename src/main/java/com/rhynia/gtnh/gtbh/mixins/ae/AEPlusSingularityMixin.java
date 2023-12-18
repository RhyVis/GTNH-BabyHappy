package com.rhynia.gtnh.gtbh.mixins.ae;

import java.util.Objects;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import appeng.items.storage.ItemExtremeStorageCell;

@Mixin(value = ItemExtremeStorageCell.class, priority = 2000, remap = false)
public class AEPlusSingularityMixin {

    @Mutable
    @Shadow
    @Final
    protected int totalTypes;

    /**
     * Increase Quantum & Singularity Cell to storage 63 types of item.
     * Will not be added to mixin config by default.
     *
     * @since 1.0.3
     */
    @Inject(method = "<init>", at = @At("RETURN"))
    public void bh$ItemExtremeStorageCell(String name, long bytes, int types, int perType, double drain,
        CallbackInfo ci) {
        if (Objects.equals(name, "Quantum") | Objects.equals(name, "Singularity")) {
            this.totalTypes = 63;
        }
    }
}
