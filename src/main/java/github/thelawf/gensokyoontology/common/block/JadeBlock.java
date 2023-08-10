package github.thelawf.gensokyoontology.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;

public class JadeBlock extends Block {
    public JadeBlock() {
        super(Properties.from(Blocks.DIAMOND_BLOCK).sound(SoundType.GLASS).harvestLevel(5));
    }
}
