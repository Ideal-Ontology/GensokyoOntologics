package github.thelawf.gensokyoontology.common.data;

import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraftforge.common.data.ForgeRecipeProvider;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class GSKORecipeHandler extends ForgeRecipeProvider {
    public GSKORecipeHandler(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(@Nonnull Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(ItemRegistry.BURGER_MEAT_RAW.get())
                .patternLine("#OO")
                .patternLine("XXX")
                .patternLine("YY$")
                .key('#', ItemRegistry.KITCHEN_KNIFE.get())
                .key('O', Items.BEEF)
                .key('X', Items.PORKCHOP)
                .key('Y', ItemRegistry.ONION.get())
                .key('$', Items.EGG)
                .addCriterion("buger_meat_raw", InventoryChangeTrigger.Instance.forItems(
                        ItemRegistry.KITCHEN_KNIFE.get(),Items.BEEF,Items.PORKCHOP, ItemRegistry.ONION.get(),Items.EGG
                ))
                .build(consumer);

        /*
        CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(ItemRegistry.BURGER_MEAT_RAW.get()),
                IItemProvider.asItem(ItemRegistry.BURGER_MEAT.get()),2.5F,235)
                .addCriterion("buger_meat", InventoryChangeTrigger.Instance.forItems(ItemRegistry.BURGER_MEAT_RAW.get()))
                .build(consumer);
         */
    }
}
