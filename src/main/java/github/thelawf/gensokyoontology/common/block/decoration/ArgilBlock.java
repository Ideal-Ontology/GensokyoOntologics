package github.thelawf.gensokyoontology.common.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.vector.Vector3i;

import java.util.List;

public class ArgilBlock extends Block {

    public ArgilBlock(Properties properties) {
        super(properties);
    }
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

}
