package github.thelawf.gensokyoontology.data.recipe;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.core.RecipeRegistry;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import github.thelawf.gensokyoontology.core.init.TileEntityRegistry;
import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class AltarRecipe implements IAltarRecipe {
    private final NonNullList<Block> blocks;
    private final NonNullList<Ingredient> offerings;
    private final Item centerMaterial;
    private final float powerConsumption;

    private final ItemStack recipeOutput;
    private final ResourceLocation id;

    public AltarRecipe(ResourceLocation id, NonNullList<Block> blocks, NonNullList<Ingredient> offerings, Item centerMaterial, ItemStack recipeOutput, float powerConsumption) {
        this.id = id;
        this.blocks = blocks;
        this.offerings = offerings;
        this.centerMaterial = centerMaterial;
        this.recipeOutput = recipeOutput;
        this.powerConsumption = powerConsumption;
    }

    public static Optional<AltarRecipe> getInstance(ServerWorld world, IInventory inv, BlockPos pos) {
        return world.getRecipeManager().getRecipesForType(RecipeRegistry.ALTAR_RECIPE).stream().filter(
                        recipe -> recipe.matchesIncludePos(world, inv, pos))
                .findFirst();
    }

    @Override
    public boolean matchesIncludePos(ServerWorld world, IInventory inv, BlockPos center) {
        boolean jigsawMatches = IAltarRecipe.super.matchesIncludePos(world, inv, center);
        int blockMatches = 0;
        AtomicInteger itemMatches = new AtomicInteger();
        for (int z = 1; z <= 3; z++) {
            for (int i = 0; i < 8; i++) {
                int x = ONBASHIRA_POS.get(i).getFirst();
                int y = ONBASHIRA_POS.get(i).getSecond();
                if (z == 3){
                    blockMatches += world.getBlockState(center.add(x, y, z)).getBlock() ==
                            BlockRegistry.ONBASHIRA_TOP_BLOCK.get() ? 1 : 0;

                    GSKOUtil.getTileByType(world, center.add(x, y, z), TileEntityRegistry.ONBASHIRA_TILE_ENTITY.get())
                            .ifPresent(onbashira -> itemMatches.addAndGet(this.offerings.stream()
                                    .anyMatch(offer -> offer.test(onbashira.getMaterial())) ? 1 : 0));
                }
                blockMatches += world.getBlockState(center.add(x, y, z)).getBlock() ==
                        BlockRegistry.ONBASHIRA_TOP_BLOCK.get() ? 1 : 0;
            }
        }
        return blockMatches == 24 & jigsawMatches & itemMatches.get() == this.offerings.size();
    }

    @Override
    public int getUnitCount() {
        return 1;
    }

    @Override
    public float getPowerConsumption() {
        return this.powerConsumption;
    }

    @Override
    public NonNullList<Block> getJigsawPattern() {
        return this.blocks;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return true;
    }

    @Override
    public @NotNull ItemStack getCraftingResult(@NotNull IInventory inv) {
        return this.recipeOutput.copy();
    }

    @Override
    public boolean canFit(int width, int height) {
        return false;
    }

    @Override
    public @NotNull ItemStack getRecipeOutput() {
        return this.recipeOutput.copy();
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return this.id;
    }

    @Override
    public @NotNull IRecipeSerializer<?> getSerializer() {
        return RecipeRegistry.ALTAR_SERIALIZER.get();
    }

    @Override
    public @NotNull IRecipeType<?> getType() {
        return RecipeRegistry.ALTAR_RECIPE;
    }

    @Override
    public NonNullList<Ingredient> getOfferings() {
        return this.offerings;
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return this.offerings;
    }

    @Override
    public Item getCenterMaterial() {
        return this.centerMaterial;
    }

    public static class Type implements IRecipeType<AltarRecipe> {
        @Override
        public String toString() {
            return RECIPE_ID.toString();
        }
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<AltarRecipe> {

        @Override
        public AltarRecipe read(ResourceLocation recipeId, JsonObject json) {

            float jsonFloat = JSONUtils.getFloat(json, "power");
            Item centerMaterial = JSONUtils.getItem(json, "center_material");
            ItemStack output = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "output"), true);
            Map<String, Block> map = DanmakuRecipe.deserialize(JSONUtils.getJsonObject(json, "key"));

            JsonArray jigsawPattern = JSONUtils.getJsonArray(json, "jigsaw_pattern");
            NonNullList<Block> jigsawBlocks = DanmakuRecipe.mapStrPatternToBlock(map, jigsawPattern);
            NonNullList<Ingredient> ingredients = NonNullList.create();

            for (JsonElement jsonElement : JSONUtils.getJsonArray(json, "offerings")) {
                Ingredient ingredient = CraftingHelper.getIngredient(jsonElement);
                ingredients.add(ingredient);
            }

            return new AltarRecipe(recipeId, jigsawBlocks, ingredients, centerMaterial, output, jsonFloat);
        }

        @Override
        public @Nullable AltarRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            float power = buffer.readFloat();
            ItemStack output = buffer.readItemStack();
            Item centerMaterial = ForgeRegistries.ITEMS.getValue(new ResourceLocation(buffer.readString()));

            int size = buffer.readVarInt();
            NonNullList<Block> jigsawBlocks = NonNullList.withSize(size, BlockRegistry.TOTEM_BRICKS.get());
            jigsawBlocks.replaceAll(ignored -> GSKOUtil.readBlockData(buffer));

            int i = buffer.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(i, Ingredient.EMPTY);
            for(int j = 0; j < ingredients.size(); ++j) ingredients.set(j, Ingredient.read(buffer));

            return new AltarRecipe(recipeId, jigsawBlocks, ingredients, centerMaterial, output, power);
        }

        @Override
        public void write(PacketBuffer buffer, AltarRecipe recipe) {
            buffer.writeFloat(recipe.powerConsumption);
            buffer.writeItemStack(recipe.recipeOutput);
            buffer.writeString(recipe.centerMaterial.getRegistryName().toString());

            buffer.writeVarInt(recipe.blocks.size());
            for (Block block : recipe.blocks) GSKOUtil.writeBlockData(buffer, null, block);

            buffer.writeVarInt(recipe.offerings.size());
            for (Ingredient ingredient : recipe.offerings) {
                ingredient.write(buffer);
            }
        }
    }

    public static final List<Pair<Integer, Integer>> ONBASHIRA_POS = ImmutableList.of(
              Pair.of(-1, -2), Pair.of(1, -2),
            Pair.of(-2, -1),     Pair.of(2, -1),
            Pair.of(-2,  1),     Pair.of(2,  1),
              Pair.of(-1 , 2), Pair.of(1,  2)
    );
}
