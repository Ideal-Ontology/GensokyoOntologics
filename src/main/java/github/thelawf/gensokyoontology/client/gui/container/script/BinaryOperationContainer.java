package github.thelawf.gensokyoontology.client.gui.container.script;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.ContainerType;
import org.jetbrains.annotations.Nullable;

public class BinaryOperationContainer extends ScriptBuilderContainer{
    protected BinaryOperationContainer(@Nullable ContainerType<?> type, int id) {
        super(type, id);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }
}
