package github.thelawf.gensokyoontology.common.world.feature;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;

public class GSKOFeatures {

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> SAKURA_TREE = register(
            new ResourceLocation(GensokyoOntology.MODID, "sakura_tree"), Feature.TREE.withConfiguration(
                    new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.SAKURA_LOG.get().getDefaultState()),
                            new SimpleBlockStateProvider(BlockRegistry.SAKURA_LEAVES.get().getDefaultState()),
                            new FancyFoliagePlacer(FeatureSpread.create(2), FeatureSpread.create(1), 5),
                            new FancyTrunkPlacer(8,3,2),
                            new TwoLayerFeature(1,1,2)).setIgnoreVines().build()));

    // public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MAGIC_TREE = register(
    //         new ResourceLocation(GensokyoOntology.MODID, "magic_tree"), Feature.TREE.withConfiguration(
    //                 new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.DARK_OAK_LOG.getDefaultState()),
    //                         new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState()),
    //                         new BlobFoliagePlacer(FeatureSpread.create(10),FeatureSpread.create(18),20),
    //                         new FancyTrunkPlacer(15,3,2),
    //                         new TwoLayerFeature(1,0,1)).setIgnoreVines().build()));

    // public static final ConfiguredFeature<MagicTreeConfig, ?> MAGIC_TREE = new ResourceLocation(
    //         GensokyoOntology.MODID, "magic_tree", FeatureRegistry.MAGIC_TREE.get().withConfiguration(
    //                 new MagicTreeConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.))));

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MAPLE_TREE = register(new ResourceLocation(
            GensokyoOntology.MODID, "maple_tree"), Feature.TREE.withConfiguration(
                    new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.MAPLE_LOG.get().getDefaultState()),
                            new SimpleBlockStateProvider(BlockRegistry.SAKURA_LEAVES.get().getDefaultState()),
                            new BlobFoliagePlacer(FeatureSpread.create(4), FeatureSpread.create(8), 10),
                            new FancyTrunkPlacer(7, 3, 5),
                            new TwoLayerFeature(2,3,2)).setIgnoreVines().build()));

    public static final ConfiguredFeature<?,?> LYCORIS = register(
            new ResourceLocation(GensokyoOntology.MODID, "lycoris"),Feature.FLOWER.withConfiguration(
                    new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(
                            BlockRegistry.LYCORIS_RADIATA.get().getDefaultState()),
                            SimpleBlockPlacer.PLACER).tries(3).build()).withPlacement(
                                    Features.Placements.HEIGHTMAP_PLACEMENT));

    public static final ConfiguredFeature<?,?> WASABI = register(
            new ResourceLocation(GensokyoOntology.MODID, "wasabi"), Feature.RANDOM_PATCH.withConfiguration(
                    new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(
                            BlockRegistry.WASABI_BLOCK.get().getDefaultState()),
                            SimpleBlockPlacer.PLACER).tries(8).build())
                    .withPlacement(Features.Placements.VEGETATION_PLACEMENT)
                    .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).count(3));

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC,?> register(String key, ConfiguredFeature<FC,?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, feature);
    }

    protected static <FC extends IFeatureConfig, F extends Feature<FC>> ConfiguredFeature<FC, F> register(
            ResourceLocation location, ConfiguredFeature<FC, F> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, location, feature);
    }
}
