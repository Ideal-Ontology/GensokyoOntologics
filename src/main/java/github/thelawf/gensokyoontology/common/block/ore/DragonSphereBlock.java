package github.thelawf.gensokyoontology.common.block.ore;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
// 龙珠块？
public class DragonSphereBlock extends Block {
    public DragonSphereBlock() {
        super(Properties.from(Blocks.DIAMOND_BLOCK).setRequiresTool());
    }
}
