package github.thelawf.gensokyoontology.common.item.tool;

import github.thelawf.gensokyoontology.common.item.touhou.GSKOItemTier;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;

public class JadeHoe extends HoeItem {
    public JadeHoe(Properties properties) {
        super(GSKOItemTier.JADE, 4, 1.6f, properties);
    }
}
