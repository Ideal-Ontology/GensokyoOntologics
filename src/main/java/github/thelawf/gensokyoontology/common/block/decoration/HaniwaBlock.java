package github.thelawf.gensokyoontology.common.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import org.jetbrains.annotations.NotNull;

public class HaniwaBlock extends Block {
    public static final VoxelShape BODY_MAIN = makeCuboidShape(3, 0, 3, 13, 12, 13);
    public static final VoxelShape BODY_TOP = makeCuboidShape(4, 12, 4, 12, 14, 12);
    public static final VoxelShape LEFT_BASE = makeCuboidShape(13, 5, 7, 14, 8, 10);
    public static final VoxelShape LEFT_ARM = makeCuboidShape(14, 3, 8, 16, 8, 10);
    public static final VoxelShape RIGHT_BASE = makeCuboidShape(2, 5, 7, 3, 8, 10);
    public static final VoxelShape RIGHT_ARM_HORIZONTAL = makeCuboidShape(-2, 6, 7, 2, 8, 9);
    public static final VoxelShape RIGHT_ARM_VERTICAL = makeCuboidShape(-2, 8, 7, 0, 12, 9);

    @NotNull
    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(@NotNull BlockState state, @NotNull IBlockReader worldIn, @NotNull BlockPos pos, @NotNull ISelectionContext context) {
        return VoxelShapes.or(BODY_MAIN, BODY_TOP, LEFT_BASE, LEFT_ARM, RIGHT_BASE, RIGHT_ARM_HORIZONTAL, RIGHT_ARM_VERTICAL);
    }

    public HaniwaBlock() {
        super(Properties.from(Blocks.FLOWER_POT));
    }
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

}
