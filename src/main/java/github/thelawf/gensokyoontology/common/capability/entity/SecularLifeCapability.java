package github.thelawf.gensokyoontology.common.capability.entity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.ItemStackHandler;

public class SecularLifeCapability implements INBTSerializable<CompoundNBT> {

    private long lifetime;
    public static long MAX_LIFE_TIME = 24000 * 30 * 5;
    public SecularLifeCapability(long lifetime) {
        this.lifetime = lifetime;
    }
    private boolean isDirty;

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

    public long getLifetime() {
        return this.lifetime;
    }

    public void setLifetime(long lifetime) {
        this.lifetime = lifetime;
        this.markDirty();
    }

    public void addTime(long time) {
        this.lifetime += time;
        this.markDirty();
    }

    public long withTimeAdded(long time) {
        this.lifetime += time;
        this.markDirty();
        return this.lifetime;
    }

    public boolean isDirty() {
        return this.isDirty;
    }

    public void setDirty(boolean isDirty) {
        this.isDirty = isDirty;
    }

    public void markDirty() {
        this.setDirty(true);
    }
}
