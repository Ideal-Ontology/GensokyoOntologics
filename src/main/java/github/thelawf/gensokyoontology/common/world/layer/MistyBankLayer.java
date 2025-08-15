package github.thelawf.gensokyoontology.common.world.layer;

import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomes;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;
import org.jetbrains.annotations.NotNull;

public enum MistyBankLayer implements ICastleTransformer {
    INSTANCE;
    private Registry<Biome> registry;

    MistyBankLayer() {
    }

    @Override
    public int apply(@NotNull INoiseRandom context, int north, int west, int south, int east, int center) {
        if (center != getId(this.registry, GSKOBiomes.MISTY_LAKE_KEY)) {
            if (north == getId(this.registry, GSKOBiomes.MISTY_LAKE_KEY) ||
                    west == getId(this.registry, GSKOBiomes.MISTY_LAKE_KEY)||
                    south == getId(this.registry, GSKOBiomes.MISTY_LAKE_KEY) ||
                    east == getId(this.registry, GSKOBiomes.MISTY_LAKE_KEY)) {
                return getId(this.registry, GSKOBiomes.MISTY_BANK_KEY);
            }
        }
        return center;
    }

    private static int getId(Registry<Biome> biomes, RegistryKey<Biome> key) {
        return biomes.getId(biomes.getValueForKey(key));
    }

    public MistyBankLayer setup(Registry<Biome> registry) {
        this.registry = registry;
        return this;
    }
}
