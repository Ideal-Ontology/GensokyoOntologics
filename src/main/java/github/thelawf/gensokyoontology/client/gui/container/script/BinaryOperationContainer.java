package github.thelawf.gensokyoontology.client.gui.container.script;

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
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// player inventory: 20, 96
// left slot: 20, 20
// right slot: 110, 20
// out put slot: 164, 54
public class BinaryOperationContainer extends ScriptBuilderContainer{
    public static final ITextComponent NAME = new TranslationTextComponent("container." +
            GensokyoOntology.MODID + ".binary_operation.title");
    public final IInventory operationSlots = new Inventory(3);
    public final IItemHandler playerInventory;
    public BinaryOperationContainer(int id, PlayerInventory playerInventory) {
        super(ContainerRegistry.BINARY_OPERATION_CONTAINER.get(), id);
        addSlot(this.addInputSlot(this.operationSlots, 0, 20, 20));
        addSlot(this.addInputSlot(this.operationSlots, 1, 110, 20));
        this.playerInventory = new InvWrapper(playerInventory);
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

    public static INamedContainerProvider create() {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return NAME;
            }

            @Nullable
            @Override
            public Container createMenu(int windowId, @NotNull PlayerInventory playerInventory, @NotNull PlayerEntity p_createMenu_3_) {
                return new BinaryOperationContainer(windowId, playerInventory);
            }
        };
    }
}
