package github.thelawf.gensokyoontology.common.util;

import github.thelawf.gensokyoontology.common.libs.logoslib.math.GSKOMathUtil;
import net.minecraft.block.*;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;

import java.util.Random;

public class FeatureUtil {

    public static void fillEllipse(IWorldGenerationReader reader, BlockPos center, Random random, BlockStateProvider state, int radiusX, int radiusZ) {
        // 遍历圆的每个坐标位置，计算当前位置到圆心的距离，判断位置是否在圆内。加0.5是为了使树叶圆形效果更好
        BlockPos.Mutable mutable = center.toMutable();
        for (int x = -radiusX; x <= radiusX; x++) {
            for (int z = -radiusZ; z <= radiusZ; z++) {
                double distance = Math.sqrt(x * x + z * z);
                if (distance <= radiusZ + 0.5) {  //
                    mutable = (BlockPos.Mutable) mutable.add(x, 0, z);
                    placeBlock(reader, mutable, random, state);
                }
            }
        }
    }

    private static void placeBlock(IWorldGenerationReader reader, BlockPos pos, Random random, BlockStateProvider state) {
        BlockState leafState = state.getBlockState(random, pos);
        reader.setBlockState(pos, leafState, 3);
    }

    public static void placeLinealTrunks(IWorldGenerationReader reader, Random random, BlockPos start, BlockStateProvider state, int width,int height) {
        BlockPos end = new BlockPos(start.getX() + width, start.getY() + height,0);
        float distance = start.manhattanDistance(end);
        for (int i = 0; i < (int) distance; i++) {
            BlockPos pos = GSKOMathUtil.lerp(i / distance, start, end);
            placeBlock(reader, pos, random, state);
        }
    }
}
