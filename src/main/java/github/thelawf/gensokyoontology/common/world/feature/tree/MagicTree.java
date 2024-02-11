package github.thelawf.gensokyoontology.common.world.feature.tree;

import github.thelawf.gensokyoontology.common.world.feature.config.BranchesConfig;
import github.thelawf.gensokyoontology.common.world.feature.placer.BranchTrunkPlacer;
import github.thelawf.gensokyoontology.common.world.feature.placer.SphericalFoliagePlacer;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;

import java.util.Random;

public class MagicTree extends Tree {
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MAGIC_TREE = Feature.TREE.withConfiguration(
            new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.MAGIC_LOG.get().getDefaultState()),
                    new SimpleBlockStateProvider(BlockRegistry.MAGIC_LEAVES.get().getDefaultState()),
                    new SphericalFoliagePlacer(2.5f, 1.5f, FeatureSpread.create(0), 1, 0, -0.25f, 16),
                    new BranchTrunkPlacer(4, 2, 2, 4, new BranchesConfig(3, 1, 5, 1, 0.3, 0.2), false),
                    new TwoLayerFeature(1, 3, 1)).setIgnoreVines().build());

    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
        return MAGIC_TREE;
    }
}
