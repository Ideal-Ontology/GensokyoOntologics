package github.thelawf.gensokyoontology.data.recipe;

import com.google.common.collect.Maps;
import com.google.gson.*;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.core.RecipeRegistry;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class DanmakuRecipe implements IJigsawRecipe {
    public static final ResourceLocation RECIPE_ID = new ResourceLocation(GensokyoOntology.MODID, "danmaku");
    private final NonNullList<Block> blockStates;
    private final ItemStack recipeOutput;
    private final int unitCount;
    private final float powerConsumption;
    private BlockPos center;
    private final ResourceLocation id;

    public DanmakuRecipe(ResourceLocation id, NonNullList<Block> blockStates, int unitCount, float power, ItemStack recipeOutputIn) {
        this.id = id;
        this.powerConsumption = power;
        this.unitCount = unitCount;
        this.blockStates = blockStates;
        this.recipeOutput = recipeOutputIn;
    }

    public static class Type implements IRecipeType<DanmakuRecipe> {
        @Override
        public String toString() {
            return RECIPE_ID.toString();
        }
    }


    @Override
    public boolean matches(IInventory inv, World worldIn) {
        if (this.center == null) return false;
        if (!inv.getStackInSlot(0).equals(new ItemStack(ItemRegistry.DANMAKU_SHOT.get()))) return false;

        BlockPos.Mutable pos = new BlockPos.Mutable(center.getX() -2, center.getY(), center.getZ()-2);
        int matches = 0;
        for (int x = 1; x < 6; x++) {
            for (int z = 1; z < 6; z++) {
                pos.add(x, 0, z);
                if (this.blockStates.get((x * z) - 1).equals(worldIn.getBlockState(pos).getBlock())) matches++;
            }
        }
        return matches == 25;
    }

    @Override
    public int getUnitCount() {
        return this.unitCount;
    }

    @Override
    public float getPowerConsumption(){
        return this.powerConsumption;
    }

    @Override
    public void setCenterPos(BlockPos pos){
        this.center = pos;
    }

    @Override
    public NonNullList<Block> getBlockStates() {
        return this.blockStates;
    }

    @Override
    public BlockPos getCenterPos() {
        return this.center;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return this.recipeOutput;
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return this.recipeOutput.copy();
    }

    @Override
    public ResourceLocation getId() {
        return RECIPE_ID;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return new Serializer();
    }

    @Override
    public IRecipeType<?> getType() {
        return RecipeRegistry.DANMAKU_RECIPE;
    }

    public static Map<String, Block> deserialize(JsonObject json){
        Map<String, Block> map = Maps.newHashMap();

        for(Map.Entry<String, JsonElement> entry : json.entrySet()) {
            if (entry.getKey().length() != 1) {
                throw new JsonSyntaxException("Invalid key entry: '" + entry.getKey() + "' is an invalid symbol (must be 1 character only).");
            }

            if (" ".equals(entry.getKey())) {
                throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
            }

            map.put(entry.getKey(), GSKOUtil.readBlockFromJson(entry.getValue()));
        }

        map.put(" ", Blocks.AIR);
        return map;
    }

    public static NonNullList<Block> mapStrPatternToBlock(Map<String, Block> map, JsonArray jArray){
        String[] pattern = new String[jArray.size()];
        for (int i = 0; i < jArray.size(); i++) pattern[i] = jArray.get(i).getAsString();

        NonNullList<Block> blocks = NonNullList.create();
        for (String s : pattern) blocks.add(map.get(s));
        return blocks;
    }

    public static class Serializer extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>>  implements IRecipeSerializer<DanmakuRecipe> {
        private static final ResourceLocation NAME = GensokyoOntology.withRL("danmaku");

        /**
         * 该合成配方的json格式如下（以 crystal_shot.json 为例）：<br>
         * <code>
         *     {<br>
         *         &ensp "type": "gensokyoontology:danmaku",<br>
         *         &ensp "power": 1.0,<br>
         *         &ensp "unit_count": 10,<br>
         *         &ensp "output": "gensokyontology:crytal_shot"<br>
         *         &ensp "jigsaw_pattern": [<br>
         *         &ensp&ensp "OOXOO", <br>
         *         &ensp&ensp "OXOXO" <br>
         *         &ensp&ensp "XOOOX", <br>
         *         &ensp&ensp "OXOXO", <br>
         *         &ensp&ensp "OOXOO", <br>
         *         ],<br>
         *         "key": {<br>
         *         "O": "gensokyoontology:totem_bricks",<br>
         *         "X": "gensokyoontology:asanoha_bricks"<br>
         *         }<br>
         *     }<br>
         * </code>
         * 即当地砖的图案为：<br>
         *   O O X O O <br>
         *   O X O X O <br>
         *   X O O O X <br>
         *   O X O X O <br>
         *   O O X O O <br>
         * 其中，"O" 为图腾砖，"X" 为麻叶花纹砖的时候，在弹幕工作台中每放置10个弹幕之点可以通过消耗1.0点法力值将这些弹幕之点合成为1个晶弹。
         * */
        public @NotNull DanmakuRecipe read(ResourceLocation recipeId, @NotNull JsonObject json) {

            int jsonInt = JSONUtils.getInt(json, "unit_count");
            float jsonFloat = JSONUtils.getFloat(json, "power");

            ItemStack output = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "output"), true);
            Map<String, Block> map = DanmakuRecipe.deserialize(JSONUtils.getJsonObject(json, "key"));

            JsonArray jigsawPattern = JSONUtils.getJsonArray(json, "jigsaw_pattern");
            NonNullList<Block> jigsawBlocks = DanmakuRecipe.mapStrPatternToBlock(map, jigsawPattern);

            return new DanmakuRecipe(recipeId, jigsawBlocks, jsonInt, jsonFloat, output);
        }

        public DanmakuRecipe read(@NotNull ResourceLocation recipeId, PacketBuffer buffer) {
            int unit = buffer.readVarInt();
            float powerConsume = buffer.readFloat();
            ItemStack output = buffer.readItemStack();

            NonNullList<Block> jigsawBlocks = NonNullList.withSize(25, BlockRegistry.ALTAR_FLOOR_BLOCK.get());
            jigsawBlocks.replaceAll(ignored -> GSKOUtil.readBlockData(buffer));
            return new DanmakuRecipe(recipeId, jigsawBlocks, unit, powerConsume, output);
        }

        public void write(PacketBuffer buffer, DanmakuRecipe recipe) {
            buffer.writeResourceLocation(recipe.id);
            buffer.writeItemStack(recipe.getRecipeOutput(), false);
            buffer.writeInt(recipe.getUnitCount());
            buffer.writeFloat(recipe.getPowerConsumption());
            for (Block blockState : recipe.blockStates) GSKOUtil.writeBlockData(buffer, null, blockState);
        }
    }
}
