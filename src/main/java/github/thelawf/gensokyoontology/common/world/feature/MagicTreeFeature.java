package github.thelawf.gensokyoontology.common.world.feature;

import com.mojang.serialization.Codec;
import github.thelawf.gensokyoontology.common.world.feature.config.MagicTreeConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class MagicTreeFeature extends Feature<MagicTreeConfig> {
    public MagicTreeFeature(Codec<MagicTreeConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, MagicTreeConfig config) {
        if (pos.getX() % rand.nextInt(15) > 10 && pos.getZ() % rand.nextInt(10) == 0) {
            generateCanopy(reader.getWorld(), pos);
            return true;
        }
        return false;
    }

    private void generateCanopy(IWorld world, BlockPos pos) {
        BlockState state = Blocks.OAK_LEAVES.getDefaultState();
        world.setBlockState(pos, state, 1);
    }
}
