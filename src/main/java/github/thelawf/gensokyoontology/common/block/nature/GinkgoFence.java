package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.Blocks;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.SoundType;

public class GinkgoFence extends FenceBlock {
    public GinkgoFence() {
        super(Properties.from(Blocks.OAK_FENCE).sound(SoundType.WOOD));
    }
}
