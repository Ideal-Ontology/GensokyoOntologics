package github.thelawf.gensokyoontology.data.recipe;

import com.google.common.collect.Lists;
import github.thelawf.gensokyoontology.core.RecipeRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeManager;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class GSKORecipeHandler {

    private final RecipeManager recipeManager;

    public static GSKORecipeHandler getInstance() {
        return new GSKORecipeHandler();
    }

    public GSKORecipeHandler() {
        ClientWorld world = Objects.requireNonNull(Minecraft.getInstance().world);
        this.recipeManager = world.getRecipeManager();
    }

    public List<ExtractorRecipeWrapper> extractorRecipes() {
        List<SorceryExtractorRecipe> recipeMap = this.recipeManager.getRecipesForType(RecipeRegistry.SORCERY_RECIPE);
        List<ExtractorRecipeWrapper> recipes = Lists.newArrayList();
        SorceryExtractorRecipe recipe;
        ItemStack output;

        for(Iterator<SorceryExtractorRecipe> iterator = recipeMap.iterator(); iterator.hasNext(); recipes.add(
                new ExtractorRecipeWrapper(output, recipe.getUp(), recipe.getLeft(), recipe.getRight(), recipe.getDown()))){
            recipe = iterator.next();
            output = recipe.getRecipeOutput();
        }
        return recipes;
    }
}
