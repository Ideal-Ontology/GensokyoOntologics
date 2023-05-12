package github.thelawf.gensokyoontology.common.item.food;

import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

/**
 * 食物：烤八目鳗，来自《东方夜雀食堂》
 * <br>
 * 原作者 @KK-二色幽紫蝶 @ZUN/Team ShanHaiAlice
 */
public class YattsumeUnaYaki extends Item {
    private static final Food food = (new Food.Builder())
            .saturation(4)
            .hunger(4)
            .setAlwaysEdible()
            .build();
    public YattsumeUnaYaki() {
        super(new Properties().group(GSKOItemTab.GSKO_ITEM_TAB).food(food));
    }
}
