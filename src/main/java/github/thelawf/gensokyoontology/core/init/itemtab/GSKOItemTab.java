package github.thelawf.gensokyoontology.core.init.itemtab;

import github.thelawf.gensokyoontology.core.init.ItemInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class GSKOItemTab extends ItemGroup {

    public static final GSKOItemTab GSKO_ITEM_TAB = new GSKOItemTab();

    public GSKOItemTab() {
        super("gensokyo_ontology_items");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ItemInit.HOTSPRING_BUCKET.get());
    }
}
