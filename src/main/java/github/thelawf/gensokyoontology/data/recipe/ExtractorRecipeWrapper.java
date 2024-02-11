package github.thelawf.gensokyoontology.data.recipe;

import com.google.common.collect.Lists;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import java.util.Iterator;
import java.util.List;

public class ExtractorRecipeWrapper {
    private final List<List<ItemStack>> inputs = Lists.newArrayList();
    private final ItemStack output;

    public ExtractorRecipeWrapper(ItemStack output, ItemStack up, ItemStack left, ItemStack right, ItemStack down) {
        this.output = output;
        this.inputs.add(Lists.newArrayList(up, left, right, down));
        for (List<ItemStack> stacks : this.inputs) {
            Ingredient input = (Ingredient) stacks;
            if (!input.hasNoMatchingItems()) {
                this.inputs.add(Lists.newArrayList(input.getMatchingStacks()));
            }
        }
    }
}
