package github.thelawf.gensokyoontology.common.block;

import github.thelawf.gensokyoontology.common.container.DanmakuCraftingContainer;
import github.thelawf.gensokyoontology.common.container.script.OneSlotContainer;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.SDanmakuTilePacket;
import github.thelawf.gensokyoontology.common.tileentity.DanmakuTabelTileEntity;
import github.thelawf.gensokyoontology.common.tileentity.ITileEntityGetter;
import github.thelawf.gensokyoontology.core.RecipeRegistry;
import github.thelawf.gensokyoontology.core.init.ContainerRegistry;
import github.thelawf.gensokyoontology.core.init.TileEntityRegistry;
import net.minecraft.block.*;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.monster.piglin.PiglinTasks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
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
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        DanmakuTabelTileEntity tile = (DanmakuTabelTileEntity) worldIn.getTileEntity(pos);
        if (tile == null) {return ActionResultType.FAIL;}
        if (worldIn.isRemote) return ActionResultType.SUCCESS;
        if (Screen.hasShiftDown()) {

            GSKONetworking.sendToClientPlayer(new SDanmakuTilePacket(tile.getPower()), player);
            INamedContainerProvider container = this.getContainer(state, worldIn, pos);
            if (container != null) player.openContainer(container);
            return ActionResultType.CONSUME;
        }

        tile.tryCraft(worldIn);
        return ActionResultType.SUCCESS;
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
                    public void addOnlySlot(int index, int x, int y) {
                        this.addSlot(new Slot(this.inv, 0, 79, 30));
                    }

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
