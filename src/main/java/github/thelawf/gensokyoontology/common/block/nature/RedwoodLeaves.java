package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
//红树叶
public class RedwoodLeaves extends LeavesBlock {
    public RedwoodLeaves() {
        super(Properties.from(Blocks.SPRUCE_LEAVES).tickRandomly().sound(SoundType.PLANT));
    }
}
