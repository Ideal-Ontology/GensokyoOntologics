package github.thelawf.gensokyoontology.api.client.xmmui;


import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import org.jetbrains.annotations.Nullable;

public abstract class XMMUIContainer extends Container {
    protected XMMUIContainer(@Nullable ContainerType<?> type, int id) {
        super(type, id);
    }
}
