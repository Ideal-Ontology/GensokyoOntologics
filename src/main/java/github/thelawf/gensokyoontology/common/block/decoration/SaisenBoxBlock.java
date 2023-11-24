package github.thelawf.gensokyoontology.common.block.decoration;

import github.thelawf.gensokyoontology.common.tileentity.SaisenBoxTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ContainerBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import org.jetbrains.annotations.Nullable;

public class SaisenBoxBlock extends ContainerBlock {
    public SaisenBoxBlock() {
        super(Properties.from(Blocks.CHEST));
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader worldIn) {
        return new SaisenBoxTileEntity();
    }
}
