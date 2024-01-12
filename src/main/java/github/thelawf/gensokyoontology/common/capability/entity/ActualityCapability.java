package github.thelawf.gensokyoontology.common.capability.entity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public class ActualityCapability implements INBTSerializable<CompoundNBT> {
    private float actuality = 20f;
    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putFloat("actuality", this.actuality);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.actuality = nbt.getFloat("actuality");
    }
}
