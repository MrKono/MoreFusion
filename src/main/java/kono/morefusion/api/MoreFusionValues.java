package kono.morefusion.api;

import net.minecraft.resources.ResourceLocation;

import kono.morefusion.MoreFusionConfig;

import static kono.morefusion.api.MoreFusionUtils.*;

public class MoreFusionValues {

    public static final MoreFusionConfig.cfgMk0 mk0 = MoreFusionConfig.INSTANCE.mk0;
    public static final MoreFusionConfig.cfgMk1 mk1 = MoreFusionConfig.INSTANCE.mk1;
    public static final MoreFusionConfig.cfgMk2 mk2 = MoreFusionConfig.INSTANCE.mk2;
    public static final MoreFusionConfig.cfgMk3 mk3 = MoreFusionConfig.INSTANCE.mk3;
    public static final MoreFusionConfig.cfgMk4 mk4 = MoreFusionConfig.INSTANCE.mk4;
    public static final MoreFusionConfig.cfgMk5 mk5 = MoreFusionConfig.INSTANCE.mk5;
    public static final MoreFusionConfig.cfgMk6 mk6 = MoreFusionConfig.INSTANCE.mk6;
    public static final MoreFusionConfig.cfgMk7 mk7 = MoreFusionConfig.INSTANCE.mk7;

    public static final String MODID = "morefusion";

    public static final String NAME = "MoreFusion";

    public static ResourceLocation mrId(String name) {
        return new ResourceLocation(MODID, name);
    }
}
