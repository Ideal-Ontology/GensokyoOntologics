package github.thelawf.gensokyoontology.common.world.dimension.biome;

import com.mojang.serialization.Codec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;

import java.util.List;

public class GSKOBiomeDistributor extends BiomeProvider {
    protected GSKOBiomeDistributor(List<Biome> biomes) {
        super(biomes);
    }

    @Override
    protected Codec<? extends BiomeProvider> getBiomeProviderCodec() {
        return null;
    }

    @Override
    public BiomeProvider getBiomeProvider(long seed) {
        return null;
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z) {
        return null;
    }
}
