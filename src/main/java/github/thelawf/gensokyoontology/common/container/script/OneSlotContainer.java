package github.thelawf.gensokyoontology.common.container.script;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.container.WrapPlayerContainer;
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

public class OneSlotContainer extends WrapPlayerContainer {

    public final IInventory inv = new Inventory(1);

    public OneSlotContainer(int id, PlayerInventory playerInventory) {
        super(ContainerRegistry.ONE_SLOT_CONTAINER.get(), playerInventory, id);
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
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    public static INamedContainerProvider create(String title) {
        return new INamedContainerProvider() {
            @Override
            @NotNull
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("gui." + GensokyoOntology.MODID + "." + title +".title");
            }
            @NotNull
            @Override
            public Container createMenu(int windowId, @NotNull PlayerInventory playerInventory, @NotNull PlayerEntity p_createMenu_3_) {
                return new OneSlotContainer(windowId, playerInventory);
            }
        };
    }
}
