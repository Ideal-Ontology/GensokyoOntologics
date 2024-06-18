package github.thelawf.gensokyoontology.common.block.ore;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class CrimsonMetalBlock extends Block {
    public CrimsonMetalBlock() {
        super(Properties.copy(Blocks.ANCIENT_DEBRIS).strength(9f).requiresCorrectToolForDrops());
    }
}
