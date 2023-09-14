package github.thelawf.gensokyoontology.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;

public class IshiZakuraBlock extends Block {
    public IshiZakuraBlock() {
        super(Properties.from(Blocks.STONE).sound(SoundType.GLASS));
    }
}
