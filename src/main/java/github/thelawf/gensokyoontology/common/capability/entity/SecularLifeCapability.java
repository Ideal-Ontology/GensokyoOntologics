package github.thelawf.gensokyoontology.common.capability.entity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public class SecularLifeCapability implements INBTSerializable<CompoundNBT> {

    private long lifetime;
    public static long MAX_LIFE_TIME = 24000 * 30 * 5;
    public SecularLifeCapability(long lifetime) {
        this.lifetime = lifetime;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putLong("lifetime", this.lifetime);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        if (nbt.contains("lifetime")) {
            this.lifetime = nbt.getLong("lifetime");
        }
    }
}
