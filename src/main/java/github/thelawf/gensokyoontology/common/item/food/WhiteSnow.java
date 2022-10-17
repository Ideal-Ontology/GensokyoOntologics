package github.thelawf.gensokyoontology.common.item.food;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class WhiteSnow extends Item {
    private static final Food food = new Food.Builder()
            .saturation(10)
            .hunger(7)
            .setAlwaysEdible()
            .build();

    public WhiteSnow() {
        super(new Properties().group(ItemGroup.FOOD).containerItem(Items.BOWL).food(food));
    }
}
