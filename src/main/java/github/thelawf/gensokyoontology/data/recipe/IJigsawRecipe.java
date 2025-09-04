package github.thelawf.gensokyoontology.data.recipe;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;


public interface IJigsawRecipe extends IRecipe<IInventory> {
    int getUnitCount();
    float getPowerConsumption();
    NonNullList<Block> getJigsawPattern();
}
