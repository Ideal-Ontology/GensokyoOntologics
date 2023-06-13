package github.thelawf.gensokyoontology.common.item.danmaku;

import github.thelawf.gensokyoontology.common.libs.danmakulib.DanmakuType;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOCombatTab;
import net.minecraft.item.Item;

public class StarShot extends Item {

    public final DanmakuType type;

    public StarShot(DanmakuType type) {
        super(new Properties().group(GSKOCombatTab.GSKO_COMBAT_TAB));
        this.type = type;
    }
}
