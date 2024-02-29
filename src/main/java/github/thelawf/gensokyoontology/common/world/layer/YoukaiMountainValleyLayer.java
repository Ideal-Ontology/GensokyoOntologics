package github.thelawf.gensokyoontology.common.world.layer;

import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomes;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

public enum YoukaiMountainValleyLayer implements ICastleTransformer {
    INSTANCE;

    private Registry<Biome> registry;

    public YoukaiMountainValleyLayer setup(Registry<Biome> registry) {
        this.registry = registry;
        return this;
    }

    @Override
    public int apply(INoiseRandom context, int north, int west, int south, int east, int center) {
        if (east == getId(this.registry, GSKOBiomes.YOUKAI_MOUNTAIN_KEY) ||
            south == getId(this.registry, GSKOBiomes.YOUKAI_MOUNTAIN_KEY) ||
            west == getId(this.registry, GSKOBiomes.YOUKAI_MOUNTAIN_KEY) ||
            north == getId(this.registry, GSKOBiomes.YOUKAI_MOUNTAIN_KEY)) {
            return getId(this.registry, GSKOBiomes.UNTRODDEN_VALLEY_KEY);
        }
        else {
            return center;
        }
    }

    private static int getId(Registry<Biome> biomes, RegistryKey<Biome> key) {
        return biomes.getId(biomes.getValueForKey(key));
    }
}
