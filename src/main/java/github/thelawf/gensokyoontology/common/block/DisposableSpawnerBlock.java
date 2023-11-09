package github.thelawf.gensokyoontology.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;

public class DisposableSpawnerBlock extends Block {
    public DisposableSpawnerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return super.hasTileEntity(state);
    }
}
