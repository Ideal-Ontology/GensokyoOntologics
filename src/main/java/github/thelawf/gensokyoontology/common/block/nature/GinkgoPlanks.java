package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;

public class GinkgoPlanks extends Block {
    public GinkgoPlanks() {
        super(Properties.from(Blocks.OAK_PLANKS).sound(SoundType.WOOD));
    }
}
