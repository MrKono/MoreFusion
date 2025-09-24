package kono.morefusion;

import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.Config;
import dev.toma.configuration.config.Configurable;
import dev.toma.configuration.config.format.ConfigFormats;

import kono.morefusion.api.MoreFusionValues;

@Config(id = MoreFusionValues.MODID)
public class MoreFusionConfig {

    public static MoreFusionConfig INSTANCE;

    public static void init() {
        if (INSTANCE == null) {
            INSTANCE = Configuration.registerConfig(MoreFusionConfig.class, ConfigFormats.yaml()).getConfigInstance();
        }
    }

    @Configurable
    public cfgMk0 mk0 = new cfgMk0();
    @Configurable
    public cfgMk1 mk1 = new cfgMk1();
    @Configurable
    public cfgMk2 mk2 = new cfgMk2();
    @Configurable
    public cfgMk3 mk3 = new cfgMk3();
    @Configurable
    public cfgMk4 mk4 = new cfgMk4();
    @Configurable
    public cfgMk5 mk5 = new cfgMk5();
    @Configurable
    public cfgMk6 mk6 = new cfgMk6();
    @Configurable
    public cfgMk7 mk7 = new cfgMk7();

    public static class cfgMk0 {

        @Configurable
        @Configurable.Comment({ "Configure the minimum tier of the Energy Hatch used in Fusion Reactor Mk0.",
                "Tiers: 0 = ULV, 1 = LV, 2 = MV ... 12 = UXV, 13 = OpV, 14 = MAX", "Default: 5 (IV)" })
        @Configurable.Range(min = 0, max = 14)
        public int tierMk0 = 5;

        @Configurable
        @Configurable.Comment({ "Configure the \"Base Heat value\" of Fusion Reactor Mk0.",
                "Calculation formula: Heat = baseHeatMk0 * energyHatchAmount", "Default: 5000000" })
        public String heatMk0 = "5000000";

        @Configurable
        @Configurable.Comment({ "Configure the \"Casing\" used for building Fusion Reactor Mk0.", "Format: modid:name",
                "Default: gtceu:high_temperature_smelting_casing" })
        public String casingMk0 = "gtceu:high_temperature_smelting_casing";

        @Configurable
        @Configurable.Comment({ "Configure the \"Glass\" used for building Fusion Reactor Mk0.", "Format: modid:name",
                "Default: gtceu:laminated_glass" })
        public String glassMk0 = "gtceu:laminated_glass";

        @Configurable
        @Configurable.Comment({ "Configure the \"Coil\" used for building Fusion Reactor Mk0.", "Format: modid:name",
                "Default: gtceu:naquadah_coil_block" })
        public String coilMk0 = "gtceu:naquadah_coil_block";

        @Configurable
        @Configurable.Comment({ "Configure the \"Casing Model\" used dor Fusion Reactor Mk0", "Format: modid:path",
                "If empty, model is applied to default casing's texture.",
                "Default: gtceu:block/casings/gcym/high_temperature_smelting_casing" })
        public String modelMk0 = "gtceu:block/casings/gcym/high_temperature_smelting_casing";
    }

    public static class cfgMk1 {

        @Configurable
        @Configurable.Comment({ "Configure the minimum tier of the Energy Hatch used in Fusion Reactor Mk1.",
                "Tiers: 0 = ULV, 1 = LV, 2 = MV ... 12 = UXV, 13 = OpV, 14 = MAX", "Default: 6 (LuV)" })
        @Configurable.Range(min = 0, max = 14)
        public int tierMk1 = 6;

        @Configurable
        @Configurable.Comment({ "Configure the \"Base Heat value\" of Fusion Reactor Mk1.",
                "Calculation formula: Heat = baseHeatMk1 * energyHatchAmount", "Default: 10000000" })
        public String heatMk1 = "10000000";

        @Configurable
        @Configurable.Comment({ "Configure the \"Casing\" used for building Fusion Reactor Mk1.", "Format: modid:name",
                "Default: gtceu:fusion_casing" })
        public String casingMk1 = "gtceu:fusion_casing";

        @Configurable
        @Configurable.Comment({ "Configure the \"Glass\" used for building Fusion Reactor Mk1.", "Format: modid:name",
                "Default: gtceu:fusion_glass" })
        public String glassMk1 = "gtceu:fusion_glass";

        @Configurable
        @Configurable.Comment({ "Configure the \"Coil\" used for building Fusion Reactor Mk1.", "Format: modid:name",
                "Default: gtceu:superconducting_coil" })
        public String coilMk1 = "gtceu:superconducting_coil";

        @Configurable
        @Configurable.Comment({ "Configure the \"Casing Model\" used dor Fusion Reactor Mk1", "Format: modid:path",
                "If empty, model is applied to default casing's texture.",
                "Default: gtceu:block/casings/fusion/fusion_casing" })
        public String modelMk1 = "gtceu:block/casings/fusion/fusion_casing";
    }

    public static class cfgMk2 {

        @Configurable
        @Configurable.Comment({ "Configure the minimum tier of the Energy Hatch used in Fusion Reactor Mk2.",
                "Tiers: 0 = ULV, 1 = LV, 2 = MV ... 12 = UXV, 13 = OpV, 14 = MAX", "Default: 7 (ZPM)" })
        @Configurable.Range(min = 0, max = 14)
        public int tierMk2 = 7;

        @Configurable
        @Configurable.Comment({ "Configure the \"Base Heat value\" of Fusion Reactor Mk2.",
                "Calculation formula: Heat = baseHeatMk2 * energyHatchAmount", "Default: 20000000" })
        public String heatMk2 = "20000000";

        @Configurable
        @Configurable.Comment({ "Configure the \"Casing\" used for building Fusion Reactor Mk2.", "Format: modid:name",
                "Default: gtceu:fusion_casing_mk2" })
        public String casingMk2 = "gtceu:fusion_casing_mk2";

        @Configurable
        @Configurable.Comment({ "Configure the \"Glass\" used for building Fusion Reactor Mk2.", "Format: modid:name",
                "Default: gtceu:fusion_glass" })
        public String glassMk2 = "gtceu:fusion_glass";

        @Configurable
        @Configurable.Comment({ "Configure the \"Coil\" used for building Fusion Reactor Mk2.", "Format: modid:name",
                "Default: gtceu:fusion_coil" })
        public String coilMk2 = "gtceu:fusion_coil";

        @Configurable
        @Configurable.Comment({ "Configure the \"Casing Model\" used dor Fusion Reactor Mk2", "Format: modid:path",
                "If empty, model is applied to default casing's texture.",
                "Default: gtceu:block/casings/fusion/fusion_casing_mk2" })
        public String modelMk2 = "gtceu:block/casings/fusion/fusion_casing_mk2";
    }

    public static class cfgMk3 {

        @Configurable
        @Configurable.Comment({ "Configure the minimum tier of the Energy Hatch used in Fusion Reactor Mk3.",
                "Tiers: 0 = ULV, 1 = LV, 2 = MV ... 12 = UXV, 13 = OpV, 14 = MAX", "Default: 8 (UV)" })
        @Configurable.Range(min = 0, max = 14)
        public int tierMk3 = 8;

        @Configurable
        @Configurable.Comment({ "Configure the \"Base Heat value\" of Fusion Reactor Mk3.",
                "Calculation formula: Heat = baseHeatMk3 * energyHatchAmount", "Default: 40000000" })
        public String heatMk3 = "40000000";

        @Configurable
        @Configurable.Comment({ "Configure the \"Casing\" used for building Fusion Reactor Mk3.", "Format: modid:name",
                "Default: gtceu:fusion_casing_mk3" })
        public String casingMk3 = "gtceu:fusion_casing_mk3";

        @Configurable
        @Configurable.Comment({ "Configure the \"Glass\" used for building Fusion Reactor Mk3.", "Format: modid:name",
                "Default: gtceu:fusion_glass" })
        public String glassMk3 = "gtceu:fusion_glass";

        @Configurable
        @Configurable.Comment({ "Configure the \"Coil\" used for building Fusion Reactor Mk3.", "Format: modid:name",
                "Default: gtceu:fusion_coil" })
        public String coilMk3 = "gtceu:fusion_coil";

        @Configurable
        @Configurable.Comment({ "Configure the \"Casing Model\" used dor Fusion Reactor Mk3", "Format: modid:path",
                "If empty, model is applied to default casing's texture.",
                "Default: gtceu:block/casings/fusion/fusion_casing_mk3" })
        public String modelMk3 = "gtceu:block/casings/fusion/fusion_casing_mk3";
    }

    public static class cfgMk4 {

        @Configurable
        @Configurable.Comment({ "Configure the minimum tier of the Energy Hatch used in Fusion Reactor Mk4.",
                "Tiers: 0 = ULV, 1 = LV, 2 = MV ... 12 = UXV, 13 = OpV, 14 = MAX", "Default: 9 (UHV)" })
        @Configurable.Range(min = 0, max = 14)
        public int tierMk4 = 9;

        @Configurable
        @Configurable.Comment({ "Configure the \"Base Heat value\" of Fusion Reactor Mk4.",
                "Calculation formula: Heat = baseHeatMk4 * energyHatchAmount", "Default: 80000000" })
        public String heatMk4 = "80000000";

        @Configurable
        @Configurable.Comment({ "Configure the \"Casing\" used for building Fusion Reactor Mk4.", "Format: modid:name",
                "Default: gtceu:atomic_casing" })
        public String casingMk4 = "gtceu:atomic_casing";

        @Configurable
        @Configurable.Comment({ "Configure the \"Glass\" used for building Fusion Reactor Mk4.", "Format: modid:name",
                "Default: gtceu:fusion_glass" })
        public String glassMk4 = "gtceu:fusion_glass";

        @Configurable
        @Configurable.Comment({ "Configure the \"Coil\" used for building Fusion Reactor Mk4.", "Format: modid:name",
                "Default: gtceu:fusion_coil" })
        public String coilMk4 = "gtceu:fusion_coil";

        @Configurable
        @Configurable.Comment({ "Configure the \"Casing Model\" used dor Fusion Reactor Mk4", "Format: modid:path",
                "If empty, model is applied to default casing's texture.",
                "Default: gtceu:block/casings/gcym/atomic_casing" })
        public String modelMk4 = "gtceu:block/casings/gcym/atomic_casing";
    }

    public static class cfgMk5 {

        @Configurable
        @Configurable.Comment({ "Configure the minimum tier of the Energy Hatch used in Fusion Reactor Mk5.",
                "Tiers: 0 = ULV, 1 = LV, 2 = MV ... 12 = UXV, 13 = OpV, 14 = MAX", "Default: 10 (UEV)" })
        @Configurable.Range(min = 0, max = 14)
        public int tierMk5 = 10;

        @Configurable
        @Configurable.Comment({ "Configure the \"Base Heat value\" of Fusion Reactor Mk5.",
                "Calculation formula: Heat = baseHeatMk5 * energyHatchAmount", "Default: 160000000" })
        public String heatMk5 = "160000000";

        @Configurable
        @Configurable.Comment({ "Configure the \"Casing\" used for building Fusion Reactor Mk5.", "Format: modid:name",
                "Default: gtceu:atomic_casing" })
        public String casingMk5 = "gtceu:atomic_casing";

        @Configurable
        @Configurable.Comment({ "Configure the \"Glass\" used for building Fusion Reactor Mk5.", "Format: modid:name",
                "Default: gtceu:fusion_glass" })
        public String glassMk5 = "gtceu:fusion_glass";

        @Configurable
        @Configurable.Comment({ "Configure the \"Coil\" used for building Fusion Reactor Mk5.", "Format: modid:name",
                "Default: gtceu:fusion_coil" })
        public String coilMk5 = "gtceu:fusion_coil";

        @Configurable
        @Configurable.Comment({ "Configure the \"Casing Model\" used dor Fusion Reactor Mk5", "Format: modid:path",
                "If empty, model is applied to default casing's texture.",
                "Default: gtceu:block/casings/gcym/atomic_casing" })
        public String modelMk5 = "gtceu:block/casings/gcym/atomic_casing";
    }

    public static class cfgMk6 {

        @Configurable
        @Configurable.Comment({ "Configure the minimum tier of the Energy Hatch used in Fusion Reactor Mk6.",
                "Tiers: 0 = ULV, 1 = LV, 2 = MV ... 12 = UXV, 13 = OpV, 14 = MAX", "Default: 11 (UIV)" })
        @Configurable.Range(min = 0, max = 14)
        public int tierMk6 = 11;

        @Configurable
        @Configurable.Comment({ "Configure the \"Base Heat value\" of Fusion Reactor Mk6.",
                "Calculation formula: Heat = baseHeatMk6 * energyHatchAmount", "Default: 320000000" })
        public String heatMk6 = "320000000";

        @Configurable
        @Configurable.Comment({ "Configure the \"Casing\" used for building Fusion Reactor Mk6.", "Format: modid:name",
                "Default: gtceu:atomic_casing" })
        public String casingMk6 = "gtceu:atomic_casing";

        @Configurable
        @Configurable.Comment({ "Configure the \"Glass\" used for building Fusion Reactor Mk6.", "Format: modid:name",
                "Default: gtceu:fusion_glass" })
        public String glassMk6 = "gtceu:fusion_glass";

        @Configurable
        @Configurable.Comment({ "Configure the \"Coil\" used for building Fusion Reactor Mk6.", "Format: modid:name",
                "Default: gtceu:fusion_coil" })
        public String coilMk6 = "gtceu:fusion_coil";

        @Configurable
        @Configurable.Comment({ "Configure the \"Casing Model\" used dor Fusion Reactor Mk6", "Format: modid:path",
                "If empty, model is applied to default casing's texture.",
                "Default: gtceu:block/casings/gcym/atomic_casing" })
        public String modelMk6 = "gtceu:block/casings/gcym/atomic_casing";
    }

    public static class cfgMk7 {

        @Configurable
        @Configurable.Comment({ "Configure the minimum tier of the Energy Hatch used in Fusion Reactor Mk7.",
                "Tiers: 0 = ULV, 1 = LV, 2 = MV ... 12 = UXV, 13 = OpV, 14 = MAX", "Default: 12 (UXV)" })
        @Configurable.Range(min = 0, max = 14)
        public int tierMk7 = 12;

        @Configurable
        @Configurable.Comment({ "Configure the \"Base Heat value\" of Fusion Reactor Mk7.",
                "Calculation formula: Heat = baseHeatMk7 * energyHatchAmount", "Default: 640000000" })
        public String heatMk7 = "640000000";

        @Configurable
        @Configurable.Comment({ "Configure the \"Casing\" used for building Fusion Reactor Mk7.", "Format: modid:name",
                "Default: gtceu:atomic_casing" })
        public String casingMk7 = "gtceu:atomic_casing";

        @Configurable
        @Configurable.Comment({ "Configure the \"Glass\" used for building Fusion Reactor Mk7.", "Format: modid:name",
                "Default: gtceu:fusion_glass" })
        public String glassMk7 = "gtceu:fusion_glass";

        @Configurable
        @Configurable.Comment({ "Configure the \"Coil\" used for building Fusion Reactor Mk7.", "Format: modid:name",
                "Default: gtceu:fusion_coil" })
        public String coilMk7 = "gtceu:fusion_coil";

        @Configurable
        @Configurable.Comment({ "Configure the \"Casing Model\" used dor Fusion Reactor Mk7", "Format: modid:path",
                "If empty, model is applied to default casing's texture.",
                "Default: gtceu:block/casings/gcym/atomic_casing" })
        public String modelMk7 = "gtceu:block/casings/gcym/atomic_casing";
    }
}
