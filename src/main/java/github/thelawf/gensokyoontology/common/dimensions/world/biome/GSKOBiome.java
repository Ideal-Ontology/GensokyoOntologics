package github.thelawf.gensokyoontology.common.dimensions.world.biome;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeMaker;

public class GSKOBiome {
    // execute as @a[gamemode=creative,nbt={SelectedItem:{id:"minecraft:feather"},Inventory:[{Slot:"0b",id:"minecraft:feather"}]}] at positioned ^ ^ ^ run clone x1 y1 z1 x2 y2 z2 ~-3 ~-2 ~-3 masked normal
    public static final int GSKO_WATER_COLOR = 0x0bccff;
    public static final int GSKO_WATER_FOG_COLOR = 0x0033aa;
    public static final int GSKO_FOG_COLOR = 0xff24be;
    public static final int GSKO_SKY_COLOR = getSkyColor(0.76f);

    // public static final Biome GSKO_WILDLAND_BIOME = new Biome.Builder()
    //         .depth(0.8f)
    //         .temperature(0.76f)
    //         .setEffects(new BiomeAmbience.Builder()
    //                 .setFogColor(GSKO_FOG_COLOR)
    //                 .setWaterFogColor(GSKO_WATER_FOG_COLOR)
    //                 .setWaterColor(GSKO_WATER_COLOR)
    //                 .build())
    //         .build();
//
    // public static final Biome GSKO_FOREST_BIOME = new Biome.Builder()
    //         .depth(0.8f)
    //         .temperature(0.8f)
    //         .build();
//
    // public static final Biome YOKAI_MOUNTAIN_BIOME = new Biome.Builder()
    //         .depth(5.3f)
    //         .build();
    // public static Biome createBiome() {
    //     return GSKO_WILDLAND_BIOME;
    // }

    public static final RegistryKey<Biome> GSKO_WILDLAND = key("gensokyo_wild_land");
    public static final RegistryKey<Biome> GSKO_FOREST = key("gensokyo_wild_forest");
    public static final RegistryKey<Biome> HUMAN_VILLAGE = key("human_village");
    public static final RegistryKey<Biome> YOUKAI_MOUNTAIN = key("youkai_mountain");
    public static final RegistryKey<Biome> MAGIC_FOREST = key("magic_forest");
    public static final RegistryKey<Biome> FORMER_HELL_BIOME = key("former_hell");
    public static final RegistryKey<Biome> SUNFLOWER_GARDEN = key("sunflower_garden");
    public static final RegistryKey<Biome> BAMBOO_FOREST_OF_LOST = key("bamboo_forest_of_lost");
    public static final RegistryKey<Biome> HIGAN_BIOME = key("higan");
    public static final RegistryKey<Biome> SANZU_RIVER = key("sanzu_river");
    public static final RegistryKey<Biome> DREAM_WORLD_BIOME = key("dream_world");
    public static final RegistryKey<Biome> LUNAR_CAPITAL_BIOME = key("lunar_capital");
    public static final RegistryKey<Biome> LUNAR_SURFACE_BIOME = key("lunar_surface");
    public static final RegistryKey<Biome> TRANQUILITY_SEA_INSIDE = key("sea_of_tranquility");

    private static RegistryKey<Biome> key(String keyId){
        return RegistryKey.getOrCreateKey(Registry.BIOME_KEY,new ResourceLocation(GensokyoOntology.MODID,keyId));
    }

    private static int getSkyColor(float temperature) {
        float shift = MathHelper.clamp(temperature / 3.0F, -1.0F, 1.0F);
        return MathHelper.hsvToRGB((224.0F / 360.0F) - shift * 0.05F, 0.5F + shift * 0.1F, 1.0F);
    }
}
