package github.thelawf.gensokyoontology.common.world.feature.tree;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class MegaRedwoodTree extends Feature<NoFeatureConfig> {
    public MegaRedwoodTree(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        for (int i = 0; i < 40; i++) {
            if (i > 30 && rand.nextInt(40) < 10) {
                return false;
            }
            reader.setBlockState(pos.up(), Blocks.ACACIA_LOG.getDefaultState(), 5);
        }
        return true;
    }
}
