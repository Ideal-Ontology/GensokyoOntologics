package github.thelawf.gensokyoontology.common.util;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.dimensions.layer.GenerateCommonLayer;
import github.thelawf.gensokyoontology.common.dimensions.world.biome.GSKOBiomes;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeRegistry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.Layer;
import net.minecraft.world.gen.layer.ZoomLayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
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
    public static <T extends IArea, C extends IExtendedNoiseRandom<T>> IAreaFactory<T> buildBiomes(final LongFunction<C> seed, Registry<Biome> registry) {
        IAreaFactory<T> biomes = GenerateCommonLayer.INSTANCE.setUp(registry).apply(seed.apply(1L));
        biomes = ZoomLayer.NORMAL.apply(seed.apply(1000L), biomes);
        biomes = ZoomLayer.NORMAL.apply(seed.apply(1001L), biomes);
        biomes = ZoomLayer.NORMAL.apply(seed.apply(1002), biomes);
        biomes = ZoomLayer.NORMAL.apply(seed.apply(1003), biomes);
        biomes = ZoomLayer.NORMAL.apply(seed.apply(1004), biomes);

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
