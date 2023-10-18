package github.thelawf.gensokyoontology.common.world.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.placement.IPlacementConfig;

public class ChunkAverageConfig implements IPlacementConfig {
    public static final Codec<ChunkAverageConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.intRange(1,5).fieldOf("count").orElse(3).forGetter(o -> o.count)
    ).apply(instance, ChunkAverageConfig::new));

    public final int count;

    public ChunkAverageConfig(int count) {
        this.count = count;
    }

}
