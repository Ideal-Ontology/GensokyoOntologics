package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;

public class ZelkovaLog extends RotatedPillarBlock {
    public ZelkovaLog() {
        super(Properties.from(Blocks.OAK_LOG).sound(SoundType.WOOD));
    }
}
