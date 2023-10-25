package github.thelawf.gensokyoontology.common.compat.jei;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.core.RecipeRegistry;
import github.thelawf.gensokyoontology.data.recipe.DanmakuRecipe;
import github.thelawf.gensokyoontology.data.recipe.SorceryRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;
import java.util.stream.Collectors;

@JeiPlugin
public class GSKOPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(GensokyoOntology.MODID, "jei_plugin");
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        IModPlugin.super.registerItemSubtypes(registration);
    }

    @Override
    public void registerIngredients(IModIngredientRegistration registration) {
        IModPlugin.super.registerIngredients(registration);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new DanmakuRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        IModPlugin.super.registerRecipeCatalysts(registration);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager manager = Objects.requireNonNull(Minecraft.getInstance().world).getRecipeManager();
        registration.addRecipes(manager.getRecipesForType(RecipeRegistry.SORCERY_RECIPE).stream()
                .filter(Objects::nonNull).collect(Collectors.toList()),
                SorceryRecipeCategory.UID);

        registration.addRecipes(manager.getRecipesForType(RecipeRegistry.DANMAKU_RECIPE).stream()
                .filter(recipe -> recipe instanceof DanmakuRecipe).collect(Collectors.toList()),
                DanmakuRecipeCategory.UID);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        IModPlugin.super.registerRecipeTransferHandlers(registration);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        IModPlugin.super.registerGuiHandlers(registration);
    }
}
