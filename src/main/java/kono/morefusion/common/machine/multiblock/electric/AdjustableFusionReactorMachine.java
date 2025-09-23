package kono.morefusion.common.machine.multiblock.electric;

import net.minecraft.world.level.block.Block;

import com.gregtechceu.gtceu.api.block.IFusionCasingType;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.common.block.FusionCasingBlock;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.FusionReactorMachine;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static kono.morefusion.api.MoreFusionValues.getBaseHeat;

public class AdjustableFusionReactorMachine extends FusionReactorMachine {

    public AdjustableFusionReactorMachine(IMachineBlockEntity holder, int index) {
        super(holder, index);
    }

    public static long calculateEnergyStorageFactor(int index, int energyInputAmount) {
        return energyInputAmount * getBaseHeat().get(index);
    }

    public static Block getCasingState(int index) {
        return switch (index) {
            case LuV -> FUSION_CASING.get(); // gtceu:fusion_casing
            case ZPM -> FUSION_CASING_MK2.get(); // gtceu:fusion_casing_mk2
            default -> FUSION_CASING_MK3.get(); // gtceu:fusion_casing_mk3
            // gtceu:high_temperature_smelting_casing
            // gtceu:atomic_casing

            // gtceu:fusion_glass
            // gtceu:laminated_glass
        };
    }

    public static Block getCoilState(int index) {
        if (index == LuV)
            return SUPERCONDUCTING_COIL.get(); // gtceu:superconducting_coil
        // gtceu:naquadah_coil_block

        return FUSION_COIL.get(); // gtceu:fusion_coil
    }

    public static IFusionCasingType getCasingType(int tier) {
        return switch (tier) {
            case LuV -> FusionCasingBlock.CasingType.FUSION_CASING;
            case ZPM -> FusionCasingBlock.CasingType.FUSION_CASING_MK2;
            case UV -> FusionCasingBlock.CasingType.FUSION_CASING_MK3;
            default -> FusionCasingBlock.CasingType.FUSION_CASING;
        };
    }
}
