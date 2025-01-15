package github.thelawf.gensokyoontology.common.capability.entity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.LongNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.ItemStackHandler;

public class SecularLifeCapability implements INBTSerializable<LongNBT> {

    private long lifetime;
    public static long MAX_LIFE_TIME = 24000 * 30 * 5;
    public SecularLifeCapability(long lifetime) {
        this.lifetime = lifetime;
    }
    private boolean isDirty;

    @Override
    public LongNBT serializeNBT() {
        return LongNBT.valueOf(this.lifetime);
    }

    @Override
    public void deserializeNBT(LongNBT nbt) {
        this.lifetime = nbt.getLong();
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
