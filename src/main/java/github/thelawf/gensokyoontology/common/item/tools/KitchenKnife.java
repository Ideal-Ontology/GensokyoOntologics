package github.thelawf.gensokyoontology.common.item.tools;

import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.client.gui.recipebook.RecipeList;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.item.*;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.item.crafting.ShapelessRecipe;
import net.minecraft.tileentity.ChestTileEntity;

import java.util.Random;

public class KitchenKnife extends SwordItem {

    public KitchenKnife() {
        super(ItemTier.IRON, 3, -2.4F,
                new Properties().group(GSKOItemTab.GSKO_ITEM_TAB).maxDamage(40));
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack stack = itemStack.copy();
        if (stack.attemptDamageItem(1, random, null)) {
            return ItemStack.EMPTY;
        }
        return stack;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // 使用菜刀攻击牛可以掉落肥牛卷
        return super.hitEntity(stack, target, attacker);
    }
}
