package kono.morefusion.common.data;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;

import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;

import kono.morefusion.common.machine.multiblock.electric.AdjustableFusionReactorMachine;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.MULTIBLOCK;

public class MoreFusionRecipeTypes {

    public static void init() {}

    public static final GTRecipeType FUSION_RECIPES_MF = GTRecipeTypes.register("fusion_reactor_mf", MULTIBLOCK)
            .setMaxIOSize(4, 4, 4, 4)
            .setEUIO(IO.IN)
            .setProgressBar(GuiTextures.PROGRESS_BAR_FUSION, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.ARC)
            .setOffsetVoltageText(true)
            .setMaxTooltips(4)
            .setSmallRecipeMap(GTRecipeTypes.FUSION_RECIPES)
            .setUiBuilder(AdjustableFusionReactorMachine::addEUToStartLabel);
}
