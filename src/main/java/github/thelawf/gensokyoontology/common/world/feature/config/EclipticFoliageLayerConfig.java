package github.thelawf.gensokyoontology.common.world.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class EclipticFoliageLayerConfig extends EclipticFoliageConfig{
    public static final Codec<EclipticFoliageLayerConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            EclipticFoliageConfig.CODEC.fieldOf("foliage_shape").forGetter(config -> config.foliageShape),
            Codec.intRange(1,16).fieldOf("layer_count_min").orElse(3).forGetter(config -> config.layerCountMin),
            Codec.intRange(1,16).fieldOf("layer_count_max").orElse(5).forGetter(config -> config.layerCountMax)
    ).apply(instance, EclipticFoliageLayerConfig::new));

    public final EclipticFoliageConfig foliageShape;
    public final int layerCountMin;
    public final int layerCountMax;
    public EclipticFoliageLayerConfig(EclipticFoliageConfig foliageShape ,int layerCountMin, int layerCountMax) {
        super(foliageShape.xRadius, foliageShape.zRadius);
        this.foliageShape = foliageShape;
        this.layerCountMin = layerCountMin;
        this.layerCountMax = layerCountMax;
    }

    public static EclipticFoliageLayerConfig create(int xRadius, int zRadius, int layerCountMin, int layerCountMax) {
        return new EclipticFoliageLayerConfig(new EclipticFoliageConfig(xRadius, zRadius), layerCountMin, layerCountMax);
    }
}
