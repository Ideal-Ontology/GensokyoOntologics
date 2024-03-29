package github.thelawf.gensokyoontology.common.item.food;

import github.thelawf.gensokyoontology.common.nbt.GensokyoOntologyNBT;
import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;

public class WhiteSnow extends Item {
    private static final Food food = new Food.Builder()
            .saturation(10)
            .hunger(7)
            .setAlwaysEdible()
            .build();

    public WhiteSnow() {
        super(new Properties().group(GSKOItemTab.GSKO_ITEM_TAB).containerItem(Items.BOWL).food(food));
        this.updateItemStackNBT(GensokyoOntologyNBT.nbtWhiteSnow);
    }
}
