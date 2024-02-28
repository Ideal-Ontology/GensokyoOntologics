package github.thelawf.gensokyoontology.common.world.feature.tree;

import com.mojang.serialization.Codec;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.common.util.world.FeatureUtil;
import github.thelawf.gensokyoontology.common.world.feature.config.MagicForestConfig;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.TreeFeature;

import java.util.Random;

public class MagicForestFeature extends GSKOBiomeFeature<MagicForestConfig> {
    public MagicForestFeature(Codec<MagicForestConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, MagicForestConfig config) {
        BlockPos surfacePos = getSurfacePos(reader, pos);
        if (isDirtOrGrassBlock(reader, surfacePos)) return false;
        // if (isOverMaxTreeCountPerChunk(reader, surfacePos, 3)) return false;
        for (int j = 0; j < GSKOMathUtil.randomRange(config.minHeight, config.maxHeight); j++) {
            FeatureUtil.placeTrunkPattern(reader, rand, surfacePos.up(j), config.getTrunk());
        }
        int layerCount = GSKOMathUtil.randomRange(config.foliageLayer.layerCountMin, config.foliageLayer.layerCountMax);

        for (int j = 0; j < layerCount; j++) {
            if (config.foliageLayer.foliageShape.xRadius - j == 1) break;

            BlockPos center = new BlockPos(FeatureUtil.randomOffset(surfacePos.up(config.maxHeight - 2 + j)));
            // FeatureUtil.makeLeafSpheroid(reader, rand, center, surfacePos.getY());
            FeatureUtil.fillEllipse(reader, center, rand, config.getFoliage(),
                    config.foliageLayer.foliageShape.xRadius - j, true);
        }

        return true;
    }

}
