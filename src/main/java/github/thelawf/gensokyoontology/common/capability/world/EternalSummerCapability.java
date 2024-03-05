package github.thelawf.gensokyoontology.common.capability.world;

import net.minecraft.nbt.CompoundNBT;

public class EternalSummerCapability implements IIncidentCapability{
    private boolean isTriggered;
    public static EternalSummerCapability INSTANCE;
    public EternalSummerCapability(boolean isTriggered) {
        this.isTriggered = isTriggered;
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
        nbt.putBoolean("is_triggered", this.isTriggered);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.isTriggered = nbt.getBoolean("is_triggered");
    }
}
