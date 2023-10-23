package github.thelawf.gensokyoontology.common.item.tool;

import github.thelawf.gensokyoontology.common.item.touhou.GSKOItemTier;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;

public class JadePickaxe extends AxeItem {
    public JadePickaxe(Properties properties) {
        super(GSKOItemTier.JADE, 6f, 1.6f, properties);
    }
}
