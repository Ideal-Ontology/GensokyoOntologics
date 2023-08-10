package github.thelawf.gensokyoontology.common.block;

import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;

public class JadeOreBlock extends OreBlock {
    public JadeOreBlock() {
        super(Properties.from(Blocks.NETHERITE_BLOCK).harvestLevel(5));
    }
}
