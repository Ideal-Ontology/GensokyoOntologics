package github.thelawf.gensokyoontology.common.item.tool;

import github.thelawf.gensokyoontology.client.gui.screen.RailDashboardScreen;
import github.thelawf.gensokyoontology.common.tileentity.RailTileEntity;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import github.thelawf.gensokyoontology.core.init.TileEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class RailWrench extends Item {
    public RailWrench(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull ActionResultType onItemUse(@NotNull ItemUseContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        PlayerEntity player = context.getPlayer();
        BlockState blockState = world.getBlockState(pos);
        ItemStack wrench = context.getItem();

        if (player == null) return ActionResultType.FAIL;
        if (blockState.getBlock() != BlockRegistry.COASTER_RAIL.get()) return super.onItemUse(context);
        Optional<RailTileEntity> optional = GSKOUtil.getTileByType(world, pos, TileEntityRegistry.RAIL_TILE_ENTITY.get());

        if (!optional.isPresent()) return super.onItemUse(context);
        RailTileEntity railTile = optional.get();

        if (Screen.hasShiftDown() && world.isRemote) {
            new RailDashboardScreen(pos, railTile.getRotation()).open();
            return super.onItemUse(context);
        }
        return this.onClickFirstRail(pos, player, wrench);
    }

    private ActionResultType onClickFirstRail(BlockPos startPos, @NotNull PlayerEntity player,
                                              ItemStack wrench) {
        ItemStack connector = new ItemStack(ItemRegistry.RAIL_CONNECTOR.get());
        CompoundNBT nbt = new CompoundNBT();
        nbt.putLong("startPos", startPos.toLong());
        connector.setTag(nbt);

        wrench.shrink(1);
        player.addItemStackToInventory(connector);
        return ActionResultType.SUCCESS;
    }

    public static ActionResultType onClickNextRail(ItemUseContext targetCtx) {
        World world = targetCtx.getWorld();
        PlayerEntity player = targetCtx.getPlayer();
        BlockState blockState = world.getBlockState(targetCtx.getPos());
        ItemStack connector = targetCtx.getItem();

        if (player == null) return ActionResultType.FAIL;
        if (blockState.getBlock() != BlockRegistry.COASTER_RAIL.get()) return ActionResultType.PASS;
        if (connector.getTag() == null) return ActionResultType.PASS;

        BlockPos startPos = BlockPos.fromLong(connector.getTag().getLong("startPos"));
        Optional<RailTileEntity> optional = GSKOUtil.getTileByType(world, startPos, TileEntityRegistry.RAIL_TILE_ENTITY.get());
        if (!optional.isPresent()) return ActionResultType.PASS;
        RailTileEntity startRail = optional.get();

        startRail.setTargetPos(targetCtx.getPos());
        startRail.setShouldRender(false);

        ItemStack railItem = new ItemStack(ItemRegistry.RAIL_WRENCH.get());
        connector.shrink(1);
        player.addItemStackToInventory(railItem);
        return ActionResultType.SUCCESS;
    }

    public static ActionResultType onRemoveConnection(BlockPos pos, PlayerEntity player, ItemStack connector) {
        return ActionResultType.SUCCESS;
    }
}
