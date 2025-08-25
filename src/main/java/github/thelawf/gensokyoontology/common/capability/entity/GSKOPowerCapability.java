package github.thelawf.gensokyoontology.common.capability.entity;

import github.thelawf.gensokyoontology.common.events.ValueChangedEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.FloatNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.LongNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import org.jetbrains.annotations.Nullable;

public class GSKOPowerCapability implements INBTSerializable<FloatNBT> {
    private float count;
    private boolean isDirty;
    public static final float MAX = 5.00F;
    public static final float MIN = 0.00F;
    public static GSKOPowerCapability INSTANCE;
    public static final String ID = "gsko_power";

    public GSKOPowerCapability(float count) {
        this.count = count;
    }

    @Override
    public FloatNBT serializeNBT() {

        return FloatNBT.valueOf(this.count);
    }

    @Override
    public void deserializeNBT(FloatNBT nbt) {
        this.count = nbt.getFloat();
    }

    public void setCount(float count) {
        MinecraftForge.EVENT_BUS.post(new ValueChangedEvent<>(this.count, count));
        this.count = MathHelper.clamp(count, MIN, MAX);
        this.markDirty();
    }

    public float getCount() {
        return this.count;
    }

    public void add(float count) {
        this.count = MathHelper.clamp(this.count + count, MIN, MAX);
        this.markDirty();
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
