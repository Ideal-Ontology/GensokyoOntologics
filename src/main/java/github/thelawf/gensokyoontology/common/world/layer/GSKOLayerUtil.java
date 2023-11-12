package github.thelawf.gensokyoontology.common.world.layer;

import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomes;
import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomesProvider;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeRegistry;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.*;
import net.minecraft.world.gen.layer.traits.IAreaTransformer1;

import javax.annotation.Nonnull;
import java.util.function.LongFunction;

public class GSKOLayerUtil extends Layer {

    private final LazyArea area;

    public GSKOLayerUtil(IAreaFactory<LazyArea> lazyAreaFactoryIn) {
        super(() -> null);
        this.area = lazyAreaFactoryIn.make();
    }

    public static Layer makeGSKOLayers(long seed, Registry<Biome> registry) {
        IAreaFactory<LazyArea> areaFactory = buildBiomes((context) -> new LazyAreaLayerContext(25, seed, context), registry);
        return new GSKOLayerUtil(areaFactory);
    }


    /**
     * 世界生成的主要逻辑——通过该方法里面的IAreaFactory操作区块网格 <br>
     * Biome 数字id 的位置：{@link BiomeRegistry} <br>
     * 原版生物群系Layer 的生成逻辑的位置： {@link LayerUtil#setupOverworldLayer(boolean, int, int, LongFunction)}
     */
    public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> buildBiomes(final LongFunction<C> context, Registry<Biome> registry) {
        IAreaFactory<T> area = GenerateCommonLayer.INSTANCE.setUp(registry).apply(context.apply(1L));
        area = ZoomLayer.FUZZY.apply(context.apply(1000L), area);
        area = ZoomLayer.NORMAL.apply(context.apply(1001L), area);
        area = ZoomLayer.NORMAL.apply(context.apply(1002L), area);
        area = ZoomLayer.NORMAL.apply(context.apply(1003L), area);

        area = ZoomLayer.NORMAL.apply(context.apply(1004L), area);
        area = ZoomLayer.NORMAL.apply(context.apply(1005L), area);
        area = ZoomLayer.NORMAL.apply(context.apply(1006L), area);

        IAreaFactory<T> river = repeat(1000L, ZoomLayer.NORMAL, area, 0, context);
        // river = GSKORiverLayer.INSTANCE.apply(context.apply(7L), area);
        river = StartRiverLayer.INSTANCE.apply(context.apply(100L), river);
        river = AddMistyLakeLayer.INSTANCE.apply(context.apply(30L), area);
        river = repeat(1000L, ZoomLayer.NORMAL, area, 2, context);
        river = repeat(1000L, ZoomLayer.NORMAL, area, 2, context);

        /* RiverLayer 里的 p_151730_0_表示海洋的生物群系数字ID，使用riverFilter 先判断是否是海洋，如果是海洋，
         * 则直接返回海洋的生物群系数字ID，否则进行奇偶性判断，如果为奇数表示非生物群系（-1），偶数表示河流的生物群系数字ID（6）
         */
        river = RiverLayer.INSTANCE.apply(context.apply(1L), river);
        river = SmoothLayer.INSTANCE.apply(context.apply(10L), river);
        area = SmoothLayer.INSTANCE.apply(context.apply(3008), area);
        area = MixRiverLayer.INSTANCE.apply(context.apply(3001L), area, river);

        return area;
    }

    public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> repeat(long seed, IAreaTransformer1 parent, IAreaFactory<T> p_202829_3_, int count, LongFunction<C> contextFactory) {
        IAreaFactory<T> iareafactory = p_202829_3_;

        for (int i = 0; i < count; ++i) {
            iareafactory = parent.apply(contextFactory.apply(seed + (long) i), iareafactory);
        }

        return iareafactory;
    }

    @SuppressWarnings("all")
    @Override
    @Nonnull
    public Biome func_242936_a(Registry<Biome> biomes, int x, int z) {
        int id = this.area.getValue(x, z);
        Biome biome = biomes.getByValue(id);

        if (biome == null)
            throw new IllegalStateException("Unknown biome id emitted by layers: " + id);

        return biome;

    }

    public static int getId(Registry<Biome> biomes, RegistryKey<Biome> key) {
        return biomes.getId(biomes.getValueForKey(key));
    }

    public static int getGSKORiver(INoiseRandom random, Registry<Biome> biomes, int value) {
        for (RegistryKey<Biome> biome : GSKOBiomesProvider.GSKO_BIOMES) {
            return Math.max(value, getId(biomes, biome));
        }
        return value;
    }

    public static int getMistyLake(INoiseRandom random, Registry<Biome> biomes, int value) {
        return value == getId(biomes, GSKOBiomes.MAGIC_FOREST_KEY) && random.random(10) > 5 ?
                value : getId(biomes, GSKOBiomes.MISTY_LAKE_KEY);
    }

    public static int getWindGoddessLake(INoiseRandom random, Registry<Biome> biomes, int value) {
        return value == getId(biomes, GSKOBiomes.YOUKAI_MOUNTAIN_KEY) && random.random(10) > 8 ?
                value : getId(biomes, GSKOBiomes.WIND_GODDESS_LAKE_KEY);
    }
}
