package github.thelawf.gensokyoontology.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;

public class SakuraPlanks extends Block {
    public SakuraPlanks() {
        super(Properties.from(Blocks.OAK_PLANKS).hardnessAndResistance(2.0f, 3.0f)
                .sound(SoundType.WOOD));
    }
}
