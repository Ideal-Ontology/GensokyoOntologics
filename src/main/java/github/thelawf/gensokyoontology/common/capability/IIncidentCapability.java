package github.thelawf.gensokyoontology.common.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface IIncidentCapability extends INBTSerializable<CompoundNBT> {

    void applyIncident();
}
