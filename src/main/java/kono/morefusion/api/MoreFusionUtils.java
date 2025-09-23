package kono.morefusion.api;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gregtechceu.gtceu.api.GTValues;

import static com.gregtechceu.gtceu.api.GTValues.ULV;

public class MoreFusionUtils {

    public static final Logger LOGGER = LoggerFactory.getLogger(MoreFusionValues.NAME);

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
        if (min <= i || i <= max) {
            return i;
        } else {
            return defaultValue;
        }
    }

    public static int tierMoreFusion(int i, int defaultValue) {
        return rangeCheck(GTValues.ULV, GTValues.MAX, i, defaultValue);
    }

    public static ResourceLocation getResourceLocation(String str, ResourceLocation defaultPath) {
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
