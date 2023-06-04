package github.thelawf.gensokyoontology.common.world.dimension.feature;

import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.BastionRemantsStructure;
import net.minecraft.world.gen.feature.structure.VillageStructure;
import net.minecraft.world.gen.feature.structure.VillagesPools;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;

public class GSKOFeatures {

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> SAKURA_TREE = register(
            "sakura_tree", Feature.TREE.withConfiguration(
                    new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.SAKURA_LOG.get().getDefaultState()),
                            new SimpleBlockStateProvider(BlockRegistry.SAKURA_LEAVES.get().getDefaultState()),
                            new FancyFoliagePlacer(FeatureSpread.create(2), FeatureSpread.create(1), 5),
                            new FancyTrunkPlacer(8,3,2),
                            new TwoLayerFeature(1,1,2)).setIgnoreVines().build()));

    public static final ConfiguredFeature<?,?> LYCORIS = register(
            "lycoris",Feature.FLOWER.withConfiguration(
                    new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(
                            BlockRegistry.LYCORIS_RADIATA.get().getDefaultState()),
                            SimpleBlockPlacer.PLACER).tries(3).build()).withPlacement(
                                    Features.Placements.HEIGHTMAP_PLACEMENT));

    public static final ConfiguredFeature<?,?> WASABI = register(
            "wasabi", Feature.RANDOM_PATCH.withConfiguration(
                    new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(
                            BlockRegistry.WASABI_BLOCK.get().getDefaultState()),
                            SimpleBlockPlacer.PLACER).tries(8).build())
                    .withPlacement(Features.Placements.VEGETATION_PLACEMENT)
                    .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).count(3));

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC,?> register(String key, ConfiguredFeature<FC,?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, feature);
    }
}
