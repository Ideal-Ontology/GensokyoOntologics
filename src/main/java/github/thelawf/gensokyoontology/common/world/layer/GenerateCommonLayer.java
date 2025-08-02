package github.thelawf.gensokyoontology.common.world.layer;

import com.google.common.collect.ImmutableList;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomes;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.util.List;

import static github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomes.MISTY_LAKE_BIOME;

/**
 * {@link IAreaTransformer0} 是一切生物群系生成的开始，通过噪声函数直接得到一个单一生物群系
 * 温度系数的平滑过渡噪声图。Minecraft原版最开始便是使用该类创建了两张初始地图，一张是海陆分布图，
 * 另一张则是海洋温度图。
 * <p>
 * 在1.16.5的世界生成之前我们需要明白的几个名词：
 * <p>
 * 32 × 32 个区块 -- 区域
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
            GSKOBiomes.SCARLET_MANSION_PRECINCTS_KEY,
            GSKOBiomes.HAKUREI_SHRINE_PRECINCTS_KEY,
            GSKOBiomes.BAMBOO_FOREST_LOST_KEY,
            GSKOBiomes.SUNFLOWER_GARDEN_KEY,
            GSKOBiomes.YOUKAI_MOUNTAIN_KEY,
            GSKOBiomes.MAGIC_FOREST_KEY,
            GSKOBiomes.asKey(MISTY_LAKE_BIOME),
            GSKOBiomes.BEAST_PATH_KEY,
            GSKOBiomes.HIGAN_KEY
    );


    @Override
    public int apply(@NotNull INoiseRandom random, int x, int y) {
        if (x == 0 && y == 0) {
            return getId(registry, GSKOBiomes.HUMAN_VILLAGE_KEY);
        }
        return getCommonBiomeID(random);
    }

    private int getCommonBiomeID(INoiseRandom random) {
        Annotation a = GensokyoOntology.class.getAnnotation(Mod.class);
        return getId(registry, GenerateCommonLayer.commonBiomes.get(
                random.random(GenerateCommonLayer.commonBiomes.size())));
    }

    private static int getId(Registry<Biome> biomes, RegistryKey<Biome> key) {
        return biomes.getId(biomes.getValueForKey(key));
    }
}
