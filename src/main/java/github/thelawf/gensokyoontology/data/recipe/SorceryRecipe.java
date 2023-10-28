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
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SorceryRecipe implements ISorceryRecipe {

    private final ResourceLocation id;
    private final ItemStack output;
    // private final ItemStack up;
    // private final ItemStack left;
    // private final ItemStack right;
    // private final ItemStack down;

    private final NonNullList<Ingredient> inputs;

    // public SorceryRecipe(ResourceLocation id, ItemStack output, ItemStack up, ItemStack left, ItemStack right, ItemStack down) {
    //     this.id = id;
    //     this.up = up;
    //     this.left = left;
    //     this.right = right;
    //     this.down = down;
//
    //     this.output = output;
    //     this.inputs = NonNullList.withSize(4, Ingredient.fromItems(up.getItem(), left.getItem(), right.getItem(), down.getItem()));
    // }

    public SorceryRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> inputs) {
        this.id = id;
        this.inputs = inputs;
        this.output = output;
    }

    // @Override
    // public boolean matches(@NotNull IInventory inv, @NotNull World worldIn) {
    //     LogManager.getLogger().info("Match Checking!");
    //     return this.inputs.get(0).test(up) && this.inputs.get(1).test(left) &&
    //             this.inputs.get(2).test(right) && this.inputs.get(3).test(down);
    // }

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

            // ItemStack upStack = JSONUtils.getItem(ingredients, "up").getDefaultInstance();
            // ItemStack leftStack = JSONUtils.getItem(ingredients, "left").getDefaultInstance();
            // ItemStack rightStack = JSONUtils.getItem(ingredients, "right").getDefaultInstance();
            // ItemStack downStack = JSONUtils.getItem(ingredients, "down").getDefaultInstance();

            for (int i = 0; i < ingredients.size(); i++) {
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
            // return new SorceryRecipe(recipeId, output,
            //         Ingredient.read(buffer).getMatchingStacks()[0],
            //         Ingredient.read(buffer).getMatchingStacks()[1],
            //         Ingredient.read(buffer).getMatchingStacks()[2],
            //         Ingredient.read(buffer).getMatchingStacks()[3]);
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
