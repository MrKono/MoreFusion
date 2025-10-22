const MoreFusionUtil = Java.loadClass("kono.morefusion.api.MoreFusionUtils")
const CustomFusionReactorMachine = Java.loadClass("kono.morefusion.common.machine.multiblock.electric.CustomFusionReactorMachine")

ItemEvents.tooltip(e => {
    e.addAdvanced(`gtceu:custom_fusion_reactor_primitive`, (item, add, text) => {
        text.add(1, Text.translatable('gtceu.machine.fusion_reactor.overclocking'))
        text.add(2, Text.translatable('morefusion.machine.fusion_reactor.capacity', 
                MoreFusionUtil.formatWithSIPrefix(CustomFusionReactorMachine.calculateEnergyStorageFactor(GTValues.EV, 281250, 16))))
    })
    e.addAdvanced(`gtceu:custom_fusion_reactor_mk0`, (item, add, text) => {
        text.add(1, Text.translatable('gtceu.machine.fusion_reactor.overclocking'))
        text.add(2, Text.translatable('morefusion.machine.fusion_reactor.capacity', 
                MoreFusionUtil.formatWithSIPrefix(CustomFusionReactorMachine.calculateEnergyStorageFactor(GTValues.IV, 5000000, 16))))
    })
    e.addAdvanced(`gtceu:custom_fusion_reactor_mk1`, (item, add, text) => {
        text.add(1, Text.translatable('gtceu.machine.fusion_reactor.overclocking'))
        text.add(2, Text.translatable('morefusion.machine.fusion_reactor.capacity', 
                MoreFusionUtil.formatWithSIPrefix(CustomFusionReactorMachine.calculateEnergyStorageFactor(GTValues.LuV, 10000000, 16))))
    })
    e.addAdvanced(`gtceu:custom_fusion_reactor_mk2`, (item, add, text) => {
        text.add(1, Text.translatable('gtceu.machine.fusion_reactor.overclocking'))
        text.add(2, Text.translatable('morefusion.machine.fusion_reactor.capacity', 
                MoreFusionUtil.formatWithSIPrefix(CustomFusionReactorMachine.calculateEnergyStorageFactor(GTValues.ZPM, 20000000, 16))))
    })
    e.addAdvanced(`gtceu:custom_fusion_reactor_mk3`, (item, add, text) => {
        text.add(1, Text.translatable('gtceu.machine.fusion_reactor.overclocking'))
        text.add(2, Text.translatable('morefusion.machine.fusion_reactor.capacity', 
                MoreFusionUtil.formatWithSIPrefix(CustomFusionReactorMachine.calculateEnergyStorageFactor(GTValues.ZPM, 40000000, 16))))
    })
})