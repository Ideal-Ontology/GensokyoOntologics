package github.thelawf.gensokyoontology.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class JadeBlock extends Block {
    public JadeBlock() {
        super(Properties.from(Blocks.NETHERITE_BLOCK).harvestLevel(5));
    }
}
