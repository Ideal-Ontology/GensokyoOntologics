package github.thelawf.gensokyoontology.common.block;

import github.thelawf.gensokyoontology.common.tileentity.DisposableSpawnerTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import org.jetbrains.annotations.Nullable;

public class DisposableSpawnerBlock extends Block {
    public DisposableSpawnerBlock() {
        super(Properties.from(Blocks.SPAWNER));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new DisposableSpawnerTile();
    }
}
