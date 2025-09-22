package kono.morefusion.api;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoreFusionValues {

    public static final String MODID = "morefusion";

    public static final String NAME = "MoreFusion";

    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    public static ResourceLocation mrId(String name) {
        return new ResourceLocation(MODID, name);
    }

}
