package com.rhynia.gtnh.gtbh.mixinplugin;

import java.util.Arrays;
import java.util.List;

public enum Mixin {

    GTPPMegaABSMixin("gtpp.MegaABSMixin", TargetedMod.GTPP);

    public final String mixinClass;
    public final List<TargetedMod> targetedMods;

    Mixin(String mixinClass, TargetedMod... targetedMods) {
        this.mixinClass = mixinClass;
        this.targetedMods = Arrays.asList(targetedMods);
    }

    public boolean shouldLoad(List<TargetedMod> loadedMods) {
        return loadedMods.containsAll(this.targetedMods);
    }

}
