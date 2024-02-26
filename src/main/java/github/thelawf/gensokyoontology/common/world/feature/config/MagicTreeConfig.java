package github.thelawf.gensokyoontology.common.world.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;

public class MagicTreeConfig extends GSKOTreeConfig {

    protected final GSKOTreeConfig treeProvider;
    private final int minHeight;
    private final int maxHeight;
    private final int offset;
    // public final int rootCountMin;
    // private final int rootCountMax;
    // private final int branchCountMin;
    // private final int branchCountMax;
    // private final int branchLengthMin;
    // private final int branchLengthMax;
    // private final float slopeMin;
    // private final float slopeMax;


    public static final BlockStateProvider FOLIAGE_BLOCK = new SimpleBlockStateProvider(BlockRegistry.MAGIC_LEAVES.get().getDefaultState());
    public static final BlockStateProvider TRUNK_BLOCK = new SimpleBlockStateProvider(BlockRegistry.MAGIC_LOG.get().getDefaultState());

    public static final Codec<MagicTreeConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            GSKOTreeConfig.CODEC.fieldOf("tree_provider").forGetter(config -> config.treeProvider),
            Codec.INT.fieldOf("min_height").orElse(10).forGetter(config -> config.maxHeight),
            Codec.INT.fieldOf("max_height").orElse(10).forGetter(config -> config.maxHeight),
            Codec.INT.fieldOf("offset").orElse(3).forGetter(config -> config.offset)
    ).apply(instance, MagicTreeConfig::new));

    public MagicTreeConfig(GSKOTreeConfig treeProvider, int minHeight, int maxHeight, int offset) {
        super(treeProvider.foliageBlock, treeProvider.trunkBlock, true);
        this.treeProvider = treeProvider;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.offset = offset;
    }

    public GSKOTreeConfig getTreeProvider() {
        return this.treeProvider;
    }

    public int getMinHeight() {
        return this.minHeight;
    }

    public int getMaxHeight() {
        return this.maxHeight;
    }

}
