package github.thelawf.gensokyoontology.common.compat.jei;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.gui.container.DanmakuCraftingContainer;
import github.thelawf.gensokyoontology.client.gui.screen.DanmakuCraftingScreen;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import github.thelawf.gensokyoontology.data.recipe.DanmakuRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class DanmakuRecipeCategory implements IRecipeCategory<DanmakuRecipe> {

    public static final ResourceLocation UID = new ResourceLocation(GensokyoOntology.MODID, "danmaku");
    public static final ResourceLocation TEXTURE = DanmakuCraftingScreen.DANMAKU_CRAFTING_TEXTURE;

    private final IDrawable background;
    private final IDrawable icon;

    public DanmakuRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 217, 211);
        this.icon = helper.createDrawableIngredient(new ItemStack(BlockRegistry.DANMAKU_TABLE.get()));
    }

    @Override
    @NotNull
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    @NotNull
    public Class<DanmakuRecipe> getRecipeClass() {
        return DanmakuRecipe.class;
    }

    @Override
    @NotNull
    public String getTitle() {
        return BlockRegistry.DANMAKU_TABLE.get().getTranslatedName().getString();
    }

    @Override
    @NotNull
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setIngredients(DanmakuRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, DanmakuRecipe recipe, IIngredients ingredients) {

    }

}
