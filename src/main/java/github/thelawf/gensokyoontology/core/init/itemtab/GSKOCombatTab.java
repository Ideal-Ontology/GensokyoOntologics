package github.thelawf.gensokyoontology.core.init.itemtab;

import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class GSKOCombatTab extends ItemGroup {

    public static final GSKOCombatTab GSKO_COMBAT_TAb = new GSKOCombatTab();

    public GSKOCombatTab() {
        super("gensokyo_ontology_combat");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ItemRegistry.DANMAKU_TEST_ITEM.get());
    }
}
