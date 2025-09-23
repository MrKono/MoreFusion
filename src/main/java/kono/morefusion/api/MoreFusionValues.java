package kono.morefusion.api;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.resources.ResourceLocation;

import kono.morefusion.MoreFusionConfig;

import static kono.morefusion.api.MoreFusionUtils.*;

public class MoreFusionValues {

    public static final MoreFusionConfig config = MoreFusionConfig.INSTANCE;

    public static final String MODID = "morefusion";

    public static final String NAME = "MoreFusion";

    public static ResourceLocation mrId(String name) {
        return new ResourceLocation(MODID, name);
    }

    public static List<Long> getBaseHeat() {
        List<Long> baseHeatList = new ArrayList<>();
        // Mk0
        baseHeatList.add(castHeat(config.mk0.heatMk0, 5000000));
        // Mk1
        baseHeatList.add(castHeat(config.mk1.heatMk1, 10000000));
        // Mk2
        baseHeatList.add(castHeat(config.mk2.heatMk2, 20000000));
        // Mk3
        baseHeatList.add(castHeat(config.mk3.heatMk3, 40000000));
        // Mk4
        baseHeatList.add(castHeat(config.mk4.heatMk4, 80000000));
        // Mk5
        baseHeatList.add(castHeat(config.mk5.heatMk5, 160000000));
        // Mk6
        baseHeatList.add(castHeat(config.mk6.heatMk6, 320000000));
        // Mk7
        baseHeatList.add(castHeat(config.mk7.heatMk7, 640000000));

        return baseHeatList;
    }
}
