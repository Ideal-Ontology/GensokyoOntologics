package github.thelawf.gensokyoontology.data;

import github.thelawf.gensokyoontology.common.capability.entity.VillagerOrder;
import github.thelawf.gensokyoontology.data.tag.GSKOItemTags;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderingManager {

    public static VillagerOrder orderAll(){
        return makeRandomOrder(true, true, true, true);
    }

    public static VillagerOrder orderMainDish(){
        return makeRandomOrder(true, false , false, true);
    }
    public static VillagerOrder orderSnacks(){
        return makeRandomOrder(false, true, true, true);
    }

    public static VillagerOrder orderAfternoonTea(){
        return makeRandomOrder(false, true, false, true);
    }

    public static  VillagerOrder orderAppetizer(){
        return makeRandomOrder(false, false, true, false);
    }

    public static VillagerOrder orderDessert(){
        return makeRandomOrder(false, true, false, false);
    }

    public static VillagerOrder orderDrinks(){
        return makeRandomOrder(false, false, false, true);
    }

    public static VillagerOrder makeCustomOrder(VillagerOrder order, Item food, Tags.IOptionalNamedTag<Item> foodTypeTag){
        if (foodTypeTag.equals(GSKOItemTags.ENTREES)) {
            order.setEntrees(food.getDefaultInstance());
        }
        if (foodTypeTag.equals(GSKOItemTags.DESSERT)) {
            order.setDessert(food.getDefaultInstance());
        }
        if (foodTypeTag.equals(GSKOItemTags.DRINKS)) {
            order.setDrinks(food.getDefaultInstance());
        }
        if (foodTypeTag.equals(GSKOItemTags.APPETIZER)) {
            order.setAppetizer(food.getDefaultInstance());
        }
        return order;
    }

    public static VillagerOrder makeRandomOrder(boolean entreesOrdered, boolean dessertOrdered,
                                                boolean appetizerOrdered, boolean drinksOrdered)
    {
        VillagerOrder order = new VillagerOrder();
        order.setEntrees(setRandomEntrees(entreesOrdered));
        order.setDessert(setRandomDessert(dessertOrdered));
        order.setDrinks(setRandomDrinks(drinksOrdered));
        order.setAppetizer(setRandomAppetizer(appetizerOrdered));
        return order;
    }

    public static ItemStack setRandomEntrees(boolean ordered){
        List<ItemStack> entrees = new ArrayList<>();
        ForgeRegistries.ITEMS.forEach((item) -> {
            if (!item.isIn(GSKOItemTags.ORDERABLE)) return;
            if (!item.isIn(GSKOItemTags.ENTREES)) return;
            entrees.add(new ItemStack(item));
        });

        Random rand = new Random();
        return ordered ? entrees.get(rand.nextInt(entrees.size())) : ItemStack.EMPTY;
    }

    public static ItemStack setRandomDessert(boolean ordered){
        List<ItemStack> desserts = new ArrayList<>();
        ForgeRegistries.ITEMS.forEach((item) -> {
            if (!item.isIn(GSKOItemTags.ORDERABLE)) return;
            if (!item.isIn(GSKOItemTags.DESSERT)) return;
            desserts.add(new ItemStack(item));
        });
        Random rand = new Random();
        return ordered ? desserts.get(rand.nextInt(desserts.size())) : ItemStack.EMPTY;
    }

    public static ItemStack setRandomAppetizer(boolean ordered){
        List<ItemStack> appetizers = new ArrayList<>();
        ForgeRegistries.ITEMS.forEach((item) -> {
            if (!item.isIn(GSKOItemTags.ORDERABLE)) return;
            if (!item.isIn(GSKOItemTags.APPETIZER)) return;
            appetizers.add(new ItemStack(item));
        });
        Random rand = new Random();
        return ordered ? appetizers.get(rand.nextInt(appetizers.size())) : ItemStack.EMPTY;
    }

    public static ItemStack setRandomDrinks(boolean ordered){
        List<ItemStack> drinks = new ArrayList<>();
        ForgeRegistries.ITEMS.forEach((item) -> {
            if (!item.isIn(GSKOItemTags.ORDERABLE)) return;
            if (!item.isIn(GSKOItemTags.DRINKS)) return;
            drinks.add(new ItemStack(item));
        });
        Random rand = new Random();
        return ordered ? drinks.get(rand.nextInt(drinks.size())) : ItemStack.EMPTY;
    }
}
