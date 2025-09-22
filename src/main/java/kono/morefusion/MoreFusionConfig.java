package kono.morefusion;

import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.Config;
import dev.toma.configuration.config.Configurable;
import dev.toma.configuration.config.format.ConfigFormats;
import kono.morefusion.api.MoreFusionValues;

@Config(id = MoreFusionValues.MODID)
public class MoreFusionConfig {

    public static  MoreFusionConfig INSTANCE;

    public static void init() {
        INSTANCE = Configuration.registerConfig(MoreFusionConfig.class, ConfigFormats.yaml())
                .getConfigInstance();
    }

    @Configurable
    public cfgMk0 mk0 = new cfgMk0();
    @Configurable
    public cfgMk1 mk1 = new cfgMk1();
    @Configurable
    public cfgMk2 mk2 = new cfgMk2();
    @Configurable
    public cfgMk3 mk3 = new cfgMk3();
    @Configurable
    public cfgMk4 mk4 = new cfgMk4();
    @Configurable
    public cfgMk5 mk5 = new cfgMk5();
    @Configurable
    public cfgMk6 mk6 = new cfgMk6();
    @Configurable
    public cfgMk7 mk7 = new cfgMk7();

    public static class cfgMk0 {}

    public static class cfgMk1 {}

    public static class cfgMk2 {}

    public static class cfgMk3 {}

    public static class cfgMk4 {}

    public static class cfgMk5 {}

    public static class cfgMk6 {}

    public static class cfgMk7 {}
}
