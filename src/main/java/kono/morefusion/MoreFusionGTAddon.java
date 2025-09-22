package kono.morefusion;

import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import kono.morefusion.api.MoreFusionValues;
import kono.morefusion.common.data.MoreFusionRegistration;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

public class MoreFusionGTAddon  implements IGTAddon {

    @Override
    public GTRegistrate getRegistrate() {
        return MoreFusionRegistration.REGISTRATE;
    }

    @Override
    public void initializeAddon() {}

    @Override
    public String addonModId() {
        return MoreFusionValues.MODID;
    }

    @Override
    public void registerTagPrefixes() {}

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
    }

    @Override
    public void removeRecipes(Consumer<ResourceLocation> consumer) {
    }
}