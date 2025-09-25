package kono.morefusion.common.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.BiFunction;

import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.property.GTMachineModelProperties;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.MultiblockShapeInfo;
import com.gregtechceu.gtceu.api.registry.registrate.MultiblockMachineBuilder;
import com.gregtechceu.gtceu.client.renderer.machine.DynamicRenderHelper;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.utils.GTUtil;

import kono.morefusion.common.machine.multiblock.electric.ConfigurableFusionReactorMachine;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeModifiers.BATCH_MODE;
import static com.gregtechceu.gtceu.common.data.GTRecipeModifiers.DEFAULT_ENVIRONMENT_REQUIREMENT;
import static com.gregtechceu.gtceu.common.data.models.GTMachineModels.createWorkableCasingMachineModel;
import static com.gregtechceu.gtceu.utils.FormattingUtil.toRomanNumeral;
import static kono.morefusion.api.MoreFusionUtils.formatWithSIPrefix;
import static kono.morefusion.common.data.MoreFusionCreativeTabs.MOREFUSION;
import static kono.morefusion.common.data.MoreFusionRegistration.REGISTRATE;

public class MoreFusionMachines {

    static {
        REGISTRATE.creativeModeTab(() -> MOREFUSION);
    }

    public static void init() {}

    public static final MultiblockMachineDefinition[] FUSION_REACTOR_NF = registerTieredMultis("fusion_reactor_mf",
            ConfigurableFusionReactorMachine::new, (idTier, builder) -> builder
                    .rotationState(RotationState.ALL)
                    .langValue("[MF] Fusion Reactor Computer MK %s"
                            .formatted(idTier == GTValues.IV ? "ZERO" : toRomanNumeral(idTier - GTValues.IV)))
                    .recipeType(MoreFusionRecipeTypes.FUSION_RECIPES_MF)
                    .recipeModifiers(DEFAULT_ENVIRONMENT_REQUIREMENT,
                            ConfigurableFusionReactorMachine::recipeModifier, BATCH_MODE)
                    .tooltipBuilder((stack, tooltip) -> {
                        if (GTUtil.isCtrlDown()) {
                            tooltip.add(Component.translatable("morefusion.machine.fusion_reactor.capacity",
                                    ConfigurableFusionReactorMachine.calculateEnergyStorageFactor(
                                            Math.max(0, idTier - GTValues.IV), 16)));
                            tooltip.add(Component.translatable("gtceu.machine.fusion_reactor.overclocking"));
                            tooltip.add(Component.translatable("morefusion.multiblock.fusion_reactor_mf_description",
                                    idTier - IV,
                                    VN[ConfigurableFusionReactorMachine.getFusionTier(idTier - IV)],
                                    ConfigurableFusionReactorMachine
                                            .calculateEnergyStorageFactor(Math.max(0, idTier - GTValues.IV), 1),
                                    ConfigurableFusionReactorMachine
                                            .calculateEnergyStorageFactor(Math.max(0, idTier - GTValues.IV), 16)));
                        } else {
                            tooltip.add(Component.translatable("morefusion.machine.fusion_reactor.capacity",
                                    formatWithSIPrefix(ConfigurableFusionReactorMachine.calculateEnergyStorageFactor(
                                            Math.max(0, idTier - GTValues.IV), 16))));
                            tooltip.add(Component.translatable("gtceu.machine.fusion_reactor.overclocking"));
                            tooltip.add(Component.translatable("morefusion.multiblock.fusion_reactor_mf_description",
                                    idTier - IV,
                                    VN[ConfigurableFusionReactorMachine.getFusionTier(idTier - IV)],
                                    formatWithSIPrefix(ConfigurableFusionReactorMachine
                                            .calculateEnergyStorageFactor(Math.max(0, idTier - GTValues.IV), 1)),
                                    formatWithSIPrefix(ConfigurableFusionReactorMachine
                                            .calculateEnergyStorageFactor(Math.max(0, idTier - GTValues.IV), 16))));
                            tooltip.add(Component.translatable("morefusion.machine.fusion_reactor_mf.hold_ctrl"));
                        }
                    })
                    .appearanceBlock(
                            () -> ConfigurableFusionReactorMachine.getCasingState(Math.max(0, idTier - GTValues.IV)))
                    .pattern((definition) -> {
                        final int mk = Math.max(0, idTier - GTValues.IV);
                        final int effTier = ConfigurableFusionReactorMachine.getFusionTier(mk);

                        var casing = blocks(ConfigurableFusionReactorMachine.getCasingState(mk));
                        var glass = blocks(ConfigurableFusionReactorMachine.getGlassState(mk));
                        var coil = blocks(ConfigurableFusionReactorMachine.getCoilState(mk));

                        return FactoryBlockPattern.start()
                                .aisle("###############", "######OGO######", "###############")
                                .aisle("######ICI######", "####GGAAAGG####", "######ICI######")
                                .aisle("####CC###CC####", "###EAAOGOAAE###", "####CC###CC####")
                                .aisle("###C#######C###", "##EKEG###GEKE##", "###C#######C###")
                                .aisle("##C#########C##", "#GAE#######EAG#", "##C#########C##")
                                .aisle("##C#########C##", "#GAG#######GAG#", "##C#########C##")
                                .aisle("#I###########I#", "OAO#########OAO", "#I###########I#")
                                .aisle("#C###########C#", "GAG#########GAG", "#C###########C#")
                                .aisle("#I###########I#", "OAO#########OAO", "#I###########I#")
                                .aisle("##C#########C##", "#GAG#######GAG#", "##C#########C##")
                                .aisle("##C#########C##", "#GAE#######EAG#", "##C#########C##")
                                .aisle("###C#######C###", "##EKEG###GEKE##", "###C#######C###")
                                .aisle("####CC###CC####", "###EAAOGOAAE###", "####CC###CC####")
                                .aisle("######ICI######", "####GGAAAGG####", "######ICI######")
                                .aisle("###############", "######OSO######", "###############")
                                .where('S', controller(blocks(definition.get())))
                                .where('G', glass.or(casing))
                                .where('E', casing.or(
                                        blocks(PartAbility.INPUT_ENERGY.getBlockRange(effTier, MAX)
                                                .toArray(Block[]::new))
                                                .setMinGlobalLimited(1).setPreviewCount(16)))
                                .where('C', casing)
                                .where('K', coil)
                                .where('O', casing.or(abilities(PartAbility.EXPORT_FLUIDS, PartAbility.EXPORT_ITEMS)))
                                .where('A', air())
                                .where('I',
                                        casing.or(abilities(PartAbility.IMPORT_FLUIDS).setMinGlobalLimited(2))
                                                .or(abilities(PartAbility.IMPORT_ITEMS)))
                                .where('#', any())
                                .build();
                    })
                    .shapeInfos((controller) -> {
                        List<MultiblockShapeInfo> shapeInfos = new ArrayList<>();
                        final int mk = Math.max(0, idTier - GTValues.IV);
                        final int effTier = ConfigurableFusionReactorMachine.getFusionTier(mk);

                        var casing = ConfigurableFusionReactorMachine.getCasingState(mk);
                        var glass = ConfigurableFusionReactorMachine.getGlassState(mk);
                        var coil = ConfigurableFusionReactorMachine.getCoilState(mk);

                        MultiblockShapeInfo.ShapeInfoBuilder baseBuilder = MultiblockShapeInfo.builder()
                                .aisle("###############", "######NMN######", "###############")
                                .aisle("######DCD######", "####GG###GG####", "######UCU######")
                                .aisle("####CC###CC####", "###w##SGS##e###", "####CC###CC####")
                                .aisle("###C#######C###", "##nKsG###GsKn##", "###C#######C###")
                                .aisle("##C#########C##", "#G#e#######w#G#", "##C#########C##")
                                .aisle("##C#########C##", "#G#G#######G#G#", "##C#########C##")
                                .aisle("#D###########D#", "W#E#########W#E", "#U###########U#")
                                .aisle("#C###########C#", "G#G#########G#G", "#C###########C#")
                                .aisle("#D###########D#", "W#E#########W#E", "#U###########U#")
                                .aisle("##C#########C##", "#G#G#######G#G#", "##C#########C##")
                                .aisle("##C#########C##", "#G#e#######w#G#", "##C#########C##")
                                .aisle("###C#######C###", "##sKnG###GnKs##", "###C#######C###")
                                .aisle("####CC###CC####", "###w##NGN##e###", "####CC###CC####")
                                .aisle("######DCD######", "####GG###GG####", "######UCU######")
                                .aisle("###############", "######SGS######", "###############")
                                .where('M', controller, Direction.NORTH)
                                .where('C', casing)
                                .where('G', glass)
                                .where('K', coil)
                                .where('W', GTMachines.FLUID_EXPORT_HATCH[effTier], Direction.WEST)
                                .where('E', GTMachines.FLUID_EXPORT_HATCH[effTier], Direction.EAST)
                                .where('S', GTMachines.ITEM_EXPORT_BUS[effTier], Direction.SOUTH)
                                .where('N', GTMachines.ITEM_EXPORT_BUS[effTier], Direction.NORTH)
                                .where('w', GTMachines.ENERGY_INPUT_HATCH[effTier], Direction.WEST)
                                .where('e', GTMachines.ENERGY_INPUT_HATCH[effTier], Direction.EAST)
                                .where('s', GTMachines.ENERGY_INPUT_HATCH[effTier], Direction.SOUTH)
                                .where('n', GTMachines.ENERGY_INPUT_HATCH[effTier], Direction.NORTH)
                                .where('U', GTMachines.FLUID_IMPORT_HATCH[effTier], Direction.UP)
                                .where('D', GTMachines.ITEM_IMPORT_BUS[effTier], Direction.DOWN)
                                .where('#', Blocks.AIR.defaultBlockState());

                        shapeInfos.add(baseBuilder.shallowCopy()
                                .where('G', casing)
                                .build());
                        shapeInfos.add(baseBuilder.build());
                        return shapeInfos;
                    })
                    .modelProperty(GTMachineModelProperties.RECIPE_LOGIC_STATUS, RecipeLogic.Status.IDLE)
                    .model(createWorkableCasingMachineModel(
                            ConfigurableFusionReactorMachine.getCasingModel(Math.max(0, idTier - GTValues.IV)),
                            GTCEu.id("block/multiblock/fusion_reactor"))
                            .andThen(b -> b.addDynamicRenderer(DynamicRenderHelper::createFusionRingRender)))
                    .hasBER(true)
                    .register(),
            IV, LuV, ZPM, UV, UHV, UEV, UIV, UXV);

    public static MultiblockMachineDefinition[] registerTieredMultis(String name,
                                                                     BiFunction<IMachineBlockEntity, Integer, MultiblockControllerMachine> factory,
                                                                     BiFunction<Integer, MultiblockMachineBuilder, MultiblockMachineDefinition> builder,
                                                                     int... tiers) {
        MultiblockMachineDefinition[] definitions = new MultiblockMachineDefinition[GTValues.TIER_COUNT];
        for (int tier : tiers) {
            MultiblockMachineBuilder register = REGISTRATE
                    .multiblock(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_" + name,
                            holder -> factory.apply(holder, tier))
                    .tier(tier);
            definitions[tier] = builder.apply(tier, register);
        }
        return definitions;
    }
}
