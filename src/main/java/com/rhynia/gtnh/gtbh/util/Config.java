package com.rhynia.gtnh.gtbh.util;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    // region EOH
    private static final String EOH = "EOH";
    public static double Conf_EOH_CostPowBase;
    public static long Conf_EOH_OutputEUMultiplier;

    // endregion
    public static void synchronizeConfiguration(File cFile) {
        Configuration conf = new Configuration(cFile);
        Conf_EOH_CostPowBase = Double.parseDouble(conf.getString("EOH_CostPowBase", EOH, "0.44", "Type: Double"));
        Conf_EOH_OutputEUMultiplier = Long.parseLong(conf.getString("EOH_OutputEUMultiplier", EOH, "1", "Type: Long"));
        if (conf.hasChanged()) {
            conf.save();
        }
    }
}
