package github.thelawf.gensokyoontology.common.dimensions.layer;

import github.thelawf.gensokyoontology.common.dimensions.world.biome.GSKOBiome;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

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
        this.gskoForest = getID(biomeIn, GSKOBiome.GSKO_FOREST);
        this.humanVillage = getID(biomeIn, GSKOBiome.HUMAN_VILLAGE);
        this.youkaiMountain = getID(biomeIn, GSKOBiome.YOUKAI_MOUNTAIN);
        this.magicForest = getID(biomeIn, GSKOBiome.MAGIC_FOREST);
        this.formerHellBiome = getID(biomeIn, GSKOBiome.FORMER_HELL_BIOME);
        this.sunflowerGarden = getID(biomeIn, GSKOBiome.SUNFLOWER_GARDEN);
        this.bambooForestLost = getID(biomeIn, GSKOBiome.BAMBOO_FOREST_OF_LOST);
        this.higanBiome = getID(biomeIn, GSKOBiome.HIGAN_BIOME);
        this.sanzuRiver = getID(biomeIn, GSKOBiome.SANZU_RIVER);
        this.dreamWorldBiome = getID(biomeIn, GSKOBiome.DREAM_WORLD_BIOME);
        this.lunarCapitalBiome = getID(biomeIn, GSKOBiome.LUNAR_CAPITAL_BIOME);
        this.lunarSurfaceBiome = getID(biomeIn, GSKOBiome.LUNAR_SURFACE_BIOME);
        this.tranquilitySea = getID(biomeIn, GSKOBiome.TRANQUILITY_SEA_INSIDE);
    }


    private static int getID(Registry<Biome> biomes, RegistryKey<Biome> biomeKey) {
        return biomes.getId(biomes.getValueForKey(biomeKey));
    }

}
