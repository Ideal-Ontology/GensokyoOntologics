package github.thelawf.gensokyoontology.common.world.layer;

import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomes;
import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomesProvider;
import net.minecraft.util.RegistryKey;
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

        if (x < -1500 + context.random(320) && x >= -500) {
            if (z < -300 && z > -500 - context.random(200)) {
                return GSKOLayerUtil.getId(this.registry, GSKOBiomes.YOUKAI_MOUNTAIN_KEY);
            }
        }

        else if (x > -100 + context.random(150) && x < 400) {
            if (z > -150 && z < 200 - context.random(100)) {
                return GSKOLayerUtil.getId(this.registry, GSKOBiomes.HUMAN_VILLAGE_KEY);
            }
        }
        else if (x > 200 && x < 500 + context.random(220)) {
            if (z > 230 - context.random(50) && z < 800) {
                return GSKOLayerUtil.getId(this.registry, GSKOBiomes.BAMBOO_FOREST_LOST_KEY);
            }
        }
        return 0;
    }

    private int getBiomeByLocation(int x, int z, int xBound1, int xBound2, int zBound1, int zBound2, RegistryKey<Biome> biomeKey) {
        if (x > xBound1 && x < xBound2) {
            if (z > zBound1 && z < zBound2) {
                return GSKOLayerUtil.getId(this.registry, biomeKey);
            }
        }
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
