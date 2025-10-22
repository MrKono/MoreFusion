package kono.morefusion.common.machine.multiblock.electric;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.IEnergyContainer;
import com.gregtechceu.gtceu.api.capability.recipe.EURecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.TickableSubscription;
import com.gregtechceu.gtceu.api.machine.feature.ITieredMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableEnergyContainer;
import com.gregtechceu.gtceu.api.misc.EnergyContainerList;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.FusionReactorMachine;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.gregtechceu.gtceu.utils.GTUtil;

import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import com.lowdragmc.lowdraglib.utils.LocalizationUtils;

import static com.gregtechceu.gtceu.common.machine.multiblock.electric.FusionReactorMachine.FUSION_OC;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2IntAVLTreeMap;
import it.unimi.dsi.fastutil.longs.Long2IntSortedMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMaps;
import lombok.Getter;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CustomFusionReactorMachine extends WorkableElectricMultiblockMachine implements ITieredMachine {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            CustomFusionReactorMachine.class,
            WorkableElectricMultiblockMachine.MANAGED_FIELD_HOLDER);

    // Max EU -> Tier map, used to find minimum tier needed for X EU to start
    private static final Long2IntSortedMap FUSION_ENERGY = new Long2IntAVLTreeMap();
    // Tier -> Suffix map, i.e. LuV -> MKI
    private static final Int2ObjectMap<String> FUSION_NAMES = new Int2ObjectArrayMap<>();
    // Minimum registered fusion reactor tier
    private static int MINIMUM_TIER = GTValues.MAX;

    @Getter
    private final long baseHeat;
    @Getter
    private final int energyHatchAmount;

    @Getter
    private final int tier;
    @Nullable
    protected EnergyContainerList inputEnergyContainers;
    @Persisted
    protected long heat = 0;
    @Persisted
    protected final NotifiableEnergyContainer energyContainer;
    @Getter
    @DescSynced
    private Integer color = -1;
    @Nullable
    protected TickableSubscription preHeatSubs;

    public CustomFusionReactorMachine(IMachineBlockEntity holder, int tier, @NotNull String tierName, long baseHeat) {
        super(holder);
        this.tier = tier;
        this.energyContainer = createEnergyContainer();
        this.baseHeat = baseHeat;
        this.energyHatchAmount = 16;
        registerFusionTier(tier, tierName);
    }

    public CustomFusionReactorMachine(IMachineBlockEntity holder, int tier, @NotNull String tierName, long baseHeat,
                                      int energyHatchAmount) {
        super(holder);
        this.tier = tier;
        this.energyContainer = createEnergyContainer();
        this.baseHeat = baseHeat;
        this.energyHatchAmount = energyHatchAmount;

        registerFusionTier(tier, tierName);
    }

    // Initialization
    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    public NotifiableEnergyContainer createEnergyContainer() {
        // create an internal energy container for temp storage. its capacity is decided when the structure formed.
        // it doesn't provide any capability of all sides, but null for the goggles mod to check it storages.
        var container = new NotifiableEnergyContainer(this, 0, 0, 0, 0, 0);
        container.setCapabilityValidator(Objects::isNull);
        return container;
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (!isRemote()) {
            updatePreHeatSubscription();
        }
    }

    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        // capture all energy containers
        List<IEnergyContainer> energyContainers = new ArrayList<>();
        Long2ObjectMap<IO> ioMap = getMultiblockState().getMatchContext().getOrCreate("ioMap",
                Long2ObjectMaps::emptyMap);
        for (IMultiPart part : getParts()) {
            IO io = ioMap.getOrDefault(part.self().getPos().asLong(), IO.BOTH);
            if (io == IO.NONE || io == IO.OUT) continue;
            var handlerLists = part.getRecipeHandlers();
            for (var handlerList : handlerLists) {
                if (!handlerList.isValid(io)) continue;

                handlerList.getCapability(EURecipeCapability.CAP).stream()
                        .filter(IEnergyContainer.class::isInstance)
                        .map(IEnergyContainer.class::cast)
                        .forEach(energyContainers::add);
                traitSubscriptions.add(handlerList.subscribe(this::updatePreHeatSubscription, EURecipeCapability.CAP));
            }
        }
        this.inputEnergyContainers = new EnergyContainerList(energyContainers);
        energyContainer.resetBasicInfo(calculateEnergyStorageFactor(tier, baseHeat, energyContainers.size()), 0, 0, 0,
                0);
        updatePreHeatSubscription();
    }

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();
        this.inputEnergyContainers = null;
        heat = 0;
        energyContainer.resetBasicInfo(0, 0, 0, 0, 0);
        energyContainer.setEnergyStored(0);
        updatePreHeatSubscription();
    }

    // Recipe Logic
    protected void updatePreHeatSubscription() {
        // do preheat logic for heat cool down and charge internal energy container
        if (heat > 0 || (inputEnergyContainers != null && inputEnergyContainers.getEnergyStored() > 0 &&
                energyContainer.getEnergyStored() < energyContainer.getEnergyCapacity())) {
            preHeatSubs = subscribeServerTick(preHeatSubs, this::updateHeat);
        } else if (preHeatSubs != null) {
            preHeatSubs.unsubscribe();
            preHeatSubs = null;
        }
    }

    public static ModifierFunction recipeModifier(@NotNull MetaMachine machine, @NotNull GTRecipe recipe) {
        if (!(machine instanceof CustomFusionReactorMachine fusionReactorMachine)) {
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

    @Override
    public boolean onWorking() {
        GTRecipe recipe = recipeLogic.getLastRecipe();
        assert recipe != null;
        if (recipe.data.contains("eu_to_start")) {
            long heatDiff = recipe.data.getLong("eu_to_start") - this.heat;
            // if the remaining energy needed is more than stored, do not run
            if (heatDiff > 0) {
                recipeLogic.setWaiting(Component.translatable("gtceu.recipe_logic.insufficient_fuel"));

                // if the remaining energy needed is more than stored, do not run
                if (this.energyContainer.getEnergyStored() < heatDiff)
                    return super.onWorking();
                // remove the energy needed
                this.energyContainer.removeEnergy(heatDiff);
                // increase the stored heat
                this.heat += heatDiff;
                this.updatePreHeatSubscription();
            }
        }

        if (color == -1) {
            if (!recipe.getOutputContents(FluidRecipeCapability.CAP).isEmpty()) {
                var stack = FluidRecipeCapability.CAP
                        .of(recipe.getOutputContents(FluidRecipeCapability.CAP).get(0).getContent()).getStacks()[0];
                int newColor = 0xFF000000 | GTUtil.getFluidColor(stack);
                if (!Objects.equals(color, newColor)) {
                    color = newColor;
                }
            }
        }
        return super.onWorking();
    }

    public void updateHeat() {
        if ((getRecipeLogic().isIdle() || !isWorkingEnabled() ||
                (getRecipeLogic().isWaiting() && getRecipeLogic().getProgress() == 0)) && heat > 0) {
            heat = heat <= 10000 ? 0 : (heat - 10000);
        }
        var leftStorage = energyContainer.getEnergyCapacity() - energyContainer.getEnergyStored();
        if (inputEnergyContainers != null && leftStorage > 0) {
            energyContainer.addEnergy(inputEnergyContainers.removeEnergy(leftStorage));
        }
        updatePreHeatSubscription();
    }

    @Override
    public void onWaiting() {
        super.onWaiting();
        color = -1;
    }

    @Override
    public void afterWorking() {
        super.afterWorking();
        color = -1;
    }

    @Override
    public long getMaxVoltage() {
        return Math.min(GTValues.V[tier], super.getMaxVoltage());
    }

    // GUI
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
                        FormattingUtil.formatNumberReadable2F(euToStart, false),
                        FUSION_NAMES.get(tier))));
    }

    // MISC
    public void registerFusionTier(int tier, @NotNull String name) {
        long maxEU = calculateEnergyStorageFactor(tier, baseHeat, energyHatchAmount);
        FUSION_ENERGY.put(maxEU, tier);
        FUSION_NAMES.put(tier, name);
        MINIMUM_TIER = Math.min(tier, MINIMUM_TIER);
    }

    private static int findCeilingTier(long euToStart) {
        long key;
        var tail = FUSION_ENERGY.tailMap(euToStart);
        if (tail.isEmpty()) key = FUSION_ENERGY.lastLongKey();
        else key = tail.firstLongKey();
        return FUSION_ENERGY.get(key);
    }

    public static long calculateEnergyStorageFactor(int tier, long baseHeat, int energyInputAmount) {
        return (long) Math.min(1, tier + 10) * energyInputAmount * baseHeat;
    }

    public static Block[] rangedEnergyHatch(int start) {
        if (start < GTValues.ULV) start = GTValues.ULV;
        if (GTValues.MAX < start) start = GTValues.MAX;
        return rangedEnergyHatch(start, GTValues.MAX);
    }

    public static Block[] rangedEnergyHatch(int start, int end) {
        if (end < start) start = end;
        if (start < GTValues.ULV) start = GTValues.ULV;
        if (GTValues.MAX < start) start = GTValues.MAX;
        return PartAbility.INPUT_ENERGY.getBlockRange(start, end).toArray(Block[]::new);
    }
}
