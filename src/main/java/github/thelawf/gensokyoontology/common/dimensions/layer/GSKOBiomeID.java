package github.thelawf.gensokyoontology.common.dimensions.layer;

import github.thelawf.gensokyoontology.common.dimensions.world.biome.GSKOBiomes;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.Layer;

public class GSKOBiomeID {
    public final int gskoForest;
    public final int humanVillage;
    public final int youkaiMountain;
    public final int magicForest;
    public final int formerHellBiome;
    public final int sunflowerGarden;
    public final int bambooForestLost;
    public final int higanBiome;
    public final int sanzuRiver;
    public final int dreamWorldBiome;
    public final int lunarCapitalBiome;
    public final int lunarSurfaceBiome;
    public final int tranquilitySea;

    public GSKOBiomeID(Registry<Biome> biomeIn) {
        this.gskoForest = getID(biomeIn, GSKOBiomes.GSKO_FOREST_KEY);
        this.humanVillage = getID(biomeIn, GSKOBiomes.HUMAN_VILLAGE_KEY);
        this.youkaiMountain = getID(biomeIn, GSKOBiomes.YOUKAI_MOUNTAIN_KEY);
        this.magicForest = getID(biomeIn, GSKOBiomes.MAGIC_FOREST_KEY);
        this.formerHellBiome = getID(biomeIn, GSKOBiomes.FORMER_HELL_KEY);
        this.sunflowerGarden = getID(biomeIn, GSKOBiomes.SUNFLOWER_GARDEN_KEY);
        this.bambooForestLost = getID(biomeIn, GSKOBiomes.BAMBOO_FOREST_OF_LOST_KEY);
        this.higanBiome = getID(biomeIn, GSKOBiomes.HIGAN_BIOME_KEY);
        this.sanzuRiver = getID(biomeIn, GSKOBiomes.SANZU_RIVER_KEY);
        this.dreamWorldBiome = getID(biomeIn, GSKOBiomes.DREAM_WORLD_KEY);
        this.lunarCapitalBiome = getID(biomeIn, GSKOBiomes.LUNAR_CAPITAL_KEY);
        this.lunarSurfaceBiome = getID(biomeIn, GSKOBiomes.LUNAR_SURFACE_KEY);
        this.tranquilitySea = getID(biomeIn, GSKOBiomes.TRANQUILITY_SEA_INSIDE);
    }

    private static int getID(Registry<Biome> biomes, RegistryKey<Biome> biomeKey) {
        return biomes.getId(biomes.getValueForKey(biomeKey));
    }

}
