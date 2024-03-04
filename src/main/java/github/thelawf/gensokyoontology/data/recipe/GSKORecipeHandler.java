package github.thelawf.gensokyoontology.data.recipe;

import com.google.common.collect.Lists;
import github.thelawf.gensokyoontology.core.RecipeRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.NonNullList;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class GSKORecipeHandler {

    private final RecipeManager recipeManager;

    public static GSKORecipeHandler getInstance() {
        return new GSKORecipeHandler();
    }

    public GSKORecipeHandler() {
        ClientWorld world = Objects.requireNonNull(Minecraft.getInstance().world);
        this.recipeManager = world.getRecipeManager();
    }

}
