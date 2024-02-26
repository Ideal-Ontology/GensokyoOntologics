package github.thelawf.gensokyoontology.common.world.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class RootConfig {
    public static final Codec<RootConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.intRange(0, 8).fieldOf("min_count").orElse(3).forGetter(config -> config.minCount),
            Codec.intRange(0, 8).fieldOf("max_count").orElse(5).forGetter(config -> config.maxCount)
    ).apply(instance, RootConfig::new));

    private final int minCount;
    private final int maxCount;

    public RootConfig(int minCount, int maxCount) {
        this.minCount = minCount;
        this.maxCount = maxCount;
    }


    public int getMin() {
        return this.minCount;
    }

    public int getMax() {
        return this.maxCount;
    }
}
