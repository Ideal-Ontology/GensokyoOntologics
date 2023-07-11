package github.thelawf.gensokyoontology.common.world.feature;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import github.thelawf.gensokyoontology.core.init.FeatureRegistry;
import github.thelawf.gensokyoontology.core.init.StructureRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;

import java.util.HashMap;
import java.util.Map;

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
    //                         new SimpleBlockStateProvider(BlockRegistry.MAGIC_LEAVES.get().getDefaultState()),
    //                         new BlobFoliagePlacer(FeatureSpread.create(10),FeatureSpread.create(18),20),
    //                         new FancyTrunkPlacer(15,3,2),
    //                         new TwoLayerFeature(1,0,1)).setIgnoreVines().build()));

    public static final ConfiguredFeature<NoFeatureConfig, ?> MAGIC_TREE = register(new ResourceLocation(
            GensokyoOntology.MODID, "magic_tree"), FeatureRegistry.MAGIC_TREE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MAPLE_TREE = register(new ResourceLocation(
            GensokyoOntology.MODID, "maple_tree"), Feature.TREE.withConfiguration(
                    new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.MAPLE_LOG.get().getDefaultState()),
                            new SimpleBlockStateProvider(BlockRegistry.SAKURA_LEAVES.get().getDefaultState()),
                            new BlobFoliagePlacer(FeatureSpread.create(4), FeatureSpread.create(8), 10),
                            new FancyTrunkPlacer(7, 3, 5),
                            new TwoLayerFeature(2,3,2)).setIgnoreVines().build()));

    public static final ConfiguredFeature<BigMushroomFeatureConfig, ?> HUGE_BLUE_MUSHROOM = register(new ResourceLocation(
            GensokyoOntology.MODID, "huge_blue_mushroom"), Feature.HUGE_RED_MUSHROOM.withConfiguration(
                  new BigMushroomFeatureConfig(new SimpleBlockStateProvider(BlockRegistry.BLUE_MUSHROOM_BLOCK.get().getDefaultState()),
                          new SimpleBlockStateProvider(Blocks.MUSHROOM_STEM.getDefaultState()),6)));

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

    public static final StructureFeature<?, ?> MYSTIA_STRUCTURE = StructureRegistry.MYSTIA_IZAKAYA.get()
            .withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);
    public static final StructureFeature<?, ?> HAKUREI_STRUCTURE = StructureRegistry.HAKUREI_SHRINE.get()
            .withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC,?> register(String key, ConfiguredFeature<FC,?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, feature);
    }

    public static <FC extends IFeatureConfig, F extends Feature<FC>> ConfiguredFeature<FC, F> register(
            ResourceLocation location, ConfiguredFeature<FC, F> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, location, feature);
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
