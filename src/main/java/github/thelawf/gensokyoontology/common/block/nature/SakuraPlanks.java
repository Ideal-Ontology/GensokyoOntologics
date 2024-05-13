package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.block.SoundType;

public class SakuraPlanks extends Block {
    public SakuraPlanks() {
        super(Properties.copy(Blocks.OAK_PLANKS).hardnessAndResistance(2.0f, 3.0f)
                .sound(SoundType.WOOD));
    }
}
