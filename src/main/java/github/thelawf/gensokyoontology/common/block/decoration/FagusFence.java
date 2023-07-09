package github.thelawf.gensokyoontology.common.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.SoundType;

public class FagusFence extends FenceBlock {
    public FagusFence() {
        super(Properties.from(Blocks.OAK_FENCE).sound(SoundType.WOOD));
    }
}
