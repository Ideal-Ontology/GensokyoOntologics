package github.thelawf.gensokyoontology.common.block.ore;

import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;

public class IzanagiObjectOre extends OreBlock {
    public IzanagiObjectOre() {
        super(Properties.from(Blocks.LAPIS_ORE).setRequiresTool()
                .hardnessAndResistance(3.0f,3.0f)
                .sound(SoundType.STONE));
    }
}
