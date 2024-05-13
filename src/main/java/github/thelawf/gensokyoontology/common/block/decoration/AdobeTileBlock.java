package github.thelawf.gensokyoontology.common.block.decoration;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AdobeTileBlock extends Block {
    public static final VoxelShape TILE_SHAPE = box(0,0,0,1,1,1);
    public AdobeTileBlock() {
        super(Properties.copy(Blocks.TERRACOTTA));
    }

    
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return TILE_SHAPE;
    }
}
