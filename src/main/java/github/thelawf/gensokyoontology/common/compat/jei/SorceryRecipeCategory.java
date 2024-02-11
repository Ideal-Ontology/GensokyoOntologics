package github.thelawf.gensokyoontology.common.compat.jei;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.gui.screen.SorceryExtractorScreen;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import github.thelawf.gensokyoontology.data.recipe.SorceryExtractorRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class SorceryRecipeCategory implements IRecipeCategory<SorceryExtractorRecipe> {

    public static final ResourceLocation UID = new ResourceLocation(GensokyoOntology.MODID, "sorcery_extract");
    public static final ResourceLocation TEXTURE = SorceryExtractorScreen.SORCERY_GUI_TEXTURE;

    private final IDrawable background;
    private final IDrawable icon;

    public SorceryRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 217, 211);
        this.icon = helper.createDrawableIngredient(new ItemStack(BlockRegistry.SORCERY_EXTRACTOR.get()));
    }

    @Override
    @NotNull
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    @NotNull
    public Class<? extends SorceryExtractorRecipe> getRecipeClass() {
        return SorceryExtractorRecipe.class;
    }

    @Override
    @NotNull
    public String getTitle() {
        return BlockRegistry.SORCERY_EXTRACTOR.get().getTranslatedName().getString();
    }

    @Override
    @NotNull
    public IDrawable getBackground() {
        return background;
    }

    @Override
    @NotNull
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setIngredients(SorceryExtractorRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayout recipeLayout, @NotNull SorceryExtractorRecipe recipe, @NotNull IIngredients ingredients) {
        IGuiItemStackGroup guiStack = recipeLayout.getItemStacks();
        guiStack.init(0, true, 99, 12);
        guiStack.init(1, true, 54, 56);
        guiStack.init(2, true, 145, 56);
        guiStack.init(3, true, 99, 101);
        guiStack.init(4, false, 99, 56);
    }
}
