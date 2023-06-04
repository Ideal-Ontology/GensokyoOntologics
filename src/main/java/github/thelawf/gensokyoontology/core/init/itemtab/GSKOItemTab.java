package github.thelawf.gensokyoontology.core.init.itemtab;

import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class GSKOItemTab extends ItemGroup {

    public static final GSKOItemTab GSKO_ITEM_TAB = new GSKOItemTab();

    public GSKOItemTab() {
        super("gensokyoontology_items");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ItemRegistry.LYCORIS_RADIATA.get());
    }
}
