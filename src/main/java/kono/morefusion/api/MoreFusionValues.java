package kono.morefusion.api;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.common.block.FusionCasingBlock;
import com.gregtechceu.gtceu.common.data.GCYMBlocks;
import com.gregtechceu.gtceu.common.data.GTBlocks;

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

    public static List<Long> getBaseHeat() {
        List<Long> baseHeatList = new ArrayList<>();
        // Mk0
        baseHeatList.add(castHeat(mk0.heatMk0, 5000000));
        // Mk1
        baseHeatList.add(castHeat(mk1.heatMk1, 10000000));
        // Mk2
        baseHeatList.add(castHeat(mk2.heatMk2, 20000000));
        // Mk3
        baseHeatList.add(castHeat(mk3.heatMk3, 40000000));
        // Mk4
        baseHeatList.add(castHeat(mk4.heatMk4, 80000000));
        // Mk5
        baseHeatList.add(castHeat(mk5.heatMk5, 160000000));
        // Mk6
        baseHeatList.add(castHeat(mk6.heatMk6, 320000000));
        // Mk7
        baseHeatList.add(castHeat(mk7.heatMk7, 640000000));

        return baseHeatList;
    }

    public static List<Block> getCasing() {
        Block atomic = GCYMBlocks.CASING_ATOMIC.get();
        List<Block> blocks = new ArrayList<>();
        // Mk0
        blocks.add(getBlockFromString(mk0.casingMk0, GCYMBlocks.CASING_HIGH_TEMPERATURE_SMELTING.get()));
        // Mk1
        blocks.add(getBlockFromString(mk1.casingMk1, GTBlocks.FUSION_CASING.get()));
        // Mk2
        blocks.add(getBlockFromString(mk2.casingMk2, GTBlocks.FUSION_CASING_MK2.get()));
        // Mk3
        blocks.add(getBlockFromString(mk3.casingMk3, GTBlocks.FUSION_CASING_MK3.get()));
        // Mk4
        blocks.add(getBlockFromString(mk4.casingMk4, atomic));
        // Mk5
        blocks.add(getBlockFromString(mk5.casingMk5, atomic));
        // Mk6
        blocks.add(getBlockFromString(mk6.casingMk6, atomic));
        // Mk7
        blocks.add(getBlockFromString(mk7.casingMk7, atomic));

        return blocks;
    }

    public static List<Block> getGlass() {
        Block glass = GTBlocks.FUSION_GLASS.get();
        List<Block> glasses = new ArrayList<>();
        // Mk0
        glasses.add(getBlockFromString(mk0.glassMk0, GTBlocks.CASING_LAMINATED_GLASS.get()));
        // Mk1
        glasses.add(getBlockFromString(mk1.glassMk1, glass));
        // Mk2
        glasses.add(getBlockFromString(mk2.glassMk2, glass));
        // Mk3
        glasses.add(getBlockFromString(mk3.glassMk3, glass));
        // Mk4
        glasses.add(getBlockFromString(mk4.glassMk4, glass));
        // Mk5
        glasses.add(getBlockFromString(mk5.glassMk5, glass));
        // Mk6
        glasses.add(getBlockFromString(mk6.glassMk6, glass));
        // Mk7
        glasses.add(getBlockFromString(mk7.glassMk7, glass));

        return glasses;
    }

    public static List<Block> getCoil() {
        Block coil = GTBlocks.FUSION_COIL.get();
        List<Block> coils = new ArrayList<>();
        // Mk0
        coils.add(getBlockFromString(mk0.coilMk0, GTBlocks.COIL_NAQUADAH.get()));
        // Mk1
        coils.add(getBlockFromString(mk1.coilMk1, GTBlocks.SUPERCONDUCTING_COIL.get()));
        // Mk2
        coils.add(getBlockFromString(mk2.coilMk2, coil));
        // Mk3
        coils.add(getBlockFromString(mk3.coilMk3, coil));
        // Mk4
        coils.add(getBlockFromString(mk4.coilMk4, coil));
        // Mk5
        coils.add(getBlockFromString(mk5.coilMk5, coil));
        // Mk6
        coils.add(getBlockFromString(mk6.coilMk6, coil));
        // Mk7
        coils.add(getBlockFromString(mk7.coilMk7, coil));

        return coils;
    }

    public static List<Integer> getFusionTier() {
        List<Integer> tiers = new ArrayList<>();
        // Mk0
        tiers.add(tierMoreFusion(mk0.tierMk0, GTValues.IV));
        // Mk1
        tiers.add(tierMoreFusion(mk1.tierMk1, GTValues.LuV));
        // Mk2
        tiers.add(tierMoreFusion(mk2.tierMk2, GTValues.ZPM));
        // Mk3
        tiers.add(tierMoreFusion(mk3.tierMk3, GTValues.UV));
        // Mk4
        tiers.add(tierMoreFusion(mk4.tierMk4, GTValues.UHV));
        // Mk5
        tiers.add(tierMoreFusion(mk5.tierMk5, GTValues.UEV));
        // Mk6
        tiers.add(tierMoreFusion(mk6.tierMk6, GTValues.UIV));
        // Mk7
        tiers.add(tierMoreFusion(mk7.tierMk7, GTValues.UXV));

        return tiers;
    }

    public static List<ResourceLocation> getLocation() {
        List<ResourceLocation> locations = new ArrayList<>();
        // Mk0
        locations.add(getResourceLocation(mk0.modelMk0,
                GTCEu.id("block/casings/gcym/high_temperature_smelting_casing")));
        // Mk1
        locations
                .add(getResourceLocation(mk1.modelMk1, FusionCasingBlock.CasingType.FUSION_CASING.getTexture()));
        // Mk2
        locations.add(
                getResourceLocation(mk2.modelMk2, FusionCasingBlock.CasingType.FUSION_CASING_MK2.getTexture()));
        // Mk3
        locations.add(
                getResourceLocation(mk3.modelMk3, FusionCasingBlock.CasingType.FUSION_CASING_MK3.getTexture()));
        // Mk4
        locations.add(getResourceLocation(mk4.modelMk4, GTCEu.id("block/casings/gcym/atomic_casing")));
        // Mk5
        locations.add(getResourceLocation(mk5.modelMk5, GTCEu.id("block/casings/gcym/atomic_casing")));
        // Mk6
        locations.add(getResourceLocation(mk6.modelMk6, GTCEu.id("block/casings/gcym/atomic_casing")));
        // Mk7
        locations.add(getResourceLocation(mk7.modelMk7, GTCEu.id("block/casings/gcym/atomic_casing")));

        return locations;
    }
}
