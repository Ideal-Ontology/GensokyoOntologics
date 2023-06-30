package github.thelawf.gensokyoontology.common.world.layer;

import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomes;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.layer.traits.IBishopTransformer;


public enum AddMagicForestLayer implements IBishopTransformer {
    INSTANCE;

    private Registry<Biome> registry;
    public AddMagicForestLayer setUp(Registry<Biome> registry) {
        this.registry = registry;
        return this;
    }

    @Override
    public int apply(INoiseRandom context, int x, int southEast, int p_202792_4_, int p_202792_5_, int p_202792_6_) {
        return GSKOLayerUtil.getId(this.registry, GSKOBiomes.MAGIC_FOREST_KEY);
    }

    @Override
    public <R extends IArea> IAreaFactory<R> apply(IExtendedNoiseRandom<R> context, IAreaFactory<R> areaFactory) {
        return IBishopTransformer.super.apply(context, areaFactory);
    }

    @Override
    public int apply(IExtendedNoiseRandom<?> context, IArea area, int x, int z) {
        return IBishopTransformer.super.apply(context, area, x, z);
    }

    @Override
    public int getOffsetX(int x) {
        return IBishopTransformer.super.getOffsetX(x);
    }

    @Override
    public int getOffsetZ(int z) {
        return IBishopTransformer.super.getOffsetZ(z);
    }
}
