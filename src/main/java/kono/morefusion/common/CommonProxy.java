package kono.morefusion.common;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import kono.morefusion.MoreFusionConfig;
import kono.morefusion.common.data.MoreFusionRegistration;
import kono.morefusion.common.machine.multiblock.electric.ConfigurableFusionReactorMachine;
import kono.morefusion.data.MoreFusionDataGen;

public class CommonProxy {

    public CommonProxy() {
        init();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    }

    public static void init() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MoreFusionRegistration.REGISTRATE.registerRegistrate();
        MoreFusionConfig.init();
        MoreFusionDataGen.init();

        ConfigurableFusionReactorMachine.registerFusionTier(0, " (Mk ZERO)");
        ConfigurableFusionReactorMachine.registerFusionTier(1, " (Mk I)");
        ConfigurableFusionReactorMachine.registerFusionTier(2, " (Mk II)");
        ConfigurableFusionReactorMachine.registerFusionTier(3, " (Mk III)");
        ConfigurableFusionReactorMachine.registerFusionTier(4, " (Mk IV)");
        ConfigurableFusionReactorMachine.registerFusionTier(5, " (Mk V)");
        ConfigurableFusionReactorMachine.registerFusionTier(6, " (Mk VI)");
        ConfigurableFusionReactorMachine.registerFusionTier(7, " (Mk VII)");
    }
}
