package github.thelawf.gensokyoontology.common.world.feature.config;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.placement.SimplePlacement;

import java.util.Random;
import java.util.stream.Stream;

public class ChunkPlacement extends SimplePlacement<ChunkAverageConfig> {

    public ChunkPlacement(Codec<ChunkAverageConfig> codec) {
        super(codec);
    }

    @Override
    protected Stream<BlockPos> getPositions(Random random, ChunkAverageConfig config, BlockPos pos) {
        return null;
    }
}
