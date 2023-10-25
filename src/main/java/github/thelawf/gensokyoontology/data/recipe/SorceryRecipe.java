package github.thelawf.gensokyoontology.data.recipe;

import com.google.common.collect.Lists;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SorceryRecipe implements ISorceryRecipe {

    private final ResourceLocation id;
    private final ItemStack output;
    private final ItemStack up;
    private final ItemStack left;
    private final ItemStack right;
    private final ItemStack down;

    private final NonNullList<Ingredient> inputs;

    public SorceryRecipe(ResourceLocation id, ItemStack output, ItemStack up, ItemStack left, ItemStack right, ItemStack down) {
        this.id = id;
        this.up = up;
        this.left = left;
        this.right = right;
        this.down = down;

        this.output = output;
        inputs = NonNullList.withSize(4, Ingredient.fromItems(up.getItem(), left.getItem(), right.getItem(), down.getItem()));
    }

    @Override
    public boolean matches(@NotNull IInventory inv, @NotNull World worldIn) {
        return false;
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
        return NonNullList.withSize(4, Ingredient.fromItems(up.getItem(), left.getItem(), right.getItem(), down.getItem()));
    }

    @Override
    @NotNull
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

            ItemStack upStack = JSONUtils.getItem(json, "up").getDefaultInstance();
            ItemStack leftStack = JSONUtils.getItem(json, "left").getDefaultInstance();
            ItemStack rightStack = JSONUtils.getItem(json, "right").getDefaultInstance();
            ItemStack downStack = JSONUtils.getItem(json, "down").getDefaultInstance();

            return new SorceryRecipe(recipeId, output, upStack, leftStack, rightStack, downStack);
        }

        @Nullable
        @Override
        public SorceryRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(4, Ingredient.EMPTY);

            inputs.replaceAll(ignored -> Ingredient.read(buffer));

            ItemStack output = buffer.readItemStack();
            return new SorceryRecipe(recipeId, output,
                    Ingredient.read(buffer).getMatchingStacks()[0],
                    Ingredient.read(buffer).getMatchingStacks()[1],
                    Ingredient.read(buffer).getMatchingStacks()[2],
                    Ingredient.read(buffer).getMatchingStacks()[3]);
        }

        @Override
        public void write(PacketBuffer buffer, SorceryRecipe recipe) {
            buffer.writeVarInt(recipe.inputs.size());
            for (Ingredient ingredient : recipe.inputs) {
                ingredient.write(buffer);
            }
            buffer.writeItemStack(recipe.getRecipeOutput(), false);
        }
    }
}
