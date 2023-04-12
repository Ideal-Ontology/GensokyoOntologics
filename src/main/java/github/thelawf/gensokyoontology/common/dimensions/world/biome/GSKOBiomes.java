package github.thelawf.gensokyoontology.common.dimensions.world.biome;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.data.WorldgenDataConsumer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GSKOBiomes {

    // execute as @a[gamemode=creative,nbt={SelectedItem:{id:"minecraft:feather"},Inventory:[{Slot:"0b",id:"minecraft:feather"}]}] at positioned ^ ^ ^ run clone x1 y1 z1 x2 y2 z2 ~-3 ~-2 ~-3 masked normal
    public static final int GSKO_WATER_COLOR = 0x0bccff;
    public static final int GSKO_WATER_FOG_COLOR = 0x0033aa;
    public static final int GSKO_FOG_COLOR = 0xff24be;
    public static final int GSKO_SKY_COLOR = getSkyColor(0.76f);

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, GensokyoOntology.MODID);

    public GSKOBiomes(WorldgenDataConsumer<Biome> worldgen) {
        // worldgen.register(MAGIC_FOREST_KEY, MAGIC_FOREST_BIOME);
    }

    /**
     * 主世界的八岳山，生成概率极低，可在此通过神隐的方式进入幻想乡
     */
    public static final Biome YATSUGA_TAKE_BIOME = GSKOBiomeMaker.makeYatsugaTakeBiome();
    public static final Biome YAMOTSU_HIRASAKA = GSKOBiomeMaker.makeYamotsuHirasaka();
    public static final Biome HELL_VALLEY = GSKOBiomeMaker.makeHellValley();
    public static final Biome OUTSIDE_CITY_FIELD = GSKOBiomeMaker.makeOutsideCityBiome();

    public static final Biome GSKO_WILDLAND_BIOME = GSKOBiomeMaker.makeGSKOWildLand();
    public static final Biome YOUKAI_MOUNTAIN_BIOME = GSKOBiomeMaker.makeYoukaiMoutain();
    public static final Biome MAGIC_FOREST_BIOME = GSKOBiomeMaker.makeMagicForest();
    public static final Biome BAMBOO_FOREST_LOST_BIOME = GSKOBiomeMaker.makeBambooForestLost();

    public static final RegistryKey<Biome> YATSUGA_TAKE_KEY = key("mountain_yatsugatake");
    public static final RegistryKey<Biome> GSKO_FOREST_KEY = key("gensokyo_forest");
    public static final RegistryKey<Biome> GSKO_PLAINS_KEY = key("gensokyo_plains");
    public static final RegistryKey<Biome> YOUKAI_MOUNTAIN_KEY = key("youkai_mountain");
    public static final RegistryKey<Biome> SUNFLOWER_GARDEN_KEY = key("sunflower_garden");
    public static final RegistryKey<Biome> BAMBOO_FOREST_LOST_KEY = key("bamboo_forest_of_lost");
    public static final RegistryKey<Biome> MAGIC_FOREST_KEY = key("magic_forest");
    public static final RegistryKey<Biome> HUMAN_VILLAGE_KEY = key("human_village");



    private static RegistryKey<Biome> key(String name) {
        return RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(GensokyoOntology.MODID, name));
    }

    private static int getSkyColor(float temperature) {
        float shift = MathHelper.clamp(temperature / 3.0F, -1.0F, 1.0F);
        return MathHelper.hsvToRGB((224.0F / 360.0F) - shift * 0.05F, 0.5F + shift * 0.1F, 1.0F);
    }

    @SuppressWarnings("all")
    private static Biome register(int id, RegistryKey<Biome> key, Biome biome) {
        return WorldGenRegistries.register(WorldGenRegistries.BIOME, id, key, biome);
    }

    private static int getId(Registry<Biome> biomes, RegistryKey<Biome> key) {
        return biomes.getId(biomes.getValueForKey(key));
    }
}
