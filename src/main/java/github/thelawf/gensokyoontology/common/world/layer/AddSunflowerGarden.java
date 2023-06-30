package github.thelawf.gensokyoontology.common.world.layer;

import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

public enum AddSunflowerGarden implements ICastleTransformer {
    INSTANCE;

    @Override
    public int apply(INoiseRandom context, int north, int west, int south, int east, int center) {
        return 0;
    }

    @Override
    public <R extends IArea> IAreaFactory<R> apply(IExtendedNoiseRandom<R> context, IAreaFactory<R> areaFactory) {
        return ICastleTransformer.super.apply(context, areaFactory);
    }

    @Override
    public int apply(IExtendedNoiseRandom<?> context, IArea area, int x, int z) {
        return ICastleTransformer.super.apply(context, area, x, z);
    }

    @Override
    public int getOffsetX(int x) {
        return ICastleTransformer.super.getOffsetX(x);
    }

    @Override
    public int getOffsetZ(int z) {
        return ICastleTransformer.super.getOffsetZ(z);
    }
}
