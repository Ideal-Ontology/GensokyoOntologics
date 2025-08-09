package github.thelawf.gensokyoontology.data.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.core.RecipeRegistry;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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

public class DanmakuRecipe implements IJigsawRecipe {
    public static final ResourceLocation RECIPE_ID = new ResourceLocation(GensokyoOntology.MODID, "danmaku");
    private final NonNullList<Block> blockStates;
    private final ItemStack recipeOutput;
    private BlockPos center;
    private final ResourceLocation id;

    public DanmakuRecipe(ResourceLocation id, NonNullList<Block> blockStates, ItemStack recipeOutputIn) {
        this.id = id;
        this.blockStates = blockStates;
        this.recipeOutput = recipeOutputIn;
    }

    public static class Type implements IRecipeType<DanmakuRecipe> {
        @Override
        public String toString() {
            return RECIPE_ID.toString();
        }
    }

    /**
     *   O X O X O
     *   X O X O X
     *   X O # O X
     *   O X O X O
     *   O O X O O
     * */
    @Override
    public boolean matches(IInventory inv, World worldIn) {
        if (this.center == null) return false;
        if (!inv.getStackInSlot(0).equals(new ItemStack(ItemRegistry.DANMAKU_SHOT.get()))) return false;

        BlockPos.Mutable pos = new BlockPos.Mutable(center.getX() -2, center.getY(), center.getZ()-2);
        int matches = 0;
        for (int x = 0; x < 5; x++) {
            for (int z = 0; z < 5; z++) {
                pos.add(x, 0, z);
                if (this.blockStates.get(x * z).equals(worldIn.getBlockState(pos))) matches++;
            }
        }
        return matches == 25;
    }

    public void setCenterPos(BlockPos pos){
        this.center = pos;
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

    public static class Serializer extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>>  implements IRecipeSerializer<DanmakuRecipe> {
        private static final ResourceLocation NAME = GensokyoOntology.withRL("danmaku");

        public @NotNull DanmakuRecipe read(ResourceLocation recipeId, @NotNull JsonObject json) {

            ItemStack output = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "output"), true);
            JsonArray jigsawPattern = JSONUtils.getJsonArray(json, "jigsaw_pattern");
            NonNullList<Block> inputs = NonNullList.create();

            for (int i = 0; i < jigsawPattern.size(); i++) {
                inputs.add(Ingredient.deserialize());
            }

            return new SorceryExtractorRecipe(recipeId, output, inputs);
            return null;
        }

        public DanmakuRecipe read(@NotNull ResourceLocation recipeId, PacketBuffer buffer) {
            ItemStack output = buffer.readItemStack();
            int i = buffer.readVarInt();
            NonNullList<Block> jigsawBlocks = NonNullList.withSize(i, BlockRegistry.ALTAR_FLOOR_BLOCK.get());
            jigsawBlocks.replaceAll(ignored -> GSKOUtil.readBlockData(buffer));
            return new DanmakuRecipe(recipeId, jigsawBlocks, output);
        }

        public void write(PacketBuffer buffer, DanmakuRecipe recipe) {
            buffer.writeItemStack(recipe.getRecipeOutput(), false);
            for (Block blockState : recipe.blockStates) GSKOUtil.writeBlockData(buffer, null, blockState);
        }
    }
}
