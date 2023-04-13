package github.thelawf.gensokyoontology.common.dimensions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.thelawf.gensokyoontology.common.dimensions.layer.SimpleNoise;
import github.thelawf.gensokyoontology.common.dimensions.world.biome.GSKOBiomesProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Blockreader;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

/**
 * {@link ChunkGenerator} 里面的 func_230352_b()方法是主要的散布维度的方法
 */
public class GSKOChunkGenerator extends ChunkGenerator {

    public static final Logger LOGGER = LogManager.getLogger();
    private final Settings settings;
    private long seed;

    public GSKOChunkGenerator(BiomeProvider provider,long seed, Settings settings) {
        super(provider, new DimensionStructuresSettings(false));
        this.seed = seed;
        this.settings = settings;
        LOGGER.info("Gensokyo Chunk Generator Registered");
    }

    public static final Codec<Settings> SETTINGS_CODEC = RecordCodecBuilder.create(instance ->
        instance.group(
                Codec.INT.fieldOf("base").forGetter(Settings::getBaseHeight),
                Codec.FLOAT.fieldOf("verticalvariance").forGetter(Settings::getVerticalVariance),
                Codec.FLOAT.fieldOf("horizontalvariance").forGetter(Settings::getHorizontalVariance)
        ).apply(instance, Settings::new));

    public static final Codec<GSKOChunkGenerator> CHUNK_GEN_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    BiomeProvider.CODEC.fieldOf("biome_source").forGetter(chunkGenerator -> chunkGenerator.biomeProvider),
                    Codec.LONG.fieldOf("seed").stable().orElseGet(() -> GSKODimensions.seed).forGetter(obj -> obj.seed),
                    SETTINGS_CODEC.fieldOf("settings").forGetter(GSKOChunkGenerator::getSettings)
            ).apply(instance, instance.stable(GSKOChunkGenerator::new)));

    public Registry<Biome> getBiomeRegistry() {
        return ((GSKOBiomesProvider) biomeProvider).getBiomeRegistry();
    }

    public Settings getSettings() {
        return this.settings;
    }

    @Override
    @Nonnull
    protected Codec<? extends ChunkGenerator> func_230347_a_() {
        return CHUNK_GEN_CODEC;
    }

    @Override
    public ChunkGenerator func_230349_a_(long p_230349_1_) {
        return new GSKOChunkGenerator(this.biomeProvider.getBiomeProvider(p_230349_1_), p_230349_1_, this.settings);
    }

    @Override
    public void generateSurface(@NotNull WorldGenRegion region, @NotNull IChunk chunk) {
        BlockState grassBlock = Blocks.GRASS_BLOCK.getDefaultState();
        BlockState stone = Blocks.STONE.getDefaultState();
        BlockState bedrock = Blocks.BEDROCK.getDefaultState();

        ChunkPos chunkPos = chunk.getPos();

        int x,z;
        BlockPos.Mutable positions = new BlockPos.Mutable();

        // 先在y=0的位置铺一层基岩
        for (x = 0; x < 16; x++) {
            for (z = 0; z < 16; z++) {
                chunk.setBlockState(positions.add(x, 0, z), bedrock, true);
            }
        }

        for (x = 0; x < 16; x++) {
            for (z = 0; z < 16; z++) {
                chunk.setBlockState(positions.add(x, 64, z), grassBlock, true);
            }
        }
        long seed = region.getWorld().getSeed();
        LOGGER.info(chunkPos.x + ", " + chunkPos.z);

        // 再使用噪声生成器生成地表的草方块和地下的石头
        for (x = 0; x < 16; x++) {
            for (z = 0; z < 16; z++) {
                int globalX = chunkPos.x * 16 + x;
                int globalZ = chunkPos.z * 16 + z;
                chunk.setBlockState(positions.add(globalX, SimpleNoise.getNoiseHeight(
                        seed, new BlockPos(globalX, 0, globalZ), 64, 60),
                        globalZ), grassBlock, false);
                chunk.setBlockState(positions.add(globalX, SimpleNoise.getNoiseHeight(
                                seed, new BlockPos(globalX, 0, globalZ), 64, 55),
                        globalZ), stone, false);

            }
        }
    }

    @Override
    public void func_230352_b_(@NotNull IWorld world, @NotNull StructureManager structureManager, @NotNull IChunk chunk) {

    }

    @Override
    public int getHeight(int x, int z, Heightmap.@NotNull Type heightmapType) {
        return 0;
    }

    @Override
    @Nonnull
    public IBlockReader func_230348_a_(int p_230348_1_, int p_230348_2_) {
        return new Blockreader(new BlockState[0]);
    }

    private static class Settings {
        private final int baseHeight;
        private final float verticalVariance;
        private final float horizontalVariance;

        public Settings(int baseHeight, float verticalVariance, float horizontalVariance) {
            this.baseHeight = baseHeight;
            this.verticalVariance = verticalVariance;
            this.horizontalVariance = horizontalVariance;
        }

        public float getVerticalVariance() {
            return verticalVariance;
        }

        public int getBaseHeight() {
            return baseHeight;
        }

        public float getHorizontalVariance() {
            return horizontalVariance;
        }
    }
}
