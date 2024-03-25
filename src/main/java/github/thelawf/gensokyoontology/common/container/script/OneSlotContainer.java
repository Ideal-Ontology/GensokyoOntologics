package github.thelawf.gensokyoontology.common.container.script;

import github.thelawf.gensokyoontology.common.container.WrapPlayerContainer;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public abstract class OneSlotContainer extends WrapPlayerContainer {

    public final IInventory inv = new Inventory(1);

    public OneSlotContainer(ContainerType<?> type, int id, PlayerInventory playerInventory) {
        super(type, playerInventory, id);
        this.addPlayerInventorySlots(32, 130);
        addSlot(new Slot(this.inv, 0, 176, 59){
            @Override
            public boolean isItemValid(@NotNull ItemStack stack) {
                return stack.getItem() == ItemRegistry.CONST_BUILDER.get() ||
                        stack.getItem() == ItemRegistry.V3D_BUILDER.get() ||
                        stack.getItem() == ItemRegistry.DANMAKU_BUILDER.get();
            }
        });
    }

    @Override
    public void onContainerClosed(PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        this.clearContainer(playerIn, playerIn.world, this.inv);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

}
