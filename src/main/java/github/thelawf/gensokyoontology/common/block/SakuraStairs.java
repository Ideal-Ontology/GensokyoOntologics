package github.thelawf.gensokyoontology.common.block;

import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import net.minecraft.block.*;
import net.minecraft.item.BlockItemUseContext;

import java.util.function.Supplier;

public class SakuraStairs extends Block {
    public SakuraStairs() {
        super(Properties.from(Blocks.OAK_STAIRS).sound(SoundType.WOOD));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return super.getStateForPlacement(context);
    }
}
