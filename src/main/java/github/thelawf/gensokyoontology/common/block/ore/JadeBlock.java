package github.thelawf.gensokyoontology.common.block.ore;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
// 玉
public class JadeBlock extends Block {
    public JadeBlock() {
        super(Properties.from(Blocks.GLASS).sound(SoundType.GLASS).harvestLevel(5));
    }
}
