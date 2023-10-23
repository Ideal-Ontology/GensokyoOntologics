package github.thelawf.gensokyoontology.common.item.tool;

import github.thelawf.gensokyoontology.common.item.touhou.GSKOItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;

public class JadeSword extends SwordItem {
    public JadeSword(Properties properties) {
        super(GSKOItemTier.JADE, 7, 1.6f, properties);
    }
}
