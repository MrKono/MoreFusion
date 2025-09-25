package kono.morefusion.common.machine.multiblock.electric;

import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import org.jetbrains.annotations.NotNull;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableEnergyContainer;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import com.gregtechceu.gtceu.common.block.FusionCasingBlock;
import com.gregtechceu.gtceu.common.data.GCYMBlocks;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.FusionReactorMachine;
import com.gregtechceu.gtceu.utils.FormattingUtil;

import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.utils.LocalizationUtils;

import kono.morefusion.api.MoreFusionUtils;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static kono.morefusion.api.MoreFusionUtils.*;
import static kono.morefusion.api.MoreFusionUtils.tierMoreFusion;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2IntAVLTreeMap;
import it.unimi.dsi.fastutil.longs.Long2IntSortedMap;
import lombok.Getter;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ConfigurableFusionReactorMachine extends FusionReactorMachine {

    @Getter
    private final int mk;
    private final int effTier;

    @Persisted
    protected long heat = 0;
    @Persisted
    protected final NotifiableEnergyContainer energyContainer;

    // Max EU -> Tier map, used to find minimum tier needed for X EU to start
    private static final Long2IntSortedMap FUSION_ENERGY = new Long2IntAVLTreeMap();
    // Tier -> Suffix map, i.e. LuV -> MKI
    private static final Int2ObjectMap<String> FUSION_NAMES = new Int2ObjectArrayMap<>(4);
    // Minimum registered fusion reactor tier
    private static int MINIMUM_TIER = MAX;

    public ConfigurableFusionReactorMachine(IMachineBlockEntity holder, int idTier) {
        super(holder, Math.max(0, idTier - IV));
        this.mk = Math.max(0, idTier - GTValues.IV);
        this.effTier = getFusionTier(mk);
        this.energyContainer = createEnergyContainer();
    }

    public static int getFusionTier(int mk) {
        return tierMoreFusion(cfgMk(mk).tier(), mk + IV);
    }

    @Override
    public int getTier() {
        return effTier;
    }

    @Override
    public long getMaxVoltage() {
        return Math.min(GTValues.V[effTier], super.getMaxVoltage());
    }

    public static ModifierFunction recipeModifier(@NotNull MetaMachine machine, @NotNull GTRecipe recipe) {
        if (!(machine instanceof ConfigurableFusionReactorMachine fusionReactorMachine)) {
            return RecipeModifier.nullWrongType(FusionReactorMachine.class, machine);
        }
        if (RecipeHelper.getRecipeEUtTier(recipe) > fusionReactorMachine.getTier() ||
                !recipe.data.contains("eu_to_start") ||
                recipe.data.getLong("eu_to_start") > fusionReactorMachine.energyContainer.getEnergyCapacity()) {
            return ModifierFunction.NULL;
        }

        long heatDiff = recipe.data.getLong("eu_to_start") - fusionReactorMachine.heat;

        // if the stored heat is >= required energy, recipe is okay to run
        if (heatDiff <= 0) {
            return FUSION_OC.getModifier(machine, recipe, fusionReactorMachine.getMaxVoltage(), false);
        }
        // if the remaining energy needed is more than stored, do not run
        if (fusionReactorMachine.energyContainer.getEnergyStored() < heatDiff) return ModifierFunction.NULL;

        // remove the energy needed
        fusionReactorMachine.energyContainer.removeEnergy(heatDiff);
        // increase the stored heat
        fusionReactorMachine.heat += heatDiff;
        fusionReactorMachine.updatePreHeatSubscription();
        return FUSION_OC.getModifier(machine, recipe, fusionReactorMachine.getMaxVoltage(), false);
    }

    //////////////////////////////////////
    // ******** GUI *********//
    //////////////////////////////////////
    @Override
    public void addDisplayText(List<Component> textList) {
        super.addDisplayText(textList);
        if (isFormed()) {
            textList.add(Component.translatable("gtceu.multiblock.fusion_reactor.energy",
                    this.energyContainer.getEnergyStored(), this.energyContainer.getEnergyCapacity()));
            textList.add(Component.translatable("gtceu.multiblock.fusion_reactor.heat", heat));
        }
    }

    public static void addEUToStartLabel(GTRecipe recipe, WidgetGroup group) {
        long euToStart = recipe.data.getLong("eu_to_start");
        if (euToStart <= 0) return;
        int recipeTier = RecipeHelper.getPreOCRecipeEuTier(recipe);
        int fusionTier = findCeilingTier(euToStart);
        int tier = Math.max(MINIMUM_TIER, Math.max(recipeTier, fusionTier));
        group.addWidget(new LabelWidget(-2, group.getSizeHeight() - 10,
                LocalizationUtils.format("gtceu.recipe.eu_to_start",
                        formatWithSIPrefix(euToStart),
                        FUSION_NAMES.get(tier))));
    }

    //////////////////////////////////////
    // ******** MISC *********//
    //////////////////////////////////////

    public static long calculateEnergyStorageFactor(int mk, int energyInputAmount) {
        return (long) energyInputAmount *
                castHeat(MoreFusionUtils.cfgMk(mk).heat(), (long) Math.pow(2, (mk - 1)) * 1000000);
    }

    private static int findCeilingTier(long euToStart) {
        long key;
        // tail = submap where all keys are >= EU to start
        // if tail is empty, then EU is greater than all the EU values, so we choose the last key
        // otherwise we want the first key in the tail map
        var tail = FUSION_ENERGY.tailMap(euToStart);
        if (tail.isEmpty()) key = FUSION_ENERGY.lastLongKey();
        else key = tail.firstLongKey();
        return FUSION_ENERGY.get(key);
    }

    public static void registerFusionTier(int mk, @NotNull String name) {
        int effTier = getFusionTier(mk);
        long maxEU = calculateEnergyStorageFactor(mk, 16);
        FUSION_ENERGY.put(maxEU, effTier);
        FUSION_NAMES.put(effTier, name);
        MINIMUM_TIER = Math.min(effTier, MINIMUM_TIER);
    }

    public static Block getCasingState(int mk) {
        Block defaultCasing = switch (mk) {
            case 0 -> GCYMBlocks.CASING_HIGH_TEMPERATURE_SMELTING.get();
            case 1 -> GTBlocks.FUSION_CASING.get();
            case 2 -> GTBlocks.FUSION_CASING_MK2.get();
            case 3 -> GTBlocks.FUSION_CASING_MK3.get();
            default -> GCYMBlocks.CASING_ATOMIC.get();
        };
        return getBlockFromString(MoreFusionUtils.cfgMk(mk).casing(), defaultCasing);
    }

    public static Block getCoilState(int mk) {
        Block defaultCoil = switch (mk) {
            case 0 -> GTBlocks.COIL_NAQUADAH.get();
            case 1 -> GTBlocks.SUPERCONDUCTING_COIL.get();
            default -> GTBlocks.FUSION_COIL.get();
        };
        return getBlockFromString(MoreFusionUtils.cfgMk(mk).coil(), defaultCoil);
    }

    public static Block getGlassState(int mk) {
        Block defaultGlass = mk == 0 ? GTBlocks.CASING_LAMINATED_GLASS.get() : GTBlocks.FUSION_GLASS.get();
        return getBlockFromString(MoreFusionUtils.cfgMk(mk).glass(), defaultGlass);
    }

    public static ResourceLocation getCasingModel(int mk) {
        ResourceLocation defaultRL = switch (mk) {
            case 0 -> GTCEu.id("block/casings/gcym/high_temperature_smelting_casing");
            case 1 -> FusionCasingBlock.CasingType.FUSION_CASING.getTexture();
            case 2 -> FusionCasingBlock.CasingType.FUSION_CASING_MK2.getTexture();
            case 3 -> FusionCasingBlock.CasingType.FUSION_CASING_MK3.getTexture();
            default -> GTCEu.id("block/casings/gcym/atomic_casing");
        };
        return getResourceLocationFromString(MoreFusionUtils.cfgMk(mk).model(), defaultRL);
    }
}
