package github.thelawf.gensokyoontology.common.util;

import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.List;

public class GSKOGUIUtil {

    public static List<List<ItemStack>> makeRecipes() {
        List<List<ItemStack>> recipes = new ArrayList<>();

        recipes.add(GSKOGUIUtil.createExtractorRecipe(
                new ItemStack(ItemRegistry.ORB_JADE.get()),
                new ItemStack(ItemRegistry.TALES_SCARLET_MIST.get()),
                ItemStack.EMPTY,
                ItemStack.EMPTY,
                new ItemStack(ItemRegistry.SORCERY_SCARLET_MIST.get())));

        recipes.add(GSKOGUIUtil.createExtractorRecipe(
                new ItemStack(Items.ENDER_EYE),
                new ItemStack(ItemRegistry.GITSUNE_TUBE_FULL.get()),
                new ItemStack(ItemRegistry.TALES_OCCULT_BALL.get()),
                ItemStack.EMPTY,
                new ItemStack(ItemRegistry.OCCULT_BALL.get())));
        return recipes;
    }

    public static List<ItemStack> createExtractorRecipe(ItemStack upSlot, ItemStack leftSlot,
                                                        ItemStack rightSlot, ItemStack downSlot, ItemStack result) {
        List<ItemStack> stacks = new ArrayList<>();
        stacks.add(upSlot);
        stacks.add(leftSlot);
        stacks.add(rightSlot);
        stacks.add(downSlot);
        stacks.add(result);
        return stacks;
    }
}
