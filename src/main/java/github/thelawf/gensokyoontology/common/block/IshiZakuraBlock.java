package github.thelawf.gensokyoontology.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.BlockGetter;
import org.jetbrains.annotations.NotNull;

public class IshiZakuraBlock extends Block {

    private static final VoxelShape shape;

    static {
        VoxelShape voxelShape = Block.box(4, 0, 4, 12, 6, 12);
        shape = VoxelShapes.or(voxelShape);
    }

    @SuppressWarnings("deprecation")
    @NotNull
    @Override
    public VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos, @NotNull ISelectionContext context) {
        return shape;
    }

    public IshiZakuraBlock() {
        super(Properties.copy(Blocks.STONE).sound(SoundType.GLASS));
    }
}
