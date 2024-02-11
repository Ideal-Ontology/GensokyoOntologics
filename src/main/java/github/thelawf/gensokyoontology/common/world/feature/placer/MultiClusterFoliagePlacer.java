package github.thelawf.gensokyoontology.common.world.feature.placer;

import com.google.common.collect.ImmutableList;
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
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class MultiClusterFoliagePlacer extends FoliagePlacer {
    private final int count;
    private final int offsetX;
    private final int offsetY;
    private final int offsetZ;

    private final List<BlockPos> positionList;

    public static final Codec<MultiClusterFoliagePlacer> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    FeatureSpread.createCodec(0, 5, 5).fieldOf("offset").forGetter(o -> o.offset),
                    Codec.INT.fieldOf("count").forGetter(o -> o.count),
                    Codec.INT.fieldOf("offsetX").forGetter(o -> o.offsetX),
                    Codec.INT.fieldOf("offsetY").forGetter(o -> o.offsetY),
                    Codec.INT.fieldOf("offsetZ").forGetter(o -> o.offsetZ),
                    Codec.list(BlockPos.CODEC).fieldOf("position_list").forGetter(o -> ImmutableList.copyOf(o.positionList))
            ).apply(instance, MultiClusterFoliagePlacer::new));

    public MultiClusterFoliagePlacer(FeatureSpread offset, int count, int offsetX, int offsetY, int offsetZ, List<BlockPos> positionList) {
        super(FeatureSpread.create((int) ((double) (offsetX + offsetZ) / 2)), offset);
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        this.count = count;
        this.positionList = positionList;
    }

    @Override
    @NotNull
    protected FoliagePlacerType<?> getPlacerType() {
        return null;
    }

    /**
     * @param reader             树叶生成的世界
     * @param random
     * @param config             树木生成配置
     * @param trunkHeight        树干的高度
     * @param foliage
     * @param foliageHeight
     * @param radius             树叶的半径
     * @param foliageSet         所有树叶的集合
     * @param offset             偏移值
     * @param mutableBoundingBox
     */
    @Override
    protected void func_230372_a_(IWorldGenerationReader reader, Random random, BaseTreeFeatureConfig config,
                                  int trunkHeight, Foliage foliage, int foliageHeight, int radius,
                                  Set<BlockPos> foliageSet, int offset, MutableBoundingBox mutableBoundingBox) {
        // 获取树叶应该在何处生成
        BlockPos center = foliage.func_236763_a_().up(offset);

        int x = foliage.func_236764_b_() + this.offsetX;
        int y = foliage.func_236764_b_() + this.offsetY;
        int z = foliage.func_236764_b_() + this.offsetZ;

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
