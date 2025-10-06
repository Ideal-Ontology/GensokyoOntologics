package github.thelawf.gensokyoontology.common.block.decoration;

import github.thelawf.gensokyoontology.common.tileentity.OrderingTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockReader;
import org.jetbrains.annotations.Nullable;

public class OrderBoardBlock extends HorizontalBlock {
    public OrderBoardBlock() {
        super(Properties.from(Blocks.ACACIA_SIGN));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new OrderingTileEntity();
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        for(Direction direction : context.getNearestLookingDirections()) {
            BlockState blockstate;
            if (direction.getAxis() == Direction.Axis.Y && direction.getString().equals("up") || direction.getString().equals("down")) {
                blockstate = this.getDefaultState().with(HORIZONTAL_FACING, Direction.EAST);
            } else {
                blockstate = this.getDefaultState().with(HORIZONTAL_FACING, direction.getOpposite());
            }

            if (blockstate.isValidPosition(context.getWorld(), context.getPos())) {
                return blockstate;
            }
        }

        return super.getStateForPlacement(context);
    }

}
