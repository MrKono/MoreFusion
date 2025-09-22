package kono.morefusion;

import kono.morefusion.api.MoreFusionValues;
import kono.morefusion.client.ClientProxy;
import kono.morefusion.common.CommonProxy;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MoreFusionValues.MODID)
public class MoreFusion {

    public MoreFusion() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register Machine
        //modEventBus.addGenericListener(MachineDefinition.class, EventHandler::registerMachines);
        // Register RecipeType
        //modEventBus.addGenericListener(GTRecipeType.class, EventHandler::registerRecipeTypes);
        DistExecutor.unsafeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    }
}
