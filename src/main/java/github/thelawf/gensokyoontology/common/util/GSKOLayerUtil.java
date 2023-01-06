package github.thelawf.gensokyoontology.common.util;

import github.thelawf.gensokyoontology.common.dimensions.layer.GSKOBiomeID;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.Layer;
import net.minecraft.world.gen.layer.ZoomLayer;

public class GSKOLayerUtil {
    public static Layer gskoBuildingProcedure(long seed, Registry<Biome> biomes) {
        GSKOBiomeID biomeID = new GSKOBiomeID(biomes);
        //final IAreaFactory<LazyArea> noiseLayer =
        return null;
    }
}
