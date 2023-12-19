package com.rhynia.gtnh.gtbh.mixins.ae;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import appeng.core.api.definitions.ApiItems;
import appeng.items.storage.ItemExtremeStorageCell;

@Mixin(value = ApiItems.class, remap = false)
public class MixinQuantumCell {

    /**
     * Quantum Cell storage 63 types of item.
     *
     * @since 1.0.7
     */
    @Redirect(
        method = "<init>",
        at = @At(
            value = "NEW",
            target = "(Ljava/lang/String;JIID)Lappeng/items/storage/ItemExtremeStorageCell;",
            ordinal = 1))
    private ItemExtremeStorageCell bh$cellQuantumTypes(String name, long bytes, int types, int perType, double drain) {
        return new ItemExtremeStorageCell("Quantum", Integer.MAX_VALUE / 16, 63, 8, 1000);
    }
}
