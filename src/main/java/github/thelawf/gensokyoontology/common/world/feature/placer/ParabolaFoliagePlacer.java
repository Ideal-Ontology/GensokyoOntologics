package github.thelawf.gensokyoontology.common.world.feature.placer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;

import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * 从现实中的银杏树得来的灵感。将树叶分为若干层，每一层以树叶中心点为圆心径向“斜抛”出树叶，形成一段抛物线轨迹，并在该轨迹上生成树叶。
 * 轮辐：spoke
 */
public class ParabolaFoliagePlacer extends FoliagePlacer {
    /**
     * 该字段决定该树叶的层数及每层对应的斜抛运动抛物线参数
     */
    private final List<ParabolaConfig> parabolaLayers;

    public static final Codec<ParabolaFoliagePlacer> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            FeatureSpread.createCodec(3, 6, 5).fieldOf("radius").forGetter(o -> o.radius),
            FeatureSpread.createCodec(2, 3, 3).fieldOf("offset").forGetter(o -> o.offset),
            Codec.list(ParabolaConfig.CODEC).fieldOf("parabolaLayers").forGetter(placer -> placer.parabolaLayers)
    ).apply(instance, ParabolaFoliagePlacer::new));

    public ParabolaFoliagePlacer(FeatureSpread radius, FeatureSpread offset, List<ParabolaConfig> parabolaLayers) {
        super(radius, offset);
        this.parabolaLayers = parabolaLayers;
    }


    @Override
    protected FoliagePlacerType<?> getPlacerType() {
        return null;
    }

    /**
     * 1. 此方法MCP名为 generateFoliage() <br>
     * 2. 使用 {@link Foliage#func_236763_a_() func_236763_a_() -> Foliage.getCenter()} 来获取树叶的生成中心点。树干放置器中会返回一个树叶列表，表示哪些地方可以作为这个中心点生成树叶。
     * 3. 局部变量 thetaDeg 表示斜抛方向与当前径向方向（通过parabolaCount确定）的夹角。
     */
    @Override
    protected void func_230372_a_(IWorldGenerationReader reader, Random random, BaseTreeFeatureConfig config,
                                  int trunkHeight, Foliage foliage, int foliageHeight, int radius,
                                  Set<BlockPos> leaves, int offset, MutableBoundingBox mutableBoundingBox) {
        BlockPos pos = foliage.func_236763_a_(); // foliage.getCenter()
        this.parabolaLayers.forEach(parabolaConfig -> {
            int layerLevel = parabolaConfig.layerLevel;
            int parabolaCount = parabolaConfig.parabolaCount;
            float thetaDeg = parabolaConfig.pitchDeg;

            int foliageX = 0;
            int foliageY = 0;
            for (int i = 0; i < parabolaCount; i++) {
                Vector3d shootDirection = parabolaConfig.getShootVec(i);
            }
        });
    }

    @Override
    public int func_230374_a_(Random random, int i, BaseTreeFeatureConfig config) {
        return config.trunkPlacer.getHeight(random);
    }

    @Override
    protected boolean func_230373_a_(Random p_230373_1_, int p_230373_2_, int p_230373_3_, int p_230373_4_, int p_230373_5_, boolean p_230373_6_) {
        return false;
    }
}
