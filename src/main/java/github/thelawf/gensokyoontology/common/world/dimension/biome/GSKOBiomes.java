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

    public static final RegistryKey<Biome> YATSUGA_TAKE_KEY = makeKey("mountain_yatsugatake");
    public static final RegistryKey<Biome> GSKO_FOREST_KEY = makeKey("gensokyo_forest");
    public static final RegistryKey<Biome> GSKO_PLAINS_KEY = makeKey("gensokyo_plains");
    public static final RegistryKey<Biome> GSKO_RIVER_KEY = makeKey("gensokyo_river");
    public static final RegistryKey<Biome> YOUKAI_MOUNTAIN_KEY = makeKey("youkai_mountain");
    public static final RegistryKey<Biome> SUNFLOWER_GARDEN_KEY = makeKey("sunflower_garden");
    public static final RegistryKey<Biome> BAMBOO_FOREST_LOST_KEY = makeKey("bamboo_forest_of_lost");
    public static final RegistryKey<Biome> MAGIC_FOREST_KEY = makeKey("magic_forest");
    public static final RegistryKey<Biome> HUMAN_VILLAGE_KEY = makeKey("human_village");
    public static final RegistryKey<Biome> MISTY_LAKE_KEY = makeKey("misty_lake");
    public static final RegistryKey<Biome> SANZU_RIVER_KEY = makeKey("sanzu_river");
    public static final RegistryKey<Biome> HIGAN_KEY = makeKey("higan");
    public static final RegistryKey<Biome> WIND_GODDESS_LAKE_KEY = makeKey("wind_goddess_lake");


    private static RegistryKey<Biome> makeKey(String name) {
        return RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(GensokyoOntology.MODID, name));
    }

}
