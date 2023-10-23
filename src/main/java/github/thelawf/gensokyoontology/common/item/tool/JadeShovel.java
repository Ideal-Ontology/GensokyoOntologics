package github.thelawf.gensokyoontology.common.item.tool;

import github.thelawf.gensokyoontology.common.item.touhou.GSKOItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;

public class JadeShovel extends ShovelItem {
    public JadeShovel(Properties properties) {
        super(GSKOItemTier.JADE, 5f, 1.6f, properties);
    }
}
