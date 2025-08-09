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
import net.minecraftforge.common.crafting.CraftingHelper;
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
        int matches = 0;
        for (int i = 0; i < 4; i++) {
            if (i < this.inputs.size()) {
                if (this.inputs.get(i).test(inv.getStackInSlot(i))) {
                    matches++;
                }
            }
        }
        return matches == this.inputs.size();
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
        @NotNull
        public SorceryExtractorRecipe read(@NotNull ResourceLocation recipeId, @NotNull JsonObject json) {

            ItemStack output = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "output"), true);
            JsonArray ingredients = JSONUtils.getJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.create();

            for (int i = 0; i < ingredients.size(); i++) {
                inputs.add(Ingredient.deserialize(ingredients.get(i)));
            }

            return new SorceryExtractorRecipe(recipeId, output, inputs);
        }

        @Nullable
        @Override
        public SorceryExtractorRecipe read(@NotNull ResourceLocation recipeId, @NotNull PacketBuffer buffer) {
            ItemStack output = buffer.readItemStack();
            int i = buffer.readVarInt();
            NonNullList<Ingredient> inputs = NonNullList.withSize(i, Ingredient.EMPTY);
            inputs.replaceAll(ignored -> Ingredient.read(buffer));
            return new SorceryExtractorRecipe(recipeId, output, inputs);
        }

        @Override
        public void write(PacketBuffer buffer, SorceryExtractorRecipe recipe) {
            buffer.writeItemStack(recipe.getRecipeOutput(), false);
            buffer.writeVarInt(recipe.inputs.size());
            for (Ingredient ingredient : recipe.inputs) {
                ingredient.write(buffer);
            }
        }
    }

}
