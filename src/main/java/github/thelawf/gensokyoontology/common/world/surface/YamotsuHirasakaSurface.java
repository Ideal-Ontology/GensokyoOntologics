package github.thelawf.gensokyoontology.common.world.surface;

import com.mojang.serialization.Codec;
import github.thelawf.gensokyoontology.api.FastNoiseLite;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.datafix.fixes.WorldGenSettings;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

public class YamotsuHirasakaSurface extends SurfaceBuilder<SurfaceBuilderConfig> {
    public static final BlockState GRASS = Blocks.GRASS.getDefaultState();
    public static final BlockState STONE = Blocks.STONE.getDefaultState();
    public static final SurfaceBuilderConfig KAOLIN_CONFIG = new SurfaceBuilderConfig(GRASS, STONE, STONE);
    private final FastNoiseLite baseNoise;
    private final FastNoiseLite voronoiNoise;
    private final FastNoiseLite detailNoise;
    private final FastNoiseLite domainWarpNoise;
    public YamotsuHirasakaSurface(Codec<SurfaceBuilderConfig> codec) {
        super(codec);
        baseNoise = new FastNoiseLite();
        baseNoise.SetNoiseType(FastNoiseLite.NoiseType.Perlin);
        baseNoise.SetFrequency(0.008f);
        baseNoise.SetFractalType(FastNoiseLite.FractalType.FBm);
        baseNoise.SetFractalOctaves(3);

        voronoiNoise = new FastNoiseLite();
        voronoiNoise.SetNoiseType(FastNoiseLite.NoiseType.Cellular);
        voronoiNoise.SetFrequency(0.15f);
        voronoiNoise.SetCellularDistanceFunction(FastNoiseLite.CellularDistanceFunction.Euclidean);
        voronoiNoise.SetCellularReturnType(FastNoiseLite.CellularReturnType.Distance);

        detailNoise = new FastNoiseLite();
        detailNoise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2);
        detailNoise.SetFrequency(0.25f);
        detailNoise.SetFractalType(FastNoiseLite.FractalType.Ridged);
        detailNoise.SetFractalOctaves(5);

        domainWarpNoise = new FastNoiseLite();
        domainWarpNoise.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2);
        domainWarpNoise.SetFrequency(0.02f);
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
        // 获取生成高度
        float height = getTerrainHeight(x, z, seed);
        int terrainHeight = (int) MathHelper.clamp(height, seaLevel - 10, 256);

        // 生成地表层
        BlockPos.Mutable pos = new BlockPos.Mutable();
        for (int y = terrainHeight; y >= 0; --y) {
            pos.setPos(x, y, z);
            BlockState targetBlock = y > seaLevel + 2 ? config.getTop() :
                    y > seaLevel ? config.getUnder() : defaultBlock;

            chunkIn.setBlockState(pos, targetBlock, false);
        }
    }

    private float getTerrainHeight(int worldX, int worldZ, long seed) {
        baseNoise.SetSeed((int) seed);
        voronoiNoise.SetSeed((int) seed + 12345);
        domainWarpNoise.SetSeed((int) seed + 54321);

        float x = worldX * 0.8f;
        float z = worldZ * 0.8f;

        // 基础地形
        float baseHeight = baseNoise.GetNoise(x, z) * 12.0f;

        // 域扭曲
        float warpX = x + domainWarpNoise.GetNoise(x, z) * 15.0f;
        float warpZ = z + domainWarpNoise.GetNoise(x + 100.0f, z - 50.0f) * 15.0f;

        // 峰林生成
        float voronoi = Math.abs(voronoiNoise.GetNoise(warpX, warpZ));
        float hillHeight = Math.max(0.0f, 1.2f - voronoi * 2.5f);
        hillHeight = (float) Math.pow(hillHeight, 3.0) * 25.0f;

        // 表面细节
        float detail = detailNoise.GetNoise(x * 2.0f, z * 2.0f) * 0.5f + 0.5f;
        hillHeight *= detail * 0.8f + 0.2f;

        // 混合计算
        return baseHeight + hillHeight * smoothStep(baseHeight / 15.0f) + 64;
    }

    private float smoothStep(float t) {
        t = MathHelper.clamp(t, 0.0f, 1.0f);
        return t * t * (3.0f - 2.0f * t);
    }

    public ConfiguredSurfaceBuilder<?> withConfig() {
        return new ConfiguredSurfaceBuilder<>(this, KAOLIN_CONFIG);
    }
}
