package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.AbstractBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.block.RotatedPillarBlock;

public class ShinbokuLog extends RotatedPillarBlock {
    public ShinbokuLog() {
        super(Properties.copy(Blocks.OAK_LOG));
    }
}
