package github.thelawf.gensokyoontology.common.util.block;

import net.minecraft.util.IStringSerializable;

public enum ClockHandDirection implements IStringSerializable {
    CLOCK_1,
    CLOCK_2,
    CLOCK_3
    ;


    @Override
    public String getString() {
        return String.valueOf(this.ordinal()+1);
    }
}
