package github.thelawf.gensokyoontology.common.item.tool;

import github.thelawf.gensokyoontology.common.item.touhou.GSKOItemTier;
import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;

public class JadeAxe extends AxeItem {
    public JadeAxe(Properties properties) {
        super(GSKOItemTier.JADE, 8, 1.6f, properties);
    }
}
