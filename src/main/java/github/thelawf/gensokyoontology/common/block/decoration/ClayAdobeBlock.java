package github.thelawf.gensokyoontology.common.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

public class ClayAdobeBlock extends Block {

    public ClayAdobeBlock() {
        super(Properties.from(Blocks.TERRACOTTA));
    }
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

}
