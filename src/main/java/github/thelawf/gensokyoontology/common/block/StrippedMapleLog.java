package github.thelawf.gensokyoontology.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;

public class StrippedMapleLog extends RotatedPillarBlock {
    public StrippedMapleLog() {
        super(Properties.from(Blocks.STRIPPED_ACACIA_LOG).sound(SoundType.WOOD));
    }
}
