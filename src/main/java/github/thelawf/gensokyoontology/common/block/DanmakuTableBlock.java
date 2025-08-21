package github.thelawf.gensokyoontology.common.block;

import github.thelawf.gensokyoontology.common.container.DanmakuCraftingContainer;
import github.thelawf.gensokyoontology.common.container.script.OneSlotContainer;
import github.thelawf.gensokyoontology.common.tileentity.DanmakuTabelTileEntity;
import github.thelawf.gensokyoontology.common.tileentity.ITileEntityGetter;
import github.thelawf.gensokyoontology.core.init.ContainerRegistry;
import github.thelawf.gensokyoontology.core.init.TileEntityRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DanmakuTableBlock extends Block implements ITileEntityGetter<DanmakuTabelTileEntity> {
    public DanmakuTableBlock() {
        super(Properties.from(Blocks.CRAFTING_TABLE).sound(SoundType.WOOD));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return false;
    }

    @Override
    @NotNull
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(@NotNull BlockState state, World worldIn, @NotNull BlockPos pos, @NotNull PlayerEntity player,
                                             @NotNull Hand handIn, @NotNull BlockRayTraceResult hit) {
        if (Screen.hasShiftDown()) {
            player.openContainer(state.getContainer(worldIn, pos));
            return ActionResultType.CONSUME;
        }
        this.getTileEntity(worldIn, pos).ifPresent(tile -> tile.tryCraft(worldIn));
        return ActionResultType.SUCCESS;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onBlockClicked(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        super.onBlockClicked(state, worldIn, pos, player);
    }

    @Nullable
    @Deprecated
    public INamedContainerProvider getContainerOld(BlockState state, World worldIn, BlockPos pos) {
        return new SimpleNamedContainerProvider((id, inventory, player) ->
                new DanmakuCraftingContainer(id, inventory, IWorldPosCallable.of(worldIn, pos)),
                DanmakuTabelTileEntity.CONTAINER_NAME);
    }

    @Nullable
    @Override
    @SuppressWarnings("deprecation")
    public INamedContainerProvider getContainer(BlockState state, World worldIn, BlockPos pos) {
        return new SimpleNamedContainerProvider((id, inventory, player) ->
                new OneSlotContainer(ContainerRegistry.DANMAKU_CRAFTING_CONTAINER.get(), id, inventory) {
                    @Override
                    public void onContainerClosed(PlayerEntity playerIn) {
                        super.onContainerClosed(playerIn);
                    }
                },
                DanmakuTabelTileEntity.CONTAINER_NAME);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new DanmakuTabelTileEntity();
    }
}
