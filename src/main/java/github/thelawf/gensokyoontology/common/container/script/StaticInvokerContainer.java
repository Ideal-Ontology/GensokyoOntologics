package github.thelawf.gensokyoontology.common.container.script;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.core.init.ContainerRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StaticInvokerContainer extends FunctionInvokerContainer{
    public final IInventory paramSlots = new Inventory(12);
    public static final ITextComponent NAME = new TranslationTextComponent("container." +
            GensokyoOntology.MODID + ".static_invoker.title");
    public StaticInvokerContainer(int id, PlayerInventory playerInventory) {
        super(ContainerRegistry.STATIC_INVOKER_CONTAINER.get(), playerInventory, id);
        this.addSlotRange(this.paramSlots, 0, 14, 29, 11, 18);
        this.addSlot(new Slot(this.paramSlots, 11, 104, 72){
            @Override
            public boolean isItemValid(@NotNull ItemStack stack) {
                return stack.getItem() == ItemRegistry.STATIC_INVOKER.get();
            }
        });
    }

    @Override
    public void onContainerClosed(@NotNull PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        this.clearContainer(playerIn, playerIn.world, this.paramSlots);
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
                return new StaticInvokerContainer(windowId, playerInventory);
            }
        };
    }

    public ItemStack getOutputStack() {
        return this.paramSlots.getStackInSlot(this.paramSlots.getSizeInventory() - 1);
    }
}
