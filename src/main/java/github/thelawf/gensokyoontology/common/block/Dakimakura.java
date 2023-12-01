package github.thelawf.gensokyoontology.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
//它扩展了Block类,所以是一个可以放置的方块
public class Dakimakura extends Block {
    // FRONT控制正面图案,BACK控制背面图案,FACING控制方块方向
    public static final BooleanProperty FRONT = BooleanProperty.create("front");
    public static final BooleanProperty BACK = BooleanProperty.create("back");
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public Dakimakura() {
        super(Properties.create(Material.WOOL).hardnessAndResistance(1.f).notSolid());
    }


// onBlockActivated方法在玩家点击时切换正反面图案
    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote && handIn == Hand.MAIN_HAND && state.get(BACK)) {
            state.cycleValue(FRONT);
            worldIn.setBlockState(pos, state);
            return ActionResultType.func_233537_a_(worldIn.isRemote);
        } else if (worldIn.isRemote && handIn == Hand.MAIN_HAND && state.get(FRONT)) {
            state.cycleValue(BACK);
            worldIn.setBlockState(pos, state);
            return ActionResultType.func_233537_a_(worldIn.isRemote);
        }

        return ActionResultType.PASS;
    }
//实现了与TileEntity相关的方法,所以与一个TileEntity实体绑定
    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return super.createTileEntity(state, world);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
//getStateForPlacement方法定义了方块的初始放置状态
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Direction direction = context.getFace();
        BlockState state = context.getWorld().getBlockState(context.getPos());
        return state.matchesBlock(this) && state.get(FACING) == direction ? this.getDefaultState().with(
                FACING, direction.getOpposite()) : this.getDefaultState().with(FACING, direction);
    }

    @Override
    public StateContainer<Block, BlockState> getStateContainer() {
        return super.getStateContainer();
    }
//fillStateContainer方法注册了方块所有属性
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FRONT);
        builder.add(BACK);
        builder.add(FACING);
        super.fillStateContainer(builder);
    }

    @Override
    public Block getBlock() {
        return this;
    }

}
