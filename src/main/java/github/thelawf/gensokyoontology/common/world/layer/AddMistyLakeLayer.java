package github.thelawf.gensokyoontology.common.world.layer;

import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomes;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;
import org.jetbrains.annotations.NotNull;

public enum AddMistyLakeLayer implements ICastleTransformer {
    INSTANCE;

    private Registry<Biome> registry;

    public AddMistyLakeLayer setUp(Registry<Biome> biomeRegistry) {
        this.registry = biomeRegistry;
        return this;
    }

    /**
     * 如果东南西北四个角落有任一群系是红魔馆的那片森林的话，就将中间的群系转变为雾之湖
     *
     * @param context 世界地形生成的随机数
     * @param center  中间的生物群系数字id
     * @param east    东边的生物群系数字id
     * @param north   南边的生物群系数字id
     * @param west    西边的生物群系数字id
     * @param south   北边的生物群系数字id
     * @return 转换之后的中间区块的生物群系数字id
     */
    @Override
    public int apply(@NotNull INoiseRandom context, int north, int west, int south, int east, int center) {
        int id = GSKOBiomeID.getID(this.registry, GSKOBiomes.SCARLET_MANSION_PRECINCTS_KEY);
        int mistyLake = GSKOBiomeID.getID(this.registry, GSKOBiomes.asKey(GSKOBiomes.MISTY_LAKE_BIOME));
        return north == id || west == id || south == id || east == id ? center : mistyLake;
    }
}
