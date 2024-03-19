package github.thelawf.gensokyoontology.common.container;

import github.thelawf.gensokyoontology.common.container.script.ScriptBuilderContainer;
import github.thelawf.gensokyoontology.core.init.ContainerRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SpellCardConsoleContainer extends ScriptBuilderContainer {
    private final TileEntity tileEntity;
    public final IInventory consoleStacks = new Inventory(31);
    public SpellCardConsoleContainer(int id, PlayerEntity player, World world, BlockPos blockPos) {
        super(ContainerRegistry.SPELL_CONSOLE_CONTAINER.get(), player.inventory, id);
        this.tileEntity = world.getTileEntity(blockPos);
        
        this.addPlayerInventorySlots(47, 168);
        if (this.tileEntity != null) {
            this.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(itemHandler -> {
                int index = 0;
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 3; j++) {
                        addSlot(new Slot(consoleStacks, index, (i * 18) + 22, (j * 23) + 25));
                        // addArgsSlot(itemHandler, index, (i * 18) + 22, (j * 23) + 25);
                        index++;
                    }
                }
                addArgsSlot(itemHandler, 30, 224, 71);
            });
        }
    }

    private void addArgsSlot(IItemHandler itemHandler, int index, int xPos, int yPos) {
        addSlot(new SlotItemHandler(itemHandler, index, xPos, yPos));
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    public boolean isAllowedItem(int index) {
        return this.consoleStacks.getStackInSlot(index).getItem() == ItemRegistry.TIME_STAMP.get() ||
                this.consoleStacks.getStackInSlot(index).getItem() == ItemRegistry.V3D_BUILDER.get() ||
                this.consoleStacks.getStackInSlot(index).getItem() == ItemRegistry.CONST_BUILDER.get() ||
                this.consoleStacks.getStackInSlot(index).getItem() == ItemRegistry.BINARY_OPERATION_BUILDER.get();
    }

    public boolean hasAllowedTag(int index) {
        CompoundNBT nbt = this.consoleStacks.getStackInSlot(index).getTag();
        if (nbt != null) {
            return nbt.keySet().contains("type") && nbt.keySet().contains("value") || nbt.keySet().contains("name");
        }
        return false;
    }

    public CompoundNBT getTag(int index) {
        CompoundNBT nbt = this.consoleStacks.getStackInSlot(index).getTag();
        return nbt == null ? new CompoundNBT() : nbt;
    }

    public void setTagInSlot(int slotIndex, CompoundNBT nbt) {
        this.consoleStacks.getStackInSlot(slotIndex).setTag(nbt);
    }

    public ItemStack getOutputStack() {
        return this.consoleStacks.getStackInSlot(this.consoleStacks.getSizeInventory() -1);
    }
}
