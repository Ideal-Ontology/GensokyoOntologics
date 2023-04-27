package github.thelawf.gensokyoontology.common.world.layer;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

/**
 * Copy from Tropicraft
 */
public class GSKOBiomeID {
    // public final int GSKO_FOREST;
    // public final int HUMAN_VILLAGE;
    // public final int YOUKAI_MOUNTAIN;
    // public final int MAGIC_FOREST;
    // public final int FORMER_HELL_BIOME;
    // public final int SUNFLOWER_GARDEN;
    // public final int BAMBOO_FOREST_LOST;
    // public final int HIGAN_BIOME;
    // public final int SANZU_RIVER;

    public GSKOBiomeID(Registry<Biome> biomeIn) {
        // this.GSKO_FOREST = getID(biomeIn, GSKOBiomes.GSKO_FOREST_KEY);
        // this.HUMAN_VILLAGE = getID(biomeIn, GSKOBiomes.HUMAN_VILLAGE_KEY);
        // this.YOUKAI_MOUNTAIN = getID(biomeIn, GSKOBiomes.YOUKAI_MOUNTAIN_KEY);
        // this.MAGIC_FOREST = getID(biomeIn, GSKOBiomes.MAGIC_FOREST_KEY);
        // this.FORMER_HELL_BIOME = getID(biomeIn, GSKOBiomes.FORMER_HELL_KEY);
        // this.SUNFLOWER_GARDEN = getID(biomeIn, GSKOBiomes.SUNFLOWER_GARDEN_KEY);
        // this.BAMBOO_FOREST_LOST = getID(biomeIn, GSKOBiomes.BAMBOO_FOREST_OF_LOST_KEY);
        // this.HIGAN_BIOME = getID(biomeIn, GSKOBiomes.HIGAN_BIOME_KEY);
        // this.SANZU_RIVER = getID(biomeIn, GSKOBiomes.SANZU_RIVER_KEY);
    }

    public static int getID(Registry<Biome> biomes, RegistryKey<Biome> biomeKey) {
        return biomes.getId(biomes.getValueForKey(biomeKey));
    }

}
