package github.thelawf.gensokyoontology.common.world.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class TrunkConfig {
    public static final Codec<TrunkConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.FLOAT.fieldOf("min_radius").orElse(1F).forGetter(config -> config.minRadius),
            Codec.FLOAT.fieldOf("max_radius").orElse(3.5F).forGetter(config -> config.maxRadius),
            Codec.INT.fieldOf("min_height").orElse(10).forGetter(config -> config.maxHeight),
            Codec.INT.fieldOf("max_height").orElse(10).forGetter(config -> config.maxHeight)
    ).apply(instance, TrunkConfig::new));

    public final float minRadius;
    public final float maxRadius;
    public final int minHeight;
    public final int maxHeight;

    public TrunkConfig(float minRadius, float maxRadius, int minHeight, int maxHeight) {
        this.minRadius = minRadius;
        this.maxRadius = maxRadius;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

}
