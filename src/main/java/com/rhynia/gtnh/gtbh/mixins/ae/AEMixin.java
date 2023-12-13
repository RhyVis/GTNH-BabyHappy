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

@SuppressWarnings("UnusedMixin")
@Mixin(value = ItemExtremeStorageCell.class, priority = 2000, remap = false)
public class AEMixin {

    @Mutable
    @Shadow
    @Final
    protected int totalTypes;

    @Inject(method = "<init>", at = @At("RETURN"))
    public void bh$ItemExtremeStorageCell(String name, long bytes, int types, int perType, double drain,
        CallbackInfo ci) {
        if (Objects.equals(name, "Quantum")) {
            this.totalTypes = 63;
        }
    }
}
