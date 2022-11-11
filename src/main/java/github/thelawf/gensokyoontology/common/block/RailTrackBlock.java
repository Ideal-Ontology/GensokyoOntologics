package github.thelawf.gensokyoontology.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RailTrackBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    // public static final EnumProperty<AxisRotations> ROTATE = EnumProperty.create("rotate", AxisRotations.class);
    //public static final EnumProperty ANCHORED_PLANE = EnumProperty.create("anchored_plane");
    private static final VoxelShape shape;
    public static final VoxelShape railHorizontal;
    public static final VoxelShape railVertical;

    static {
        VoxelShape railA = Block.makeCuboidShape(-3, 0, 0,0, 3, 16);
        VoxelShape railB = Block.makeCuboidShape(16, 0, 0,19, 3, 16);
        VoxelShape girder1 = Block.makeCuboidShape(0, 0, 3,16, 1, 6);
        VoxelShape girder2 = Block.makeCuboidShape(0, 0, 11,16, 1, 14);

        VoxelShape horizontal = Block.makeCuboidShape(-3,0,0,19, 3, 16);
        VoxelShape vertical = Block.makeCuboidShape(-3,0,0,19, 16, 0);

        shape = VoxelShapes.or(railA,railB,girder1,girder2);
        railHorizontal = VoxelShapes.or(horizontal);
        railVertical = VoxelShapes.or(vertical);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        /*
        this.setDefaultState(this.stateContainer.getBaseState().with(EAST,true));
        if (state.get(BlockStateProperties.EAST) || state.get(BlockStateProperties.WEST) ||
                state.get(BlockStateProperties.SOUTH) || state.get(BlockStateProperties.NORTH)) {
            return railVertical;
        }
        else {
            return railHorizontal;
        }
        */
        return railHorizontal;
    }

    public RailTrackBlock() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(3.f,25).notSolid());
        this.setDefaultState(this.stateContainer.getBaseState());
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (placer != null) {
            worldIn.setBlockState(pos, state.with(FACING, getStateFromEntity(pos, placer)));
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    /*
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().getOffset(FACING,false);
    }
     */

    public static Direction getStateFromEntity(BlockPos clickedBlock, Entity player){
        return Direction.getFacingFromVector((float) (player.getPosX() - clickedBlock.getX()),
                (float) (player.getPosY() - clickedBlock.getY()), (float) (player.getPosZ() - clickedBlock.getZ()));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.FACING);
        super.fillStateContainer(builder);
    }
}
