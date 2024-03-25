package github.thelawf.gensokyoontology.common.container.script;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.gui.screen.script.OneSlotContainerScreen;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.core.init.ContainerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DanmakuBuilderContainer extends OneSlotContainer {

    public DanmakuBuilderContainer(int id, PlayerInventory playerInventory) {
        super(ContainerRegistry.DB_CONTAINER.get(), id, playerInventory);
    }

    @Override
    public boolean canInteractWith(@NotNull PlayerEntity playerIn) {
        return true;
    }

    public static INamedContainerProvider create(String title) {
        return new INamedContainerProvider() {
            @Override
            @NotNull
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("container." + GensokyoOntology.MODID + "." + title +".title");
            }
            @NotNull
            @Override
            public Container createMenu(int windowId, @NotNull PlayerInventory playerInventory, @NotNull PlayerEntity p_createMenu_3_) {
                return new DanmakuBuilderContainer(windowId, playerInventory);
            }
        };
    }
}
