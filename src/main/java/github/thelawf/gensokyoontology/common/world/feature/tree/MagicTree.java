package github.thelawf.gensokyoontology.common.world.feature.tree;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.*;

import java.util.Random;

public class MagicTree extends Tree {

    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
        return GSKOTrees.MAGIC_TREE;
    }
}
