package github.thelawf.gensokyoontology.common.container.script;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class FunctionInvokerContainer extends ScriptBuilderContainer{
    public FunctionInvokerContainer(@Nullable ContainerType<?> type, PlayerInventory playerInventory, int id) {
        super(type, playerInventory, id);
    }

    @Override
    public boolean canInteractWith(@NotNull PlayerEntity playerIn) {
        return true;
    }
}
