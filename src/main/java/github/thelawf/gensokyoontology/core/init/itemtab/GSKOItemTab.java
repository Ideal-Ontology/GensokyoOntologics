package github.thelawf.gensokyoontology.core.init.itemtab;

import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GSKOItemTab extends CreativeModeTab {

    public static final GSKOItemTab GSKO_ITEM_TAB = new GSKOItemTab();

    public GSKOItemTab() {
        super("gensokyoontology_items");
    }

    @Override
    @NotNull
    public ItemStack makeIcon() {
        return new ItemStack(ItemRegistry.SAKURA_LEAVES_ITEM.get());
    }
}
