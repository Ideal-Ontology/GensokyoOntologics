package github.thelawf.gensokyoontology.common.world.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class MagicTreeConfig implements IFeatureConfig {
    public final int width;
    public final int length;
    public final int height;
    public final int offset;

    public final BlockStateProvider foliageBlock;
    public final BlockStateProvider trunkBlock;

    public static final Codec<MagicTreeConfig> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    BlockStateProvider.CODEC.fieldOf("foliage_block").forGetter(config -> config.foliageBlock),
                    BlockStateProvider.CODEC.fieldOf("trunk_block").forGetter(config -> config.trunkBlock),
                    Codec.INT.fieldOf("width").orElse(5).forGetter(config -> config.width),
                    Codec.INT.fieldOf("length").orElse(5).forGetter(config -> config.length),
                    Codec.INT.fieldOf("height").orElse(10).forGetter(config -> config.height),
                    Codec.INT.fieldOf("offset").orElse(3).forGetter(config -> config.offset)
            ).apply(instance, MagicTreeConfig::new));

    public MagicTreeConfig(BlockStateProvider foliageBlock, BlockStateProvider trunkBlock,
                           int width, int length, int height, int offset) {
        this.foliageBlock = foliageBlock;
        this.trunkBlock = trunkBlock;
        this.width = width;
        this.length = length;
        this.height = height;
        this.offset = offset;
    }

    public static class Builder {
        private final int width;
        private final int length;
        private final int offset;
        private final int height;

        private final BlockStateProvider foliageBlock;
        private final BlockStateProvider trunkBlock;

        public Builder(int width, int length, int offset, int height, BlockStateProvider foliageBlock, BlockStateProvider trunkBlock) {
            this.width = width;
            this.length = length;
            this.offset = offset;
            this.height = height;
            this.foliageBlock = foliageBlock;
            this.trunkBlock = trunkBlock;
        }

        public MagicTreeConfig build() {
            return new MagicTreeConfig(this.foliageBlock, this.trunkBlock, this.width, this.length, this.height, this.offset);
        }
    }
}
