package kono.morefusion;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;

import kono.morefusion.api.MoreFusionValues;
import kono.morefusion.client.ClientProxy;
import kono.morefusion.common.CommonProxy;

@Mod(MoreFusionValues.MODID)
public class MoreFusion {

    public MoreFusion() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register Machine
        modEventBus.addGenericListener(MachineDefinition.class, MoreFusionEventHandler::registerMachines);
        // Register RecipeType
        modEventBus.addGenericListener(GTRecipeType.class, MoreFusionEventHandler::registerRecipeTypes);

        DistExecutor.unsafeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    }
}
