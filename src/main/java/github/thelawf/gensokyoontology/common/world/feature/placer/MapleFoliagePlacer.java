package github.thelawf.gensokyoontology.common.world.feature.placer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;

import java.util.Random;
import java.util.Set;

public class MapleFoliagePlacer extends FoliagePlacer {

    private final int layerCount;

    public static final Codec<MapleFoliagePlacer> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            FeatureSpread.createCodec(0,4,4).fieldOf("radius").forGetter(o -> o.radius),
            FeatureSpread.createCodec(0,3,3).fieldOf("offset").forGetter(o -> o.offset),
            Codec.INT.fieldOf("layer_count").forGetter(o -> o.layerCount)
    ).apply(instance, MapleFoliagePlacer::new));

    public MapleFoliagePlacer(FeatureSpread radius, FeatureSpread offset, int layerCount) {
        super(radius, offset);
        this.layerCount = layerCount;
    }

    @Override
    protected FoliagePlacerType<?> getPlacerType() {
        return null;
    }

    @Override
    protected void func_230372_a_(IWorldGenerationReader reader, Random random, BaseTreeFeatureConfig config,
                                  int trunkHeight, Foliage foliage, int foliageHeight, int radius,
                                  Set<BlockPos> foliageSet, int offset, MutableBoundingBox mutableBoundingBox) {

    }

    /**
     * getFoliageHeight();
     * @param random 随机值
     * @param i 不知道
     * @param config 生成树的配置项目
     * @return 树叶应该生成的高度
     */
    @Override
    public int func_230374_a_(Random random, int i, BaseTreeFeatureConfig config) {
        return 0;
    }

    @Override
    protected boolean func_230373_a_(Random p_230373_1_, int p_230373_2_, int p_230373_3_, int p_230373_4_, int p_230373_5_, boolean p_230373_6_) {
        return false;
    }
}
