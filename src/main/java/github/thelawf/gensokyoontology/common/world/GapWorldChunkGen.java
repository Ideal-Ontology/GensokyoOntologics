package github.thelawf.gensokyoontology.common.world;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.world.Blockreader;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import org.jetbrains.annotations.NotNull;

public class GapWorldChunkGen extends ChunkGenerator {
    public GapWorldChunkGen(BiomeProvider provider, DimensionStructuresSettings settings) {
        super(provider, settings);
    }

    @Override
    protected @NotNull Codec<? extends ChunkGenerator> func_230347_a_() {
        return ChunkGenerator.field_235948_a_;
    }

    @Override
    public @NotNull ChunkGenerator func_230349_a_(long p_230349_1_) {
        return new GapWorldChunkGen(this.biomeProvider.getBiomeProvider(p_230349_1_),
                new DimensionStructuresSettings(false));
    }

    @Override
    public void generateSurface(@NotNull WorldGenRegion region, @NotNull IChunk chunk) {

    }

    @Override
    public void func_230352_b_(@NotNull IWorld world, @NotNull StructureManager manager, @NotNull IChunk chunk) {

    }

    @Override
    public int getHeight(int x, int z, Heightmap.Type heightmapType) {
        return 64;
    }

    @Override
    public IBlockReader func_230348_a_(int p_230348_1_, int p_230348_2_) {
        return new Blockreader(new BlockState[0]);
    }
}
