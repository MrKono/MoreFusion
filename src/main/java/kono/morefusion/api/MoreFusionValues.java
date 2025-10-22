package kono.morefusion.api;

import net.minecraft.resources.ResourceLocation;

public class MoreFusionValues {

    public static final String MODID = "morefusion";

    public static final String NAME = "MoreFusion";

    public static ResourceLocation modId(String name) {
        return new ResourceLocation(MODID, name);
    }
}
