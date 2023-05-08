package github.thelawf.gensokyoontology.common.world.dimension.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class GSKOTreeConfig implements IFeatureConfig {

    public final int ovalLength;
    public final int ovalWidth;
    public final int height;
    public final float smoothRate;

    public GSKOTreeConfig(int ovalLength, int ovalWidth, int height, float smoothRate) {
        this.ovalLength = ovalLength;
        this.ovalWidth = ovalWidth;
        this.height = height;
        this.smoothRate = smoothRate;
    }

    public static class Builder{
        private final int ovalLength;
        private final int ovalWidth;
        private final int height;
        private final float smoothRate;

        public Builder(int ovalLength, int ovalWidth, int height, float smoothRate) {
            this.ovalLength = ovalLength;
            this.ovalWidth = ovalWidth;
            this.height = height;
            this.smoothRate = smoothRate;
        }

        public GSKOTreeConfig build() {
            return new GSKOTreeConfig(this.ovalLength, this.ovalWidth,
                    this.height, this.smoothRate);
        }
    }
}
