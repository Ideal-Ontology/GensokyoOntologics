package github.thelawf.gensokyoontology.common.world.layer;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

public enum AddBeastPathLayer implements ICastleTransformer {
    INSTANCE;

    private Registry<Biome> biomeRegistry;

    public AddBeastPathLayer setUp(Registry<Biome> biomeRegistry) {
        this.biomeRegistry = biomeRegistry;
        return this;
    }

    @Override
    public int apply(INoiseRandom context, int north, int west, int south, int east, int center) {
        return 0;
    }
}
