package github.thelawf.gensokyoontology.common.block.cyber;

import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;

public class HashLeaves extends LeavesBlock {
    public HashLeaves() {
        super(Properties.from(Blocks.ACACIA_LEAVES).tickRandomly());
    }
}
