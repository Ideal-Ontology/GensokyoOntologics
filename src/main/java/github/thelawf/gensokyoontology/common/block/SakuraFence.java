package github.thelawf.gensokyoontology.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.SoundType;

public class SakuraFence extends FenceBlock {
    public SakuraFence() {
        super(Properties.from(Blocks.OAK_FENCE).sound(SoundType.WOOD));
    }
}
