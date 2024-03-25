package github.thelawf.gensokyoontology.common.container.script;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.core.init.ContainerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.jetbrains.annotations.NotNull;

public class V3DBContainer extends OneSlotContainer{
    public V3DBContainer(int id, PlayerInventory playerInventory) {
        super(ContainerRegistry.V3DB_CONTAINER.get(), id, playerInventory);
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
                return new V3DBContainer(windowId, playerInventory);
            }
        };
    }
}
