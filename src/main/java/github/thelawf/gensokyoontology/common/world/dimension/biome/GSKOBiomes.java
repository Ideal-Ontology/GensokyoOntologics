package github.thelawf.gensokyoontology.common.world.dimension.biome;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.*;

public class GSKOBiomes {

    // execute as @a[gamemode=creative,nbt={SelectedItem:{id:"minecraft:feather"},Inventory:[{Slot:"0b",id:"minecraft:feather"}]}] at positioned ^ ^ ^ run clone x1 y1 z1 x2 y2 z2 ~-3 ~-2 ~-3 masked normal

    /**
     * 主世界的八岳山，生成概率极低，可在此通过神隐的方式进入幻想乡
     */
    public static final Biome YATSUGA_TAKE_BIOME = GSKOBiomeMaker.makeYatsugaTakeBiome();
    public static final Biome YAMOTSU_HIRASAKA = GSKOBiomeMaker.makeYamotsuHirasaka();
    public static final Biome HELL_VALLEY = GSKOBiomeMaker.makeHellValley();
    public static final Biome OUTSIDE_CITY = GSKOBiomeMaker.makeOutsideCityBiome();

    public static final Biome SAKURA_FOREST = GSKOBiomeMaker.makeSakuraForest();

    public static final RegistryKey<Biome> YATSUGA_TAKE_KEY = key("mountain_yatsugatake");
    public static final RegistryKey<Biome> GSKO_FOREST_KEY = key("gensokyo_forest");
    public static final RegistryKey<Biome> GSKO_PLAINS_KEY = key("gensokyo_plains");
    public static final RegistryKey<Biome> GSKO_RIVER_KEY = key("gensokyo_river");
    public static final RegistryKey<Biome> YOUKAI_MOUNTAIN_KEY = key("youkai_mountain");
    public static final RegistryKey<Biome> SUNFLOWER_GARDEN_KEY = key("sunflower_garden");
    public static final RegistryKey<Biome> BAMBOO_FOREST_LOST_KEY = key("bamboo_forest_of_lost");
    public static final RegistryKey<Biome> MAGIC_FOREST_KEY = key("magic_forest");
    public static final RegistryKey<Biome> HUMAN_VILLAGE_KEY = key("human_village");
    public static final RegistryKey<Biome> MISTY_LAKE_KEY = key("misty_lake");
    public static final RegistryKey<Biome> SANZU_RIVER_KEY = key("sanzu_river");
    public static final RegistryKey<Biome> HIGAN_KEY = key("higan");
    public static final RegistryKey<Biome> WIND_GODDESS_LAKE_KEY = key("wind_goddess_lake");

    private static RegistryKey<Biome> key(String name) {
        return RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(GensokyoOntology.MODID, name));
    }

}
