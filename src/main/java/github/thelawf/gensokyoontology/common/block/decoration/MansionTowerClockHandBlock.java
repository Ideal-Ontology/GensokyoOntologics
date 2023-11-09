package github.thelawf.gensokyoontology.common.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MansionTowerClockHandBlock extends Block {
    public MansionTowerClockHandBlock() {
        super(Properties.from(Blocks.IRON_BLOCK));
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBlockHarvested(worldIn, pos, state, player);
        spawnDrops(state, worldIn, pos, null, player, new ItemStack(ItemStack.EMPTY.getItem()));
    }
}
