package github.thelawf.gensokyoontology.client.gui.container;

import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.common.util.GSKOGUIUtil;
import github.thelawf.gensokyoontology.core.init.ContainerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 各个槽位的位置：<br>
 * 上方槽位：(98, 11)<br>
 * 左边槽位：(53, 55)<br>
 * 【合成结果槽位】中间槽位：(98, 55)<br>
 * 右边槽位：(143, 48)<br>
 * 下方槽位：(98, 100)<br>
 */
public class SorceryExtractorContainer extends Container {
    private final PlayerEntity player;
    private final IItemHandler playerInventory;

    public static final Logger LOGGER = LogManager.getLogger();
    public static final List<List<ItemStack>> RECIPES = GSKOGUIUtil.makeRecipes();

    private final IWorldPosCallable POS_CALLABLE = IWorldPosCallable.DUMMY;

    private final IInventory inventory = new Inventory(5);
    public SorceryExtractorContainer(int id, PlayerInventory playerInventory, PlayerEntity player) {
        super(ContainerRegistry.SORCERY_EXTRACTOR_CONTAINER.get(), id);
        this.player = player;
        this.playerInventory = new InvWrapper(playerInventory);

        addPlayerInventorySlots(28, 128);

        // 顺序是上-左-右-下
        addInventorySlot(this.inventory, 0, 99, 12);
        addInventorySlot(this.inventory, 1, 54, 56);
        addInventorySlot(this.inventory, 2, 145, 56);
        addInventorySlot(this.inventory, 3, 99, 101);
        addInventorySlot(this.inventory, 4, 99, 56);

    }

    @Override
    public void onContainerClosed(@NotNull PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        this.clearContainer(playerIn, playerIn.world, this.inventory);
    }

    @Override
    public boolean canInteractWith(@NotNull PlayerEntity playerIn) {
        return true;
    }

    @Override
    public void onCraftMatrixChanged(IInventory inventoryIn) {
        super.onCraftMatrixChanged(inventoryIn);

    }

    private void addInventorySlot(IInventory inventory, int index, int xPos, int yPos) {
        addSlot(new Slot(inventory, index, xPos, yPos));
    }

    private Pair<IInventory, Integer> getMinStack(IInventory inventory) {
        List<Integer> stackSize = new ArrayList<>();
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            stackSize.add(inventory.getStackInSlot(i).getCount());
        }
        return Pair.of(inventory, Collections.min(stackSize));
    }

    private void addResultSlot(IInventory inventory, int index, int xPos, int yPos) {
        addSlot(new Slot(inventory, index, xPos, yPos));
    }

    private boolean matches(IInventory inventory, List<ItemStack> recipes) {

        int totalCount = 0;
        int matchCount = 0;
        Logger logger = LogManager.getLogger();

        for (int i = 0; i < 4; i++) {
            if (!recipes.get(i).getItem().equals(ItemStack.EMPTY.getItem())) {
                totalCount++;
            }

        }
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            if (!inventory.getStackInSlot(i).getItem().equals(ItemStack.EMPTY.getItem()) &&
                    inventory.getStackInSlot(i).getItem().equals(recipes.get(i).getItem())) {
                matchCount++;
            }
        }
        return totalCount == matchCount;
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }

        return index;
    }

    private void addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int verAmount,int dx, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
    }

    private void addPlayerInventorySlots(int xStart, int yStart) {
        addSlotBox(playerInventory, 9, xStart, yStart, 9, 3, 18, 18);

        yStart += 58;
        addSlotRange(playerInventory, 0, xStart, yStart, 9, 18);
    }

    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int INGREDIENT_SLOT_COUNT = 4;  // must match TileEntityInventoryBasic.NUMBER_OF_SLOTS
    private static final int RESULT_SLOT_COUNT = 1;

    @Override
    @NotNull
    public ItemStack transferStackInSlot(@NotNull PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index == 0) {

                slot.onSlotChange(itemstack1, itemstack);
            } else if (index >= 10 && index < 42) {
                if (!this.mergeItemStack(itemstack1, 1, 10, false)) {
                    if (!this.mergeItemStack(itemstack1, 10, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);
            if (index == 0) {
                playerIn.dropItem(itemstack2, false);
            }
        }

        return itemstack;
    }
}

