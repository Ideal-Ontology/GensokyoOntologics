package github.thelawf.gensokyoontology.common.container;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.Nullable;

public abstract class WrapPlayerContainer extends Container {
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
}
