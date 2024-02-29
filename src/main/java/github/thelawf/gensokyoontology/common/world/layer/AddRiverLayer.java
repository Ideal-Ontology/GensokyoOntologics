package github.thelawf.gensokyoontology.common.world.layer;

import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomes;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.layer.traits.IAreaTransformer2;
import net.minecraft.world.gen.layer.traits.IDimOffset0Transformer;

public enum AddRiverLayer implements IAreaTransformer2, IDimOffset0Transformer {
    INSTANCE;

    private Registry<Biome> registry;

    public AddRiverLayer setup(Registry<Biome> registry) {
        this.registry = registry;
        return this;
    }

    @Override
    public int apply(INoiseRandom random, IArea landArea, IArea riverArea, int p_215723_4_, int p_215723_5_) {
        int riverId = riverArea.getValue(this.getOffsetX(p_215723_4_), this.getOffsetZ(p_215723_5_));
        if (riverId == GSKOBiomeID.getID(this.registry, GSKOBiomes.GSKO_RIVER_KEY)) {

        }
        return 0;
    }


}
