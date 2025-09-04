package github.thelawf.gensokyoontology.common.container;

import github.thelawf.gensokyoontology.api.client.AbstractContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class WrapPlayerContainer extends Container{
    protected final IItemHandler playerInventory;
    protected WrapPlayerContainer(@Nullable ContainerType<?> type, PlayerInventory playerInventory, int id) {
        super(type, id);
        this.playerInventory = new InvWrapper(playerInventory);
    }

    protected int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }

        return index;
    }

    protected void addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int verAmount, int dx, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
    }

    protected void addPlayerInventorySlots(int xStart, int yStart) {
        addSlotBox(this.playerInventory, 9, xStart, yStart, 9, 3, 18, 18);
        yStart += 58;
        addSlotRange(this.playerInventory, 0, xStart, yStart, 9, 18);
    }

    @Override
    @NotNull
    public ItemStack transferStackInSlot(@NotNull PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index == 0) slot.onSlotChange(itemstack1, itemstack);
            else if (index >= 10 && index < 42) {
                if (!this.mergeItemStack(itemstack1, 1, 10, true)){
                    if (!this.mergeItemStack(itemstack1, 10, 37, true)) return ItemStack.EMPTY;
                }
            }

            if (itemstack1.isEmpty()) slot.putStack(ItemStack.EMPTY);
            else slot.onSlotChanged();

            if (itemstack1.getCount() == itemstack.getCount()) return ItemStack.EMPTY;
            ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);
            if (index == 0) playerIn.dropItem(itemstack2, false);
        }
        return itemstack;
    }
}
