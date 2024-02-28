package github.thelawf.gensokyoontology.core.init;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.world.feature.WaterfallFeature;
import github.thelawf.gensokyoontology.common.world.feature.config.MagicForestConfig;
import github.thelawf.gensokyoontology.common.world.feature.tree.CanopyFeature;
import github.thelawf.gensokyoontology.common.world.feature.tree.GSKOBiomeFeature;
import github.thelawf.gensokyoontology.common.world.feature.tree.MagicForestFeature;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.LiquidsConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 *
 *
 */
public final class FeatureRegistry {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(
            ForgeRegistries.FEATURES, GensokyoOntology.MODID);

    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> CANOPY_FEATURE = FEATURES.register(
            "canopy_feature", () -> new CanopyFeature(BaseTreeFeatureConfig.CODEC.stable()));
    public static final RegistryObject<WaterfallFeature> WATERFALL = FEATURES.register(
            "waterfall", () -> new WaterfallFeature(LiquidsConfig.CODEC.stable()));
    public static final RegistryObject<Feature<MagicForestConfig>> MAGIC_FOREST_FEATURE = FEATURES.register(
            "magic_forest_feature", () -> new MagicForestFeature(MagicForestConfig.CODEC.stable()));

    // public static final Feature<MagicForestConfig> MAGIC_FOREST_FEATURE = register("magic_forest_feature", new MagicForestFeature(
    //         MagicForestConfig.CODEC));

    @SuppressWarnings("deprecation")
    public static <C extends IFeatureConfig, F extends Feature<C>> F register(String name, F value) {
        return Registry.register(Registry.FEATURE, name, value);
    }
}
