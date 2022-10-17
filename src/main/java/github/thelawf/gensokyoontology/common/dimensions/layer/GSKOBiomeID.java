package github.thelawf.gensokyoontology.common.dimensions.layer;

import github.thelawf.gensokyoontology.common.dimensions.world.biomes.GSKOBiomes;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class GSKOBiomeID {
    public final int gskoForest;
    public final int humanVillage;
    public final int mistyLake;
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
        this.gskoForest = getID(biomeIn, GSKOBiomes.GSKO_FOREST);
        this.humanVillage = getID(biomeIn,GSKOBiomes.HUMAN_VILLAGE);
        this.mistyLake = getID(biomeIn,GSKOBiomes.MISTY_LAKE);
        this.youkaiMountain = getID(biomeIn, GSKOBiomes.YOUKAI_MOUNTAIN);
        this.magicForest = getID(biomeIn,GSKOBiomes.MAGIC_FOREST);
        this.formerHellBiome = getID(biomeIn,GSKOBiomes.FORMER_HELL_BIOME);
        this.sunflowerGarden = getID(biomeIn,GSKOBiomes.SUNFLOWER_GARDEN);
        this.bambooForestLost = getID(biomeIn,GSKOBiomes.BAMBOO_FOREST_OF_LOST);
        this.higanBiome = getID(biomeIn,GSKOBiomes.HIGAN_BIOME);
        this.sanzuRiver = getID(biomeIn, GSKOBiomes.SANZU_RIVER);
        this.dreamWorldBiome = getID(biomeIn,GSKOBiomes.DREAM_WORLD_BIOME);
        this.lunarCapitalBiome = getID(biomeIn,GSKOBiomes.LUNAR_CAPITAL_BIOME);
        this.lunarSurfaceBiome = getID(biomeIn,GSKOBiomes.LUNAR_SURFACE_BIOME);
        this.tranquilitySea = getID(biomeIn,GSKOBiomes.TRANQUILITY_SEA_INSIDE);
    }


    private static int getID(Registry<Biome> biomes, RegistryKey<Biome> biomeKey) {
        return biomes.getId(biomes.getValueForKey(biomeKey));
    }

}
