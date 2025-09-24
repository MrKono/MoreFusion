package kono.morefusion.api;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gregtechceu.gtceu.api.GTValues;

import kono.morefusion.MoreFusionConfig;

import static com.gregtechceu.gtceu.api.GTValues.ULV;

public class MoreFusionUtils {

    public static final Logger LOGGER = LoggerFactory.getLogger(MoreFusionValues.NAME);

    public record MkCfg(
                        int mk, int tier, String heat, String casing, String glass, String coil, String model) {}

    // mk→cfg を返すヘルパ
    public static MkCfg cfgMk(int mk) {
        return switch (mk) {
            case 1 -> {
                var c = MoreFusionConfig.INSTANCE.mk1;
                yield new MkCfg(1, c.tierMk1, c.heatMk1, c.casingMk1, c.glassMk1, c.coilMk1, c.modelMk1);
            }
            case 2 -> {
                var c = MoreFusionConfig.INSTANCE.mk2;
                yield new MkCfg(2, c.tierMk2, c.heatMk2, c.casingMk2, c.glassMk2, c.coilMk2, c.modelMk2);
            }
            case 3 -> {
                var c = MoreFusionConfig.INSTANCE.mk3;
                yield new MkCfg(3, c.tierMk3, c.heatMk3, c.casingMk3, c.glassMk3, c.coilMk3, c.modelMk3);
            }
            case 4 -> {
                var c = MoreFusionConfig.INSTANCE.mk4;
                yield new MkCfg(4, c.tierMk4, c.heatMk4, c.casingMk4, c.glassMk4, c.coilMk4, c.modelMk4);
            }
            case 5 -> {
                var c = MoreFusionConfig.INSTANCE.mk5;
                yield new MkCfg(5, c.tierMk5, c.heatMk5, c.casingMk5, c.glassMk5, c.coilMk5, c.modelMk5);
            }
            case 6 -> {
                var c = MoreFusionConfig.INSTANCE.mk6;
                yield new MkCfg(6, c.tierMk6, c.heatMk6, c.casingMk6, c.glassMk6, c.coilMk6, c.modelMk6);
            }
            case 7 -> {
                var c = MoreFusionConfig.INSTANCE.mk7;
                yield new MkCfg(7, c.tierMk7, c.heatMk7, c.casingMk7, c.glassMk7, c.coilMk7, c.modelMk7);
            }
            default -> {
                var c = MoreFusionConfig.INSTANCE.mk0;
                yield new MkCfg(0, c.tierMk0, c.heatMk0, c.casingMk0, c.glassMk0, c.coilMk0, c.modelMk0);
            }
        };
    }

    public static long castHeat(String str, long defaultValue) {
        final long MAX_SAFE = 576460752303423487L; // Long.MAX_VALUE / 16

        try {
            long value = Long.parseLong(str.trim());

            if (value < 1) {
                return defaultValue;
            }
            return Math.min(value, MAX_SAFE);
        } catch (NumberFormatException e) {
            MoreFusionUtils.LOGGER.error("Failed to parse baseHeat from {}. Applied default value: {}", str,
                    defaultValue);
            return defaultValue;
        }
    }

    public static Block getBlockFromString(String str, Block defaultBlock) {
        ResourceLocation df = ForgeRegistries.BLOCKS.getKey(defaultBlock);
        if (str == null || str.isEmpty()) {
            return defaultBlock;
        }
        try {
            ResourceLocation rl = new ResourceLocation(str.trim());
            Block block = ForgeRegistries.BLOCKS.getValue(rl);

            if (block == null || block == Blocks.AIR) {
                MoreFusionUtils.LOGGER.error("Failed to get Block '{}'. Applied to default '{}'", str, df);
                return defaultBlock;
            }
            return block;

        } catch (Exception e) {
            MoreFusionUtils.LOGGER
                    .error("'{}' was invalid format or not registered. Applied to default '{}'", str, df);
            return defaultBlock;
        }
    }

    public static int rangeCheck(int min, int max, int i, int defaultValue) {
        if (min <= i && i <= max) {
            return i;
        } else if (max < i) {
            return max;
        } else {
            return defaultValue;
        }
    }

    public static int tierMoreFusion(int i, int defaultValue) {
        return rangeCheck(GTValues.ULV, GTValues.MAX, i, defaultValue);
    }

    public static ResourceLocation getResourceLocationFromString(String str, ResourceLocation defaultPath) {
        if (str == null || str.isEmpty()) {
            LOGGER.error("ResourceLocation was empty. Applied default: '{}'.", defaultPath);
            return defaultPath;
        }

        try {
            String[] parts = str.trim().split(":", 2);
            if (parts.length == 2) {
                return new ResourceLocation(parts[0], parts[1]);
            } else {
                LOGGER.error("'{}' was invalid format. Applied to default: '{}'.", str, defaultPath);
                return defaultPath;
            }
        } catch (Exception e) {
            LOGGER.error("Failed to accept: '{}'. Applied to default: {}.", str, defaultPath);
            return defaultPath;
        }
    }
}
