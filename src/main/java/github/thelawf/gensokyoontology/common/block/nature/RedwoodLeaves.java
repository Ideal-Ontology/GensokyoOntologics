package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;

public class RedwoodLeaves extends LeavesBlock {
    public RedwoodLeaves() {
        super(Properties.copy(Blocks.SPRUCE_LEAVES).tickRandomly().sound(SoundType.PLANT));
    }
}
