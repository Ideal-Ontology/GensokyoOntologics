package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
/**
 * AzaleaLeavesBlock 杜鹃花叶子方块 继承了叶子方块
 */
public class AzaleaLeavesBlock extends LeavesBlock {
    public AzaleaLeavesBlock() {
        super(Properties.from(Blocks.SPRUCE_LEAVES));
    }
}
