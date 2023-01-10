package github.thelawf.gensokyoontology.common.util;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.Layer;

import javax.annotation.Nonnull;
import java.util.function.LongFunction;

public class GSKOLayerUtil extends Layer {

    private final LazyArea area;

    public GSKOLayerUtil(IAreaFactory<LazyArea> lazyAreaFactoryIn) {
        super(lazyAreaFactoryIn);
        this.area = lazyAreaFactoryIn.make();
    }

    public static Layer makeLayers(long seed, Registry<Biome> registry) {
        IAreaFactory<LazyArea> areaFactory = buildBiomes((context) -> new LazyAreaLayerContext(25, seed, context), registry);
        return new GSKOLayerUtil(areaFactory);
    }


    private static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> buildBiomes(final LongFunction<C> seed, Registry<Biome> registry) {
        return null;
    }

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
