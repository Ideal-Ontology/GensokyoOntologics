package github.thelawf.gensokyoontology.common.world.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class GSKOTreeConfig implements IFeatureConfig {
    public static final Codec<GSKOTreeConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockStateProvider.CODEC.fieldOf("foliage_block").forGetter(config -> config.foliageBlock),
            BlockStateProvider.CODEC.fieldOf("trunk_block").forGetter(config -> config.trunkBlock),
            Codec.BOOL.fieldOf("ignore_bounding_box").forGetter(o -> o.ignoreBoundingBox)
    ).apply(instance, GSKOTreeConfig::new));

    protected final BlockStateProvider foliageBlock;
    protected final BlockStateProvider trunkBlock;
    protected final boolean ignoreBoundingBox;
    public GSKOTreeConfig(BlockStateProvider foliageBlock, BlockStateProvider trunkBlock, boolean ignoreBoundingBox) {
        this.foliageBlock = foliageBlock;
        this.trunkBlock = trunkBlock;
        this.ignoreBoundingBox = ignoreBoundingBox;
    }

    public static GSKOTreeConfig create(BlockStateProvider foliageBlock, BlockStateProvider trunkBlock, boolean ignoreBoundingBox) {
        return new GSKOTreeConfig(foliageBlock, trunkBlock, ignoreBoundingBox);
    }
    public static GSKOTreeConfig create(BlockStateProvider foliageBlock, BlockStateProvider trunkBlock) {
        return new GSKOTreeConfig(foliageBlock, trunkBlock, true);
    }

    public BlockStateProvider getFoliage() {
        return this.foliageBlock;
    }

    public BlockStateProvider getTrunk() {
        return this.trunkBlock;
    }

    public boolean isIgnoreBoundingBox() {
        return this.ignoreBoundingBox;
    }
}
