package github.thelawf.gensokyoontology.common.world.layer;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC1Transformer;

public enum AddMistyLakeLayer implements IC1Transformer {
    INSTANCE;

    private Registry<Biome> biomeRegistry;
    public AddMistyLakeLayer setUp(Registry<Biome> biomeRegistry) {
        this.biomeRegistry = biomeRegistry;
        return this;
    }

    @Override
    public int apply(INoiseRandom context, int value) {
        return GSKOLayerUtil.getMistyLake(context, biomeRegistry, value);
    }

    @Override
    public int getOffsetX(int x) {
        return IC1Transformer.super.getOffsetX(x);
    }

    @Override
    public int getOffsetZ(int z) {
        return IC1Transformer.super.getOffsetZ(z);
    }
}
