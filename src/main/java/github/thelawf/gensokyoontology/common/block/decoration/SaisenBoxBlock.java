package github.thelawf.gensokyoontology.common.block.decoration;

import github.thelawf.gensokyoontology.common.tileentity.SaisenBoxTileEntity;
import net.minecraft.world.level.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.BlockGetter;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// 7784774932680779809
public class SaisenBoxBlock extends HorizontalBlock {
    public SaisenBoxBlock() {
        super(Properties.copy(Blocks.CRAFTING_TABLE));
    }
    public static final VoxelShape SHAPE = Block.box(2, 0, 2, 14, 12, 14);

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, @NotNull BlockGetter worldIn) {
        return new SaisenBoxTileEntity();
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

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING);
    }
}
