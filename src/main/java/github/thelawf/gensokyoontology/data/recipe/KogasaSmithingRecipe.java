package github.thelawf.gensokyoontology.data.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import github.thelawf.gensokyoontology.core.RecipeRegistry;
import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.jetbrains.annotations.Nullable;

public class KogasaSmithingRecipe implements IKogasaSmithingRecipe{

    private final float powerConsumption;
    private final ResourceLocation id;
    private final ItemStack recipeOutput;
    private final NonNullList<Ingredient> input;

    public KogasaSmithingRecipe(ResourceLocation id, NonNullList<Ingredient> ingredients,
                                ItemStack recipeOutputIn, float power) {
        this.id = id;
        this.powerConsumption = power;
        this.recipeOutput = recipeOutputIn;
        this.input = ingredients;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return false;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return this.recipeOutput.copy();
    }

    @Override
    public boolean canFit(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.recipeOutput.copy();
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RecipeRegistry.KOGASA_SMITHING_SERIALIZER.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return RecipeRegistry.KOGASA_SMITHING;
    }

    public static class Type implements IRecipeType<KogasaSmithingRecipe> {
        @Override
        public String toString() {
            return RECIPE_ID.toString();
        }
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<KogasaSmithingRecipe> {

        @Override
        public KogasaSmithingRecipe read(ResourceLocation recipeId, JsonObject json) {
            float powerConsumption = JSONUtils.getFloat(json, "power_consumption");
            ItemStack recipeOutput = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "recipe_output"), true);
            ItemStack smithingItem = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "smithing_item"), true);
            NonNullList<Ingredient> inputs = NonNullList.create();

            for (JsonElement jsonElement : JSONUtils.getJsonArray(json, "inputs")) {
                inputs.add(Ingredient.deserialize(jsonElement.getAsJsonObject()));
            }
            return new KogasaSmithingRecipe(recipeId, inputs, recipeOutput, powerConsumption);
        }

        @Override
        public @Nullable KogasaSmithingRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            float power = buffer.readFloat();
            ItemStack recipeOutput = buffer.readItemStack();
            ItemStack smithingItem = buffer.readItemStack();
            NonNullList<Ingredient> inputs = NonNullList.create();
            for (int i = 0; i < buffer.readVarInt(); i++) {
                inputs.add(Ingredient.read(buffer));
            }
            return new KogasaSmithingRecipe(recipeId, inputs, smithingItem, power);
        }

        @Override
        public void write(PacketBuffer buffer, KogasaSmithingRecipe recipe) {
            buffer.writeFloat(recipe.powerConsumption);
            buffer.writeItemStack(recipe.recipeOutput);

            buffer.writeVarInt(recipe.input.size());
            for (Ingredient ingredient : recipe.input) {
                ingredient.write(buffer);
            }
        }
    }
}
