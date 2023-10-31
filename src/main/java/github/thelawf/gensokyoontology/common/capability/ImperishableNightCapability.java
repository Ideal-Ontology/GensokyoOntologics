package github.thelawf.gensokyoontology.common.capability;

import net.minecraft.nbt.CompoundNBT;

public class ImperishableNightCapability implements IImperishableNight{

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
    public void setTriggered(boolean triggered) {
        this.isTriggered = triggered;
    }

    @Override
    public boolean isTriggered() {
        return this.isTriggered;
    }

    @Override
    public CompoundNBT serializeNBT() {
        return null;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {

    }
}
