package github.thelawf.gensokyoontology.common.world.feature;

import com.mojang.serialization.Codec;
import github.thelawf.gensokyoontology.common.libs.logoslib.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.common.util.FeatureUtil;
import github.thelawf.gensokyoontology.common.world.feature.config.MagicTreeConfig;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.Vector;

public class MagicTreeFeature extends Feature<NoFeatureConfig> {

    public static final BlockState MAGIC_LOG = BlockRegistry.MAPLE_LOG.get().getDefaultState();
    public static final BlockState MAGIC_LEAVES = BlockRegistry.MAGIC_LEAVES.get().getDefaultState();

    public MagicTreeFeature(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public Codec<ConfiguredFeature<NoFeatureConfig, Feature<NoFeatureConfig>>> getCodec() {
        return super.getCodec();
    }

    @Override
    public ConfiguredFeature<NoFeatureConfig, ?> withConfiguration(NoFeatureConfig config) {
        return super.withConfiguration(config);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        while (pos.getY() > 1 && isAirOrLeaves(reader, pos)) {
            pos = pos.down();
        }

        // TODO: Generate Trunks
        int trunkHeight = GSKOMathUtil.randomRange(8, 16);
        int leavesHeight = GSKOMathUtil.randomRange(3, 5);
        int x = GSKOMathUtil.randomRange(4, 9);
        int y = GSKOMathUtil.randomRange(5, 8);
        int layerOffset = GSKOMathUtil.randomRange(1,3);

        if (pos.getY() > 1 && pos.getY() + 7 + 1 < reader.getHeight()) {
            for (int i = pos.getY(); i < pos.getY() + trunkHeight + 1; i++) {
                reader.setBlockState(new BlockPos(pos.getX(), i, pos.getZ()), MAGIC_LOG, 3);
            }
        }

        // TODO: Generate Leaves With Oval Layers:
        int maxRadius = 7;
        int minRadius = 5;
        for (int i = 0; i < leavesHeight; i++) {
            if (pos.getY() > 1 && pos.getY() + 7 + 1 < reader.getHeight()) {
                for (int j = pos.getY(); j < pos.getY() + trunkHeight + 1; i++) {
                    FeatureUtil.fillEllipse(reader, new BlockPos(pos.getX(), j, pos.getZ()), MAGIC_LOG,
                            maxRadius, minRadius + rand.nextInt(2));
                }
            }

        }

        // if (pos.getX() % rand.nextInt(15) > 10 && pos.getZ() % rand.nextInt(10) == 0) {
        //     generateCanopy(reader.getWorld(), pos);
        //     return true;
        // }
        return false;
    }

    @SuppressWarnings("deprecation")
    private boolean isAirOrLeaves(IWorldGenerationBaseReader reader, BlockPos pos) {
        if (!(reader instanceof IWorldReader)) {
            return reader.hasBlockState(pos, state -> state.isAir() || state.isIn(BlockTags.LEAVES));
        }
        else {
            return reader.hasBlockState(pos, state -> state.canBeReplacedByLeaves((IWorldReader) reader, pos));
        }
    }

    private void generateCanopy(IWorld world, BlockPos pos) {
        BlockState state = Blocks.OAK_LEAVES.getDefaultState();
        world.setBlockState(pos, state, 1);
    }
}
