package github.thelawf.gensokyoontology.api.client;

import github.thelawf.gensokyoontology.api.util.IntRange;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractContainer extends Container implements ISlotMergeable {
    protected AbstractContainer(@Nullable ContainerType<?> type, int id) {
        super(type, id);
    }

    protected final void addPlayerInventorySlots(PlayerInventory playerInventory, int left, int top, int hotBarTop) {
        for(int k = 0; k < 3; ++k) {
            for(int i = 0; i < 9; ++i) {
                this.addSlot(new Slot(playerInventory, i + k * 9 + 9, left + i * 18, top + k * 18));
            }
        }

        for(int l = 0; l < 9; ++l) {
            this.addSlot(new Slot(playerInventory, l, left + l * 18, hotBarTop));
        }
    }

    // protected abstract Pair<Integer, Integer> getCraftSlotIndexRange();

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            IntRange customRange = this.getContainerSlotRange();

            if (customRange == null) {
                return ItemStack.EMPTY;
            }

            // 如果点击的是自定义槽位
            if (this.isContainerSlot(index)) {
                // 尝试将物品转移到玩家背包
                if (!this.mergeItemStack(itemstack1, customRange.max() + 1, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }
            // 如果点击的是玩家背包槽位
            else {
                // 尝试将物品转移到自定义槽位
                if (!this.mergeItemStack(itemstack1, customRange.min(), customRange.max() + 1, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }
}
