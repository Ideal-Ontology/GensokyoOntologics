package github.thelawf.gensokyoontology.common.block.ore;

import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;

public class ImmemorialAlloyBlock extends OreBlock {
    public ImmemorialAlloyBlock() {
        super(Properties.from(Blocks.DIAMOND_ORE).harvestLevel(4));
    }
}
