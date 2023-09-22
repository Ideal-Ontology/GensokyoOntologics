package github.thelawf.gensokyoontology.core;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.data.recipe.SorceryRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeRegistry {

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(
            ForgeRegistries.RECIPE_SERIALIZERS, GensokyoOntology.MODID);

    public static final RegistryObject<SorceryRecipe.Serializer> SORCERY_SERIALIZER = RECIPE_SERIALIZERS.register(
            "sorcery_serializer", SorceryRecipe.Serializer::new);

    public static final IRecipeType<SorceryRecipe> SORCERY_RECIPE = new SorceryRecipe.SorceryRecipeType();

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
        Registry.register(Registry.RECIPE_TYPE, SorceryRecipe.RECIPE_ID, SORCERY_RECIPE);
    }
}
