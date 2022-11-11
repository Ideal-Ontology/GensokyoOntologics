package github.thelawf.gensokyoontology.common.libs;

import net.minecraft.stats.IStatFormatter;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatType;

public class GSKOStat extends Stat {
    protected GSKOStat(StatType typeIn, Object valueIn, IStatFormatter formatterIn) {
        super(typeIn, valueIn, formatterIn);
    }
}
