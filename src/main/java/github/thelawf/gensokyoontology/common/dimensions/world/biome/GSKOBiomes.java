package github.thelawf.gensokyoontology.common.dimensions.world.biome;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.data.WorldgenDataConsumer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.fixes.LWJGL3KeyOptions;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

public class GSKOBiomes {

    // execute as @a[gamemode=creative,nbt={SelectedItem:{id:"minecraft:feather"},Inventory:[{Slot:"0b",id:"minecraft:feather"}]}] at positioned ^ ^ ^ run clone x1 y1 z1 x2 y2 z2 ~-3 ~-2 ~-3 masked normal
    public static final int GSKO_WATER_COLOR = 0x0bccff;
    public static final int GSKO_WATER_FOG_COLOR = 0x0033aa;
    public static final int GSKO_FOG_COLOR = 0xff24be;
    public static final int GSKO_SKY_COLOR = getSkyColor(0.76f);

    public GSKOBiomes(WorldgenDataConsumer<Biome> worldgen) {
        worldgen.register(MAGIC_FOREST_KEY, MAGIC_FOREST_BIOME);
        worldgen.register(YATSUGATAKE_KEY, YATUGA_TAKE_BIOME);
        worldgen.register(YOUKAI_MOUNTAIN_KEY, YOKAI_MOUNTAIN_BIOME);
        // worldgen.register(GSKO_FOREST_KEY, )
    }

    private static BiomeGenerationSettings.Builder makeDefaultBuilder()
    {
        //抄原版用来装饰群系(这句话来自模组 Ashihara，作者：遗失唐伞绘卷屋)
        BiomeGenerationSettings.Builder BiomegenerationSettings
                = (new BiomeGenerationSettings.Builder()
                .withSurfaceBuilder(ConfiguredSurfaceBuilders.GRASS)
                .withCarver(GenerationStage.Carving.AIR, ConfiguredCarvers.CAVE));
        DefaultBiomeFeatures.withStrongholdAndMineshaft(BiomegenerationSettings);
        DefaultBiomeFeatures.withCavesAndCanyons(BiomegenerationSettings);
        DefaultBiomeFeatures.withLavaAndWaterLakes(BiomegenerationSettings);
        DefaultBiomeFeatures.withMonsterRoom(BiomegenerationSettings);
        DefaultBiomeFeatures.withCommonOverworldBlocks(BiomegenerationSettings);
        DefaultBiomeFeatures.withOverworldOres(BiomegenerationSettings);
        DefaultBiomeFeatures.withDisks(BiomegenerationSettings);
//        DefaultBiomeFeatures.withForestGrass(BiomegenerationSettings);
        DefaultBiomeFeatures.withNormalMushroomGeneration(BiomegenerationSettings);
        DefaultBiomeFeatures.withLavaAndWaterSprings(BiomegenerationSettings);
        return BiomegenerationSettings;
    }

    /**
     * 主世界的八岳山，生成概率极低，可在此通过神隐的方式进入幻想乡
     */
    public static final Biome YATUGA_TAKE_BIOME = new Biome.Builder()
            .depth(4.5f)
            .scale(2.5f)
            .downfall(0f)
            .temperature(0.35f)
            .category(Biome.Category.EXTREME_HILLS)
            .precipitation(Biome.RainType.SNOW)
            .withMobSpawnSettings(MobSpawnInfo.EMPTY)
            .withGenerationSettings(makeDefaultBuilder().build())
            .setEffects(new BiomeAmbience.Builder()
                    .setWaterColor(0x0DA7D6)
                    .setWaterFogColor(0x282E84)
                    .setFogColor(0xC0D8FF)
                    .withGrassColor(0x59BA82)
                    .withSkyColor(getSkyColor(0.7F))
                    .setMoodSound(MoodSoundAmbience.DEFAULT_CAVE)
                    .build())
            .build()
            .setRegistryName(new ResourceLocation(GensokyoOntology.MODID, "moutain_yatsugatake"));

    // public static final Biome GSKO_WILDLAND_BIOME = new Biome.Builder()
    //         .depth(0.8f)
    //         .temperature(0.76f)
    //         .setEffects(new BiomeAmbience.Builder()
    //                 .setFogColor(GSKO_FOG_COLOR)
    //                 .setWaterFogColor(GSKO_WATER_FOG_COLOR)
    //                 .setWaterColor(GSKO_WATER_COLOR)
    //                 .withSkyColor(GSKO_SKY_COLOR)
    //                 .build())
    //         .build();

    // public static final Biome GSKO_WILD_FOREST_BIOME = new Biome.Builder()

    // All the forest type biomes except Magic Forest in Gensokyo
    // public static final Biome GSKO_FOREST_BIOME = new Biome.Builder()
    //         .depth(0.8f)
    //         .temperature(0.8f)
    //         .build();

    // All the river type biomes in Gensokyo
    // public static final Biome GSKO_RIVER_BIOME = new Biome.Builder();

    // All the lake type except Misty Lakes and Goddess Lakes in Gensokyo
    // public static final Biome GSKO_LAKE_BIOME = new Biome.Builder();

    // public static final Biome BAMBOO_FOREST_OF_LOST = new Biome.Builder();

    // TODO: subBiomes of Youkai Mountain: Youkai Mountain Foothill（妖怪树海）;
    //  Youkai Moutain Highland（伪天棚）; Wind Goddess Lake（风神之湖）
    public static final Biome YOKAI_MOUNTAIN_BIOME = new Biome.Builder()
            .depth(5.2f)
            .temperature(0.35f)
            .scale(2.5f)
            .downfall(0f)
            .category(Biome.Category.EXTREME_HILLS)
            .precipitation(Biome.RainType.SNOW)
            .withMobSpawnSettings(MobSpawnInfo.EMPTY)
            .withGenerationSettings(makeDefaultBuilder().build())
            .setEffects(new BiomeAmbience.Builder()
                    .setWaterColor(0x0DA7D6)
                    .setWaterFogColor(0x282E84)
                    .setFogColor(0xC0D8FF)
                    .withGrassColor(0x59BA82)
                    .withSkyColor(getSkyColor(0.7F))
                    .setMoodSound(MoodSoundAmbience.DEFAULT_CAVE)
                    .build())
            .build()
            .setRegistryName(new ResourceLocation(GensokyoOntology.MODID, "youkai_mountain"));

    public static final Biome MAGIC_FOREST_BIOME = new Biome.Builder()
            .depth(0.12f)
            .temperature(0.8f)
            .scale(3.8f)
            .downfall(0.2f)
            .category(Biome.Category.FOREST)
            .precipitation(Biome.RainType.RAIN)
            .withMobSpawnSettings(MobSpawnInfo.EMPTY)
            .withGenerationSettings(makeDefaultBuilder().build())
            .setEffects(new BiomeAmbience.Builder()
                    .setWaterColor(0x48A7D6)
                    .setWaterFogColor(0x282E84)
                    .setFogColor(0xC0D8FF)
                    .withGrassColor(0x59BA82)
                    .withSkyColor(getSkyColor(0.7F))
                    .setMoodSound(MoodSoundAmbience.DEFAULT_CAVE)
                    .build())
            .build()
            .setRegistryName(new ResourceLocation(GensokyoOntology.MODID, "magic_forest"));

    public static final RegistryKey<Biome> YATSUGATAKE_KEY = key("mountain_yatsugatake");
    public static final RegistryKey<Biome> GSKO_WILDLAND_KEY = key("gensokyo_wild_land");
    public static final RegistryKey<Biome> GSKO_FOREST_KEY = key("gensokyo_wild_forest");
    public static final RegistryKey<Biome> HUMAN_VILLAGE_KEY = key("human_village");
    public static final RegistryKey<Biome> YOUKAI_MOUNTAIN_KEY = key("youkai_mountain");
    public static final RegistryKey<Biome> MAGIC_FOREST_KEY = key("magic_forest");
    public static final RegistryKey<Biome> FORMER_HELL_KEY = key("former_hell");
    public static final RegistryKey<Biome> SUNFLOWER_GARDEN_KEY = key("sunflower_garden");
    public static final RegistryKey<Biome> BAMBOO_FOREST_OF_LOST_KEY = key("bamboo_forest_of_lost");
    public static final RegistryKey<Biome> HIGAN_BIOME_KEY = key("higan");
    public static final RegistryKey<Biome> SANZU_RIVER_KEY = key("sanzu_river");
    public static final RegistryKey<Biome> DREAM_WORLD_KEY = key("dream_world");
    public static final RegistryKey<Biome> LUNAR_CAPITAL_KEY = key("lunar_capital");
    public static final RegistryKey<Biome> LUNAR_SURFACE_KEY = key("lunar_surface");
    public static final RegistryKey<Biome> TRANQUILITY_SEA_INSIDE = key("sea_of_tranquility");

    private static RegistryKey<Biome> key(String keyId){
        return RegistryKey.getOrCreateKey(Registry.BIOME_KEY,new ResourceLocation(GensokyoOntology.MODID,keyId));
    }

    private static int getSkyColor(float temperature) {
        float shift = MathHelper.clamp(temperature / 3.0F, -1.0F, 1.0F);
        return MathHelper.hsvToRGB((224.0F / 360.0F) - shift * 0.05F, 0.5F + shift * 0.1F, 1.0F);
    }

}
