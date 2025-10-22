package kono.morefusion.common;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import kono.morefusion.MoreFusionConfig;
import kono.morefusion.common.data.MoreFusionRegistration;
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
    }
}
