package github.thelawf.gensokyoontology.common.world.layer;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.Layer;
import net.minecraft.world.gen.layer.SmoothLayer;
import net.minecraft.world.gen.layer.ZoomLayer;

import javax.annotation.Nonnull;
import java.util.function.LongFunction;

public class GSKOLayerUtil extends Layer {

    private final LazyArea area;

    public GSKOLayerUtil(IAreaFactory<LazyArea> lazyAreaFactoryIn) {
        super(() -> null);
        this.area = lazyAreaFactoryIn.make();
    }

    public static Layer makeLayers(long seed, Registry<Biome> registry) {
        IAreaFactory<LazyArea> areaFactory = buildBiomes((context) -> new LazyAreaLayerContext(25, seed, context), registry);
        return new GSKOLayerUtil(areaFactory);
    }


    // 世界生成的主要逻辑——通过该方法里面的IAreaFactory操作区块网格
    public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> buildBiomes(final LongFunction<C> context, Registry<Biome> registry) {
        IAreaFactory<T> biomes = GenerateCommonLayer.INSTANCE.setUp(registry).apply(context.apply(1L));
        biomes = ZoomLayer.FUZZY.apply(context.apply(2000), biomes);
        biomes = ZoomLayer.NORMAL.apply(context.apply(2001), biomes);
        biomes = ZoomLayer.NORMAL.apply(context.apply(2002), biomes);
        biomes = ZoomLayer.NORMAL.apply(context.apply(2003), biomes);
        biomes = ZoomLayer.NORMAL.apply(context.apply(2004), biomes);
        biomes = ZoomLayer.NORMAL.apply(context.apply(2005), biomes);
        biomes = ZoomLayer.NORMAL.apply(context.apply(2007), biomes);
        biomes = SmoothLayer.INSTANCE.apply(context.apply(2008), biomes);

        return biomes;
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
}
