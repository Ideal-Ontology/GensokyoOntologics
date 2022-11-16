package github.thelawf.gensokyoontology.common.recipe;

import github.thelawf.gensokyoontology.common.item.tools.KitchenKnife;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class KitchenKnifeRecipe implements ICraftingRecipe {

    @Override
    public boolean matches(CraftingInventory inv, World worldIn) {
        return false;
    }

    @Override
    public ItemStack getCraftingResult(CraftingInventory inv) {
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
        return IRecipeSerializer.CRAFTING_SHAPED;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInventory inv) {
        NonNullList<ItemStack> nnlist = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);
        for (int i = 0; i < nnlist.size(); i++) {
            ItemStack stack = inv.getStackInSlot(i);
            if (stack.hasContainerItem()) {
                nnlist.set(i, stack.getContainerItem());
            }
            else if (stack.getItem() instanceof KitchenKnife){
                ItemStack stackCopy = stack.copy();
                stackCopy.setCount(1);
                nnlist.set(i, stackCopy);
                break;
            }
        }
        return nnlist;
    }
}
