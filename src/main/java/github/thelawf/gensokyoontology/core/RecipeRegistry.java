package github.thelawf.gensokyoontology.core;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.data.recipe.DanmakuRecipe;
import github.thelawf.gensokyoontology.data.recipe.SorceryExtractorRecipe;
import net.minecraft.item.crafting.*;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeRegistry {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(
            ForgeRegistries.RECIPE_SERIALIZERS, GensokyoOntology.MODID);

    public static final RegistryObject<SorceryExtractorRecipe.Serializer> SORCERY_SERIALIZER = RECIPE_SERIALIZERS.register(
            "sorcery_extract", SorceryExtractorRecipe.Serializer::new);
    public static final RegistryObject<DanmakuRecipe.Serializer> DANMAKU_CRAFT_SERIALIZER = RECIPE_SERIALIZERS.register(
            "danmaku", DanmakuRecipe.Serializer::new);

    public static final IRecipeType<SorceryExtractorRecipe> SORCERY_RECIPE = new SorceryExtractorRecipe.Type();
    public static final IRecipeType<DanmakuRecipe> DANMAKU_RECIPE = new DanmakuRecipe.Type();

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
        Registry.register(Registry.RECIPE_TYPE, SorceryExtractorRecipe.RECIPE_ID, SORCERY_RECIPE);
        // Registry.register(Registry.RECIPE_TYPE, DanmakuRecipe.RECIPE_ID, DANMAKU_RECIPE);
    }
}
