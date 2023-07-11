package github.thelawf.gensokyoontology.common.world.feature.placer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.thelawf.gensokyoontology.core.PlacerRegistry;
import github.thelawf.gensokyoontology.core.init.BlockRegistry;
import it.unimi.dsi.fastutil.longs.AbstractLong2LongFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.Set;

public class OvalFoliagePlacer extends FoliagePlacer {

    public static final Codec<OvalFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> {
        return func_242830_b(instance).apply(instance, OvalFoliagePlacer::new);
    });

    public OvalFoliagePlacer(FeatureSpread radius, FeatureSpread offset) {
        super(radius, offset);
    }

    @Override
    @NotNull
    protected FoliagePlacerType<?> getPlacerType() {
        return PlacerRegistry.OVAL_FOLIAGE_PLACER.get();
    }

    @Override
    protected void func_230372_a_(IWorldGenerationReader reader, Random random, BaseTreeFeatureConfig config, int height, Foliage foliage, int p_230372_6_, int p_230372_7_, Set<BlockPos> blockPos, int p_230372_9_, MutableBoundingBox p_230372_10_) {
        for (int y = 0; y <= height; y++) {
            int radius = 2; // 树冠的半径

            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    double distance = Math.sqrt(x * x + z * z);
                    if (distance <= radius + 0.5 && distance >= radius - 0.5) {
                        blockPos.add(new BlockPos(x, y, z));
                    }
                }
            }
        }
    }

    @Override
    public int func_230374_a_(Random p_230374_1_, int p_230374_2_, BaseTreeFeatureConfig p_230374_3_) {
        return 0;
    }

    @Override
    protected boolean func_230373_a_(Random p_230373_1_, int p_230373_2_, int p_230373_3_, int p_230373_4_, int p_230373_5_, boolean p_230373_6_) {
        return false;
    }
}
