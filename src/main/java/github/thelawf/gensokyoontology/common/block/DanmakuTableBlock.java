package github.thelawf.gensokyoontology.common.block;

import github.thelawf.gensokyoontology.common.container.DanmakuCraftingContainer;
import github.thelawf.gensokyoontology.common.container.SorceryExtractorContainer;
import github.thelawf.gensokyoontology.common.container.script.OneSlotContainer;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.SDanmakuTilePacket;
import github.thelawf.gensokyoontology.common.network.packet.SJigsawPatternRenderPacket;
import github.thelawf.gensokyoontology.common.tileentity.DanmakuTabelTileEntity;
import github.thelawf.gensokyoontology.common.tileentity.ITileEntityGetter;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.core.RecipeRegistry;
import github.thelawf.gensokyoontology.core.init.ContainerRegistry;
import github.thelawf.gensokyoontology.core.init.TileEntityRegistry;
import github.thelawf.gensokyoontology.data.recipe.DanmakuRecipe;
import net.minecraft.block.*;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.monster.piglin.PiglinTasks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.CallbackI;

public class DanmakuTableBlock extends Block implements ITileEntityGetter<DanmakuTabelTileEntity> {
    public DanmakuTableBlock() {
        super(Properties.from(Blocks.CRAFTING_TABLE).sound(SoundType.WOOD));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    /**
     * 直接右键点击方块打开GUI界面放入弹幕之点，shift+右键点击方块合成 1 个，control+右键点击方块合成当前最大允许数量
     */
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        DanmakuTabelTileEntity tile = (DanmakuTabelTileEntity) worldIn.getTileEntity(pos);
        if (tile == null) {return ActionResultType.FAIL;}
        if (Screen.hasShiftDown()) {
            tile.tryCraft(worldIn, player, false);
            return ActionResultType.CONSUME;
        }

        if (Screen.hasControlDown()) {
            tile.tryCraft(worldIn, player, true);
            return ActionResultType.CONSUME;
        }
        if (player instanceof ServerPlayerEntity) {

            ServerPlayerEntity sender = (ServerPlayerEntity) player;
            NetworkHooks.openGui(sender, createContainer(worldIn, pos), tile.getPos());
            GSKONetworking.sendToClientPlayer(new SJigsawPatternRenderPacket(tile.getJigsawPattern()), player);
            GSKONetworking.sendToClientPlayer(new SDanmakuTilePacket(
                    tile.getPower(),
                    tile.getConsumption().getFirst(),
                    tile.getConsumption().getSecond()), player);

        }

        return ActionResultType.SUCCESS;
    }

    @Override
    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state) {
        super.onPlayerDestroy(worldIn, pos, state);
        GSKOUtil.getTileByType((World) worldIn, pos, TileEntityRegistry.DANMAKU_TABLE_TILE.get()).ifPresent(tile ->
                tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(itemHandler -> {
                    Block.spawnAsEntity((World) worldIn, pos, itemHandler.extractItem(
                            0, itemHandler.getStackInSlot(0).getCount(), false));
        }));
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBlockHarvested(worldIn, pos, state, player);
        this.onPlayerDestroy(worldIn, pos, state);
    }

    @Nullable
    public INamedContainerProvider getContainerOld(BlockState state, World worldIn, BlockPos pos) {
        return new SimpleNamedContainerProvider((id, inventory, player) ->
                new DanmakuCraftingContainer(id, player.inventory, pos), DanmakuTabelTileEntity.CONTAINER_NAME);
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

    public static INamedContainerProvider createContainer(World worldIn, BlockPos posIn) {
        return new INamedContainerProvider() {
            @Override
            @NotNull
            public ITextComponent getDisplayName() {
                return DanmakuTabelTileEntity.CONTAINER_NAME;
            }

            @Override
            public Container createMenu(int windowId, @NotNull PlayerInventory playerInventory, @NotNull PlayerEntity player) {
                return new DanmakuCraftingContainer(windowId, playerInventory, posIn);
            }
        };
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new DanmakuTabelTileEntity();
    }
}
