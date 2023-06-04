package github.thelawf.gensokyoontology.common.block;

import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nonnull;

public class OnionCropBlock extends CropsBlock {

    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.makeCuboidShape(0.0D,0.0D,0.0D,16.0D,2.0D,16.0D),
            Block.makeCuboidShape(0.0D,0.0D,0.0D,16.0D,4.0D,16.0D),
            Block.makeCuboidShape(0.0D,0.0D,0.0D,16.0D,6.0D,16.0D),
            Block.makeCuboidShape(0.0D,0.0D,0.0D,16.0D,8.0D,16.0D),
            Block.makeCuboidShape(0.0D,0.0D,0.0D,16.0D,10.0D,16.0D),
            Block.makeCuboidShape(0.0D,0.0D,0.0D,16.0D,12.0D,16.0D),
            Block.makeCuboidShape(0.0D,0.0D,0.0D,16.0D,14.0D,16.0D),
            Block.makeCuboidShape(0.0D,0.0D,0.0D,16.0D,16.0D,16.0D),
    };

    public OnionCropBlock(Properties builder) {
        super(builder);
    }

    @Override
    @Nonnull
    protected IItemProvider getSeedsItem() {
        return ItemRegistry.ONION.get();
    }

    @Override
    @Nonnull
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull IBlockReader worldIn, @Nonnull BlockPos pos, @Nonnull ISelectionContext context) {
        return SHAPE_BY_AGE[state.get(this.getAgeProperty())];
    }
}
