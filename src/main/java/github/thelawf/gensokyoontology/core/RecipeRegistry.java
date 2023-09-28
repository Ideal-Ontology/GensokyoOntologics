package github.thelawf.gensokyoontology.core;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.data.recipe.DanmakuRecipe;
import github.thelawf.gensokyoontology.data.recipe.SorceryRecipe;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Optional;

public class RecipeRegistry {

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(
            ForgeRegistries.RECIPE_SERIALIZERS, GensokyoOntology.MODID);

    public static final RegistryObject<SorceryRecipe.Serializer> SORCERY_SERIALIZER = RECIPE_SERIALIZERS.register(
            "sorcery_extract", SorceryRecipe.Serializer::new);
    public static final RegistryObject<DanmakuRecipe.Serializer> DANMAKU_CRAFT_SERIALIZER = RECIPE_SERIALIZERS.register(
            "danmaku_craft", DanmakuRecipe.Serializer::new);

    public static final IRecipeType<SorceryRecipe> SORCERY_RECIPE = new SorceryRecipe.SorceryRecipeType();
    public static final IRecipeType<ICraftingRecipe> DANMAKU_RECIPE = IRecipeType.CRAFTING;

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
        Registry.register(Registry.RECIPE_TYPE, SorceryRecipe.RECIPE_ID, SORCERY_RECIPE);
    }
}
