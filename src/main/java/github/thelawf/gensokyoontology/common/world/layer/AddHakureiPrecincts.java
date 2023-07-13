package github.thelawf.gensokyoontology.common.world.layer;

import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IAreaTransformer1;

public enum AddHakureiPrecincts implements IAreaTransformer1 {
    INSTANCE;
    @Override
    public int apply(IExtendedNoiseRandom<?> context, IArea area, int x, int z) {
        return 0;
    }

    @Override
    public int getOffsetX(int x) {
        return 0;
    }

    @Override
    public int getOffsetZ(int z) {
        return 0;
    }
}
