package github.thelawf.gensokyoontology.common.item.tool;

import github.thelawf.gensokyoontology.common.block.decoration.CoasterRailBlock;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class CoasterItem extends Item {
    public CoasterItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull ActionResultType onItemUse(@NotNull ItemUseContext context) {
        if (!(context.getWorld().getBlockState(context.getPos().up()).getBlock() == BlockRegistry.COASTER_RAIL.get()))
            return super.onItemUse(context);
        Block block = context.getWorld().getBlockState(context.getPos().up()).getBlock();
        return ActionResultType.PASS;
    }
}
