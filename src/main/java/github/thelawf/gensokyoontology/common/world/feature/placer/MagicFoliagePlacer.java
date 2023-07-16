package github.thelawf.gensokyoontology.common.world.feature.placer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.thelawf.gensokyoontology.core.PlacerRegistry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;

import java.util.Random;
import java.util.Set;

public class MagicFoliagePlacer extends FoliagePlacer {

    public static final Codec<MagicFoliagePlacer> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            FeatureSpread.createCodec(0,4,4).fieldOf("radius").forGetter(o -> o.radius),
            FeatureSpread.createCodec(0,3,3).fieldOf("offset").forGetter(o -> o.offset)
    ).apply(instance, MagicFoliagePlacer::new));

    public MagicFoliagePlacer(FeatureSpread radius, FeatureSpread offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> getPlacerType() {
        return PlacerRegistry.MAGIC_FOLIAGE_PLACER.get();
    }

    @Override
    protected void func_230372_a_(IWorldGenerationReader reader, Random random, BaseTreeFeatureConfig config,
                                  int trunkHeight, Foliage foliage, int foliageHeight, int radius,
                                  Set<BlockPos> leaves, int offset, MutableBoundingBox mutableBoundingBox) {
        BlockPos pos = foliage.func_236763_a_();
        this.func_236753_a_(reader, random, config, pos, foliage.func_236764_b_(), leaves, 1, foliage.func_236765_c_(), mutableBoundingBox);
        this.func_236753_a_(reader, random, config, pos, foliage.func_236764_b_() + 1, leaves, 0, foliage.func_236765_c_(), mutableBoundingBox);
    }

    @Override
    public int func_230374_a_(Random random, int i, BaseTreeFeatureConfig config) {
        return 0;
    }

    @Override
    protected boolean func_230373_a_(Random p_230373_1_, int p_230373_2_, int p_230373_3_, int p_230373_4_, int p_230373_5_, boolean p_230373_6_) {
        return false;
    }
}
