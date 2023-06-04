package github.thelawf.gensokyoontology.data;

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

        /*
        CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(ItemRegistry.BURGER_MEAT_RAW.get()),
                IItemProvider.asItem(ItemRegistry.BURGER_MEAT.get()),2.5F,235)
                .addCriterion("buger_meat", InventoryChangeTrigger.Instance.forItems(ItemRegistry.BURGER_MEAT_RAW.get()))
                .build(consumer);
         */
    }
}
