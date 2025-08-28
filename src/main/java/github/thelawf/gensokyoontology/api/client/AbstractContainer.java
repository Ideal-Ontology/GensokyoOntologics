package github.thelawf.gensokyoontology.api.client;

import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractContainer extends Container {
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
}
