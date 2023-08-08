package github.thelawf.gensokyoontology.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class CrimsonMetalBlock extends Block {
    public CrimsonMetalBlock() {
        super(Properties.from(Blocks.ANCIENT_DEBRIS).harvestLevel(4));
    }
}
