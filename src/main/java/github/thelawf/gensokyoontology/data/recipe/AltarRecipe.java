package github.thelawf.gensokyoontology.data.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.core.RecipeRegistry;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
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
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

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

    /**
     * 该合成配方的格式同样为拼图合成格式，（以 sc_mobius_ring.json 为例）：<br>
     * <code>
     *     {<br>
     *         "type": "gensokyoontology:altar",<br>
     *         "power": 1.0,<br>
     *         "offerings": [<br>
     *           {<br>
     *              "item": "gensokyoontology:small_shot_red",<br>
     *              "count": 1,<br>
     *              "tag":{<br>
     *                "Enchantments": [<br>
     *                  {<br>
     *                    "id": "gensokyoontology:infinite_danmaku",<br>
     *                    "lvl": 1<br>
     *                  }<br>
     *               ]<br>
     *             }<br>
     *           },<br>
     *            {<br>
     *              "item": "gensokyoontology:small_shot_orange",<br>
     *              "count": 1,<br>
     *              "tag":{<br>
     *                "Enchantments": [<br>
     *                  {<br>
     *                    "id": "gensokyoontology:infinite_danmaku",<br>
     *                    "lvl": 1<br>
     *                  }<br>
     *               ]<br>
     *             }<br>
     *           }, <br>
     *           {<br>
     *               "item": "gensokyoontology:small_shot_yellow",<br>
     *               "count": 1,<br>
     *               "tag":{<br>
     *                 "Enchantments": [<br>
     *                   {<br>
     *                     "id": "gensokyoontology:infinite_danmaku",<br>
     *                     "lvl": 1<br>
     *                   }<br>
     *                ]<br>
     *              }<br>
     *            },<br>
     *           {<br>
     *               "item": "gensokyoontology:small_shot_green",<br>
     *               "count": 1,<br>
     *               "tag":{<br>
     *                 "Enchantments": [<br>
     *                   {<br>
     *                     "id": "gensokyoontology:infinite_danmaku",<br>
     *                     "lvl": 1<br>
     *                   }<br>
     *                ]<br>
     *              }<br>
     *            },<br>
     *           {<br>
     *               "item": "gensokyoontology:small_shot_aqua",<br>
     *               "count": 1,<br>
     *               "tag":{<br>
     *                 "Enchantments": [<br>
     *                   {<br>
     *                     "id": "gensokyoontology:infinite_danmaku",<br>
     *                     "lvl": 1<br>
     *                   }<br>
     *                ]<br>
     *              }<br>
     *            },<br>
     *           {<br>
     *               "item": "gensokyoontology:small_shot_blue",<br>
     *               "count": 1,<br>
     *               "tag":{<br>
     *                 "Enchantments": [<br>
     *                   {<br>
     *                     "id": "gensokyoontology:infinite_danmaku",<br>
     *                     "lvl": 1<br>
     *                   }<br>
     *                ]<br>
     *              }<br>
     *            },<br>
     *           {<br>
     *               "item": "gensokyoontology:small_shot_purple",<br>
     *               "count": 1,<br>
     *               "tag":{<br>
     *                 "Enchantments": [<br>
     *                   {<br>
     *                     "id": "gensokyoontology:infinite_danmaku",<br>
     *                     "lvl": 1<br>
     *                   }<br>
     *                ]<br>
     *              }<br>
     *            },<br>
     *           {<br>
     *               "item": "gensokyoontology:small_shot_magenta",<br>
     *               "count": 1,<br>
     *               "tag":{<br>
     *                 "Enchantments": [<br>
     *                   {<br>
     *                     "id": "gensokyoontology:infinite_danmaku",<br>
     *                     "lvl": 1<br>
     *                   }<br>
     *                ]<br>
     *              }<br>
     *            },<br>
     *         ],<br>
     *         "center_material": "gensokyoontology:spell_card_blank"<br>
     *         "output": {<br>
     *              "item": "gensokyontology:small_shot",<br>
     *              "count": 1<br>
     *         }<br>
     *         "jigsaw_pattern": [<br>
     *             "OXXXO", <br>
     *             "XOOOX" <br>
     *             "OXXXO", <br>
     *             "XOOOX", <br>
     *             "OXXXO", <br>
     *         ],<br>
     *         "key": {<br>
     *         "O": "gensokyoontology:totem_bricks",<br>
     *         "X": "gensokyoontology:hemp_pattern_bricks"<br>
     *         }<br>
     *     }<br>
     * </code>
     * */
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
            NonNullList<Block> jigsawBlocks = NonNullList.withSize(size, BlockRegistry.ALTAR_FLOOR_BLOCK.get());
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
}
