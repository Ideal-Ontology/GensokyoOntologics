package github.thelawf.gensokyoontology.common.util;

import github.thelawf.gensokyoontology.common.libs.logoslib.math.GSKOMathUtil;
import net.minecraft.block.*;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.ISeedReader;

import java.util.List;
import java.util.Random;

public class FeatureUtil {

    public static void fillEllipse(ISeedReader reader, BlockPos center, BlockState state, int radiusX, int radiusZ) {
        int centerX = center.getX();;
        int centerY = center.getY();
        int centerZ = center.getZ();

        for (int i = centerX - radiusX; i <= centerX + radiusX; i++) {
            for (int j = centerZ - radiusZ; j <= centerZ + radiusZ; j++) {
                double x = (double) (i - centerX) / radiusX;
                double z = (double) (j - centerZ) / radiusZ;
                if (x * x + z * z <= 1) {
                    reader.setBlockState(new BlockPos(x, centerY, z), state, 3);
                }
            }
        }
    }

    public static void placeLinealTrunks(ISeedReader reader, BlockPos start, BlockState state, int width,int height) {
        state.get(BlockStateProperties.AXIS);
        BlockState blockState = Blocks.ACACIA_LOG.getDefaultState().with(BlockStateProperties.AXIS, Direction.Axis.X);
        reader.setBlockState(start, blockState, 3);
        BlockPos end = new BlockPos(start.getX() + width, start.getY() + height,0);
        float distance = start.manhattanDistance(end);
        for (int i = 0; i < (int) distance; i++) {
            BlockPos pos = GSKOMathUtil.lerp(i / distance, start, end);
            reader.setBlockState(pos, state, 4);
        }
    }


}
