package github.thelawf.gensokyoontology.common.util.block;

import net.minecraft.util.IStringSerializable;
import org.jetbrains.annotations.NotNull;

public enum ClockHandDirection implements IStringSerializable {
    CLOCK_1,
    CLOCK_2,
    CLOCK_3,
    CLOCK_4,
    CLOCK_5,
    CLOCK_6,
    CLOCK_7,
    CLOCK_8,
    CLOCK_9,
    CLOCK_10,
    CLOCK_11,
    CLOCK_12;

    @Override
    @NotNull
    public String getString() {
        return String.valueOf(this.ordinal() + 1);
    }
}
