package github.thelawf.gensokyoontology.data.recipe;

import com.google.common.collect.Lists;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;

import java.util.Iterator;
import java.util.List;

public class ExtractorRecipeWrapper {
    private final NonNullList<Ingredient> inputs;
    private final ItemStack output;

    public ExtractorRecipeWrapper(ItemStack output, NonNullList<Ingredient> ingredients) {
        this.output = output;
        this.inputs = ingredients;
    }

    public ItemStack getOutput() {
        return this.output;
    }

    public NonNullList<Ingredient> getInputs() {
        return this.inputs;
    }
}
