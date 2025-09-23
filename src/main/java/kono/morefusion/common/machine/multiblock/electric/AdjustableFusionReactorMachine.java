package kono.morefusion.common.machine.multiblock.electric;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import org.jetbrains.annotations.NotNull;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.FusionReactorMachine;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static kono.morefusion.api.MoreFusionValues.*;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2IntAVLTreeMap;
import it.unimi.dsi.fastutil.longs.Long2IntSortedMap;

public class AdjustableFusionReactorMachine extends FusionReactorMachine {

    // Max EU -> Tier map, used to find minimum tier needed for X EU to start
    private static final Long2IntSortedMap FUSION_ENERGY = new Long2IntAVLTreeMap();
    // Tier -> Suffix map, i.e. LuV -> MKI
    private static final Int2ObjectMap<String> FUSION_NAMES = new Int2ObjectArrayMap<>(4);
    // Minimum registered fusion reactor tier
    private static int MINIMUM_TIER = MAX;

    public AdjustableFusionReactorMachine(IMachineBlockEntity holder, int index) {
        super(holder, index);
    }

    public static long calculateEnergyStorageFactor(int index, int energyInputAmount) {
        return energyInputAmount * getBaseHeat().get(index);
    }

    public static void registerFusionTier(int index, @NotNull String name) {
        long maxEU = calculateEnergyStorageFactor(index, 16);
        FUSION_ENERGY.put(maxEU, index);
        FUSION_NAMES.put(index, name);
        MINIMUM_TIER = Math.min(index, MINIMUM_TIER);
    }

    public static @NotNull Block getCasingState(int index) {
        return getCasing().get(index);
    }

    public static @NotNull Block getCoilState(int index) {
        return getCoil().get(index);
    }

    public static @NotNull Block getGlassState(int index) {
        return getGlass().get(index);
    }

    public static ResourceLocation getCasingModel(int index) {
        return getLocation().get(index);
    }
}
