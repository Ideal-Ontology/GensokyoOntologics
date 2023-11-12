package github.thelawf.gensokyoontology.common.world.layer;

import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomes;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.LayerUtil;
import net.minecraft.world.gen.layer.traits.ICastleTransformer;

public enum BorderBiome implements ICastleTransformer {
    YOUKAI_JUKAI {
        private Registry<Biome> registry;

        public BorderBiome setUp(Registry<Biome> registry) {
            this.registry = registry;
            return this;
        }

        @Override
        public int apply(INoiseRandom context, int north, int west, int south, int east, int center) {
            int youkaiMountain = GSKOBiomeID.getID(registry, GSKOBiomes.YOUKAI_MOUNTAIN_KEY);
            int youkaiJukai = GSKOBiomeID.getID(registry, GSKOBiomes.YOUKAI_JUKAI_KEY);
            return north == youkaiMountain || west == youkaiMountain || south == youkaiMountain || east == youkaiMountain ? center : youkaiJukai;
        }
    },
    UNTRODDEN_VALLEY {
        private Registry<Biome> registry;

        public BorderBiome setUp(Registry<Biome> registry) {
            this.registry = registry;
            return this;
        }

        @Override
        public int apply(INoiseRandom context, int north, int west, int south, int east, int center) {
            int youkaiJukai = GSKOBiomeID.getID(registry, GSKOBiomes.YOUKAI_JUKAI_KEY);
            int untroddenValley = GSKOBiomeID.getID(registry, GSKOBiomes.UNTRODDEN_VALLEY_KEY);
            return north == youkaiJukai || west == youkaiJukai || south == youkaiJukai || east == youkaiJukai ? center : untroddenValley;
        }
    };

    private boolean replaceBiomeEdge(int[] p_151635_1_, int p_151635_2_, int p_151635_3_, int p_151635_4_, int p_151635_5_, int p_151635_6_, int p_151635_7_, int p_151635_8_) {
        if (p_151635_6_ != p_151635_7_) {
            return false;
        } else {
            if (LayerUtil.areBiomesSimilar(p_151635_2_, p_151635_7_) && LayerUtil.areBiomesSimilar(p_151635_3_, p_151635_7_) && LayerUtil.areBiomesSimilar(p_151635_5_, p_151635_7_) && LayerUtil.areBiomesSimilar(p_151635_4_, p_151635_7_)) {
                p_151635_1_[0] = p_151635_6_;
            } else {
                p_151635_1_[0] = p_151635_8_;
            }

            return true;
        }
    }
}
