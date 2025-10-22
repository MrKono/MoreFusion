package kono.morefusion.data.lang;

import com.gregtechceu.gtceu.data.lang.LangHandler;

import com.tterrag.registrate.providers.RegistrateLangProvider;

public class EnglishHandler extends LangHandler {

    public static void init(RegistrateLangProvider provider) {
        // Recipe Name
        replace(provider, "gtceu.fusion_reactor_mf", "[MF] Fusion Reactor");
        // Tooltip
        provider.add("morefusion.machine.fusion_reactor.capacity", "§7Maximum Energy Storage: §e%s EU");
        provider.add("morefusion.machine.fusion_reactor_mf.hold_ctrl",
                "Hold CTRL to display the value without a prefix.");
    }
}
