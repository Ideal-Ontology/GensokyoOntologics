package github.thelawf.gensokyoontology.common.world.feature.tree;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.biome.BiomeMaker;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class MapleTree extends Tree {

    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
        return GSKOTrees.MAPLE_TREE;
    }
}
