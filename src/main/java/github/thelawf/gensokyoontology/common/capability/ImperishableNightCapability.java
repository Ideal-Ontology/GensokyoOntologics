package github.thelawf.gensokyoontology.common.capability;

import net.minecraft.nbt.CompoundNBT;

public class ImperishableNightCapability implements IImperishableNight {

    private int time;
    private boolean isTriggered;

    public ImperishableNightCapability(int time, boolean isTriggered) {
        this.time = time;
        this.isTriggered = isTriggered;
    }

    @Override
    public void setDayTime(int time) {
        this.time = time;
    }

    @Override
    public int getDayTime() {
        return this.time;
    }

    @Override
    public void setTriggered(boolean triggered) {
        this.isTriggered = triggered;
    }

    @Override
    public boolean isTriggered() {
        return this.isTriggered;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("time", this.time);
        nbt.putBoolean("is_triggered", this.isTriggered);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        if (nbt.contains("time")) {
            this.time = nbt.getInt("time");
        }
        if (nbt.contains("is_triggered")) {
            this.isTriggered = nbt.getBoolean("is_triggered");
        }
    }
}
