package github.thelawf.gensokyoontology.common.block.nature;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class MagicLeaves extends LeavesBlock {
    public MagicLeaves() {
        super(Properties.from(Blocks.ACACIA_LEAVES).sound(SoundType.PLANT));
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        super.randomTick(state, worldIn, pos, random);
    }
}
