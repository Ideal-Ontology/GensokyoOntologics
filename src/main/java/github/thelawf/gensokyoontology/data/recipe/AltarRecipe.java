package github.thelawf.gensokyoontology.data.recipe;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AltarRecipe implements IAltarRecipe {
    @Override
    public int getUnitCount() {
        return 0;
    }

    @Override
    public float getPowerConsumption() {
        return 0;
    }

    @Override
    public NonNullList<Block> getJigsawPattern() {
        return null;
    }

    @Override
    public BlockPos getCenterPos() {
        return null;
    }

    @Override
    public void setCenterPos(BlockPos pos) {

    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        return false;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        return null;
    }

    @Override
    public boolean canFit(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }

    @Override
    public ResourceLocation getId() {
        return null;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public IRecipeType<?> getType() {
        return null;
    }
}
