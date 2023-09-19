package github.thelawf.gensokyoontology.common.compat.jei;

import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.util.ResourceLocation;

public class DanmakuRecipeCategory implements IRecipeCategory {
    @Override
    public ResourceLocation getUid() {
        return null;
    }

    @Override
    public Class getRecipeClass() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public IDrawable getBackground() {
        return null;
    }

    @Override
    public IDrawable getIcon() {
        return null;
    }

    @Override
    public void setIngredients(Object recipe, IIngredients ingredients) {

    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, Object recipe, IIngredients ingredients) {

    }
}
