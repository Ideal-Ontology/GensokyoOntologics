package github.thelawf.gensokyoontology.core.init.itemtab;

import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GSKOBlockTab extends ItemGroup {
    public static final GSKOBlockTab GSKO_BLOCK_TAB = new GSKOBlockTab();
    public GSKOBlockTab() {
        super("gensokyoontology_block");
    }

    @Override
    @NotNull
    public ItemStack createIcon() {
        return new ItemStack(ItemRegistry.DANMAKU_TABLE_ITEM.get());
    }
}
