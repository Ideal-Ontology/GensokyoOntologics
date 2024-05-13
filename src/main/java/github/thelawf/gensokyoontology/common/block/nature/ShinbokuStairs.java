package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.item.BlockItemUseContext;
import org.jetbrains.annotations.NotNull;

public class ShinbokuStairs extends StairsBlock {
    public ShinbokuStairs() {
        super(Blocks.OAK_STAIRS::getDefaultState, Properties.copy(Blocks.OAK_STAIRS));
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockItemUseContext context) {
        return super.getStateForPlacement(context);
    }
}
