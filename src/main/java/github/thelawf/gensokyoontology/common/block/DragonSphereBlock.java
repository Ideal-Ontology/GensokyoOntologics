package github.thelawf.gensokyoontology.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class DragonSphereBlock extends Block {
    public DragonSphereBlock() {
        super(Properties.from(Blocks.DIAMOND_BLOCK).setRequiresTool());
    }
}
