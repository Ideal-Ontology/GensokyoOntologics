package github.thelawf.gensokyoontology.common.item.danmaku;

import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOCombatTab;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class DanmakuShotItem extends Item {
    public final DanmakuType danmaku;

    public DanmakuShotItem(DanmakuType danmaku) {
        super(new Item.Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB));
        this.danmaku = danmaku;

    }

}
