package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
/**
 * 杜鹃花地块 继承了 叶子方块
 */
public class AzaleaBlock extends LeavesBlock {
    public AzaleaBlock() {
        super(Properties.from(Blocks.SPRUCE_LEAVES));
    }
}
