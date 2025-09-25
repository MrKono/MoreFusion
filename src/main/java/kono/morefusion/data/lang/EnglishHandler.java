package kono.morefusion.data.lang;

import java.util.Map;
import java.util.Set;

import com.gregtechceu.gtceu.data.lang.LangHandler;

import dev.toma.configuration.config.value.ConfigValue;
import dev.toma.configuration.config.value.ObjectValue;

import kono.morefusion.api.MoreFusionValues;

import com.tterrag.registrate.providers.RegistrateLangProvider;

public class EnglishHandler extends LangHandler {

    public static void init(RegistrateLangProvider provider) {
        // Recipe Name
        replace(provider, "gtceu.fusion_reactor_mf", "Fusion Reactor [MF]");
        // Tooltip
        provider.add("morefusion.machine.fusion_reactor.capacity", "§7Maximum Energy Storage: §e%s EU");
        provider.add("morefusion.multiblock.fusion_reactor_mf_description",
                "The [MF] Fusion Reactor MK %s is a large multiblock structure used for fusing elements into heavier ones. It can only use higher or equal %s Energy Hatch(es). For every Hatch it has, its buffer increases by %s EU, and has a maximum of %s.");
        provider.add("morefusion.machine.fusion_reactor_mf.hold_ctrl",
                "Hold CTRL to display the value without a prefix.");
    }

    private static void dfs(RegistrateLangProvider provider, Set<String> added, Map<String, ConfigValue<?>> map) {
        for (var entry : map.entrySet()) {
            var id = entry.getValue().getId();
            if (added.add(id)) {
                provider.add(String.format("config.%s.option.%s", MoreFusionValues.MODID, id), id);
            }
            if (entry.getValue() instanceof ObjectValue objectValue) {
                dfs(provider, added, objectValue.get());
            }
        }
    }
}
