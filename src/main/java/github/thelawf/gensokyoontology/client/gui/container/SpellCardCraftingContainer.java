package github.thelawf.gensokyoontology.client.gui.container;

import net.minecraft.client.gui.screen.EditMinecartCommandBlockScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.play.server.STabCompletePacket;
import org.jetbrains.annotations.Nullable;

public class SpellCardCraftingContainer extends Container {
    protected SpellCardCraftingContainer(@Nullable ContainerType<?> type, int id) {
        super(type, id);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

}
