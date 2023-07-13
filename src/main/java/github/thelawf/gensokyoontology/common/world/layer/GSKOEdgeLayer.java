package github.thelawf.gensokyoontology.common.world.layer;

import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomes;
import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomesProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.LayerUtil;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

public class GSKOEdgeLayer {

    public enum YoukaiJukai implements ICastleTransformer{
        INSTANCE;
        private Registry<Biome> registry;
        public YoukaiJukai setup(Registry<Biome> registry) {
            this.registry = registry;
            return this;
        }
        @Override
        public int apply(INoiseRandom context, int north, int west, int south, int east, int center) {
            int youkai_mountain_id = GSKOBiomeID.getID(registry, GSKOBiomes.YOUKAI_MOUNTAIN_KEY);
            if (north == youkai_mountain_id || west == youkai_mountain_id || south == youkai_mountain_id || east == youkai_mountain_id) {
                return context.random(10) > 7 ? center : GSKOBiomeID.getID(registry, GSKOBiomes.YOUKAI_JUKAI_KEY);
            }
            return GSKOBiomeID.getID(registry, GSKOBiomes.GSKO_PLAINS_KEY);
        }
    }

    public enum MistyLake implements ICastleTransformer {
        INSTANCE;

        private Registry<Biome> registry;
        public MistyLake setup(Registry<Biome> registry) {
            this.registry = registry;
            return this;
        }

        @Override
        public int apply(INoiseRandom context, int north, int west, int south, int east, int center) {
            int youkaiMountainId = GSKOBiomeID.getID(registry, GSKOBiomes.YOUKAI_MOUNTAIN_KEY);
            int magicForestId = GSKOBiomeID.getID(registry, GSKOBiomes.MAGIC_FOREST_KEY);
            if (north == youkaiMountainId || west == youkaiMountainId || south == youkaiMountainId || east == youkaiMountainId) {
                if (north == magicForestId || west == magicForestId || south == magicForestId || east == magicForestId) {
                    return GSKOBiomeID.getID(registry, GSKOBiomes.MISTY_LAKE_KEY);
                }
            }
            return context.random(1) == 0 ? youkaiMountainId : magicForestId;
        }
    }
}
