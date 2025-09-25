package kono.morefusion.common.data;

import net.minecraft.world.item.CreativeModeTab;

import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;

import static kono.morefusion.api.MoreFusionValues.modId;
import static kono.morefusion.common.data.MoreFusionRegistration.REGISTRATE;

import com.tterrag.registrate.util.entry.RegistryEntry;

public class MoreFusionCreativeTabs {

    public static RegistryEntry<CreativeModeTab> MOREFUSION = REGISTRATE.defaultCreativeTab("item",
            builder -> builder.displayItems(new GTCreativeModeTabs.RegistrateDisplayItemsGenerator("item", REGISTRATE))
                    .icon(GTBlocks.FUSION_CASING_MK3::asStack)
                    .title(REGISTRATE.addLang("itemGroup", modId("item"), "MoreFusion"))
                    .build())
            .register();
}
