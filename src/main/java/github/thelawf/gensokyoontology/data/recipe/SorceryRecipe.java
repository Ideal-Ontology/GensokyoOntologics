package github.thelawf.gensokyoontology.data.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import github.thelawf.gensokyoontology.core.RecipeRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class SorceryRecipe implements ISorceryRecipe{

    private final ResourceLocation id;
    private final ItemStack output;

    private final NonNullList<Ingredient> ingredients;

    public SorceryRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> ingredients) {
        this.id = id;
        this.output = output;
        this.ingredients = ingredients;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return false;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return this.output;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    public NonNullList<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RecipeRegistry.SORCERY_SERIALIZER.get();
    }

    public static class SorceryRecipeType implements IRecipeType<SorceryRecipe>{
        @Override
        public String toString() {
            return SorceryRecipe.RECIPE_ID.toString();
        }
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<SorceryRecipe> {

        @Override
        public SorceryRecipe read(ResourceLocation recipeId, JsonObject json) {
            ItemStack output = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "output"));
            JsonArray ingredients = JSONUtils.getJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(4, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.deserialize(ingredients.get(i)));
            }

            return new SorceryRecipe(recipeId, output, inputs);
        }

        @Nullable
        @Override
        public SorceryRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(4, Ingredient.EMPTY);

            inputs.replaceAll(ignored -> Ingredient.read(buffer));

            ItemStack output = buffer.readItemStack();
            return new SorceryRecipe(recipeId, output, inputs);
        }

        @Override
        public void write(PacketBuffer buffer, SorceryRecipe recipe) {
            buffer.writeVarInt(recipe.ingredients.size());
            for (Ingredient ingredient : recipe.ingredients) {
                ingredient.write(buffer);
            }
            buffer.writeItemStack(recipe.getRecipeOutput(), false);
        }
    }
}
