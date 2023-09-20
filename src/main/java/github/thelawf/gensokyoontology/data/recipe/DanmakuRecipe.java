package github.thelawf.gensokyoontology.data.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class DanmakuRecipe extends ShapedRecipe {

    public DanmakuRecipe(ResourceLocation idIn, String groupIn, NonNullList<Ingredient> recipeItemsIn, ItemStack recipeOutputIn) {
        super(idIn, groupIn, 5, 5, recipeItemsIn, recipeOutputIn);
    }

}
