package github.thelawf.gensokyoontology.common.world.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class EclipticFoliageConfig implements IFeatureConfig {
    public static final Codec<EclipticFoliageConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.intRange(0, 16).fieldOf("x_radius").orElse(3).forGetter(config -> config.xRadius),
            Codec.intRange(0, 16).fieldOf("z_radius").orElse(2).forGetter(config -> config.zRadius)
    ).apply(instance, EclipticFoliageConfig::new));

    public final int xRadius;
    public final int zRadius;
    public EclipticFoliageConfig(int xRadius, int zRadius) {
        this.xRadius = xRadius;
        this.zRadius = zRadius;
    }

}
