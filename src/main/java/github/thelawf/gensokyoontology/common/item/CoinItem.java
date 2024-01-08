package github.thelawf.gensokyoontology.common.item;

import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.item.Item;

//TODO: 把名称改为 “赛钱”
public class CoinItem extends Item {
    public float value;
    public CoinItem(float value) {
        super(new Properties().group(GSKOItemTab.GSKO_ITEM_TAB));
        this.value = value;
    }

    public float getValue() {
        return value;
    }
}
