package github.thelawf.gensokyoontology.common.item.food;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class BugerMeat extends Item {
    private static final Food food = new Food.Builder()
            .saturation(6)
            .hunger(8)
            .meat()
            .setAlwaysEdible()
            .build();

    public BugerMeat() {
        super(new Properties().group(ItemGroup.FOOD).food(food));
    }
}
