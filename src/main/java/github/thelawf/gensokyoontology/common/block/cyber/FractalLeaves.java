package github.thelawf.gensokyoontology.common.block.cyber;

import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;

public class FractalLeaves extends LeavesBlock {
    public FractalLeaves() {
        super(Properties.from(Blocks.ACACIA_LEAVES).tickRandomly());
    }
}
