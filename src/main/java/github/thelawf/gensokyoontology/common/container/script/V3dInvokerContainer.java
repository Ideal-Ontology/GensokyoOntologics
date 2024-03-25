package github.thelawf.gensokyoontology.common.container.script;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.item.script.DynamicScriptItem;
import github.thelawf.gensokyoontology.common.item.script.ScriptBuilderItem;
import github.thelawf.gensokyoontology.common.item.script.ScriptReadOnlyItem;
import github.thelawf.gensokyoontology.core.init.ContainerRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
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
import org.jetbrains.annotations.NotNull;

// 31, 21
// 140, 21
public class V3dInvokerContainer extends FunctionInvokerContainer {
    public final IInventory inventory = new Inventory(3);
    public static final ITextComponent NAME = new TranslationTextComponent("container." +
            GensokyoOntology.MODID + ".v3d_invoker.title");
    public V3dInvokerContainer(int id, PlayerInventory playerInventory) {
        super(ContainerRegistry.V3D_INVOKER_CONTAINER.get(), playerInventory, id);
        this.addPlayerInventorySlots(13, 81);
        this.addSlots(this.inventory, 0, 21, 21);
        this.addSlots(this.inventory, 1, 39, 21);
        this.addSlots(this.inventory, 2, 148, 21);
    }

    private void addSlots(IInventory inventory, int index, int x, int y) {
        this.addSlot(new Slot(inventory, index, x, y){
            @Override
            public boolean isItemValid(@NotNull ItemStack stack) {
                return index == 0 ? stack.getItem() == ItemRegistry .V3D_BUILDER.get() :
                        index == 2 ? stack.getItem() == ItemRegistry.V3D_INVOKER.get() :
                                stack.getItem() instanceof ScriptBuilderItem ||
                                stack.getItem() instanceof DynamicScriptItem ||
                                stack.getItem() instanceof ScriptReadOnlyItem;
            }
        });
    }

    @Override
    public void onContainerClosed(@NotNull PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        this.clearContainer(playerIn, playerIn.world, this.inventory);
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
                return new V3dInvokerContainer(windowId, playerInventory);
            }
        };
    }
}
