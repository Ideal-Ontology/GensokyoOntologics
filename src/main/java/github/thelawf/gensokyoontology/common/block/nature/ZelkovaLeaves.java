package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;

public class ZelkovaLeaves extends LeavesBlock {
    public ZelkovaLeaves() {
        super(Properties.from(Blocks.OAK_LEAVES).tickRandomly().sound(SoundType.PLANT));
    }
}
