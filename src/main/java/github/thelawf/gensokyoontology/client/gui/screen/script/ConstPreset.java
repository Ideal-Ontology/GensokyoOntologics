package github.thelawf.gensokyoontology.client.gui.screen.script;

import github.thelawf.gensokyoontology.common.util.GSKONBTUtil;
import net.minecraft.nbt.CompoundNBT;

public enum ConstPreset {
    NONE(new CompoundNBT()),
    PI(GSKONBTUtil.wrap(() -> {
        CompoundNBT compound = new CompoundNBT();
        compound.putDouble("pi", Math.PI);
        return compound;
    })),
    TWO_PI(GSKONBTUtil.wrap(() -> {
        CompoundNBT compound = new CompoundNBT();
        compound.putDouble("two_pi", Math.PI * 2);
        return compound;
    })),
    E(GSKONBTUtil.wrap(() -> {
        CompoundNBT compound = new CompoundNBT();
        compound.putDouble("e", Math.E);
        return compound;
    }));

    public final CompoundNBT nbt;

    ConstPreset(CompoundNBT nbt) {
        this.nbt = nbt;
    }

    public CompoundNBT get() {
        return this.nbt;
    }
}
