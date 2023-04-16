package github.thelawf.gensokyoontology.common.block.cyber;

import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;

public class HashLeaves extends LeavesBlock {
    public HashLeaves() {
        super(Properties.from(Blocks.ACACIA_LEAVES).tickRandomly().sound(SoundType.PLANT));
    }
}
