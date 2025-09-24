package kono.morefusion.common;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import kono.morefusion.MoreFusionConfig;
import kono.morefusion.common.data.MoreFusionRegistration;
import kono.morefusion.common.machine.multiblock.electric.ConfigurableFusionReactorMachine;

public class CommonProxy {

    public CommonProxy() {
        init();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    }

    public static void init() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MoreFusionRegistration.REGISTRATE.registerRegistrate();
        MoreFusionConfig.init();

        ConfigurableFusionReactorMachine.registerFusionTier(0, "(Mk0)");
        ConfigurableFusionReactorMachine.registerFusionTier(1, "(MkI)");
        ConfigurableFusionReactorMachine.registerFusionTier(2, "(MkII)");
        ConfigurableFusionReactorMachine.registerFusionTier(3, "(MkIII)");
        ConfigurableFusionReactorMachine.registerFusionTier(4, "(MkIV)");
        ConfigurableFusionReactorMachine.registerFusionTier(5, "(MkV)");
        ConfigurableFusionReactorMachine.registerFusionTier(6, "(MkVI)");
        ConfigurableFusionReactorMachine.registerFusionTier(7, "(MkVII)");
    }
}
