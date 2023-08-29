package github.thelawf.gensokyoontology.client.gui.container;

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
import java.util.List;

/**
 * 各个槽位的位置：<br>
 * 上方槽位：(71, 4)<br>
 * 左边槽位：(26, 48)<br>
 * 【合成结果槽位】中间槽位：(71, 48)<br>
 * 右边槽位：(116, 48)<br>
 * 下方槽位：(71, 93)<br>
 */
public class SorceryExtractorContainer extends Container {
    private final PlayerEntity player;
    private final IItemHandler playerInventory;

    //public static final Logger LOGGER = LogManager.getLogger();
    public static final List<List<ItemStack>> RECIPES = GSKOGUIUtil.makeRecipes();

    private final IWorldPosCallable POS_CALLABLE = IWorldPosCallable.DUMMY;

    private final Inventory ingredientInventory = new Inventory(5);
    private final Inventory resultInventory = new Inventory(1);
    public SorceryExtractorContainer(int id, PlayerInventory playerInventory, PlayerEntity player) {
        super(ContainerRegistry.SORCERY_EXTRACTOR_CONTAINER.get(), id);
        this.player = player;
        this.playerInventory = new InvWrapper(playerInventory);

        addPlayerInventorySlots(0, 120);

        addSlot(new Slot(this.ingredientInventory, 0, 71, 4));
        addSlot(new Slot(this.ingredientInventory, 1, 26, 48));
        addSlot(new Slot(this.ingredientInventory, 2, 116, 48));
        addSlot(new Slot(this.ingredientInventory, 3, 71, 93));
        addResultSlot(this.resultInventory, 4, 71, 48);

    }

    @Override
    public void onContainerClosed(@NotNull PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        this.clearContainer(playerIn, playerIn.world, this.ingredientInventory);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    @Override
    public void onCraftMatrixChanged(IInventory inventoryIn) {
        super.onCraftMatrixChanged(inventoryIn);


        Logger logger = LogManager.getLogger();
        logger.info(this.resultInventory.getStackInSlot(0));

        for (List<ItemStack> recipe : RECIPES) {
            if (matches(this.ingredientInventory, recipe)) {
                this.resultInventory.setInventorySlotContents(0, recipe.get(recipe.size()-1));
            }
        }
    }


    private void addResultSlot(IInventory inventory, int index, int xPos, int yPos) {
        addSlot(new Slot(inventory, index, xPos, yPos){
            @Override
            public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
                for (int i = 0; i < 5; i++) {
                    SorceryExtractorContainer.this.ingredientInventory.removeStackFromSlot(i);
                }
                detectAndSendChanges();
                SorceryExtractorContainer.this.onCraftMatrixChanged(
                        SorceryExtractorContainer.this.ingredientInventory);
                return super.onTake(thePlayer, stack);
            }
        });
    }

    private boolean matches(IInventory inventory, List<ItemStack> recipes) {

        int totalCount = 0;
        int matchCount = 0;
        //for (int i = 0; i < recipes.size()-1; i++) {
        //    if (recipes.get(i).hasTag()) {
        //
        //    }
        //}
        for (int i = 0; i < 4; i++) {
            if (!recipes.get(i).equals(ItemStack.EMPTY)) {
                totalCount++;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (inventory.getStackInSlot(i).equals(recipes.get(recipes.size()-1))) {
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

    @Override
    @NotNull
    public ItemStack transferStackInSlot(@NotNull PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index == 0) {

                if (!this.mergeItemStack(itemstack1, 10, 46, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (index >= 10 && index < 46) {
                if (!this.mergeItemStack(itemstack1, 1, 10, false)) {
                    if (index < 37) {
                        if (!this.mergeItemStack(itemstack1, 37, 46, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.mergeItemStack(itemstack1, 10, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.mergeItemStack(itemstack1, 10, 46, false)) {
                return ItemStack.EMPTY;
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

