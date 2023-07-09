package github.thelawf.gensokyoontology.common.block.decoration;

import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;

public class FagusLog extends RotatedPillarBlock {
    public FagusLog() {
        super(Properties.from(Blocks.OAK_LOG).sound(SoundType.WOOD));
    }
}
