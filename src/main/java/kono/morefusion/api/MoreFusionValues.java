package kono.morefusion.api;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.common.data.GCYMBlocks;
import com.gregtechceu.gtceu.common.data.GTBlocks;

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

    public static List<Block> getCasing() {
        Block atomic = GCYMBlocks.CASING_ATOMIC.get();
        List<Block> blocks = new ArrayList<>();
        // Mk0
        blocks.add(getBlockFromString(config.mk0.casingMk0, GCYMBlocks.CASING_HIGH_TEMPERATURE_SMELTING.get()));
        // Mk1
        blocks.add(getBlockFromString(config.mk1.casingMk1, GTBlocks.FUSION_CASING.get()));
        // Mk2
        blocks.add(getBlockFromString(config.mk2.casingMk2, GTBlocks.FUSION_CASING_MK2.get()));
        // Mk3
        blocks.add(getBlockFromString(config.mk3.casingMk3, GTBlocks.FUSION_CASING_MK3.get()));
        // Mk4
        blocks.add(getBlockFromString(config.mk4.casingMk4, atomic));
        // Mk5
        blocks.add(getBlockFromString(config.mk5.casingMk5, atomic));
        // Mk6
        blocks.add(getBlockFromString(config.mk6.casingMk6, atomic));
        // Mk7
        blocks.add(getBlockFromString(config.mk7.casingMk7, atomic));

        return blocks;
    }

    public static List<Block> getGlass() {
        Block glass = GTBlocks.FUSION_GLASS.get();
        List<Block> glasses = new ArrayList<>();
        // Mk0
        glasses.add(getBlockFromString(config.mk0.glassMk0, GTBlocks.CASING_LAMINATED_GLASS.get()));
        // Mk1
        glasses.add(getBlockFromString(config.mk1.glassMk1, glass));
        // Mk2
        glasses.add(getBlockFromString(config.mk2.glassMk2, glass));
        // Mk3
        glasses.add(getBlockFromString(config.mk3.glassMk3, glass));
        // Mk4
        glasses.add(getBlockFromString(config.mk4.glassMk4, glass));
        // Mk5
        glasses.add(getBlockFromString(config.mk5.glassMk5, glass));
        // Mk6
        glasses.add(getBlockFromString(config.mk6.glassMk6, glass));
        // Mk7
        glasses.add(getBlockFromString(config.mk7.glassMk7, glass));

        return glasses;
    }

    public static List<Block> getCoil() {
        Block coil = GTBlocks.FUSION_COIL.get();
        List<Block> coils = new ArrayList<>();
        // Mk0
        coils.add(getBlockFromString(config.mk0.coilMk0, GTBlocks.COIL_NAQUADAH.get()));
        // Mk1
        coils.add(getBlockFromString(config.mk1.coilMk1, GTBlocks.SUPERCONDUCTING_COIL.get()));
        // Mk2
        coils.add(getBlockFromString(config.mk2.coilMk2, coil));
        // Mk3
        coils.add(getBlockFromString(config.mk3.coilMk3, coil));
        // Mk4
        coils.add(getBlockFromString(config.mk4.coilMk4, coil));
        // Mk5
        coils.add(getBlockFromString(config.mk5.coilMk5, coil));
        // Mk6
        coils.add(getBlockFromString(config.mk6.coilMk6, coil));
        // Mk7
        coils.add(getBlockFromString(config.mk7.coilMk7, coil));

        return coils;
    }

    public static List<Integer> getFusionTier() {
        List<Integer> tiers = new ArrayList<>();
        // Mk0
        tiers.add(tierMoreFusion(config.mk0.tierMk0, GTValues.IV));
        // Mk1
        tiers.add(tierMoreFusion(config.mk1.tierMk1, GTValues.LuV));
        // Mk2
        tiers.add(tierMoreFusion(config.mk2.tierMk2, GTValues.ZPM));
        // Mk3
        tiers.add(tierMoreFusion(config.mk3.tierMk3, GTValues.UV));
        // Mk4
        tiers.add(tierMoreFusion(config.mk4.tierMk4, GTValues.UHV));
        // Mk5
        tiers.add(tierMoreFusion(config.mk5.tierMk5, GTValues.UEV));
        // Mk6
        tiers.add(tierMoreFusion(config.mk6.tierMk6, GTValues.UIV));
        // Mk7
        tiers.add(tierMoreFusion(config.mk7.tierMk7, GTValues.UXV));

        return tiers;
    }
}
