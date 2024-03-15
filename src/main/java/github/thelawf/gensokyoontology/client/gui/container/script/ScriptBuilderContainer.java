package github.thelawf.gensokyoontology.client.gui.container.script;

import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import org.jetbrains.annotations.Nullable;

public abstract class ScriptBuilderContainer extends Container {

    protected ScriptBuilderContainer(@Nullable ContainerType<?> type, int id) {
        super(type, id);
    }
}
