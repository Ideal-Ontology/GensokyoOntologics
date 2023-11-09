package github.thelawf.gensokyoontology.common.block.decoration;

import github.thelawf.gensokyoontology.common.util.block.ClockHandDirection;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MansionTowerClockHandBlock extends Block {

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final EnumProperty<ClockHandDirection> CLOCK_HAND = EnumProperty.create("clock", ClockHandDirection.class);

    public MansionTowerClockHandBlock() {
        super(Properties.from(Blocks.IRON_BLOCK));
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBlockHarvested(worldIn, pos, state, player);
        spawnDrops(state, worldIn, pos, null, player, new ItemStack(ItemRegistry.LAEVATEIN.get()));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return super.getStateForPlacement(context);
    }
}
