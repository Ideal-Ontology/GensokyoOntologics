package github.thelawf.gensokyoontology.common.item.food;

import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class TakoYaki extends Item {
    private static final Food food = (new Food.Builder())
            .saturation(7)
            .hunger(5)
            .setAlwaysEdible()
            .build();

    public TakoYaki() {
        super(new Properties().group(GSKOItemTab.GSKO_ITEM_TAB).food(food));
    }
}
