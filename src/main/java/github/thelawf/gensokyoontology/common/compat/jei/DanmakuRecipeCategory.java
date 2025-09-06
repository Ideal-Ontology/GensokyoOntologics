package github.thelawf.gensokyoontology.common.compat.jei;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.gui.screen.DanmakuCraftingScreen;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import github.thelawf.gensokyoontology.data.recipe.DanmakuRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICraftingCategoryExtension;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICustomCraftingCategoryExtension;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Size2i;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DanmakuRecipeCategory implements IRecipeCategory<DanmakuRecipe> {

    public static final ResourceLocation UID = new ResourceLocation(GensokyoOntology.MODID, "danmaku_craft");
    public static final ResourceLocation TEXTURE = DanmakuCraftingScreen.DANMAKU_CRAFTING_TEXTURE;

    private final IDrawable background;
    private final IDrawable icon;

    private static final int craftOutputSlot = 0;
    private static final int craftInputSlot1 = 1;

    public static final int width = 116;
    public static final int height = 54;
    public static final List<IDrawableStatic> PATTERNS = new ArrayList<>();

    // private final ITextComponent localizedName;
    // private final ICraftingGridHelper craftingGridHelper;

    public DanmakuRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 217, 211);
        this.icon = helper.createDrawableIngredient(new ItemStack(BlockRegistry.DANMAKU_TABLE.get()));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                PATTERNS.add(helper.createDrawable(TEXTURE, width + j * width + i * width, height, 16, 16));
            }
        }
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
    public void draw(DanmakuRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, matrixStack, mouseX, mouseY);
        recipe.getJigsawPattern().forEach(block -> {
            DanmakuCraftingScreen.BLOCK_UV_OFFS.get(block);
        });
        PATTERNS.forEach(drawable -> drawable.draw(matrixStack));
    }

    @Override
    @NotNull
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setIngredients(DanmakuRecipe recipe, IIngredients ingredients) {
        ingredients.setInputIngredients(recipe.getIngredients());
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayout recipeLayout, DanmakuRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        guiItemStacks.init(craftOutputSlot, true, 94, 18);
        guiItemStacks.set(ingredients);
    }
}
