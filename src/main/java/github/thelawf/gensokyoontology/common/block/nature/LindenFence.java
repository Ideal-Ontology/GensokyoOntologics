package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.Blocks;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.SoundType;

public class LindenFence extends FenceBlock {
    public LindenFence() {
        super(Properties.copy(Blocks.OAK_FENCE).sound(SoundType.WOOD));
    }
}
