package github.thelawf.gensokyoontology.common.dimensions.world.feature;

import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.GiantTrunkPlacer;

public class GSKOConfigFeature {

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MAGIC_TREE = register(
            "magic_tree", Feature.TREE.withConfiguration(
                    (new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(BlockRegistry.MAGIC_LOG.get().getDefaultState()),
                            new SimpleBlockStateProvider(BlockRegistry.MAGIC_LEAVES.get().getDefaultState()),
                            new BlobFoliagePlacer(FeatureSpread.create(10),FeatureSpread.create(18),20),
                            new GiantTrunkPlacer(15, 3,2),
                            new TwoLayerFeature(1,0,1))).setIgnoreVines().build()));

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC,?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, feature);
    }
}
