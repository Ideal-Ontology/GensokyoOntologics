package github.thelawf.gensokyoontology.common.world;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomesProvider;
import net.minecraft.block.BlockState;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Blockreader;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.structure.StructureManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.function.Supplier;


/**
 * {@link ChunkGenerator} 里面的 func_230352_b()方法是主要的散布维度的方法
 */

public final class GSKOChunkGenerator extends NoiseChunkGenerator {

    public static final Logger LOGGER = LogManager.getLogger();
    protected final Supplier<DimensionSettings> dimensionSettings;
    private long seed;


    public GSKOChunkGenerator(BiomeProvider provider, long seed, Supplier<DimensionSettings> settings) {
        super(provider, seed, settings);
        this.seed = seed;
        this.dimensionSettings = settings;
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
                    DimensionSettings.DIMENSION_SETTINGS_CODEC.fieldOf("settings").forGetter(GSKOChunkGenerator::getDimensionSettings)
            ).apply(instance, instance.stable(GSKOChunkGenerator::new)));

    public Registry<Biome> getBiomeRegistry() {
        return ((GSKOBiomesProvider) biomeProvider).getBiomeRegistry();
    }

    private Supplier<DimensionSettings> getDimensionSettings() {
        return this.dimensionSettings;
    }

    @Override
    @Nonnull
    protected Codec<? extends ChunkGenerator> func_230347_a_() {
        return CHUNK_GEN_CODEC;
    }

    @Override
    @Nonnull
    public ChunkGenerator func_230349_a_(long seed) {
        return new GSKOChunkGenerator(this.biomeProvider.getBiomeProvider(seed), seed, dimensionSettings);
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
