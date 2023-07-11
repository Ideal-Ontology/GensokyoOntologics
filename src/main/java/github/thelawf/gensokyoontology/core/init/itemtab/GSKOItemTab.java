package github.thelawf.gensokyoontology.core.init.itemtab;

import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GSKOItemTab extends ItemGroup {

    public static final GSKOItemTab GSKO_ITEM_TAB = new GSKOItemTab();

    public GSKOItemTab() {
        super("gensokyoontology_items");
    }

    @Override
    @NotNull
    public ItemStack createIcon() {
        return new ItemStack(ItemRegistry.MARISA_HAKKEIRO.get());
    }
}
