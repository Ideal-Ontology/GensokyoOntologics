package github.thelawf.gensokyoontology.data.recipe;

import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;

public interface IKogasaSmithingRecipe extends IRecipe<IInventory> {
    ResourceLocation ID = GSKOUtil.withRL("kogasa_smithing");

    /** 获取用于物品词条重铸的材料 */
    Item getMaterial();

    /**
     * 获取物品重铸词条（可以是附魔或别的强化tag）
     */
    RecastEntry getRecastEntry();

    /** 获取该合成所需的P点 */
    float getPowerConsumption();

}
