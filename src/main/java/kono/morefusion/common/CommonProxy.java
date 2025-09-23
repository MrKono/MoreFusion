package kono.morefusion.common;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import kono.morefusion.MoreFusionConfig;
import kono.morefusion.common.data.MoreFusionRegistration;
import kono.morefusion.common.machine.multiblock.electric.AdjustableFusionReactorMachine;

public class CommonProxy {

    public CommonProxy() {
        init();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    }

    public static void init() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MoreFusionRegistration.REGISTRATE.registerRegistrate();
        MoreFusionConfig.init();

        AdjustableFusionReactorMachine.registerFusionTier(0, "(Mk0)");
        AdjustableFusionReactorMachine.registerFusionTier(1, "(MkI)");
        AdjustableFusionReactorMachine.registerFusionTier(2, "(MkII)");
        AdjustableFusionReactorMachine.registerFusionTier(3, "(MkIII)");
        AdjustableFusionReactorMachine.registerFusionTier(4, "(MkIV)");
        AdjustableFusionReactorMachine.registerFusionTier(5, "(MkV)");
        AdjustableFusionReactorMachine.registerFusionTier(6, "(MkVI)");
        AdjustableFusionReactorMachine.registerFusionTier(7, "(MkVII)");
    }
}
