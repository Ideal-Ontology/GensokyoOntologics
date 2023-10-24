package github.thelawf.gensokyoontology.common.util;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.util.logos.math.GSKOMathUtil;
import net.minecraft.block.*;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;

import java.util.Random;
import java.util.Set;

public class FeatureUtil {

    public static void fillEllipse(IWorldGenerationReader reader, BlockPos center, Random random, BlockStateProvider state, int radiusX, int radiusZ) {
        // 遍历圆的每个坐标位置，计算当前位置到圆心的距离，判断位置是否在圆内。加0.5是为了使树叶圆形效果更好
        BlockPos.Mutable mutable = center.toMutable();
        for (int x = -radiusX; x <= radiusX; x++) {
            for (int z = -radiusZ; z <= radiusZ; z++) {
                double distance = Math.sqrt(x * x + z * z);
                if (distance <= radiusZ + 0.5) {
                    mutable = (BlockPos.Mutable) mutable.add(x, 0, z);
                    placeBlock(reader, mutable, random, state);
                }
            }
        }
    }

    private static void placeTrunk(IWorldGenerationReader reader, BlockPos pos, Random random, BlockStateProvider state) {
        BlockState trunkState = state.getBlockState(random, pos);
        reader.setBlockState(pos, trunkState, 3);
    }

    private static void placeBlock(IWorldGenerationReader reader, BlockPos pos, Random random, BlockStateProvider state) {
        BlockState blockState = state.getBlockState(random, pos);
        reader.setBlockState(pos, blockState, 3);
    }

    public static void placeDiagonalTrunks(IWorldGenerationReader reader, Random random, BlockPos start, BlockStateProvider state, int width, int height) {
        BlockPos end = new BlockPos(start.getX() + width, start.getY() + height,0);
        float distance = start.manhattanDistance(end);
        for (int i = 0; i < (int) distance; i++) {
            BlockPos pos = GSKOMathUtil.lerp(i / distance, start, end);
            placeBlock(reader, pos, random, state);
        }
    }

    public static void placeStraightTruncks(IWorldGenerationReader reader, Random random, BlockPos start, BlockStateProvider state, int height) {
        for (int i = 0; i < height; i++) {
            placeBlock(reader, new BlockPos(start.getX(), start.getY() + i, start.getZ()), random, state);
        }
    }

    public boolean isBoundaryBiome(Biome centerBiome, Biome surroundBiome) {
        // 自定义判断逻辑，根据生物群系的分类或其他属性来判断是否位于分界线上
        // 这里以生物群系分类不同为例
        return centerBiome.getRegistryName() == null && surroundBiome.getRegistryName() == null &&
                centerBiome.getRegistryName().equals(surroundBiome.getRegistryName()) &&
                centerBiome.getRegistryName().equals(new ResourceLocation(GensokyoOntology.MODID, "youkai_mountain"));
    }

    public static Direction getValidDirection(ISeedReader reader, BlockPos blockPos, Set<Block> blocks) {
        return getValidDirection(reader, blockPos, blocks, 1);
    }
    public static Direction getValidDirection(ISeedReader reader, BlockPos blockPos, Set<Block> blocks, int count) {
        for (int i = 0; i < count; i++) {
            if (blocks.contains(reader.getBlockState(blockPos.east(i)).getBlock())) {
                return Direction.EAST;
            }
            else if (blocks.contains(reader.getBlockState(blockPos.south(i)).getBlock())) {
                return Direction.SOUTH;
            }
            else if (blocks.contains(reader.getBlockState(blockPos.west(i)).getBlock())) {
                return Direction.WEST;
            }
            else if (blocks.contains(reader.getBlockState(blockPos.north(i)).getBlock())) {
                return Direction.NORTH;
            }
        }
        return Direction.UP;
    }

    public static void genRandContour(ISeedReader reader, BlockPos pos, Set<Block> blocks,
                                      int repetition, int step) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < repetition; j++) {
                switch (i) {
                    case 0:
                        pos.add(step, pos.getY(), pos.getZ()+1);
                        break;
                    case 1:
                        pos.add(pos.getX() + 1, pos.getY(), step);
                        break;
                    case 2:
                        break;
                }
            }
        }
    }
}
