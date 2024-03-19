package github.thelawf.gensokyoontology.common.container.script;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.core.init.ContainerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

// player inventory: 20, 96
// left slot: 20, 20
// right slot: 110, 20
// out put slot: 164, 54
public class BinaryOperationContainer extends ScriptBuilderContainer{
    public static final ITextComponent NAME = new TranslationTextComponent("container." +
            GensokyoOntology.MODID + ".binary_operation.title");
    public final IInventory operationSlots = new Inventory(3);
    public BinaryOperationContainer(int id, PlayerInventory playerInventory) {
        super(ContainerRegistry.BINARY_OPERATION_CONTAINER.get(), playerInventory, id);
        addSlot(this.addInputSlot(this.operationSlots, 0, 21, 21));
        addSlot(this.addInputSlot(this.operationSlots, 1, 111, 21));
        addSlot(this.addInputSlot(this.operationSlots, 2, 165, 21));

        this.addPlayerInventorySlots(21, 97);
    }

    @Override
    public void onContainerClosed(@NotNull PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        this.clearContainer(playerIn, playerIn.world, this.operationSlots);
    }

    private Slot addInputSlot(IInventory inventory, int index, int x, int y) {
        return new Slot(inventory, index, x, y) {
            @Override
            public void onSlotChanged() {
                super.onSlotChanged();
            }

            @Override
            @NotNull
            public ItemStack onTake(@NotNull PlayerEntity thePlayer, @NotNull ItemStack stack) {
                return super.onTake(thePlayer, stack);
            }
        };
    }

    @Override
    public boolean canInteractWith(@NotNull PlayerEntity playerIn) {
        return true;
    }

    public LazyOptional<IItemHandler> createCap() {
        return LazyOptional.empty();
    }

    public static INamedContainerProvider create() {
        return new INamedContainerProvider() {
            @Override
            @NotNull
            public ITextComponent getDisplayName() {
                return NAME;
            }
            @NotNull
            @Override
            public Container createMenu(int windowId, @NotNull PlayerInventory playerInventory, @NotNull PlayerEntity p_createMenu_3_) {
                return new BinaryOperationContainer(windowId, playerInventory);
            }
        };
    }
}
