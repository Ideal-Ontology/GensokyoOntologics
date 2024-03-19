package github.thelawf.gensokyoontology.common.container.script;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class UnaryOperationContainer extends Container {
    protected UnaryOperationContainer(@Nullable ContainerType<?> type, int id) {
        super(type, id);
    }

    @Override
    public boolean canInteractWith(@NotNull PlayerEntity playerIn) {
        return true;
    }
}
