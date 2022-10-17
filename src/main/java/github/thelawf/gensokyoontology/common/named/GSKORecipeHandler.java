package github.thelawf.gensokyoontology.common.named;

import github.thelawf.gensokyoontology.core.init.ItemInit;
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
        ShapedRecipeBuilder.shapedRecipe(ItemInit.BURGER_MEAT_RAW.get())
                .patternLine("#OO")
                .patternLine("XXX")
                .patternLine("YY$")
                .key('#', ItemInit.KITCHEN_KNIFE.get())
                .key('O', Items.BEEF)
                .key('X', Items.PORKCHOP)
                .key('Y', ItemInit.ONION.get())
                .key('$', Items.EGG)
                .addCriterion("buger_meat_raw", InventoryChangeTrigger.Instance.forItems(
                        ItemInit.KITCHEN_KNIFE.get(),Items.BEEF,Items.PORKCHOP,ItemInit.ONION.get(),Items.EGG
                ))
                .build(consumer);

        /*
        CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(ItemInit.BURGER_MEAT_RAW.get()),
                IItemProvider.asItem(ItemInit.BURGER_MEAT.get()),2.5F,235)
                .addCriterion("buger_meat", InventoryChangeTrigger.Instance.forItems(ItemInit.BURGER_MEAT_RAW.get()))
                .build(consumer);
         */
    }
}
