package github.thelawf.gensokyoontology.common.world.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;

public class MagicForestConfig extends GSKOTreeConfig {

    public final GSKOTreeConfig treeProvider;
    public final EclipticFoliageLayerConfig foliageLayer;
    public final int minHeight;
    public final int maxHeight;
    public final int offset;

    public static final BlockStateProvider FOLIAGE_BLOCK = new SimpleBlockStateProvider(BlockRegistry.MAGIC_LEAVES.get().getDefaultState());
    public static final BlockStateProvider TRUNK_BLOCK = new SimpleBlockStateProvider(BlockRegistry.MAGIC_LOG.get().getDefaultState());

    public static final Codec<MagicForestConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            GSKOTreeConfig.CODEC.fieldOf("tree_provider").forGetter(config -> config.treeProvider),
            EclipticFoliageLayerConfig.CODEC.fieldOf("foliage_layer").forGetter(config -> config.foliageLayer),
            Codec.INT.fieldOf("min_height").orElse(10).forGetter(config -> config.maxHeight),
            Codec.INT.fieldOf("max_height").orElse(10).forGetter(config -> config.maxHeight),
            Codec.INT.fieldOf("offset").orElse(3).forGetter(config -> config.offset)
    ).apply(instance, MagicForestConfig::new));

    public MagicForestConfig(GSKOTreeConfig treeProvider, EclipticFoliageLayerConfig foliageLayer, int minHeight, int maxHeight, int offset) {
        super(treeProvider.foliageBlock, treeProvider.trunkBlock, true);
        this.treeProvider = treeProvider;
        this.foliageLayer = foliageLayer;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.offset = offset;
    }


}
