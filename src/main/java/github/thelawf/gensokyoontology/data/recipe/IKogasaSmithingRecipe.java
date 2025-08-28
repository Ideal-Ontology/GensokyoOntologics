package github.thelawf.gensokyoontology.data.recipe;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

public interface IKogasaSmithingRecipe extends IRecipe<IInventory> {
    ResourceLocation RECIPE_ID = GensokyoOntology.withRL("kogasa_smithing");

    /** 获取用于物品词条重铸的材料 */
    ItemStack getMaterial(int slotIndex);

    /** 获取重复材料的数量（部分重复材料可以为物品添加更高的附魔等级） */
    int getMaterialCount(ItemStack stack);

    /** 获取物品重铸词条（可以是附魔或别的强化tag） */
    CompoundNBT getTagEntry(ItemStack stack);

    /** 获取该合成所需的P点 */
    float getPowerConsumption();
}
