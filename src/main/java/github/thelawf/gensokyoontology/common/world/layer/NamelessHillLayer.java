package github.thelawf.gensokyoontology.common.world.layer;

import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomes;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IBishopTransformer;
import org.jetbrains.annotations.NotNull;

public enum NamelessHillLayer implements IBishopTransformer {
    INSTANCE;

    private Registry<Biome> registry;

    public NamelessHillLayer setup(Registry<Biome> registry) {
        this.registry = registry;
        return this;
    }

    @Override
    public int apply(@NotNull INoiseRandom context, int x, int se, int sw, int ne, int nw) {
        if (se == getId(this.registry, GSKOBiomes.UNTRODDEN_VALLEY_KEY)) {
            return getId(this.registry, GSKOBiomes.NAMELESS_HILL_KEY);
        }

        return x;
    }

    private static int getId(Registry<Biome> biomes, RegistryKey<Biome> key) {
        return biomes.getId(biomes.getValueForKey(key));
    }
}
