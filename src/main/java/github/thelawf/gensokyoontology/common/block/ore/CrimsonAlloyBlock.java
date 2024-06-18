package github.thelawf.gensokyoontology.common.block.ore;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class CrimsonAlloyBlock extends Block {
    public CrimsonAlloyBlock() {
        super(Properties.copy(Blocks.NETHERITE_BLOCK).strength(9f).requiresCorrectToolForDrops());
    }
}
