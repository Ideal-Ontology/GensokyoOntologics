package github.thelawf.gensokyoontology.common.item.tools;

import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.client.gui.recipebook.RecipeList;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.*;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.item.crafting.ShapelessRecipe;
import net.minecraft.tileentity.ChestTileEntity;

public class KitchenKnife extends SwordItem {
    public KitchenKnife() {
        super(ItemTier.IRON, 3, -2.4F,
                new Properties().group(GSKOItemTab.GSKO_ITEM_TAB));
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack stack = new ItemStack(this);
        stack.setDamage(stack.getDamage() + 1);
        if (stack.getDamage() >= stack.getMaxDamage()) {
            return ItemStack.EMPTY;
        }
        return stack.copy();
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }
}
