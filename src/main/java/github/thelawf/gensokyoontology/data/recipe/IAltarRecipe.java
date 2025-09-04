package github.thelawf.gensokyoontology.data.recipe;

import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public interface IAltarRecipe extends IJigsawRecipe{
    ResourceLocation RECIPE_ID = new ResourceLocation("altar");

    NonNullList<Ingredient> getOfferings();
    Item getCenterMaterial();
}
