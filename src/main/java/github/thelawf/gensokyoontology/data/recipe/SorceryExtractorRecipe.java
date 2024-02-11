package github.thelawf.gensokyoontology.data.recipe;

import com.google.gson.JsonArray;
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
    private final ItemStack up;
    private final ItemStack left;
    private final ItemStack right;
    private final ItemStack down;

    private final NonNullList<Ingredient> inputs;

    public SorceryExtractorRecipe(ResourceLocation id, ItemStack output, ItemStack up, ItemStack left, ItemStack right, ItemStack down) {
        this.id = id;
        this.up = up;
        this.left = left;
        this.right = right;
        this.down = down;
//
        this.output = output;
        this.inputs = NonNullList.withSize(4, Ingredient.fromStacks(this.up, this.left, this.right, this.down));
    }



    @Override
    public boolean matches(@NotNull IInventory inv, @NotNull World worldIn) {
        LogManager.getLogger().info("Match Checking!");
        return this.inputs.get(0).test(this.up) && this.inputs.get(1).test(this.left) &&
                this.inputs.get(2).test(this.right) && this.inputs.get(3).test(this.down);
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

            NonNullList<Ingredient> inputs = NonNullList.withSize(4, Ingredient.EMPTY);

            ItemStack upStack = JSONUtils.getItem(ingredients, "up").getDefaultInstance();
            ItemStack leftStack = JSONUtils.getItem(ingredients, "left").getDefaultInstance();
            ItemStack rightStack = JSONUtils.getItem(ingredients, "right").getDefaultInstance();
            ItemStack downStack = JSONUtils.getItem(ingredients, "down").getDefaultInstance();

            for (int i = 0; i < ingredients.size(); i++) {
                inputs.set(i, Ingredient.deserialize(ingredients.get(i)));
            }

            return new SorceryExtractorRecipe(recipeId, output, upStack, leftStack, rightStack, downStack);
        }

        @Nullable
        @Override
        public SorceryExtractorRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(4, Ingredient.EMPTY);

            inputs.replaceAll(ignored -> Ingredient.read(buffer));
            ItemStack upStack = buffer.readItemStack();
            ItemStack leftStack = buffer.readItemStack();
            ItemStack rightStack = buffer.readItemStack();
            ItemStack downStack = buffer.readItemStack();

            ItemStack output = buffer.readItemStack();
            return new SorceryExtractorRecipe(recipeId, output, upStack, leftStack, rightStack, downStack);
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

    public ItemStack getUp() {
        return this.up.copy();
    }

    public ItemStack getLeft() {
        return this.left.copy();
    }

    public ItemStack getRight() {
        return this.right.copy();
    }

    public ItemStack getDown() {
        return this.down.copy();
    }
}
