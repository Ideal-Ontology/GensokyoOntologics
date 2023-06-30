package github.thelawf.gensokyoontology.common.world.layer;

import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.layer.traits.IBishopTransformer;

public enum AddHakureiForestLayer implements IBishopTransformer {
    INSTANCE;

    @Override
    public <R extends IArea> IAreaFactory<R> apply(IExtendedNoiseRandom<R> context, IAreaFactory<R> areaFactory) {
        return IBishopTransformer.super.apply(context, areaFactory);
    }

    @Override
    public int apply(INoiseRandom context, int x, int southEast, int p_202792_4_, int p_202792_5_, int p_202792_6_) {
        return 0;
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
