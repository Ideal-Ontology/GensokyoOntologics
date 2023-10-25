package github.thelawf.gensokyoontology.common.compat.jei;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.gui.screen.SorceryExtractorScreen;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import github.thelawf.gensokyoontology.data.recipe.SorceryRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class SorceryRecipeCategory implements IRecipeCategory<SorceryRecipe> {

    public static final ResourceLocation UID = new ResourceLocation(GensokyoOntology.MODID, "sorcery");
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
    public Class<? extends SorceryRecipe> getRecipeClass() {
        return SorceryRecipe.class;
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
    public void setIngredients(SorceryRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayout recipeLayout, @NotNull SorceryRecipe recipe, @NotNull IIngredients ingredients) {
        // recipeLayout.getItemStacks().init(0, ingredients.getInputs());
    }
}
