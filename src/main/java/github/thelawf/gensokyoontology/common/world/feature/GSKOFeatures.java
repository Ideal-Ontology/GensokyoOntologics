package github.thelawf.gensokyoontology.common.world.feature;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.world.GSKOOreType;
import github.thelawf.gensokyoontology.common.world.feature.config.EclipticFoliageLayerConfig;
import github.thelawf.gensokyoontology.common.world.feature.config.GSKOTreeConfig;
import github.thelawf.gensokyoontology.common.world.feature.config.GSKOWGConfigs;
import github.thelawf.gensokyoontology.common.world.feature.config.MagicForestConfig;
import github.thelawf.gensokyoontology.common.world.feature.tree.GSKOTrees;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import github.thelawf.gensokyoontology.core.init.FeatureRegistry;
import github.thelawf.gensokyoontology.core.init.StructureRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HugeMushroomBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;

public class GSKOFeatures {

    public static final BlockState BLUE_MUSHROOM_DOWN = BlockRegistry.BLUE_MUSHROOM_BLOCK.get().getDefaultState().with(
            HugeMushroomBlock.DOWN, Boolean.FALSE);
    public static final BlockState PURPLE_MUSHROOM_DOWN = BlockRegistry.PURPLE_MUSHROOM_BLOCK.get().getDefaultState().with(
            HugeMushroomBlock.DOWN, Boolean.FALSE);
    public static final BlockState MUSHROOM_STEM = Blocks.MUSHROOM_STEM.getDefaultState().with(HugeMushroomBlock.UP, Boolean.FALSE).with(HugeMushroomBlock.DOWN, Boolean.FALSE);

    //-----------------------------------------蘑菇-----------------------------------------------//
    public static final ConfiguredFeature<BigMushroomFeatureConfig, ?> HUGE_BLUE_MUSHROOM = Feature.HUGE_RED_MUSHROOM.withConfiguration(
            new BigMushroomFeatureConfig(new SimpleBlockStateProvider(BLUE_MUSHROOM_DOWN),
                    new SimpleBlockStateProvider(MUSHROOM_STEM), 3));

    public static final ConfiguredFeature<BigMushroomFeatureConfig, ?> HUGE_PURPLE_MUSHROOM = Feature.HUGE_RED_MUSHROOM.withConfiguration(
            new BigMushroomFeatureConfig(new SimpleBlockStateProvider(PURPLE_MUSHROOM_DOWN),
                    new SimpleBlockStateProvider(MUSHROOM_STEM), 4));

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> SHINBOKU_BASE = Feature.TREE.withConfiguration(
            new BaseTreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState()),
                    new FancyFoliagePlacer(FeatureSpread.create(3), FeatureSpread.create(1), 2),
                    new FancyTrunkPlacer(9, 2, 1),
                    new TwoLayerFeature(1, 2, 2)).setIgnoreVines().build());

    //------------------------------------------树木----------------------------------------------//
    /**
     * 从这里开始是初始化世界中自然生成的树木特征的配置
     */
    public static final ConfiguredFeature<?, ?> SAKURA_TREE = Feature.TREE.withConfiguration(
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(BlockRegistry.SAKURA_LOG.get().getDefaultState()),
                            new SimpleBlockStateProvider(BlockRegistry.SAKURA_LEAVES.get().getDefaultState()),
                            new FancyFoliagePlacer(FeatureSpread.create(3), FeatureSpread.create(1), 2),
                            new FancyTrunkPlacer(8, 3, 2),
                            new TwoLayerFeature(1, 1, 3)).setIgnoreVines().build())
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).square();

    public static final ConfiguredFeature<?, ?> SAKURA_TREE_HAKUREI_SHRINE = Feature.TREE.withConfiguration(
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(BlockRegistry.SAKURA_LOG.get().getDefaultState()),
                            new SimpleBlockStateProvider(BlockRegistry.SAKURA_LEAVES.get().getDefaultState()),
                            new FancyFoliagePlacer(FeatureSpread.create(3), FeatureSpread.create(1), 2),
                            new FancyTrunkPlacer(8, 3, 2),
                            new TwoLayerFeature(1, 1, 3)).setIgnoreVines().build())
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).square()
            .withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(3, 0.4f, 3)));

    public static final ConfiguredFeature<?, ?> BEAST_PATH_VEGETATION = Feature.RANDOM_SELECTOR.withConfiguration(
                    new MultipleRandomFeatureConfig(ImmutableList.of(
                            GSKOTrees.MAGIC_TREE.withChance(0.25f),
                            SHINBOKU_BASE.withChance(0.35f),
                            Features.FANCY_OAK.withChance(0.25f)),
                            SHINBOKU_BASE))
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).square()
            .withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(4, 0.9f, 5)));

    public static final ConfiguredFeature<?, ?> MAGIC_TREES = GSKOTrees.MAGIC_TREE.withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
            .count(1).variableCount(3).square();
    public static final ConfiguredFeature<?, ?> MAGIC_FOREST_FEATURE = FeatureRegistry.MAGIC_FOREST_FEATURE.get().withConfiguration(new MagicForestConfig(
            GSKOTreeConfig.create(MagicForestConfig.FOLIAGE_BLOCK, MagicForestConfig.TRUNK_BLOCK),
            EclipticFoliageLayerConfig.create(7, 5, 5, 7), 7, 10, 3))
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
            .count(3).variableCount(4).square();
    public static final ConfiguredFeature<?, ?> MAGIC_FOREST_TREE = FeatureRegistry.MAGIC_FOREST_FEATURE.get().withConfiguration(new MagicForestConfig(
            GSKOTreeConfig.create(MagicForestConfig.FOLIAGE_BLOCK, MagicForestConfig.TRUNK_BLOCK),
            EclipticFoliageLayerConfig.create(7, 5, 5, 7), 7, 10, 3));

    public static final ConfiguredFeature<?, ?> MAPLE_TREE_VEGETATION = GSKOTrees.MAPLE_TREE
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).count(2).variableCount(1).square();
    public static final ConfiguredFeature<?, ?> MAPLE_TREE_MOUNTAIN = Feature.TREE.withConfiguration(
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(BlockRegistry.MAPLE_LOG.get().getDefaultState()),
                            new SimpleBlockStateProvider(BlockRegistry.MAPLE_LEAVES.get().getDefaultState()),
                            new BlobFoliagePlacer(FeatureSpread.create(3), FeatureSpread.create(1, 3), 2),
                            new FancyTrunkPlacer(5, 2, 1),
                            new TwoLayerFeature(1, 0, 2)).setIgnoreVines().build())
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).square()
            .withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(2, 0.5f, 3)));

    public static final ConfiguredFeature<?, ?> ZELKOVA_TREES = GSKOTrees.ZELKOVA_TREE
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).count(3).variableCount(2).square();

    public static final ConfiguredFeature<?, ?> SHINBOKU_TREE = Feature.TREE.withConfiguration(
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                            new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState()),
                            new BlobFoliagePlacer(FeatureSpread.create(3), FeatureSpread.create(1), 2),
                            new FancyTrunkPlacer(15, 0, 1),
                            new TwoLayerFeature(1, 0, 2)).setIgnoreVines().build())
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).square()
            .withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(2, 0.4f, 2)));


    // public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> SHINBOKU = register(new ResourceLocation(GensokyoOntology.MODID, "shinboku"),
    //         Feature.TREE.withConfiguration());

    //-------------------------------------------花草生成------------------------------------------//
    public static final ConfiguredFeature<?, ?> HIGAN_LYCORIS = Feature.RANDOM_PATCH
            .withConfiguration(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.LYCORIS_RADIATA.get().getDefaultState()),
                    SimpleBlockPlacer.PLACER).tries(32).build())
            .withPlacement(Features.Placements.BAMBOO_PLACEMENT).square()
            .withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(9, 0.95f, 9)));

    public static final ConfiguredFeature<?, ?> WASABI = Feature.RANDOM_PATCH.withConfiguration(
                    new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(
                            BlockRegistry.WASABI_BLOCK.get().getDefaultState()),
                            SimpleBlockPlacer.PLACER).tries(2).build())
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).count(3);

    public static final ConfiguredFeature<?, ?> BAMBOO = Feature.BAMBOO.withConfiguration(new ProbabilityConfig(0.9f))
            .withPlacement(Features.Placements.BAMBOO_PLACEMENT)
            .square()
            .withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(9, 0.99f, 8)));

    public static final ConfiguredFeature<?, ?> PATCH_ZELKOVA_LEAVES = Feature.RANDOM_PATCH
            .withConfiguration(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(
                    BlockRegistry.ZELKOVA_LEAVES_PILE.get().getDefaultState()), SimpleBlockPlacer.PLACER).tries(8).build())
            .withPlacement(Features.Placements.PATCH_PLACEMENT).count(3).variableCount(5).square();
    public static final ConfiguredFeature<?, ?> PATCH_MAPLE_LEAVES = Feature.RANDOM_PATCH
            .withConfiguration(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(
                    BlockRegistry.MAPLE_LEAVES_PILE.get().getDefaultState()), SimpleBlockPlacer.PLACER).tries(8).build())
            .withPlacement(Features.Placements.PATCH_PLACEMENT).count(3).variableCount(5).square();
    public static final ConfiguredFeature<?, ?> PATCH_GINKGO_LEAVES = Feature.RANDOM_PATCH
            .withConfiguration(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(
                    BlockRegistry.GINKGO_LEAVES_PILE.get().getDefaultState()), SimpleBlockPlacer.PLACER).tries(8).build())
            .withPlacement(Features.Placements.PATCH_PLACEMENT).count(3).variableCount(5).square();

    //-------------------------------------------特征生成------------------------------------------//
    public static final ConfiguredFeature<?, ?> WATERFALL = FeatureRegistry.WATERFALL.get()
            .withConfiguration(new LiquidsConfig(Fluids.WATER.getDefaultState(), true, 4, 1, ImmutableSet.of(
                    Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE))).withPlacement(
                    Placement.RANGE_BIASED.configure(new TopSolidRangeConfig(8, 8, 256))).square().count(50);

    public static final ConfiguredFeature<?, ?> GSKO_STONE_PILE = Feature.BLOCK_PILE.withConfiguration(
                    new BlockStateProvidingFeatureConfig(new SimpleBlockStateProvider(Blocks.STONE.getDefaultState())))
            .withPlacement(Placement.HEIGHTMAP_WORLD_SURFACE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG));

    //-------------------------------------------植被生成------------------------------------------//
    public static final ConfiguredFeature<?, ?> MAGIC_FOREST_VEGETATION = Feature.RANDOM_SELECTOR.withConfiguration(
                    new MultipleRandomFeatureConfig(ImmutableList.of(
                            MAGIC_FOREST_TREE.withChance(0.8f),
                            HUGE_BLUE_MUSHROOM.withChance(0.05f),
                            HUGE_PURPLE_MUSHROOM.withChance(0.05f)), GSKOTrees.MAGIC_TREE))
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).count(4).variableCount(6).square();
    public static final ConfiguredFeature<?, ?> UNTRODDEN_VALLEY_VEGETATION = Feature.RANDOM_SELECTOR.withConfiguration(
                    new MultipleRandomFeatureConfig(ImmutableList.of(
                            GSKOTrees.ZELKOVA_TREE.withChance(0.2f),
                            GSKOTrees.SAKURA_TREE.withChance(0.1f),
                            GSKOTrees.GINKGO_TREE.withChance(0.2f)), GSKOTrees.MAPLE_TREE))
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).count(3).variableCount(5).square();

    //-------------------------------------------建筑生成------------------------------------------//
    public static final StructureFeature<?, ?> MYSTIA_STRUCTURE = StructureRegistry.MYSTIA_IZAKAYA.get()
            .withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);
    public static final StructureFeature<?, ?> HAKUREI_STRUCTURE = StructureRegistry.HAKUREI_SHRINE.get()
            .withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);
    public static final StructureFeature<?, ?> CHIREIDEN = StructureRegistry.CHIREIDEN.get()
            .withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);

    public static final StructureFeature<?, ?> BEAST_PATHWAY = StructureRegistry.BEAST_PATHWAY.get()
            .withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);

    public static final StructureFeature<?, ?> CIRNO_ICE_HOUSE = StructureRegistry.CIRNO_ICE_HOUSE.get()
            .withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);

    public static final StructureFeature<?, ?> HUMAN_VILLAGE = StructureRegistry.HUMAN_VILLAGE.get()
            .withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);

    // public static final StructureFeature<?, ?> WATERFALL_FEATURE = StructureRegistry.WATERFALL_NINE_HEAVEN.get()
    //         .withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);

    public static void registerFeature() {
        Registry<ConfiguredFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_FEATURE;

        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "higan_lycoris"), HIGAN_LYCORIS);
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "wasabi"), WASABI);
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "bamboo"), BAMBOO);

        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "magic_forest_feature"), MAGIC_FOREST_FEATURE);
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "magic_forest_vegetation"), MAGIC_FOREST_VEGETATION);
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "magic_trees"), MAGIC_TREES);

        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "patch_ginkgo_leaves"), PATCH_GINKGO_LEAVES);
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "patch_maple_leaves"), PATCH_MAPLE_LEAVES);
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "patch_zelkova_leaves"), PATCH_ZELKOVA_LEAVES);

        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "maple_tree_vegetation"), MAPLE_TREE_VEGETATION);
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "maple_tree_mountain"), MAPLE_TREE_MOUNTAIN);

        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "sakura_tree"), SAKURA_TREE);
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "sakura_tree_hakurei_shrine"), SAKURA_TREE_HAKUREI_SHRINE);
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "shinboku_tree"), SHINBOKU_TREE);

        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "zelkova_trees"), ZELKOVA_TREES);
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "beast_path_vegetation"), BEAST_PATH_VEGETATION);
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "untrodden_valley_vegetation"), UNTRODDEN_VALLEY_VEGETATION);

        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "huge_blue_mushroom"), HUGE_BLUE_MUSHROOM);
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "huge_purple_mushroom"), HUGE_PURPLE_MUSHROOM);

        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "waterfall"), WATERFALL);
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "gsko_stone_cluster"), GSKO_STONE_PILE);
    }

    public static void registerStructure() {
        Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;
        // 可以继续添加多个
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "mystia_izakaya"), MYSTIA_STRUCTURE);
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "hakurei_shrine"), HAKUREI_STRUCTURE);
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "cirno_ice_house"), CIRNO_ICE_HOUSE);
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "chireiden"), CHIREIDEN);
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "beast_pathway"), BEAST_PATHWAY);
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "human_village"), HUMAN_VILLAGE);

        FlatGenerationSettings.STRUCTURES.put(StructureRegistry.MYSTIA_IZAKAYA.get(), MYSTIA_STRUCTURE);
        FlatGenerationSettings.STRUCTURES.put(StructureRegistry.HAKUREI_SHRINE.get(), HAKUREI_STRUCTURE);
        FlatGenerationSettings.STRUCTURES.put(StructureRegistry.CIRNO_ICE_HOUSE.get(), CIRNO_ICE_HOUSE);
        FlatGenerationSettings.STRUCTURES.put(StructureRegistry.BEAST_PATHWAY.get(), BEAST_PATHWAY);
        FlatGenerationSettings.STRUCTURES.put(StructureRegistry.HUMAN_VILLAGE.get(), HUMAN_VILLAGE);
        FlatGenerationSettings.STRUCTURES.put(StructureRegistry.CHIREIDEN.get(), CHIREIDEN);
    }

    public static ConfiguredFeature<?, ?> makeOreFeature(GSKOOreType oreType, Feature<OreFeatureConfig> oreFeature,
                                                         OreFeatureConfig config, ConfiguredPlacement<?> placement) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, oreType.getLazyBlock().get().getRegistryName(),
                oreFeature.withConfiguration(config).withPlacement(placement).square().count(oreType.getMaxVeinSize()));
    }

    public static ConfiguredFeature<?, ?> makeIzanoOreFeature(GSKOOreType oreType, OreFeatureConfig config) {
        return makeOreFeature(oreType, Feature.NO_SURFACE_ORE, config, Placement.RANGE.configure(
                new TopSolidRangeConfig(2, 4, 12))).square().count(oreType.getMaxVeinSize());
    }

    public static void registerOre() {
        Registry<ConfiguredFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_FEATURE;

        Registry.register(registry, GSKOOreType.IMMEMORIAL_ALLOY.getLazyBlock().get().getRegistryName(),
                withOreFeature(Feature.NO_SURFACE_ORE, GSKOWGConfigs.CRIMSON_ALLOY_CONFIG, GSKOWGConfigs.IMMEMORIAL_ALLOY_PLACEMENT));

        Registry.register(registry, GSKOOreType.JADE_GENSOKYO.getLazyBlock().get().getRegistryName(),
                withOreFeature(Feature.NO_SURFACE_ORE, GSKOWGConfigs.JADE_GENSOKYO_CONFIG, GSKOWGConfigs.JADE_GENSOKYO_PLANCEMENT));

        Registry.register(registry, GSKOOreType.DRAGON_SPHERE.getLazyBlock().get().getRegistryName(),
                withOreFeature(Feature.NO_SURFACE_ORE, GSKOWGConfigs.DRAGON_SPHERE_CONFIG, GSKOWGConfigs.DRAGON_SPHERE_PLACEMENT));
    }

    public static ConfiguredFeature<?, ?> withOreFeature(Feature<OreFeatureConfig> featureIn, OreFeatureConfig configIn, ConfiguredPlacement<?> placementIn) {
        return featureIn.withConfiguration(configIn).withPlacement(placementIn);
    }
}
