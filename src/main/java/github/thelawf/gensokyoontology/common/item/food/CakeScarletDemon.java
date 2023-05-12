package github.thelawf.gensokyoontology.common.item.food;

import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class CakeScarletDemon extends Item {
    private static final Food food = (new Food.Builder())
            .saturation(8)
            .hunger(10)
            .setAlwaysEdible()
            .effect(() -> new EffectInstance(Effects.HEALTH_BOOST,5*100,2),0.5f)
            .build();
    public CakeScarletDemon() {
        super(new Properties().group(GSKOItemTab.GSKO_ITEM_TAB).food(food));
    }
}
