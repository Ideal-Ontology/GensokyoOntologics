package github.thelawf.gensokyoontology.common.world.feature.tree;

import github.thelawf.gensokyoontology.common.world.feature.config.BranchesConfig;
import github.thelawf.gensokyoontology.common.world.feature.placer.BranchTrunkPlacer;
import github.thelawf.gensokyoontology.common.world.feature.placer.SphericalFoliagePlacer;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;

public class GSKOTrees {
    /**
     * 特征地物生成目前遇到了三大坑：<br>
     * 1. MC特有的两套注册系统，且非要你注册之后才能用，特有的将面向过程编程变成面向json编程<br>
     * 2. 如果不加withPlacement()，那么树木特征将会生成在每个区块的（0，0）位置，但是：<br>
     * 3. 世界生成注册绑定的树木特征和树苗方块绑定的树木特征的类型不一样，解决方法是直接把两种不同的树木特征泛型
     * 都写上，树苗方块绑定ConfiguredFeature<BaseTreeFeatureConfig, ?>, 世界生成注册绑定 ConfiguredFeature<?, ?>
     */
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MAPLE_TREE = Feature.TREE.withConfiguration(
            new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.MAPLE_LOG.get().getDefaultState()),
                    new SimpleBlockStateProvider(BlockRegistry.MAPLE_LEAVES.get().getDefaultState()),
                    new BlobFoliagePlacer(FeatureSpread.create(2), FeatureSpread.create(1), 2),
                    new BranchTrunkPlacer(7, 2, 2, 5, new BranchesConfig(3, 2, 5, 2, 0.23, 0.23), false),
                    new TwoLayerFeature(1, 1, 2)).setIgnoreVines().build());
    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MAGIC_TREE = Feature.TREE.withConfiguration(
            new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.MAGIC_LOG.get().getDefaultState()),
                    new SimpleBlockStateProvider(BlockRegistry.MAGIC_LEAVES.get().getDefaultState()),
                    new SphericalFoliagePlacer(4.5f, 1.5f, FeatureSpread.create(0), 1, 0, -0.25f, 16),
                    new FancyTrunkPlacer(10, 2, 3),
                    new TwoLayerFeature(1, 3, 1)).setIgnoreVines().build());

    // public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> MAGIC_TREE = Feature.TREE.withConfiguration(
    //         new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.MAGIC_LOG.get().getDefaultState()),
    //                 new SimpleBlockStateProvider(BlockRegistry.MAGIC_LEAVES.get().getDefaultState()),
    //                 new SphericalFoliagePlacer(4.5f, 1.5f, FeatureSpread.create(0), 1, 0, -0.25f, 16),
    //                 new BranchTrunkPlacer(10, 2, 3, 3, new BranchesConfig(3, 2, 4, 2, 0.23, 0.23), false),
    //                 new TwoLayerFeature(1, 3, 1)).setIgnoreVines().build());

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> SAKURA_TREE = Feature.TREE.withConfiguration(
            new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.SAKURA_LOG.get().getDefaultState()),
                    new SimpleBlockStateProvider(BlockRegistry.SAKURA_LEAVES.get().getDefaultState()),
                    new FancyFoliagePlacer(FeatureSpread.create(3), FeatureSpread.create(1), 2),
                    new FancyTrunkPlacer(8, 3, 2),
                    new TwoLayerFeature(1, 1, 3)).setIgnoreVines().build());
}
