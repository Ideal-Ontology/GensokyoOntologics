package github.thelawf.gensokyoontology.common.world.layer;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC0Transformer;

/**
 * {@link IC0Transformer} 是{@link net.minecraft.world.gen.layer.traits.IAreaTransformer1}
 * 的一种情况，用于将区元部分 2 × 2 的方块大小放大至 3 × 3的方块大小时确定中间方块的方块状态和生物群系。
 * 一般用于判断河流的生成。
 */
public enum GSKORiverLayer implements IC0Transformer {
    INSTANCE;

    private Registry<Biome> biomeRegistry;
    public GSKORiverLayer setUp(Registry<Biome> biomeRegistry) {
        this.biomeRegistry = biomeRegistry;
        return this;
    }

    @Override
    public int apply(INoiseRandom context, int value) {
        return GSKOLayerUtil.getMistyLake(context, biomeRegistry, value);
    }
}
