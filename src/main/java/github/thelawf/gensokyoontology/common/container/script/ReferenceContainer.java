package github.thelawf.gensokyoontology.common.container.script;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ReferenceContainer extends ScriptBuilderContainer{
    public ReferenceContainer(@Nullable ContainerType<?> type, PlayerInventory playerInventory, int id) {
        super(type, playerInventory, id);
    }

    @Override
    public boolean canInteractWith(@NotNull PlayerEntity playerIn) {
        return true;
    }
}
