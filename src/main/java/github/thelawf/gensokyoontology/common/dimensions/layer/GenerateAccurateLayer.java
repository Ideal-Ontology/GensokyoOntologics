package github.thelawf.gensokyoontology.common.dimensions.layer;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IAreaTransformer1;

/**
 * {@link IAreaTransformer1} 是在原有单纯噪声生成地图的基础上对其进行局部的修改或是进行整体
 * 的放大化操作，该类还可细分为{@link net.minecraft.world.gen.layer.traits.IBishopTransformer}
 * 和 {@link net.minecraft.world.gen.layer.traits.ICastleTransformer} 两种情况。
 */
public enum GenerateAccurateLayer implements IAreaTransformer1 {
    INSTANCE;

    GenerateAccurateLayer() {
    }

    private Registry<Biome> registry;

    public GenerateAccurateLayer setUp(Registry<Biome> registry) {
        this.registry = registry;
        return this;
    }

    @Override
    public int apply(IExtendedNoiseRandom<?> context, IArea area, int x, int z) {

        return 0;
    }

    @Override
    public int getOffsetX(int x) {
        return x | 4;
    }

    @Override
    public int getOffsetZ(int z) {
        return z | 5;
    }
}
