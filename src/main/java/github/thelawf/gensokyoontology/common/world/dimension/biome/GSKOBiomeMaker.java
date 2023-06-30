package github.thelawf.gensokyoontology.common.world.dimension.biome;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.FairyEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

import java.util.ArrayList;
import java.util.List;

public final class GSKOBiomeMaker {


    private static BiomeGenerationSettings.Builder makeDefaultBuilder()
    {
        //抄原版用来装饰群系(这句话来自模组 Ashihara，作者：遗失唐伞绘卷屋)
        BiomeGenerationSettings.Builder biomegenerationSettings
                = (new BiomeGenerationSettings.Builder()
                .withSurfaceBuilder(ConfiguredSurfaceBuilders.GRASS)
                .withCarver(GenerationStage.Carving.AIR, ConfiguredCarvers.CAVE));
        DefaultBiomeFeatures.withStrongholdAndMineshaft(biomegenerationSettings);
        DefaultBiomeFeatures.withCavesAndCanyons(biomegenerationSettings);
        DefaultBiomeFeatures.withLavaAndWaterLakes(biomegenerationSettings);
        DefaultBiomeFeatures.withMonsterRoom(biomegenerationSettings);
        DefaultBiomeFeatures.withCommonOverworldBlocks(biomegenerationSettings);
        DefaultBiomeFeatures.withOverworldOres(biomegenerationSettings);
        DefaultBiomeFeatures.withDisks(biomegenerationSettings);
        DefaultBiomeFeatures.withNormalMushroomGeneration(biomegenerationSettings);
        DefaultBiomeFeatures.withLavaAndWaterSprings(biomegenerationSettings);
        return biomegenerationSettings;
    }

    private static BiomeGenerationSettings.Builder addFeatureIfContains(List<Features> features) {
        BiomeGenerationSettings.Builder biomegenerationSettings
                = (new BiomeGenerationSettings.Builder()
                .withSurfaceBuilder(ConfiguredSurfaceBuilders.GRASS)
                .withCarver(GenerationStage.Carving.AIR, ConfiguredCarvers.CAVE));
        DefaultBiomeFeatures.withStrongholdAndMineshaft(biomegenerationSettings);
        DefaultBiomeFeatures.withCavesAndCanyons(biomegenerationSettings);
        DefaultBiomeFeatures.withLavaAndWaterLakes(biomegenerationSettings);
        DefaultBiomeFeatures.withMonsterRoom(biomegenerationSettings);
        DefaultBiomeFeatures.withCommonOverworldBlocks(biomegenerationSettings);
        DefaultBiomeFeatures.withOverworldOres(biomegenerationSettings);
        DefaultBiomeFeatures.withDisks(biomegenerationSettings);
        DefaultBiomeFeatures.withNormalMushroomGeneration(biomegenerationSettings);
        DefaultBiomeFeatures.withLavaAndWaterSprings(biomegenerationSettings);

        features.forEach(f -> {
            if (features.contains(Features.PUMPKINS)) {
                DefaultBiomeFeatures.withSugarCaneAndPumpkins(biomegenerationSettings);
            }
            if (features.contains(Features.FOREST_GRASS)) {
                DefaultBiomeFeatures.withForestGrass(biomegenerationSettings);
            }
            if (features.contains(Features.ACACIA_TREE)) {
                DefaultBiomeFeatures.withSavannaTrees(biomegenerationSettings);
            }
            if (features.contains(Features.PLAINS_GRASS)) {
                DefaultBiomeFeatures.withPlainGrassVegetation(biomegenerationSettings);
            }
            if (features.contains(Features.NORMAL_GRASS_PATCH)) {
                DefaultBiomeFeatures.withNormalGrassPatch(biomegenerationSettings);
            }
        });


        return biomegenerationSettings;
    }

    public static Biome makeYatsugaTakeBiome() {
        List<Features> features = new ArrayList<>();
        features.add(Features.ACACIA_TREE);
        features.add(Features.NORMAL_GRASS_PATCH);

        return new Biome.Builder()
                .depth(2.8f)
                .scale(1.5f)
                .downfall(0.3f)
                .temperature(0.35f)
                .category(Biome.Category.EXTREME_HILLS)
                .precipitation(Biome.RainType.SNOW)
                .withMobSpawnSettings(MobSpawnInfo.EMPTY)
                .withGenerationSettings(addFeatureIfContains(features).build())
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


    public static Biome makeYamotsuHirasaka() {
        List<Features> features = new ArrayList<>();
        features.add(Features.PLAINS_GRASS);
        features.add(Features.NORMAL_GRASS_PATCH);

        return new Biome.Builder()
                .depth(0.1f)
                .scale(0.34f)
                .downfall(0.08f)
                .temperature(0.5f)
                .category(Biome.Category.FOREST)
                .precipitation(Biome.RainType.RAIN)
                .withMobSpawnSettings(MobSpawnInfo.EMPTY)
                .withGenerationSettings(addFeatureIfContains(features).build())
                .withTemperatureModifier(Biome.TemperatureModifier.NONE)
                .setEffects(new BiomeAmbience.Builder()
                        .withSkyColor(0xBEBB97)
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
                .scale(0.45f)
                .downfall(0.02f)
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
                .setRegistryName(GensokyoOntology.MODID, "outside_city");
    }

    public static Biome makeSakuraForest() {
        List<Features> features = new ArrayList<>();
        features.add(Features.FOREST_GRASS);
        features.add(Features.NORMAL_GRASS_PATCH);

        return new Biome.Builder()
                .depth(0.1f)
                .scale(0.42f)
                .downfall(0.12f)
                .temperature(0.7f)
                .category(Biome.Category.FOREST)
                .precipitation(Biome.RainType.RAIN)
                .withMobSpawnSettings(new MobSpawnInfo.Builder().withSpawner(
                        EntityClassification.MONSTER, new MobSpawnInfo.Spawners(
                                FairyEntity.FAIRY, 68, 3,5)).build())
                .withGenerationSettings(addFeatureIfContains(features).build())
                .withTemperatureModifier(Biome.TemperatureModifier.NONE)
                .setEffects(new BiomeAmbience.Builder().setWaterColor(0x0DA7D6)
                        .setWaterFogColor(0x282E84)
                        .setFogColor(0xC0D8FF)
                        .withGrassColor(0x59BA82)
                        .withSkyColor(getSkyColor(0.7F))
                        .setMoodSound(MoodSoundAmbience.DEFAULT_CAVE)
                        .build())
                .build()
                .setRegistryName(GensokyoOntology.MODID, "sakura_forest");
    }

    public static Biome makeHellValley() {
        return new Biome.Builder()
                .depth(1.2f)
                .scale(0.4f)
                .downfall(0.7f)
                .temperature(0.88f)
                .category(Biome.Category.MESA)
                .precipitation(Biome.RainType.SNOW)
                .withMobSpawnSettings(MobSpawnInfo.EMPTY)
                .withGenerationSettings(makeDefaultBuilder().build())
                .withTemperatureModifier(Biome.TemperatureModifier.NONE)
                .setEffects(new BiomeAmbience.Builder()
                        .withSkyColor(0xFFE894)
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

    public enum Features {
        OAK_TREE,
        FANCY_OAK_TREE,
        ACACIA_TREE,
        SAKURA_TREE,
        BAMBOO,
        LYCORIS,
        SUNFLOWER,
        PUMPKINS,
        FOREST_GRASS,
        PLAINS_GRASS,
        NORMAL_GRASS_PATCH
    }
}
