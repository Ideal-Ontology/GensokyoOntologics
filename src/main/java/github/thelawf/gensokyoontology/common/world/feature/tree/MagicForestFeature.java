package github.thelawf.gensokyoontology.common.world.feature.tree;

import com.mojang.serialization.Codec;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.common.util.world.FeatureUtil;
import github.thelawf.gensokyoontology.common.world.feature.config.MagicForestConfig;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;

import java.util.Random;

public class MagicForestFeature extends GSKOBiomeFeature<MagicForestConfig> {
    public MagicForestFeature(Codec<MagicForestConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, MagicForestConfig config) {
        if (!isDirtOrGrassBlock(reader, pos)) return false;
        // if (isOverMaxTreeCountPerChunk(reader, pos, 3)) return false;
        for (int i = 0; i < GSKOMathUtil.randomRange(config.minHeight, config.maxHeight); i++) {
            FeatureUtil.placeTrunkPattern(reader, rand, pos.up(i), config.getTrunk());
        }
        int layerCount = GSKOMathUtil.randomRange(config.foliageLayer.layerCountMin, config.foliageLayer.layerCountMax);

        for (int i = 0; i < layerCount; i++) {
            BlockPos center = new BlockPos(FeatureUtil.randomOffset(pos.up(config.maxHeight + 1 + i)));
            FeatureUtil.fillEllipse(reader, center, rand, config.getFoliage(),
                    config.foliageLayer.foliageShape.xRadius - i, config.foliageLayer.foliageShape.zRadius - i);
        }

        return true;
    }

}
