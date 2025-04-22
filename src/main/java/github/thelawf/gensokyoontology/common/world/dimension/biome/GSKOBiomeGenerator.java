package github.thelawf.gensokyoontology.common.world.dimension.biome;

import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class GSKOBiomeGenerator {
    public static void generate() {
        addBiome(GSKOBiomes.YATSUGA_TAKE_BIOME.get(), BiomeManager.BiomeType.WARM, 10);
        addBiome(GSKOBiomes.YAMOTSU_HIRASAKA.get(), BiomeManager.BiomeType.COOL, 8);
    }

    public static void addBiome(Biome biome, BiomeManager.BiomeType type, int weight) {
        RegistryKey<Biome> biomeRegistry = RegistryKey.getOrCreateKey(ForgeRegistries.Keys.BIOMES,
                Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(biome)));
        BiomeManager.addBiome(type, new BiomeManager.BiomeEntry(biomeRegistry, weight));
    }

}
