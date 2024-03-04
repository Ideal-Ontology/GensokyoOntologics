package github.thelawf.gensokyoontology.data.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import github.thelawf.gensokyoontology.core.RecipeRegistry;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SorceryExtractorRecipe implements ISorceryExtractorRecipe {

    private final ResourceLocation id;
    private final ItemStack output;

    private final NonNullList<Ingredient> inputs;

    public SorceryExtractorRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> inputs) {
        this.id = id;

        this.output = output;
        this.inputs = inputs;
    }

    @Override
    public boolean matches(@NotNull IInventory inv, @NotNull World worldIn) {
        return this.inputs.get(0).test(inv.getStackInSlot(0)) && this.inputs.get(1).test(inv.getStackInSlot(1)) &&
                this.inputs.get(2).test(inv.getStackInSlot(2)) && this.inputs.get(3).test(inv.getStackInSlot(3));
    }

    @Override
    @NotNull
    public ItemStack getCraftingResult(@NotNull IInventory inv) {
        return this.output;
    }

    @Override
    @NotNull
    public ItemStack getRecipeOutput() {
        return this.output.copy();
    }

    @Override
    @NotNull
    public ResourceLocation getId() {
        return this.id;
    }

    @NotNull
    public NonNullList<Ingredient> getIngredients() {
        return this.inputs;
    }

    @Override
    @NotNull
    public IRecipeSerializer<?> getSerializer() {
        return RecipeRegistry.SORCERY_SERIALIZER.get();
    }

    public static class SorceryRecipeType implements IRecipeType<SorceryExtractorRecipe> {
        @Override
        public String toString() {
            return SorceryExtractorRecipe.RECIPE_ID.toString();
        }
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<SorceryExtractorRecipe> {

        @Override
        public SorceryExtractorRecipe read(ResourceLocation recipeId, JsonObject json) {
            ItemStack output = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "output"));
            JsonArray ingredients = JSONUtils.getJsonArray(json, "ingredients");

            NonNullList<Ingredient> inputs = NonNullList.create();

            // sonElement up = ingredients.get(0);
            // sonElement left = ingredients.get(1);
            // sonElement right = ingredients.get(2);
            // sonElement down = ingredients.get(3);

            // temStack upStack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(up, "up"));
            // temStack leftStack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(left, "left"));
            // temStack rightStack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(right, "right"));
            // temStack downStack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(down, "down"));

            for (int i = 0; i < ingredients.size(); i++) {
                inputs.add(Ingredient.deserialize(ingredients.get(i)));
            }

            return new SorceryExtractorRecipe(recipeId, output, inputs);
        }

        @Nullable
        @Override
        public SorceryExtractorRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(4, Ingredient.EMPTY);

            inputs.replaceAll(ignored -> Ingredient.read(buffer));

            ItemStack output = buffer.readItemStack();
            return new SorceryExtractorRecipe(recipeId, output, inputs);
        }

        @Override
        public void write(PacketBuffer buffer, SorceryExtractorRecipe recipe) {
            buffer.writeVarInt(recipe.inputs.size());
            for (Ingredient ingredient : recipe.inputs) {
                ingredient.write(buffer);
            }
            buffer.writeItemStack(recipe.getRecipeOutput(), false);
        }
    }

}
