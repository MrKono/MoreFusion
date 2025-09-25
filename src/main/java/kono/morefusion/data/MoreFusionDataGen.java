package kono.morefusion.data;

import kono.morefusion.common.data.MoreFusionRegistration;
import kono.morefusion.data.lang.EnglishHandler;

import com.tterrag.registrate.providers.ProviderType;

public class MoreFusionDataGen {

    public static void init() {
        MoreFusionRegistration.REGISTRATE.addDataGenerator(ProviderType.LANG, EnglishHandler::init);
    }
}
