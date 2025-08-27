package github.thelawf.gensokyoontology.data.recipe;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.block.nature.RedwoodLeaves;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;

public interface IKogasaSmithingRecipe extends IRecipe<IInventory> {
    ResourceLocation RECIPE_ID = GensokyoOntology.withRL("kogasa_smithing");
}
