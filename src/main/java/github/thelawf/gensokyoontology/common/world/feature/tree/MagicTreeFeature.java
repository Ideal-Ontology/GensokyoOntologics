package github.thelawf.gensokyoontology.common.world.feature.tree;

import com.mojang.serialization.Codec;
import github.thelawf.gensokyoontology.common.util.world.FeatureUtil;
import github.thelawf.gensokyoontology.common.world.feature.config.GSKOTreeConfig;
import github.thelawf.gensokyoontology.common.world.feature.config.MagicTreeConfig;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.IFeatureConfig;

import java.util.Random;

public class MagicTreeFeature extends GSKOTreeFeatureBase<MagicTreeConfig>{
    public MagicTreeFeature(Codec<MagicTreeConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, MagicTreeConfig config) {
        if (!isDirtOrGrassBlock(reader, pos)) return false;
        if (isOverMaxTreeCountPerChunk(reader, pos, 3)) return false;
        FeatureUtil.fillEllipse(reader, pos, rand, MagicTreeConfig.FOLIAGE_BLOCK, 3, 2);
        return true;
    }

}
