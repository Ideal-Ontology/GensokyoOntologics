package github.thelawf.gensokyoontology.common.dimensions.world.biome;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

public final class GSKOBiomeMaker {


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

    public static Biome makeMagicForest() {
        return new Biome.Builder()
                .category(Biome.Category.FOREST)
                .depth(1.05f)
                .downfall(0f)
                .scale(3.5f)
                .temperature(0.7f)
                .precipitation(Biome.RainType.RAIN)
                .setEffects(new BiomeAmbience.Builder().setWaterColor(0x0DA7D6)
                        .setWaterFogColor(0x282E84)
                        .setFogColor(0xC0D8FF)
                        .withGrassColor(0x59BA82)
                        .withSkyColor(getSkyColor(0.7F))
                        .setMoodSound(MoodSoundAmbience.DEFAULT_CAVE)
                        .build())
                .withGenerationSettings(makeDefaultBuilder().build())
                .withMobSpawnSettings(MobSpawnInfo.EMPTY)
                .withTemperatureModifier(Biome.TemperatureModifier.NONE)
                .build()
                .setRegistryName(GensokyoOntology.MODID, "magic_forest");
    }

    public static Biome makeYoukaiMoutain() {
        return new Biome.Builder()
                .depth(5.2f)
                .temperature(0.35f)
                .scale(2.5f)
                .downfall(0f)
                .category(Biome.Category.EXTREME_HILLS)
                .precipitation(Biome.RainType.SNOW)
                .setEffects(new BiomeAmbience.Builder()
                        .setWaterColor(0x0DA7D6)
                        .setWaterFogColor(0x282E84)
                        .setFogColor(0xC0D8FF)
                        .withGrassColor(0x59BA82)
                        .withSkyColor(getSkyColor(0.7F))
                        .setMoodSound(MoodSoundAmbience.DEFAULT_CAVE)
                        .build())
                .withMobSpawnSettings(MobSpawnInfo.EMPTY)
                .withGenerationSettings(makeDefaultBuilder().build())
                .withTemperatureModifier(Biome.TemperatureModifier.NONE)
                .build()
                .setRegistryName(GensokyoOntology.MODID, "youkai_mountain");

    }

    public static Biome makeGSKOWildLand() {
        return new Biome.Builder()
                .depth(1.7f)
                .scale(2.5f)
                .downfall(0f)
                .temperature(0.35f)
                .category(Biome.Category.EXTREME_HILLS)
                .precipitation(Biome.RainType.SNOW)
                .setEffects(new BiomeAmbience.Builder()
                        .setWaterColor(0xEDA7D6)
                        .setWaterFogColor(0x282E84)
                        .setFogColor(0xC0D8FF)
                        .withGrassColor(0x59BA82)
                        .withSkyColor(getSkyColor(0.7F))
                        .setMoodSound(MoodSoundAmbience.DEFAULT_CAVE)
                        .build())
                .withMobSpawnSettings(MobSpawnInfo.EMPTY)
                .withGenerationSettings(makeDefaultBuilder().build())
                .withTemperatureModifier(Biome.TemperatureModifier.NONE)
                .build()
                .setRegistryName(GensokyoOntology.MODID, "youkai_mountain");

    }

    public static Biome makeYatsugaTakeBiome() {
        return new Biome.Builder()
                .depth(4.5f)
                .scale(2.5f)
                .downfall(0f)
                .temperature(0.35f)
                .category(Biome.Category.EXTREME_HILLS)
                .precipitation(Biome.RainType.SNOW)
                .withMobSpawnSettings(MobSpawnInfo.EMPTY)
                .withGenerationSettings(makeDefaultBuilder().build())
                .withTemperatureModifier(Biome.TemperatureModifier.NONE)
                .setEffects(new BiomeAmbience.Builder()
                        .setWaterColor(0x0DA7D6)
                        .setWaterFogColor(0x282E84)
                        .setFogColor(0xC0D8FF)
                        .withGrassColor(0x59BA82)
                        .withSkyColor(getSkyColor(0.7F))
                        .setMoodSound(MoodSoundAmbience.DEFAULT_CAVE)
                        .build())
                .build()
                .setRegistryName(GensokyoOntology.MODID, "mountain_yatsugatake");
    }

    public static Biome makeBambooForestLost() {
        return new Biome.Builder()
                .depth(1.5f)
                .scale(4.5f)
                .downfall(0f)
                .temperature(0.3f)
                .category(Biome.Category.JUNGLE)
                .precipitation(Biome.RainType.RAIN)
                .withMobSpawnSettings(MobSpawnInfo.EMPTY)
                .withGenerationSettings(makeDefaultBuilder().build())
                .withTemperatureModifier(Biome.TemperatureModifier.NONE)
                .setEffects(new BiomeAmbience.Builder()
                        .setWaterColor(0x0DA7D6)
                        .setWaterFogColor(0x282E84)
                        .setFogColor(0xC0D8FF)
                        .withGrassColor(0x59BA82)
                        .withSkyColor(getSkyColor(0.7F))
                        .setMoodSound(MoodSoundAmbience.DEFAULT_CAVE)
                        .build())
                .build()
                .setRegistryName(GensokyoOntology.MODID, "bamboo_forest_of_lost");
    }

    public static Biome makeYamotsuHirasaka() {
        return new Biome.Builder()
                .depth(0.1f)
                .scale(0.34f)
                .downfall(0.08f)
                .temperature(0.5f)
                .category(Biome.Category.FOREST)
                .precipitation(Biome.RainType.RAIN)
                .withMobSpawnSettings(MobSpawnInfo.EMPTY)
                .withGenerationSettings(makeDefaultBuilder().build())
                .withTemperatureModifier(Biome.TemperatureModifier.NONE)
                .setEffects(new BiomeAmbience.Builder()
                        .withSkyColor(0xFFE894)
                        .setFogColor(0xC0D8FF)
                        .setWaterColor(0x7C3E9)
                        .setWaterFogColor(0xC31A1)
                        .setMoodSound(MoodSoundAmbience.DEFAULT_CAVE)
                        .build())
                .build()
                .setRegistryName(GensokyoOntology.MODID, "yamotsu_hirasaka");
    }

    public static Biome makeOutsideCityBiome() {
        return new Biome.Builder()
                .depth(0.1f)
                .scale(3.5f)
                .downfall(0.08f)
                .temperature(0.5f)
                .category(Biome.Category.PLAINS)
                .precipitation(Biome.RainType.RAIN)
                .withMobSpawnSettings(MobSpawnInfo.EMPTY)
                .withGenerationSettings(makeDefaultBuilder().build())
                .withTemperatureModifier(Biome.TemperatureModifier.NONE)
                .setEffects(new BiomeAmbience.Builder()
                        .withSkyColor(0x12A4E2)
                        .setFogColor(0xC0D8FF)
                        .setWaterColor(0x7C3E9)
                        .setWaterFogColor(0xC31A1)
                        .setMoodSound(MoodSoundAmbience.DEFAULT_CAVE)
                        .build())
                .build()
                .setRegistryName(GensokyoOntology.MODID, "outside_city_field");
    }

    public static Biome makeHellValley() {
        return new Biome.Builder()
                .depth(1.2f)
                .scale(0.4f)
                .downfall(0.9f)
                .temperature(0.88f)
                .category(Biome.Category.MESA)
                .precipitation(Biome.RainType.SNOW)
                .withMobSpawnSettings(MobSpawnInfo.EMPTY)
                .withGenerationSettings(makeDefaultBuilder().build())
                .withTemperatureModifier(Biome.TemperatureModifier.NONE)
                .setEffects(new BiomeAmbience.Builder()
                        .withSkyColor(0xBEBB97)
                        .setFogColor(0x97A2B4)
                        .setWaterColor(0xBD9D00)
                        .setWaterFogColor(0x50533)
                        .setMoodSound(MoodSoundAmbience.DEFAULT_CAVE)
                        .build())
                .build()
                .setRegistryName(GensokyoOntology.MODID, "hell_valley");
    }

    private static int getSkyColor(float temperature) {
        float shift = MathHelper.clamp(temperature / 3.0F, -1.0F, 1.0F);
        return MathHelper.hsvToRGB((224.0F / 360.0F) - shift * 0.05F, 0.5F + shift * 0.1F, 1.0F);
    }
}
