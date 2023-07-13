package github.thelawf.gensokyoontology.common.world.feature;

import com.mojang.serialization.Lifecycle;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.world.feature.config.MagicTreeConfig;
import github.thelawf.gensokyoontology.common.world.feature.placer.OvalFoliagePlacer;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import github.thelawf.gensokyoontology.core.init.FeatureRegistry;
import github.thelawf.gensokyoontology.core.init.StructureRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HugeMushroomBlock;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.ForkyTrunkPlacer;

public class GSKOFeatures {

    public static final BlockState BLUE_MUSHROOM_UP = BlockRegistry.PURPLE_MUSHROOM_BLOCK.get().getDefaultState().with(
            HugeMushroomBlock.UP, Boolean.TRUE);
    public static final BlockState PURPLE_MUSHROOM_UP = BlockRegistry.BLUE_MUSHROOM_BLOCK.get().getDefaultState().with(
            HugeMushroomBlock.UP, Boolean.TRUE);
    public static final BlockState MUSHROOM_STEM = Blocks.MUSHROOM_STEM.getDefaultState().with(HugeMushroomBlock.UP, Boolean.valueOf(false)).with(HugeMushroomBlock.DOWN, Boolean.valueOf(false));

    // 特征地物生成目前遇到了三大坑：
    // 1. MC特有的两套注册系统，且非要你注册之后才能用，特有的将面向过程编程变成面向json编程
    // 2. 如果不加withPlacement()，那么树木特征将会生成在每个区块的（0，0）位置，但是：
    // 3. 世界生成注册绑定的树木特征和树苗方块绑定的树木特征的类型不一样，解决方法是直接把两种不同的树木特征泛型
    // 都写上，树苗方块绑定ConfiguredFeature<BaseTreeFeatureConfig, ?>, 世界生成注册绑定 ConfiguredFeature<?, ?>
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> SAKURA_TREE_BASE = Feature.TREE.withConfiguration(
            new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.SAKURA_LOG.get().getDefaultState()),
                    new SimpleBlockStateProvider(BlockRegistry.SAKURA_LEAVES.get().getDefaultState()),
                    new FancyFoliagePlacer(FeatureSpread.create(2), FeatureSpread.create(1), 2),
                    new FancyTrunkPlacer(8,3,2),
                    new TwoLayerFeature(1,1,3)).setIgnoreVines().build());

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MAGIC_TREE_BASE = Feature.TREE.withConfiguration(
            new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.MAGIC_LOG.get().getDefaultState()),
                    new SimpleBlockStateProvider(BlockRegistry.MAGIC_LEAVES.get().getDefaultState()),
                    new OvalFoliagePlacer(FeatureSpread.create(2,3), FeatureSpread.create(1,2)),
                    new FancyTrunkPlacer(1,2,3),
                    new TwoLayerFeature(1,0,0)).setIgnoreVines().build());

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MAPLE_TREE_BASE = Feature.TREE.withConfiguration(
            new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.MAPLE_LOG.get().getDefaultState()),
                    new SimpleBlockStateProvider(BlockRegistry.MAPLE_LEAVES.get().getDefaultState()),
                    new BlobFoliagePlacer(FeatureSpread.create(2), FeatureSpread.create(0), 2),
                    new FancyTrunkPlacer(8, 2, 1),
                    new TwoLayerFeature(1,1,2)).setIgnoreVines().build());

    public static final ConfiguredFeature<?, ?> SAKURA_TREE = Feature.TREE.withConfiguration(
                    new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.SAKURA_LOG.get().getDefaultState()),
                            new SimpleBlockStateProvider(BlockRegistry.SAKURA_LEAVES.get().getDefaultState()),
                            new FancyFoliagePlacer(FeatureSpread.create(2), FeatureSpread.create(1), 2),
                            new FancyTrunkPlacer(8,3,2),
                            new TwoLayerFeature(1,1,3)).setIgnoreVines().build())
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).square();

    public static final ConfiguredFeature<?, ?> MAGIC_TREE = Feature.TREE.withConfiguration(
            new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.MAGIC_LOG.get().getDefaultState()),
                    new SimpleBlockStateProvider(BlockRegistry.MAGIC_LEAVES.get().getDefaultState()),
                    new OvalFoliagePlacer(FeatureSpread.create(2,3), FeatureSpread.create(1,2)),
                    new FancyTrunkPlacer(1,2,3),
                    new TwoLayerFeature(1,0,0)).setIgnoreVines().build())
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).square()
            .withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(4, 0.8f, 9)));

    public static final ConfiguredFeature<?, ?> MAPLE_TREE = Feature.TREE.withConfiguration(
                    new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.MAPLE_LOG.get().getDefaultState()),
                            new SimpleBlockStateProvider(BlockRegistry.MAPLE_LEAVES.get().getDefaultState()),
                            new BlobFoliagePlacer(FeatureSpread.create(2), FeatureSpread.create(0), 2),
                            new FancyTrunkPlacer(8, 2, 1),
                            new TwoLayerFeature(1,1,2)).setIgnoreVines().build())
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).square();

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> GSKO_OAK = Feature.TREE.withConfiguration(
            new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                    new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState()),
                    new FancyFoliagePlacer(FeatureSpread.create(1), FeatureSpread.create(0), 1),
                    new FancyTrunkPlacer(7,0,1),
                    new TwoLayerFeature(1,0,2)).setIgnoreVines().build());

    public static final ConfiguredFeature<BigMushroomFeatureConfig, ?> HUGE_BLUE_MUSHROOM = Feature.HUGE_RED_MUSHROOM.withConfiguration(
            new BigMushroomFeatureConfig(new SimpleBlockStateProvider(BLUE_MUSHROOM_UP),
                          new SimpleBlockStateProvider(MUSHROOM_STEM),6));

    public static final ConfiguredFeature<BigMushroomFeatureConfig, ?> HUGE_PURPLE_MUSHROOM = Feature.HUGE_RED_MUSHROOM.withConfiguration(
            new BigMushroomFeatureConfig(new SimpleBlockStateProvider(PURPLE_MUSHROOM_UP),
                    new SimpleBlockStateProvider(PURPLE_MUSHROOM_UP),5));
    public static final ConfiguredFeature<?, ?> BAMBOO = Feature.BAMBOO.withConfiguration(new ProbabilityConfig(0.9f))
            .withPlacement(Features.Placements.BAMBOO_PLACEMENT)
            .square()
            .withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(9, 0.99f, 8)));

    // public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> SHINBOKU = register(new ResourceLocation(GensokyoOntology.MODID, "shinboku"),
    //         Feature.TREE.withConfiguration());

    public static final ConfiguredFeature<?,?> LYCORIS = Feature.FLOWER.withConfiguration(
                    new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(
                            BlockRegistry.LYCORIS_RADIATA.get().getDefaultState()),
                            SimpleBlockPlacer.PLACER).tries(3).build()).withPlacement(
                                    Features.Placements.HEIGHTMAP_PLACEMENT);

    public static final ConfiguredFeature<?,?> WASABI = Feature.RANDOM_PATCH.withConfiguration(
                    new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(
                            BlockRegistry.WASABI_BLOCK.get().getDefaultState()),
                            SimpleBlockPlacer.PLACER).tries(2).build())
                    .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).count(3);

    public static final StructureFeature<?, ?> MYSTIA_STRUCTURE = StructureRegistry.MYSTIA_IZAKAYA.get()
            .withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);
    public static final StructureFeature<?, ?> HAKUREI_STRUCTURE = StructureRegistry.HAKUREI_SHRINE.get()
            .withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);


    public static <FC extends IFeatureConfig, F extends Feature<FC>> ConfiguredFeature<FC, F> register(
            ResourceLocation location, ConfiguredFeature<FC, F> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, location, feature);
    }

    public static void registerFeature() {
        Registry<ConfiguredFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_FEATURE;
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "lycoris"), LYCORIS);
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "wasabi"), WASABI);
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "bamboo"), BAMBOO);

        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "magic_tree"), MAGIC_TREE);
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "maple_tree"), MAPLE_TREE);
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "sakura_tree"), SAKURA_TREE);
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "gsko_oak_tree"), GSKO_OAK);

        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "huge_blue_mushroom"), HUGE_BLUE_MUSHROOM);
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "huge_purple_mushroom"), HUGE_PURPLE_MUSHROOM);
    }

    public static void registerStructure() {
        Registry<StructureFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;
        //可以继续添加多个
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "mystia_izakaya"), MYSTIA_STRUCTURE);
        Registry.register(registry, new ResourceLocation(GensokyoOntology.MODID, "hakurei_shrine"), HAKUREI_STRUCTURE);

        FlatGenerationSettings.STRUCTURES.put(StructureRegistry.MYSTIA_IZAKAYA.get(), MYSTIA_STRUCTURE);
        FlatGenerationSettings.STRUCTURES.put(StructureRegistry.HAKUREI_SHRINE.get(), HAKUREI_STRUCTURE);
    }

}