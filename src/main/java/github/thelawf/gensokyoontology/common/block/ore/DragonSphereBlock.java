package github.thelawf.gensokyoontology.common.block.ore;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class DragonSphereBlock extends Block {
    public DragonSphereBlock() {
        super(Properties.copy(Blocks.DIAMOND_BLOCK).setRequiresTool());
    }
}
