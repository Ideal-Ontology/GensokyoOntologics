package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.levelgen.feature.stateproviders.RotatedBlockProvider;

public class GSKOLog extends RotatedPillarBlock {
    public GSKOLog() {
        super(Properties.copy(Blocks.ACACIA_LOG));
    }
}
