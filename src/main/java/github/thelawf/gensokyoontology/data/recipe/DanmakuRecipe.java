package github.thelawf.gensokyoontology.data.recipe;

import com.google.gson.JsonObject;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.core.RecipeRegistry;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DanmakuRecipe extends ShapedRecipe {
    public static final ResourceLocation RECIPE_ID = new ResourceLocation(GensokyoOntology.MODID, "danmaku_craft");
    public DanmakuRecipe(String groupIn, NonNullList<Ingredient> recipeItemsIn, ItemStack recipeOutputIn) {
        super(RECIPE_ID, groupIn, 5, 5, recipeItemsIn, recipeOutputIn);
    }

    public static class Type implements IRecipeType<DanmakuRecipe> {
        @Override
        public String toString() {
            return RECIPE_ID.toString();
        }
    }

    // @Override
    // public boolean matches(CraftingInventory inv, World worldIn) {
    //     return false;
    // }
//
    // @Override
    // public ItemStack getCraftingResult(CraftingInventory inv) {
    //     return null;
    // }
//
    // @Override
    // public boolean canFit(int width, int height) {
    //     return false;
    // }
//
    // @Override
    // public ItemStack getRecipeOutput() {
    //     return null;
    // }
//
    // @Override
    // public ResourceLocation getId() {
    //     return null;
    // }
//
    // @Override
    // public IRecipeSerializer<?> getSerializer() {
    //     return null;
    // }
//
    // public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<DanmakuRecipe> {
    //     @Override
    //     public DanmakuRecipe read(ResourceLocation recipeId, JsonObject json) {
    //         return null;
    //     }
//
    //     @Nullable
    //     @Override
    //     public DanmakuRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
    //         return null;
    //     }
//
    //     @Override
    //     public void write(PacketBuffer buffer, DanmakuRecipe recipe) {
//
    //     }
    // }
}
