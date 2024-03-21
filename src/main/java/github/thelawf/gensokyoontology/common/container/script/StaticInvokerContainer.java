package github.thelawf.gensokyoontology.common.container.script;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import org.jetbrains.annotations.Nullable;

public class StaticInvokerContainer extends FunctionInvokerContainer{
    public StaticInvokerContainer(@Nullable ContainerType<?> type, PlayerInventory playerInventory, int id) {
        super(type, playerInventory, id);
    }
}
