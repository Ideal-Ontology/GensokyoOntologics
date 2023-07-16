package github.thelawf.gensokyoontology.common.world.feature.placer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.thelawf.gensokyoontology.core.PlacerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BranchTrunkPlacer extends AbstractTrunkPlacer {

    private final int width;
    private final int height;

    public static final Codec<BranchTrunkPlacer> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("trunk_height").forGetter(o -> o.baseHeight),
            Codec.INT.fieldOf("branch_min_height").forGetter(o -> o.heightRandA),
            Codec.INT.fieldOf("branch_max_count").forGetter(o -> o.heightRandB),
            Codec.INT.fieldOf("width").forGetter(o -> o.width),
            Codec.INT.fieldOf("height").forGetter(o -> o.height)
    ).apply(instance, BranchTrunkPlacer::new));

    public BranchTrunkPlacer(int baseHeight, int heightRandA, int heightRandB, int width, int height) {
        super(baseHeight, heightRandA, heightRandB);
        this.width = width;
        this.height = height;
    }

    @Override
    protected TrunkPlacerType<?> getPlacerType() {
        // return PlacerRegistry.BRANCH_TRUNK_PLACER;
        return null;
    }

    @Override
    public List<FoliagePlacer.Foliage> getFoliages(IWorldGenerationReader reader, Random rand, int treeHeight, BlockPos pos, Set<BlockPos> logs, MutableBoundingBox bounds, BaseTreeFeatureConfig config) {
        return growBranches(reader, rand, pos, logs, bounds, config, new ArrayList<>());
    }

    private List<FoliagePlacer.Foliage> growBranches(IWorldGenerationReader world, Random random, BlockPos origin, Set<BlockPos> logs, MutableBoundingBox bounds, BaseTreeFeatureConfig config, List<FoliagePlacer.Foliage> leafNodes) {
        if (this.heightRandB > 4 || this.heightRandA < 4)
            return leafNodes;


        double thetaOffset = random.nextDouble() * 2 * Math.PI;

        // Place 1-2 branches
        for (int i = 0; i < this.heightRandB; i++) {
            // Get angle of this branch
            double theta = (((double) i / this.heightRandB) * 2 * Math.PI) + thetaOffset;

            // Add a random offset to the theta
            theta += random.nextDouble() * Math.PI * 0.15;

            // Make branches 3-4 blocks long
            int dist = random.nextInt(3) == 0 ? 4 : 3;

            for (int j = 1; j <= dist; j++) {
                int x = (int) (Math.cos(theta) * j);
                int z = (int) (Math.sin(theta) * j);
                BlockPos local = origin.add(x, 0, z);

                // Get axis based on position
                Direction.Axis axis = getAxisBetween(origin, local);

                // Place branch and add to logs
                func_236913_a_(world, local, config.trunkProvider.getBlockState(random, local).with(RotatedPillarBlock.AXIS, axis), bounds);
                logs.add(local);

                // Add leaves around the branch
                if (j == dist) {
                    leafNodes.add(new FoliagePlacer.Foliage(local.up(1), -2, false));
                }
            }
        }
        return leafNodes;
    }

    public static Direction.Axis getAxisBetween(BlockPos start, BlockPos end) {
        Direction.Axis axis = Direction.Axis.Y;
        int xOffset = Math.abs(end.getX() - start.getX());
        int zOffset = Math.abs(end.getZ() - start.getZ());
        int maxOffset = Math.max(xOffset, zOffset);

        if (maxOffset > 0) {
            if (xOffset == maxOffset) {
                axis = Direction.Axis.X;
            } else {
                axis = Direction.Axis.Z;
            }
        }

        return axis;
    }
}
