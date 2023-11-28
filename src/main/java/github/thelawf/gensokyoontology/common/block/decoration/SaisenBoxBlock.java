package github.thelawf.gensokyoontology.common.block.decoration;

import github.thelawf.gensokyoontology.common.tileentity.SaisenBoxTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ContainerBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// 7784774932680779809

public class SaisenBoxBlock extends Block {
    public SaisenBoxBlock() {
        super(Properties.from(Blocks.CRAFTING_TABLE));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, @NotNull IBlockReader worldIn) {
        return new SaisenBoxTileEntity();
    }
}
