package github.thelawf.gensokyoontology.common.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;

public class SakuraLeaves extends LeavesBlock {
    public SakuraLeaves() {
        super(Properties.from(Blocks.ACACIA_LEAVES)
                .tickRandomly().sound(SoundType.PLANT));
    }
}
