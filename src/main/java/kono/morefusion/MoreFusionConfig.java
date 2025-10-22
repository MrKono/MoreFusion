package kono.morefusion;

import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.Config;
import dev.toma.configuration.config.Configurable;
import dev.toma.configuration.config.format.ConfigFormats;

import kono.morefusion.api.MoreFusionValues;

@Config(id = MoreFusionValues.MODID)
public class MoreFusionConfig {

    public static MoreFusionConfig INSTANCE;

    public static void init() {
        if (INSTANCE == null) {
            INSTANCE = Configuration.registerConfig(MoreFusionConfig.class, ConfigFormats.yaml()).getConfigInstance();
        }
    }
}
