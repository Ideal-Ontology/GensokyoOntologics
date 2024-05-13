package github.thelawf.gensokyoontology.common.world.feature;

import com.mojang.serialization.Codec;
import github.thelawf.gensokyoontology.common.util.world.FeatureUtil;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.LiquidsConfig;

import java.util.Random;
import java.util.Set;

public class WaterfallFeature extends Feature<LiquidsConfig> {

    public WaterfallFeature(Codec<LiquidsConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, LiquidsConfig config) {
        if (!config.acceptedBlocks.contains(reader.getBlockState(pos).getBlock()))
            return false;

        if (reader.getBlockState(pos.up()).getBlock().equals(Blocks.AIR)) {
            int i = 0;
            int j = 0;
            if (config.acceptedBlocks.contains(reader.getBlockState(pos.west()).getBlock())) {
                ++j;
            }

            if (config.acceptedBlocks.contains(reader.getBlockState(pos.east()).getBlock())) {
                ++j;
            }

            if (config.acceptedBlocks.contains(reader.getBlockState(pos.north()).getBlock())) {
                ++j;
            }

            if (config.acceptedBlocks.contains(reader.getBlockState(pos.south()).getBlock())) {
                ++j;
            }

            if (config.acceptedBlocks.contains(reader.getBlockState(pos.down()).getBlock())) {
                ++j;
            }

            int k = 0;
            if (reader.isAirBlock(pos.west())) {
                ++k;
            }

            if (reader.isAirBlock(pos.east())) {
                ++k;
            }

            if (reader.isAirBlock(pos.north())) {
                ++k;
            }

            if (reader.isAirBlock(pos.south())) {
                ++k;
            }

            if (reader.isAirBlock(pos.down())) {
                ++k;
            }

            if (j == config.rockAmount && k == config.holeAmount) {
                reader.setBlockState(pos, config.state.getBlockState(), 3);
                reader.getPendingFluidTicks().scheduleTick(pos, config.state.getFluid(), 0);
                ++i;
            }
            if (rand.nextInt(20) > 18) {
                placeBlocks(reader, 9, pos, config.state.getBlockState(), config.acceptedBlocks);
            }


            return i > 0;
        } else if (config.needsBlockBelow && !config.acceptedBlocks.contains(reader.getBlockState(pos.down()).getBlock())) {
            return false;
        } else {
            return false;
        }

    }

    private void placeBlocks(ISeedReader reader, int size, BlockPos pos, BlockState state, Set<Block> blocks) {
        for (int i = 0; i < size; i++) {
            placeWaterIfValid(reader, i, pos, state, blocks);
        }
    }

    private void placeWaterIfValid(ISeedReader reader, int size, BlockPos pos, BlockState state, Set<Block> blocks) {
        switch (FeatureUtil.getValidDirection(reader, pos, blocks, size)) {
            case EAST:
                reader.setBlockState(pos.east(size), state, 3);
                reader.setBlockState(pos.east(size).up(), state, 3);
                break;
            case SOUTH:
                reader.setBlockState(pos.south(size), state, 3);
                reader.setBlockState(pos.south(size).up(), state, 3);
                break;
            case WEST:
                reader.setBlockState(pos.west(size), state, 3);
                reader.setBlockState(pos.west(size).up(), state, 3);
                break;
            case NORTH:
                reader.setBlockState(pos.north(size), state, 3);
                reader.setBlockState(pos.north(size).up(), state, 3);
                break;
            default:
                reader.setBlockState(pos, state, 2);
                reader.setBlockState(pos.up(), state, 2);
                break;
        }
    }
}
