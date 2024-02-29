package github.thelawf.gensokyoontology.common.world.layer;

import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomes;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC0Transformer;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

/**
 * {@link IC0Transformer} 是{@link net.minecraft.world.gen.layer.traits.IAreaTransformer1}
 * 的一种情况，用于将区元部分 2 × 2 的方块大小放大至 3 × 3的方块大小时确定中间方块的方块状态和生物群系。
 * 一般用于判断河流的生成。<br>
 */
public enum GSKORiverLayer implements ICastleTransformer {
    INSTANCE;

    private Registry<Biome> registry;

    public GSKORiverLayer setUp(Registry<Biome> registry) {
        this.registry = registry;
        return this;
    }

    @Override
    public int apply(INoiseRandom context, int north, int west, int south, int east, int center) {
        int gskoRiver = GSKOBiomeID.getID(registry, GSKOBiomes.GSKO_RIVER_KEY);
        return riverFilter(center) == -1 || riverFilter(north) == -1 || riverFilter(west) == -1 ||
                riverFilter(south) == -1 || riverFilter(east) == -1 ? -1 : gskoRiver;
    }

    private int riverFilter(int biomeId) {
        int mistyLake = GSKOBiomeID.getID(registry, GSKOBiomes.MISTY_LAKE_KEY);
        int gskoRiver = GSKOBiomeID.getID(registry, GSKOBiomes.GSKO_RIVER_KEY);
        return biomeId == mistyLake ? -1 : gskoRiver;
    }
}
