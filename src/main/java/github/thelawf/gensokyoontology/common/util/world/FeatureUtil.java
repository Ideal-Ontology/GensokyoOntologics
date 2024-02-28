package github.thelawf.gensokyoontology.common.util.world;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import net.minecraft.block.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.feature.TreeFeature;

import java.util.Random;
import java.util.Set;

public class FeatureUtil {

    /**
     * Modified from Twilight Forest.
     */
    public static void fillEllipse(IWorldGenerationReader reader, BlockPos center, Random random, BlockStateProvider state, int radiusX, int radiusZ) {
        // 遍历圆的每个坐标位置，计算当前位置到圆心的距离，判断位置是否在圆内。加0.5是为了使树叶圆形效果更好
        for (int x = center.getX() - radiusX; x <= center.getX() + radiusX; x++) {
            for (int z = center.getZ() - radiusX; z <= center.getZ() + radiusX; z++) {
                if (isInsideCircle(x, center.getY(), z, center.getX(), center.getY(), center.getZ(), radiusX)) {
                    BlockPos pos = new BlockPos(x, center.getY(), z);
                    placeBlock(reader, pos, random, state);
                }
            }
        }
    }

    public static void fillEllipse(IWorldGenerationReader reader, BlockPos center, Random random, BlockStateProvider state, int radius, boolean ignoreTrunk) {
        // 遍历圆的每个坐标位置，计算当前位置到圆心的距离，判断位置是否在圆内。加0.5是为了使树叶圆形效果更好
        for (int x = center.getX() - radius; x <= center.getX() + radius; x++) {
            for (int z = center.getZ() - radius; z <= center.getZ() + radius; z++) {
                if (isInsideCircle(x, center.getY(), z, center.getX(), center.getY(), center.getZ(), radius)) {
                    BlockPos pos = new BlockPos(x, center.getY(), z);
                    if (ignoreTrunk) {
                        placeFoliage(reader, pos, random, state);
                    }
                    else {
                        placeBlock(reader, pos, random, state);
                    }
                }
            }
        }
    }
    public static boolean isInsideCircle(int x, int y, int z, int centerX, int centerY, int centerZ, int radius) {
        return Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2) + Math.pow(z - centerZ, 2) <= Math.pow(radius, 2);
    }

    public static boolean isInsideEllipse(int x, int y, int z, int centerX, int centerY, int centerZ, int semiMajorAxis, int semiMinorAxis, int verticalAxis) {
        return Math.pow(x - centerX, 2) / Math.pow(semiMajorAxis, 2) + Math.pow(y - centerY, 2) / Math.pow(verticalAxis, 2) + Math.pow(z - centerZ, 2) / Math.pow(semiMinorAxis, 2) <= 1;
    }

    /**
     * Copy from Twilight Forest<br>
     * Moves distance along the vector.
     * <p>
     * This goofy function takes a float between 0 and 1 for the angle, where 0 is 0 degrees, .5 is 180 degrees and 1 and 360 degrees.
     * For the tilt, it takes a float between 0 and 1 where 0 is straight up, 0.5 is straight out and 1 is straight down.
     */
    public static BlockPos translate(BlockPos pos, double distance, double angle, double tilt) {
        double rangle = angle * 2.0D * Math.PI;
        double rtilt = tilt * Math.PI;

        return pos.add(
                Math.round(Math.sin(rangle) * Math.sin(rtilt) * distance),
                Math.round(Math.cos(rtilt) * distance),
                Math.round(Math.cos(rangle) * Math.sin(rtilt) * distance)
        );
    }

    private static void placeTrunk(IWorldGenerationReader reader, BlockPos pos, Random random, BlockStateProvider state) {
        BlockState trunkState = state.getBlockState(random, pos);
        reader.setBlockState(pos, trunkState, 3);
    }

    private static void placeBlock(IWorldGenerationReader reader, BlockPos pos, Random random, BlockStateProvider state) {
        reader.setBlockState(pos, state.getBlockState(random, pos), 3);
    }

    private static void placeFoliage(IWorldGenerationReader reader, BlockPos pos, Random random, BlockStateProvider state) {
        if (!isTrunkBlockAt(reader, pos) && !TreeFeature.isReplaceableAt(reader, pos)) return;
        reader.setBlockState(pos, state.getBlockState(random, pos), 3);
    }
    public static boolean isTrunkBlockAt(IWorldGenerationBaseReader reader, BlockPos pos) {
        return reader.hasBlockState(pos, (state) -> state.isIn(BlockTags.LOGS));
    }

    public static void placeDiagonalTrunks(IWorldGenerationReader reader, Random random, BlockPos start, BlockStateProvider state, int width, int height) {
        BlockPos end = new BlockPos(start.getX() + width, start.getY() + height, 0);
        float distance = start.manhattanDistance(end);
        for (int i = 0; i < (int) distance; i++) {
            BlockPos pos = GSKOMathUtil.lerp(i / distance, start, end);
            placeBlock(reader, pos, random, state);
        }
    }

    public static void placeTrunkPattern(ISeedReader reader, Random random, BlockPos start, BlockStateProvider state) {
        int chance = GSKOMathUtil.randomRange(1,2);
        switch (chance) {
            case 1:
                int t1 = GSKOMathUtil.randomRange(2,3);
                BlockPos pos1 = randomOffset(start);
                for (int i = 0; i < t1; i++) placeCrossPattern(reader, random, pos1.up(i), state);
                break;
            default:
            case 2:
                int t2 = GSKOMathUtil.randomRange(2,3);
                BlockPos pos2 = randomOffset(start);
                for (int i = 0; i < t2; i++) place2b2Pattern(reader, random, pos2.up(i), state);
                break;
        }
    }

    public static BlockPos randomOffset(BlockPos pos) {
        int chance = GSKOMathUtil.randomRange(1, 4);
        switch (chance) {
            default:
            case 1:
                return new BlockPos(pos.east());
            case 2:
                return new BlockPos(pos.south());
            case 3:
                return new BlockPos(pos.north());
            case 4:
                return new BlockPos(pos.west());
        }
    }

    /**
     * 生成十字形状的方块组：<br>
     * &nbsp X<br>
     * XXX<br>
     * &nbsp X
     */
    public static void placeCrossPattern(ISeedReader reader, Random random, BlockPos start, BlockStateProvider state) {
        placeBlock(reader, start, random, state);
        placeBlock(reader, start.toMutable().east(1), random, state);
        placeBlock(reader, start.toMutable().south(1), random, state);
        placeBlock(reader, start.toMutable().west(1), random, state);
        placeBlock(reader, start.toMutable().north(1), random, state);
    }

    /**
     * 生成2x2矩形的方块组：<br>
     * XX<br>
     * XX
     */
    public static void place2b2Pattern(ISeedReader reader, Random random, BlockPos start, BlockStateProvider state) {
        placeBlock(reader, start, random, state);
        placeBlock(reader, start.toMutable().west(1), random, state);
        placeBlock(reader, start.toMutable().south(1), random, state);
        placeBlock(reader, start.toMutable().move(1, 0, 1), random, state);
    }

    public static void placeCornerPattern(ISeedReader reader, Random random, BlockPos start, BlockStateProvider state) {
        placeBlock(reader, start, random, state);
        placeBlock(reader, start.toMutable().west(1), random, state);
        placeBlock(reader, start.toMutable().south(1), random, state);
    }

    public static void placeStraightBlocks(IWorldGenerationReader reader, Random random, BlockPos start, BlockStateProvider state, int height) {
        for (int i = 0; i < height; i++) {
            placeBlock(reader, new BlockPos(start.getX(), start.getY() + i, start.getZ()), random, state);
        }
    }

    // Copy from Twilight Forest
    public static void makeLeafSpheroid(IWorldGenerationReader world, Random random, BlockPos centerPos, float xzRadius, float yRadius, float verticalBias, BlockStateProvider state, Set<BlockPos> leaves) {
        float xzRadiusSquared = xzRadius * xzRadius;
        float yRadiusSquared = yRadius * yRadius;
        float superRadiusSquared = xzRadiusSquared * yRadiusSquared;
        placeLeafBlock(world, random, centerPos, state, leaves);

        for (int y = 0; y <= yRadius; y++) {
            if (y > yRadius) continue;

            placeLeafBlock(world, random, centerPos.add( 0,  y, 0), state, leaves);
            placeLeafBlock(world, random, centerPos.add( 0,  y, 0), state, leaves);
            placeLeafBlock(world, random, centerPos.add( 0,  y, 0), state, leaves);
            placeLeafBlock(world, random, centerPos.add( 0,  y, 0), state, leaves);

            placeLeafBlock(world, random, centerPos.add( 0, -y, 0), state, leaves);
            placeLeafBlock(world, random, centerPos.add( 0, -y, 0), state, leaves);
            placeLeafBlock(world, random, centerPos.add( 0, -y, 0), state, leaves);
            placeLeafBlock(world, random, centerPos.add( 0, -y, 0), state, leaves);
        }

        for (int x = 0; x <= xzRadius; x++) {
            for (int z = 1; z <= xzRadius; z++) {
                if (x * x + z * z > xzRadiusSquared) continue;

                placeLeafBlock(world, random, centerPos.add(  x, 0,  z), state, leaves);
                placeLeafBlock(world, random, centerPos.add( -x, 0, -z), state, leaves);
                placeLeafBlock(world, random, centerPos.add( -z, 0,  x), state, leaves);
                placeLeafBlock(world, random, centerPos.add(  z, 0, -x), state, leaves);

                for (int y = 1; y <= yRadius; y++) {
                    float xzSquare = ((x * x + z * z) * yRadiusSquared);

                    if (xzSquare + (((y - verticalBias) * (y - verticalBias)) * xzRadiusSquared) <= superRadiusSquared) {
                        placeLeafBlock(world, random, centerPos.add(  x,  y,  z), state, leaves);
                        placeLeafBlock(world, random, centerPos.add( -x,  y, -z), state, leaves);
                        placeLeafBlock(world, random, centerPos.add( -z,  y,  x), state, leaves);
                        placeLeafBlock(world, random, centerPos.add(  z,  y, -x), state, leaves);
                    }

                    if (xzSquare + (((y + verticalBias) * (y + verticalBias)) * xzRadiusSquared) <= superRadiusSquared) {
                        placeLeafBlock(world, random, centerPos.add(  x, -y,  z), state, leaves);
                        placeLeafBlock(world, random, centerPos.add( -x, -y, -z), state, leaves);
                        placeLeafBlock(world, random, centerPos.add( -z, -y,  x), state, leaves);
                        placeLeafBlock(world, random, centerPos.add(  z, -y, -x), state, leaves);
                    }
                }
            }
        }
    }

    // Copy from Twilight Forest
    public static void makeLeafSpheroid(IWorldGenerationReader world, Random random, BlockPos centerPos, float xzRadius, float yRadius, float verticalBias, BlockStateProvider state) {
        float xzRadiusSquared = xzRadius * xzRadius;
        float yRadiusSquared = yRadius * yRadius;
        float superRadiusSquared = xzRadiusSquared * yRadiusSquared;
        placeLeafBlock(world, random, centerPos, state);

        for (int y = 0; y <= yRadius; y++) {
            if (y > yRadius) continue;

            placeLeafBlock(world, random, centerPos.add( 0,  y, 0), state);
            placeLeafBlock(world, random, centerPos.add( 0,  y, 0), state);
            placeLeafBlock(world, random, centerPos.add( 0,  y, 0), state);
            placeLeafBlock(world, random, centerPos.add( 0,  y, 0), state);

            placeLeafBlock(world, random, centerPos.add( 0, -y, 0), state);
            placeLeafBlock(world, random, centerPos.add( 0, -y, 0), state);
            placeLeafBlock(world, random, centerPos.add( 0, -y, 0), state);
            placeLeafBlock(world, random, centerPos.add( 0, -y, 0), state);
        }

        for (int x = 0; x <= xzRadius; x++) {
            for (int z = 1; z <= xzRadius; z++) {
                if (x * x + z * z > xzRadiusSquared) continue;

                placeLeafBlock(world, random, centerPos.add(  x, 0,  z), state);
                placeLeafBlock(world, random, centerPos.add( -x, 0, -z), state);
                placeLeafBlock(world, random, centerPos.add( -z, 0,  x), state);
                placeLeafBlock(world, random, centerPos.add(  z, 0, -x), state);

                for (int y = 1; y <= yRadius; y++) {
                    float xzSquare = ((x * x + z * z) * yRadiusSquared);

                    if (xzSquare + (((y - verticalBias) * (y - verticalBias)) * xzRadiusSquared) <= superRadiusSquared) {
                        placeLeafBlock(world, random, centerPos.add(  x,  y,  z), state);
                        placeLeafBlock(world, random, centerPos.add( -x,  y, -z), state);
                        placeLeafBlock(world, random, centerPos.add( -z,  y,  x), state);
                        placeLeafBlock(world, random, centerPos.add(  z,  y, -x), state);
                    }

                    if (xzSquare + (((y + verticalBias) * (y + verticalBias)) * xzRadiusSquared) <= superRadiusSquared) {
                        placeLeafBlock(world, random, centerPos.add(  x, -y,  z), state);
                        placeLeafBlock(world, random, centerPos.add( -x, -y, -z), state);
                        placeLeafBlock(world, random, centerPos.add( -z, -y,  x), state);
                        placeLeafBlock(world, random, centerPos.add(  z, -y, -x), state);
                    }
                }
            }
        }
    }

    public static void placeLeafBlock(IWorldGenerationReader world, Random random, BlockPos pos, BlockStateProvider state, Set<BlockPos> leavesPos) {
        if (/*leavesPos.contains(pos) ||*/ !TreeFeature.isReplaceableAt(world, pos))
            return;

        world.setBlockState(pos, state.getBlockState(random, pos), 3);
        leavesPos.add(pos.toImmutable());
    }

    public static void placeLeafBlock(IWorldGenerationReader world, Random random, BlockPos pos, BlockStateProvider state) {
        if (!TreeFeature.isReplaceableAt(world, pos))
            return;
        world.setBlockState(pos, state.getBlockState(random, pos), 3);
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
            } else if (blocks.contains(reader.getBlockState(blockPos.south(i)).getBlock())) {
                return Direction.SOUTH;
            } else if (blocks.contains(reader.getBlockState(blockPos.west(i)).getBlock())) {
                return Direction.WEST;
            } else if (blocks.contains(reader.getBlockState(blockPos.north(i)).getBlock())) {
                return Direction.NORTH;
            }
        }
        return Direction.UP;
    }


    /**
     * Copy from Twilight Forest<br>
     * Get an array of values that represent a line from point A to point B
     */
    public static BlockPos[] getBresenhamArrays(BlockPos src, BlockPos dest) {
        return getBresenhamArrays(src.getX(), src.getY(), src.getZ(), dest.getX(), dest.getY(), dest.getZ());
    }

    /**
     * Copy from Twilight Forest<br>
     * Get an array of values that represent a line from point A to point B
     * todo 1.9 lazify this into an iterable?
     */
    public static BlockPos[] getBresenhamArrays(int x1, int y1, int z1, int x2, int y2, int z2) {
        int i, dx, dy, dz, absDx, absDy, absDz, x_inc, y_inc, z_inc, err_1, err_2, doubleAbsDx, doubleAbsDy, doubleAbsDz;

        BlockPos pixel = new BlockPos(x1, y1, z1);
        BlockPos lineArray[];

        dx = x2 - x1;
        dy = y2 - y1;
        dz = z2 - z1;
        x_inc = (dx < 0) ? -1 : 1;
        absDx = Math.abs(dx);
        y_inc = (dy < 0) ? -1 : 1;
        absDy = Math.abs(dy);
        z_inc = (dz < 0) ? -1 : 1;
        absDz = Math.abs(dz);
        doubleAbsDx = absDx << 1;
        doubleAbsDy = absDy << 1;
        doubleAbsDz = absDz << 1;

        if ((absDx >= absDy) && (absDx >= absDz)) {
            err_1 = doubleAbsDy - absDx;
            err_2 = doubleAbsDz - absDx;
            lineArray = new BlockPos[absDx + 1];
            for (i = 0; i < absDx; i++) {
                lineArray[i] = pixel;
                if (err_1 > 0) {
                    pixel = pixel.up(y_inc);
                    err_1 -= doubleAbsDx;
                }
                if (err_2 > 0) {
                    pixel = pixel.south(z_inc);
                    err_2 -= doubleAbsDx;
                }
                err_1 += doubleAbsDy;
                err_2 += doubleAbsDz;
                pixel = pixel.east(x_inc);
            }
        } else if ((absDy >= absDx) && (absDy >= absDz)) {
            err_1 = doubleAbsDx - absDy;
            err_2 = doubleAbsDz - absDy;
            lineArray = new BlockPos[absDy + 1];
            for (i = 0; i < absDy; i++) {
                lineArray[i] = pixel;
                if (err_1 > 0) {
                    pixel = pixel.east(x_inc);
                    err_1 -= doubleAbsDy;
                }
                if (err_2 > 0) {
                    pixel = pixel.south(z_inc);
                    err_2 -= doubleAbsDy;
                }
                err_1 += doubleAbsDx;
                err_2 += doubleAbsDz;
                pixel = pixel.up(y_inc);
            }
        } else {
            err_1 = doubleAbsDy - absDz;
            err_2 = doubleAbsDx - absDz;
            lineArray = new BlockPos[absDz + 1];
            for (i = 0; i < absDz; i++) {
                lineArray[i] = pixel;
                if (err_1 > 0) {
                    pixel = pixel.up(y_inc);
                    err_1 -= doubleAbsDz;
                }
                if (err_2 > 0) {
                    pixel = pixel.east(x_inc);
                    err_2 -= doubleAbsDz;
                }
                err_1 += doubleAbsDy;
                err_2 += doubleAbsDx;
                pixel = pixel.south(z_inc);
            }
        }
        lineArray[lineArray.length - 1] = pixel;

        return lineArray;
    }
}
