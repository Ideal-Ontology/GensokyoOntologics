package github.thelawf.gensokyoontology.common.dimensions.layer;

import com.google.common.collect.ImmutableList;
import github.thelawf.gensokyoontology.common.dimensions.world.biome.GSKOBiomes;
import github.thelawf.gensokyoontology.common.dimensions.world.biome.GSKOBiomesProvider;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;

import java.util.List;

/**
 * {@link IAreaTransformer0} 是一切生物群系生成的开始，通过噪声函数直接得到一个单一生物群系
 * 温度系数的平滑过渡噪声图。Minecraft原版最开始便是使用该类创建了两张初始地图，一张是海陆分布图，
 * 另一张则是海洋温度图。
 * <p>
 * 在1.16.5的世界生成之前我们需要明白的几个名词：
 * <p>
 * 32 × 32 × 256 方块大小 -- 区域
 * <p>
 * 16 × 16 × 256 方块大小 -- 区块
 * <p>
 * 4 × 4 × 64 方块大小 -- 区段【以数组的形式存储区元的4×4的方块，所以一个区段内含有64个区元元素】
 * <p>
 * 4 × 4 方块大小 -- 区元
 * <p>
 * 2 × 2 方块大小 -- 区元的最小部分
 * <p>
 * 1 × 1 方块大小 -- Minecraft方块
 */
public enum GenerateCommonLayer implements IAreaTransformer0 {
    INSTANCE;

    GenerateCommonLayer() {
    }

    private Registry<Biome> registry;


    public GenerateCommonLayer setUp(Registry<Biome> registry) {
        this.registry = registry;
        return this;
    }

    private static final List<RegistryKey<Biome>> commonBiomes = ImmutableList.of(
            GSKOBiomes.GSKO_WILDLAND_KEY,
            GSKOBiomes.GSKO_FOREST_KEY,
            GSKOBiomes.MAGIC_FOREST_KEY,
            GSKOBiomes.YOUKAI_MOUNTAIN_KEY,
            GSKOBiomes.HUMAN_VILLAGE_KEY,
            GSKOBiomes.BAMBOO_FOREST_OF_LOST_KEY
    );

    @Override
    public int apply(INoiseRandom random, int x, int y) {
        return getCommonBiomeID(random, commonBiomes);
    }

    private int getCommonBiomeID(INoiseRandom random, List<RegistryKey<Biome>> biomes) {
        return GSKOBiomesProvider.getBiomeID(biomes.get(random.random(biomes.size())), registry);
    }
}
