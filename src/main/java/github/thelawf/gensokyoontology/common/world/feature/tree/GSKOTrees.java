package github.thelawf.gensokyoontology.common.world.feature.tree;

import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;

public class GSKOTrees {


    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> SAKURA_TREE = FeatureUtils.register("sakura_tree", Feature.TREE,
            new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(BlockRegistry.SAKURA_LOG.get().defaultBlockState()),
                    new FancyTrunkPlacer(8, 3, 2),
                    BlockStateProvider.simple(BlockRegistry.SAKURA_LEAVES.get().defaultBlockState()),
                    new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 2),
                    new TwoLayersFeatureSize(1, 1, 3)).ignoreVines().build());
}
