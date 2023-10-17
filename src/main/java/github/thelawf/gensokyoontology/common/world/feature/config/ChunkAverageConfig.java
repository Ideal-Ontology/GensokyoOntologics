package github.thelawf.gensokyoontology.common.world.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.placement.IPlacementConfig;

public class ChunkAverageConfig implements IPlacementConfig {
    public static final Codec<ChunkAverageConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("count").orElse(0).forGetter(o -> o.count)
    ).apply(instance, ChunkAverageConfig::new));

    public final int count;

    public ChunkAverageConfig(int count) {
        this.count = count;
    }

}
