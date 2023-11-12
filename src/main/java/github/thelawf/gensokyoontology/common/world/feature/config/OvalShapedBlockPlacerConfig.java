package github.thelawf.gensokyoontology.common.world.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class OvalShapedBlockPlacerConfig implements IFeatureConfig {


    public final BlockStateProvider trunkProvider;
    public final BlockStateProvider foliageProvider;
    public final int ovalLength;
    public final int ovalWidth;
    public final int height;

    public static final Codec<OvalShapedBlockPlacerConfig> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter(obj -> obj.trunkProvider),
                    BlockStateProvider.CODEC.fieldOf("foliage_provider").forGetter(obj -> obj.foliageProvider),
                    Codec.INT.fieldOf("oval_length").orElse(8).forGetter(obj -> obj.ovalLength),
                    Codec.INT.fieldOf("oval_width").orElse(8).forGetter(obj -> obj.ovalWidth),
                    Codec.INT.fieldOf("height").orElse(12).forGetter(obj -> obj.height)
            ).apply(instance, OvalShapedBlockPlacerConfig::new));

    public OvalShapedBlockPlacerConfig(BlockStateProvider trunkProvider, BlockStateProvider foliageProvider, int ovalLength, int ovalWidth, int height) {
        this.ovalLength = ovalLength;
        this.ovalWidth = ovalWidth;
        this.height = height;
        this.trunkProvider = trunkProvider;
        this.foliageProvider = foliageProvider;
    }

    public static class Builder {
        private final int ovalLength;
        private final int ovalWidth;
        private final int height;

        private final BlockStateProvider trunkProvider;
        private final BlockStateProvider foliageProvider;

        public Builder(int ovalLength, int ovalWidth, int height, float smoothRate, BlockStateProvider trunkProvider, BlockStateProvider foliageProvider) {
            this.ovalLength = ovalLength;
            this.ovalWidth = ovalWidth;
            this.height = height;
            this.trunkProvider = trunkProvider;
            this.foliageProvider = foliageProvider;
        }

        public OvalShapedBlockPlacerConfig build() {
            return new OvalShapedBlockPlacerConfig(this.trunkProvider, this.foliageProvider,
                    this.ovalLength, this.ovalWidth, this.height);
        }
    }
}
