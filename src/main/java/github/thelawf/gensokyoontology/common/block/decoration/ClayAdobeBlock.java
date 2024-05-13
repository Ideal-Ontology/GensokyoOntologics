package github.thelawf.gensokyoontology.common.block.decoration;

import github.thelawf.gensokyoontology.common.tileentity.AdobeTileEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.BlockGetter;
import net.minecraft.world.server.ServerWorld;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ClayAdobeBlock extends Block {

    public ClayAdobeBlock() {
        super(Properties.copy(Blocks.TERRACOTTA));
    }
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, BlockGetter world) {
        return new AdobeTileEntity();
    }

    // @SuppressWarnings("deprecation")
    public List<Vector3i> getCarvedPos(ServerWorld serverWorld, BlockPos pos) {
        if (serverWorld.getTileEntity(pos) instanceof AdobeTileEntity) {
            // this.getShape();
            AdobeTileEntity adobe = getTileEntity(serverWorld, pos);
            return adobe.getCarvedPositions();
        }
        return new ArrayList<>();
    }

    public AdobeTileEntity getTileEntity(ServerWorld serverWorld, BlockPos pos) {
        return (AdobeTileEntity) serverWorld.getTileEntity(pos);
    }

    public static final int[] VOXEL_LEVEL = new int[]{
            0,0,0,0,  0,0,0,0,  0,0,0,0,  0,0,0,0,
            0,0,0,0,  0,0,0,0,  0,0,0,0,  0,0,0,0,
            0,0,0,0,  0,0,0,0,  0,0,0,0,  0,0,0,0,
            0,0,0,0,  1,1,1,1,  1,1,1,1,  0,0,0,0,

            0,0,0,0,  1,1,1,1,  1,1,1,1,  0,0,0,0,
            0,0,0,0,  1,1,1,1,  1,1,1,1,  0,0,0,0,
            0,0,0,0,  1,1,1,1,  1,1,1,1,  0,0,0,0,
            0,0,0,0,  1,1,1,1,  1,1,1,1,  0,0,0,0,

            0,0,0,0,  1,1,1,1,  1,1,1,1,  0,0,0,0,
            0,0,0,0,  1,1,1,1,  1,1,1,1,  0,0,0,0,
            0,0,0,0,  1,1,1,1,  1,1,1,1,  0,0,0,0,
            0,0,0,0,  1,1,1,1,  1,1,1,1,  0,0,0,0,

            0,0,0,0,  1,1,1,1,  1,1,1,1,  0,0,0,0,
            0,0,0,0,  0,0,0,0,  0,0,0,0,  0,0,0,0,
            0,0,0,0,  0,0,0,0,  0,0,0,0,  0,0,0,0,
            0,0,0,0,  0,0,0,0,  0,0,0,0,  0,0,0,0,
    };
}
