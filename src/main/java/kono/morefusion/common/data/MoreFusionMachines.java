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
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;

import kono.morefusion.api.MoreFusionValues;
import kono.morefusion.common.machine.multiblock.electric.AdjustableFusionReactorMachine;

import static com.gregtechceu.gtceu.api.GTValues.VN;
import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeModifiers.BATCH_MODE;
import static com.gregtechceu.gtceu.common.data.GTRecipeModifiers.DEFAULT_ENVIRONMENT_REQUIREMENT;
import static com.gregtechceu.gtceu.common.data.models.GTMachineModels.createWorkableCasingMachineModel;
import static kono.morefusion.common.data.MoreFusionRegistration.REGISTRATE;

public class MoreFusionMachines {

    public static void init() {}

    public static final MultiblockMachineDefinition[] FUSION_REACTOR_MF = registerTieredMultis("fusion_reactor_mf",
            AdjustableFusionReactorMachine::new, (index, builder) -> builder
                    .rotationState(RotationState.ALL)
                    .recipeType(GTRecipeTypes.FUSION_RECIPES)
                    .recipeModifiers(DEFAULT_ENVIRONMENT_REQUIREMENT,
                            AdjustableFusionReactorMachine::recipeModifier, BATCH_MODE)
                    .tooltips(
                            Component.translatable("gtceu.machine.fusion_reactor.capacity",
                                    AdjustableFusionReactorMachine.calculateEnergyStorageFactor(index, 16)),
                            Component.translatable("gtceu.machine.fusion_reactor.overclocking"),
                            Component.translatable("gtceu.multiblock.%s_fusion_reactor.description"
                                    .formatted(
                                            VN[MoreFusionValues.getFusionTier().get(index)].toLowerCase(Locale.ROOT))))
                    .appearanceBlock(() -> AdjustableFusionReactorMachine.getCasingState(index))
                    .pattern((definition) -> {
                        var casing = blocks(AdjustableFusionReactorMachine.getCasingState(index));
                        int tier = MoreFusionValues.getFusionTier().get(index);
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
                                .where('G', blocks(AdjustableFusionReactorMachine.getGlassState(index)).or(casing))
                                .where('E', casing.or(
                                        blocks(PartAbility.INPUT_ENERGY.getBlockRange(tier, GTValues.MAX)
                                                .toArray(Block[]::new))
                                                .setMinGlobalLimited(1).setPreviewCount(16)))
                                .where('C', casing)
                                .where('K', blocks(AdjustableFusionReactorMachine.getCoilState(index)))
                                .where('O',
                                        casing.or(abilities(PartAbility.EXPORT_FLUIDS))
                                                .or(abilities(PartAbility.EXPORT_ITEMS)))
                                .where('A', air())
                                .where('I',
                                        casing.or(abilities(PartAbility.IMPORT_FLUIDS).setMinGlobalLimited(2))
                                                .or(abilities(PartAbility.IMPORT_ITEMS)))
                                .where('#', any())
                                .build();
                    })
                    .shapeInfos((controller) -> {
                        List<MultiblockShapeInfo> shapeInfos = new ArrayList<>();
                        int tier = MoreFusionValues.getFusionTier().get(index);

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
                                .where('C', AdjustableFusionReactorMachine.getCasingState(index))
                                .where('G', AdjustableFusionReactorMachine.getGlassState(index))
                                .where('K', AdjustableFusionReactorMachine.getCoilState(index))
                                .where('W', GTMachines.FLUID_EXPORT_HATCH[tier], Direction.WEST)
                                .where('E', GTMachines.FLUID_EXPORT_HATCH[tier], Direction.EAST)
                                .where('S', GTMachines.FLUID_EXPORT_HATCH[tier], Direction.SOUTH)
                                .where('N', GTMachines.ITEM_EXPORT_BUS[tier], Direction.NORTH)
                                .where('w', GTMachines.ENERGY_INPUT_HATCH[tier], Direction.WEST)
                                .where('e', GTMachines.ENERGY_INPUT_HATCH[tier], Direction.EAST)
                                .where('s', GTMachines.ENERGY_INPUT_HATCH[tier], Direction.SOUTH)
                                .where('n', GTMachines.ENERGY_INPUT_HATCH[tier], Direction.NORTH)
                                .where('U', GTMachines.FLUID_IMPORT_HATCH[tier], Direction.UP)
                                .where('D', GTMachines.ITEM_EXPORT_BUS[tier], Direction.DOWN)
                                .where('#', Blocks.AIR.defaultBlockState());

                        shapeInfos.add(baseBuilder.shallowCopy()
                                .where('G', AdjustableFusionReactorMachine.getCasingState(index))
                                .build());
                        shapeInfos.add(baseBuilder.build());
                        return shapeInfos;
                    })
                    .modelProperty(GTMachineModelProperties.RECIPE_LOGIC_STATUS, RecipeLogic.Status.IDLE)
                    .model(createWorkableCasingMachineModel(AdjustableFusionReactorMachine.getCasingModel(index),
                            GTCEu.id("block/multiblock/fusion_reactor"))
                            .andThen(b -> b.addDynamicRenderer(DynamicRenderHelper::createFusionRingRender)))
                    .hasBER(true)
                    .register(),
            0, 1, 2, 3, 4, 5, 6, 7);

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
