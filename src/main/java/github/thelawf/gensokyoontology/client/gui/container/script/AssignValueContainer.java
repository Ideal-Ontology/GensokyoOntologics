package github.thelawf.gensokyoontology.client.gui.container.script;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.ContainerType;
import org.jetbrains.annotations.Nullable;

public class AssignValueContainer extends ScriptBuilderContainer{
    protected AssignValueContainer(@Nullable ContainerType<?> type, int id) {
        super(type, id);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }
}
