package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.block.SoundType;

public class ShinbokuPlanks extends Block {
    public ShinbokuPlanks() {
        super(Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.WOOD));
    }
}
