package github.thelawf.gensokyoontology.common.world.feature.config;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.WorldDecoratingHelper;
import net.minecraft.world.gen.placement.HeightmapBasedPlacement;
import net.minecraft.world.gen.placement.SimplePlacement;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ChunkPlacement extends HeightmapBasedPlacement<ChunkAverageConfig> {

    public ChunkPlacement(Codec<ChunkAverageConfig> codec) {
        super(codec);
    }

    @Override
    public Stream<BlockPos> getPositions(WorldDecoratingHelper helper, Random rand, ChunkAverageConfig config, BlockPos pos) {
        return IntStream.range(0, 16).mapToObj(value -> {
            int x = rand.nextInt(16) + pos.getX();
            int z = rand.nextInt(16) + pos.getZ();
            return new BlockPos(x, helper.func_242893_a(this.func_241858_a(config), x, z), z);
        });
    }

    @Override
    protected Heightmap.Type func_241858_a(ChunkAverageConfig config) {
        return Heightmap.Type.MOTION_BLOCKING;
    }
}
