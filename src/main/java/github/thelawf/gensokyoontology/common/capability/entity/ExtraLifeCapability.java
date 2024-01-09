package github.thelawf.gensokyoontology.common.capability.entity;

import net.minecraft.command.impl.GameModeCommand;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.GameType;
import net.minecraftforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;

public class ExtraLifeCapability implements INBTSerializable<CompoundNBT> {
    private int extraLifeCount = 0;

    public int getExtraLifeCount() {
        return extraLifeCount;
    }

    public void setExtraLifeCount(int extraLifeCount) {
        this.extraLifeCount = extraLifeCount;
    }

    public void addExtraLife(int extraLifeCount) {
        this.extraLifeCount += extraLifeCount;
    }

    @Override
    @NotNull
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("extra_life", this.extraLifeCount);
        return nbt;
    }

    @Override
    public void deserializeNBT(@NotNull CompoundNBT nbt) {
        if (nbt.contains("extra_life")) {
            this.extraLifeCount = nbt.getInt("extra_life");
        }
    }
}
