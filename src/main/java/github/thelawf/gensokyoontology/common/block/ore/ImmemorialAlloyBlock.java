package github.thelawf.gensokyoontology.common.block.ore;

import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
// 巨大合金？
public class ImmemorialAlloyBlock extends OreBlock {
    public ImmemorialAlloyBlock() {
        super(Properties.from(Blocks.NETHERITE_BLOCK).harvestLevel(4));
    }
}
