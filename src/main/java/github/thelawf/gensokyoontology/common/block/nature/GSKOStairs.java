package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class GSKOStairs extends StairBlock {
    public GSKOStairs() {
        super(Blocks.ACACIA_STAIRS::defaultBlockState, Properties.copy(Blocks.ACACIA_STAIRS));
    }
}
