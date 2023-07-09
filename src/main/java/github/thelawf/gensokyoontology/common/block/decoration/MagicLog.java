package github.thelawf.gensokyoontology.common.block.decoration;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;

public class MagicLog extends RotatedPillarBlock {
    public MagicLog() {
        super(Properties.from(Blocks.OAK_LOG));
    }
}
