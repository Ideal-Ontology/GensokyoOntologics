package github.thelawf.gensokyoontology.common.item.tools;

import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.item.IItemTier;
import net.minecraft.item.SwordItem;

public class PraxisSword extends SwordItem {
    public PraxisSword() {
        super(GSKOItemTier.PRAXIS, 483645, -0.01F,
                new Properties().group(GSKOItemTab.GSKO_ITEM_TAB));
    }
}
