package kono.morefusion;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;

import kono.morefusion.api.MoreFusionValues;
import kono.morefusion.common.data.MoreFusionMachines;
import kono.morefusion.common.data.MoreFusionRecipeTypes;

@Mod.EventBusSubscriber(modid = MoreFusionValues.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MoreFusionEventHandler {

    public static void registerMachines(GTCEuAPI.RegisterEvent<ResourceLocation, MachineDefinition> event) {
        MoreFusionMachines.init();
    }

    public static void registerRecipeTypes(GTCEuAPI.RegisterEvent<ResourceLocation, GTRecipeType> event) {
        MoreFusionRecipeTypes.init();
        GTRecipeTypes.FUSION_RECIPES.onRecipeBuild(
                (builder, provider) -> MoreFusionRecipeTypes.FUSION_RECIPES_MF.copyFrom(builder).save(provider));
    }
}
