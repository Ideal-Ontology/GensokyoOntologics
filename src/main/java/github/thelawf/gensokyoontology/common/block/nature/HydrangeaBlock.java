package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
// 绣球花块 继承 灌木方块
public class HydrangeaBlock extends BushBlock {
    public HydrangeaBlock() {
        // 滨菊
        super(Properties.from(Blocks.OXEYE_DAISY));
    }
}
