package github.thelawf.gensokyoontology.common.container;

import com.mojang.datafixers.DataFixUtils;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.client.GSKOGUIUtil;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuUtil;
import github.thelawf.gensokyoontology.core.RecipeRegistry;
import github.thelawf.gensokyoontology.core.init.ContainerRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import github.thelawf.gensokyoontology.data.recipe.DanmakuRecipe;
import github.thelawf.gensokyoontology.data.recipe.GSKORecipeHandler;
import net.minecraft.block.AbstractChestBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.CraftingResultSlot;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 弹幕合成界面UV数据：<br><br>
 * - 玩家背包槽位左上角坐标: (x=27, y=123)<br>
 * - 弹幕之点槽位组合左上角坐标: (x=15, y=20)<br>
 * - 合成结果槽位组合左上角坐标: (x=148, y=40)<br><br>
 * - Minecraft 原版一个槽位的长宽: (w=18, h=18)<br><br>
 * - 弹幕之点槽位数量：5 × 5<br>
 * - 合成结果槽位数量：4 × 4
 */
// js9qg@powerscrews.com
public class DanmakuCraftingContainer extends Container {
    // private final DomainFieldEntity fieldEntity;
    protected final int centerX = 79;
    protected final int centerY = 32;
    private final PlayerEntity player;
    private final TileEntity tileEntity;
    private final PlayerInventory playerInv;
    private final IInventory danmakuInv;

    public static final Logger LOGGER = LogManager.getLogger();


    public DanmakuCraftingContainer(int windowId, PlayerInventory playerInv, BlockPos pos) {
        super(ContainerRegistry.DANMAKU_CRAFTING_CONTAINER.get(), windowId);

        this.player = playerInv.player;
        this.tileEntity = player.world.getTileEntity(pos);
        this.playerInv = player.inventory;
        this.danmakuInv = new Inventory(1);

        if (this.tileEntity != null) {
            this.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(itemHandler -> {
                this.danmakuInv.setInventorySlotContents(0, itemHandler.getStackInSlot(0));
                this.addSlot(new SlotItemHandler(itemHandler, 0, 80, 35));
            });
        }
        this.addPlayerInventorySlots(this.playerInv, 8, 83, 141);
    }

    public Block getJigsawPart(int relativeX, int relativeY){
        if (relativeX < 0 || relativeX > 4 || relativeY < 0 || relativeY > 4) return Blocks.AIR;
        AtomicReference<Block> blockRef = new AtomicReference<>();
        GSKOUtil.getRecipeIf(this.player.world, RecipeRegistry.DANMAKU_RECIPE, this.danmakuInv, recipe -> {
            blockRef.set(recipe.getBlockStates().get(relativeY * 5 + relativeX));
        });
        return blockRef.get() == null ? Blocks.AIR : blockRef.get();
    }

    @Override
    public boolean canInteractWith(@NotNull PlayerEntity playerIn) {
        return true;
    }


    @Override
    public void onCraftMatrixChanged(@NotNull IInventory inventoryIn) {
        super.onCraftMatrixChanged(inventoryIn);
        // this.updateCraftingResult();
    }



    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private void addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int verAmount, int dx, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
    }

    private void addSlotBox(IInventory inventory, int index, int x, int y, int horAmount, int verAmount, int dx, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(inventory, index, x, y, horAmount, dx);
            y += dy;
        }
    }

    private int addSlotRange(IInventory inventory, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new Slot(inventory, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }


    private void addPlayerInventorySlots(PlayerInventory playerInventory, int left, int top, int hotBarTop) {
        for(int k = 0; k < 3; ++k) {
            for(int i = 0; i < 9; ++i) {
                this.addSlot(new Slot(playerInventory, i + k * 9 + 9, left + i * 18, top + k * 18));
            }
        }

        for(int l = 0; l < 9; ++l) {
            this.addSlot(new Slot(playerInventory, l, left + l * 18, hotBarTop));
        }
    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of3D the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int DANMAKU_SLOT_COUNT = 29;  // must match TileEntityInventoryBasic.NUMBER_OF_SLOTS

    @Override
    public @NotNull ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            itemstack = slotStack.copy();

            // 容器槽位
            if (index == 0) {
                if (!this.mergeItemStack(slotStack, 1, 37, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(slotStack, itemstack);
            }
            // 玩家背包槽位
            else if (index < 37) {
                // 检查是否为弹幕射击物品
                if (slotStack.getItem() == ItemRegistry.DANMAKU_SHOT.get()) {
                    if (!this.mergeItemStack(slotStack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                }
                // 主背包区 (1-27)
                else if (index < 28) {
                    if (!this.mergeItemStack(slotStack, 28, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                }
                // 快捷栏 (28-36)
                else if (!this.mergeItemStack(slotStack, 1, 28, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (slotStack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (slotStack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, slotStack);
        }

        return itemstack;
    }


    @Deprecated
    protected void updateCraftingResult() {
        if (!this.player.world.isRemote) {
            World world = this.player.world;
            ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)this.player;

            Optional<DanmakuRecipe> optional = world.getServer().getRecipeManager().getRecipes().stream().flatMap(iRecipe ->  {
                if (iRecipe instanceof DanmakuRecipe) {
                    DanmakuRecipe recipe = (DanmakuRecipe) iRecipe;
                    return DataFixUtils.orElseGet(RecipeRegistry.DANMAKU_RECIPE.matches(recipe, world, this.danmakuInv).map(Stream::of),
                            Stream::empty);
                }
                return null;
            }).findFirst();
            // Optional<DanmakuRecipe> optional = world.getServer().getRecipeManager().getRecipe(RecipeRegistry.DANMAKU_RECIPE, inventory, world);

            GSKOUtil.getRecipeIf(this.player.world, RecipeRegistry.DANMAKU_RECIPE, this.danmakuInv, recipe -> {
                if (recipe.matches(this.danmakuInv, world)) {
                    ItemStack itemstack = recipe.getCraftingResult(this.danmakuInv);
                    // serverplayerentity.connection.sendPacket(new SSetSlotPacket(this.windowId, 0, itemstack));
                }
            });

        }
    }

    /**
     * "__A__"<br>
     * "__A__"<br>
     * "AAAAA"    -> 合成 星弹物品<br>
     * "_A_A_"<br>
     * "A___A"<br>
     * <br><br>
     * "_A_A_"<br>
     * "A_A_A"<br>
     * "A___A"    -> 合成 心弹物品<br>
     * "_A_A_"<br>
     * "__A__"<br>
     * <br><br>
     * "AAAAA"<br>
     * "A___A"<br>
     * "A___A"    -> 合成 大弹物品<br>
     * "A___A"<br>
     * "AAAAA"<br>
     * <br><br>
     * "_A_A_"<br>
     * "A_A_A"<br>
     * "A___A"    -> 合成 心弹物品<br>
     * "_A_A_"<br>
     * "__A__"<br>
     *
     * @param slots 槽位的索引
     * @return 能够合成出物品的槽位的索引集合
     */
    @Deprecated
    private List<Integer> createRecipeIndexes(Integer... slots) {
        List<Integer> list = new ArrayList<>();
        return new ArrayList<>(Arrays.asList(slots));
    }

    @Deprecated
    private boolean matches(IInventory inventoryIn, List<Integer> list) {
        int matchCount = 0;
        for (int i : list) {
            if (inventoryIn.getStackInSlot(i).getItem() == ItemRegistry.DANMAKU_SHOT.get()) {
                matchCount++;
            }
        }
        return matchCount == list.size();
    }

    @Deprecated
    private boolean matches(IInventory inventoryIn, Map<Integer, ItemStack> stackMap) {
        int matchCount = 0;
        int emptyCount = 0;
        List<Object> list = Arrays.asList(stackMap.keySet().toArray());
        for (int i = 0; i < inventoryIn.getSizeInventory(); i++) {
            if (list.get(i).equals(i)) {
                matchCount++;
            } else {
                emptyCount++;
            }
        }
        return matchCount == stackMap.size() && matchCount + emptyCount == inventoryIn.getSizeInventory();
    }

    @Deprecated
    private boolean isResultMatrixChange(IInventory inventory) {
        for (Slot inventorySlot : this.inventorySlots) {
            return inventorySlot.getHasStack();
        }
        return false;
    }
}
