package github.thelawf.gensokyoontology.common.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.util.INBTSerializable;

public interface IIdeologyCapability extends INBTSerializable<CompoundNBT> {

    AxisAlignedBB getRange();

    void setRange(AxisAlignedBB aabb);
}
