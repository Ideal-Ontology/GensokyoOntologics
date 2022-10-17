package github.thelawf.gensokyoontology.common.dimensions.world.biomes;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GensokyoOntology.MODID)
public final class GSKOBiomes {
    public static final int GSKO_WATER_COLOR = 0x0bccff;
    public static final int GSKO_WATER_FOG_COLOR = 0x0033aa;
    public static final int GSKO_FOG_COLOR = 0xff24be;
    public static final int GSKO_SKY_COLOR = getSkyColor(0.76f);

    public static final RegistryKey<Biome> GSKO_FOREST = key("gensokyo_wild_forest");
    public static final RegistryKey<Biome> HUMAN_VILLAGE = key("human_village");
    public static final RegistryKey<Biome> MISTY_LAKE = key("misty_lake");
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
