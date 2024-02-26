package github.thelawf.gensokyoontology.common.world.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class TrunkConfig {
    public static final Codec<TrunkConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.FLOAT.fieldOf("min_radius").orElse(1F).forGetter(config -> config.minRadius),
            Codec.FLOAT.fieldOf("max_radius").orElse(3.5F).forGetter(config -> config.maxRadius)
    ).apply(instance, TrunkConfig::new));

    private final float minRadius;
    private final float maxRadius;

    public TrunkConfig(float minRadius, float maxRadius) {
        this.minRadius = minRadius;
        this.maxRadius = maxRadius;
    }

    public float getMin() {
        return this.minRadius;
    }

    public float getMax() {
        return this.maxRadius;
    }
}
