package github.thelawf.gensokyoontology.common.block.decoration;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class MapleSaplingBlock extends SaplingBlock {
    public MapleSaplingBlock(Properties properties) {
        super(null, properties);
    }

    @Override
    public void placeTree(ServerWorld serverWorldIn, BlockPos posIn, BlockState stateIn, Random randomIn) {
        super.placeTree(serverWorldIn, posIn, stateIn, randomIn);
    }
}
