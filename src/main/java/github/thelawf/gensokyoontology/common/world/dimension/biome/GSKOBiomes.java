package github.thelawf.gensokyoontology.common.world.dimension.biome;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class GSKOBiomes {

    // execute as @a[gamemode=creative,nbt={SelectedItem:{id:"minecraft:feather"},Inventory:[{Slot:"0b",id:"minecraft:feather"}]}] at positioned ^ ^ ^ run clone x1 y1 z1 x2 y2 z2 ~-3 ~-2 ~-3 masked normal

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES,
            GensokyoOntology.MODID);

    /**
     * 主世界的八岳山，生成概率极低，可在此通过神隐的方式进入幻想乡
     */
    public static final Biome YATSUGA_TAKE_BIOME = GSKOBiomeMaker.makeYatsugaTakeBiome();
    public static final Biome YAMOTSU_HIRASAKA = GSKOBiomeMaker.makeYamotsuHirasaka();
    public static final Biome HELL_VALLEY = GSKOBiomeMaker.makeHellValley();
    public static final Biome OUTSIDE_CITY = GSKOBiomeMaker.makeOutsideCityBiome();

    public static final Biome SAKURA_FOREST = GSKOBiomeMaker.makeSakuraForest();

    public static final RegistryKey<Biome> GSKO_FOREST_KEY = makeKey("gensokyo_forest");
    public static final RegistryKey<Biome> GSKO_PLAINS_KEY = makeKey("gensokyo_plains");
    public static final RegistryKey<Biome> GSKO_RIVER_KEY = makeKey("gensokyo_river");
    public static final RegistryKey<Biome> HAKUREI_SHRINE_PRECINCTS_KEY = makeKey("hakurei_shrine_precincts");
    public static final RegistryKey<Biome> SCARLET_MANSION_PRECINCTS_KEY = makeKey("scarlet_mansion_precincts");
    public static final RegistryKey<Biome> BEAST_PATH_KEY = makeKey("beast_path");
    public static final RegistryKey<Biome> UNTRODDEN_VALLEY_KEY = makeKey("untrodden_valley");
    public static final RegistryKey<Biome> YOUKAI_JUKAI_KEY = makeKey("youkai_jukai");
    public static final RegistryKey<Biome> YOUKAI_MOUNTAIN_KEY = makeKey("youkai_mountain");
    public static final RegistryKey<Biome> SUNFLOWER_GARDEN_KEY = makeKey("sunflower_garden");
    public static final RegistryKey<Biome> BAMBOO_FOREST_LOST_KEY = makeKey("bamboo_forest_of_lost");
    public static final RegistryKey<Biome> MAGIC_FOREST_KEY = makeKey("magic_forest");
    public static final RegistryKey<Biome> DOLL_FOREST_KEY = makeKey("doll_forest");
    public static final RegistryKey<Biome> NAMELESS_HILL_KEY = makeKey("nameless_hill");
    public static final RegistryKey<Biome> MUENZUKA = makeKey("muenzuka");
    public static final RegistryKey<Biome> HUMAN_VILLAGE_KEY = makeKey("human_village");
    public static final RegistryKey<Biome> MISTY_LAKE_KEY = makeKey("misty_lake");
    public static final RegistryKey<Biome> SANZU_RIVER_KEY = makeKey("sanzu_river");
    public static final RegistryKey<Biome> HIGAN_KEY = makeKey("higan");
    public static final RegistryKey<Biome> WIND_GODDESS_LAKE_KEY = makeKey("wind_goddess_lake");
    public static final RegistryKey<Biome> YOUKAI_TANUKI_FOREST = makeKey("youkai_tanuki_forest");
    public static final RegistryKey<Biome> LETHE_RIVER_BANK_KEY = makeKey("lethe_river_bank");
    public static final RegistryKey<Biome> FAKE_HEAVEN_SHELF_KEY = makeKey("fake_heaven_shelf");

    public static final RegistryKey<Biome> FORMER_HELL_KEY = makeKey("former_hell");
    public static final RegistryKey<Biome> FORMER_CAPITAL_KEY = makeKey("former_capital");
    public static final RegistryKey<Biome> CHIREIDEN_REGION_KEY = makeKey("chireiden");
    public static final RegistryKey<Biome> BLAZING_HELL_RUINS_KEY = makeKey("blazing_hell_ruins_key");

    public static final RegistryKey<Biome> NETHER_VOID_KEY = makeKey("nether_void");
    public static final RegistryKey<Biome> NETHER_LAND_KEY = makeKey("nether_land");
    public static final RegistryKey<Biome> NETHER_SAKURA_FOREST_KEY = makeKey("nether_sakura_forest");

    private static RegistryKey<Biome> makeKey(String name) {
        return RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(GensokyoOntology.MODID, name));
    }

}
