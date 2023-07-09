package github.thelawf.gensokyoontology.common.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;

public class FagusPlanks extends Block {
    public FagusPlanks() {
        super(Properties.from(Blocks.OAK_PLANKS).sound(SoundType.WOOD));
    }
}
