package github.thelawf.gensokyoontology.common.world.layer;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC1Transformer;

public enum AddWindGoddessLakeLayer implements IC1Transformer {
    INSTANCE;

    private Registry<Biome> biomeRegistry;
    public AddWindGoddessLakeLayer setUp(Registry<Biome> biomeRegistry) {
        this.biomeRegistry = biomeRegistry;
        return this;
    }

    @Override
    public int apply(INoiseRandom context, int value) {
        return GSKOLayerUtil.getWindGoddessLake(context, biomeRegistry, value);
    }

}
