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
                .setRegistryName(GensokyoOntology.MODID, "mountain_yatsugatake");
    }

    public static Biome makeBambooForestLost() {
        return new Biome.Builder()
                .depth(1.5f)
                .scale(4.5f)
                .downfall(0f)
                .temperature(0.3f)
                .category(Biome.Category.JUNGLE)
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
                .setRegistryName(GensokyoOntology.MODID, "bamboo_forest_of_lost");
    }

    private static int getSkyColor(float temperature) {
        float shift = MathHelper.clamp(temperature / 3.0F, -1.0F, 1.0F);
        return MathHelper.hsvToRGB((224.0F / 360.0F) - shift * 0.05F, 0.5F + shift * 0.1F, 1.0F);
    }
}
