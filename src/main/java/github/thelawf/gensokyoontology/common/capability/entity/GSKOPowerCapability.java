package github.thelawf.gensokyoontology.common.capability.entity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public class GSKOPowerCapability implements INBTSerializable<CompoundNBT> {
    private float count;
    private boolean isDirty;
    public static final float MAX = 5.00F;
    public static final float MIN = 0.00F;

    public GSKOPowerCapability(float count) {
        this.count = count;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putFloat("power_count", this.count);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.count = nbt.getFloat("power_count");
    }

    public void setCount(float count) {
        this.count = count > MAX ? MAX : Math.max(count, MIN);
    }

    public float getCount() {
        return this.count;
    }

    public void add(float count) {
        this.count = this.count + count > MAX ? MAX : Math.max(this.count + count, MIN);
    }

    public void markDirty() {
        this.isDirty = true;
    }

    public boolean isDirty() {
        return this.isDirty;
    }

    public void setDirty(boolean dirty) {
        this.isDirty = dirty;
    }
}
