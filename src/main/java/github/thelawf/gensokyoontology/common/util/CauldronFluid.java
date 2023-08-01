package github.thelawf.gensokyoontology.common.util;

import net.minecraft.util.IStringSerializable;
import org.jetbrains.annotations.NotNull;

public enum CauldronFluid implements IStringSerializable {
    EMPTY("none"),
    WATER("water"),
    LAVA("lava"),
    HOT_SPRING("hot_spring"),
    PAPER_PULP("paper_pulp"),
    SAKE_WINE("sake_wine");

    public final String name;

    CauldronFluid(String name) {
        this.name = name;
    }

    @Override
    @NotNull
    public String getString() {
        return this.name;
    }
}
