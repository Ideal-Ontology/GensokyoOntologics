package github.thelawf.gensokyoontology.data.recipe;

import com.google.common.collect.Lists;
import github.thelawf.gensokyoontology.core.RecipeRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.NonNullList;

import java.util.*;
import java.util.stream.Collectors;

public class GSKORecipeHandler {

    private final RecipeManager recipeManager;

    public static GSKORecipeHandler getInstance() {
        return new GSKORecipeHandler();
    }

    public GSKORecipeHandler() {
        ClientWorld world = Objects.requireNonNull(Minecraft.getInstance().world);
        this.recipeManager = world.getRecipeManager();
    }

    /** NonNullList是所有写在 data/modid/recipes 目录下面的配方，而 Ingredient 是每个 json 配方里面的原料。<br>
     * 将 NonNullList 获取到的 Ingredient 内部的 ItemStack 数组展平并映射为流，再把展平后的 ItemStack 流映射为物品数量的 int 流，再转为
     * 列表之后被 {@link Collections#min(Collection) Collections.min}方法获取到最小值。
     *
     */
    public static int getMinForIngredients(NonNullList<Ingredient> ingredients) {
        return Collections.min(ingredients.stream().flatMap(ingredient -> Arrays.stream(
                ingredient.getMatchingStacks()).map(ItemStack::getCount)).collect(Collectors.toList()));
    }
}
