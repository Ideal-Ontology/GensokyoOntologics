package github.thelawf.gensokyoontology.data.recipe;

import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;


public interface IJigsawRecipe extends IRecipe<IInventory> {
    int getUnitCount();
    float getPowerConsumption();
    NonNullList<Block> getBlockStates();
    BlockPos getCenterPos();
    void setCenterPos(BlockPos pos);
}
