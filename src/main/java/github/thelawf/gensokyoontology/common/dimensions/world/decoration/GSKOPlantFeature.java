package github.thelawf.gensokyoontology.common.dimensions.world.decoration;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.DecoratedFeature;
import net.minecraft.world.gen.feature.DecoratedFeatureConfig;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;

import java.util.Random;

public class GSKOPlantFeature extends FlowersFeature {
    // 劇毒少女
    public GSKOPlantFeature(Codec<DecoratedFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean isValidPosition(IWorld world, BlockPos pos, IFeatureConfig config) {
        return false;
    }

    @Override
    public int getFlowerCount(IFeatureConfig config) {
        return 0;
    }

    @Override
    public BlockPos getNearbyPos(Random rand, BlockPos pos, IFeatureConfig config) {
        return null;
    }

    @Override
    public BlockState getFlowerToPlace(Random rand, BlockPos pos, IFeatureConfig confgi) {
        return null;
    }
}
