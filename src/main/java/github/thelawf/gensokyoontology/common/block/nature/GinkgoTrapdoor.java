package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.TrapDoorBlock;

public class GinkgoTrapdoor extends TrapDoorBlock {
    public GinkgoTrapdoor() {
        super(Properties.from(Blocks.OAK_TRAPDOOR).sound(SoundType.WOOD));
    }
}
