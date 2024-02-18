package github.thelawf.gensokyoontology.common.item.tool;

import github.thelawf.gensokyoontology.common.item.touhou.GSKOItemTier;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;

public class JadePickaxe extends PickaxeItem {
    public JadePickaxe(Properties properties) {
        super(GSKOItemTier.JADE, 6, 1.6f, properties);
    }
}
