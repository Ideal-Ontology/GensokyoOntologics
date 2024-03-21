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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.jetbrains.annotations.NotNull;

// 31, 21
// 140, 21
public class V3dInvokerContainer extends FunctionInvokerContainer {
    private final IInventory inventory = new Inventory(2);
    public static final ITextComponent NAME = new TranslationTextComponent("container." +
            GensokyoOntology.MODID + ".v3d_invoker.title");
    public V3dInvokerContainer(int id, PlayerInventory playerInventory) {
        super(ContainerRegistry.V3D_INVOKER_CONTAINER.get(), playerInventory, id);
        this.addPlayerInventorySlots(13, 81);
        this.addSlot(new Slot(this.inventory, 0, 31, 21));
        this.addSlot(new Slot(this.inventory, 1, 140, 21));
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
