package github.thelawf.gensokyoontology.common.world.surface;

import com.mojang.serialization.Codec;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;

public class YatsugaTakeSurface extends SurfaceBuilder<SurfaceBuilderConfig> {
    public static final BlockState KAOLIN = BlockRegistry.KAOLIN.get().getDefaultState();
    public static final BlockState KAOLINITE = BlockRegistry.KAOLINITE.get().getDefaultState();
    public static final SurfaceBuilderConfig KAOLIN_CONFIG = new SurfaceBuilderConfig(KAOLIN, KAOLINITE, Blocks.GRAVEL.getDefaultState());
    public YatsugaTakeSurface(Codec<SurfaceBuilderConfig> codec) {
        super(codec);
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
        if (noise > 1.75D) {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, SurfaceBuilder.STONE_STONE_GRAVEL_CONFIG);
        } else if (noise > -0.5D) {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, KAOLIN_CONFIG);
        } else {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG);
        }
    }

    public ConfiguredSurfaceBuilder<?> withConfig() {
        return new ConfiguredSurfaceBuilder<>(this, KAOLIN_CONFIG);
    }
}
