package github.thelawf.gensokyoontology.common.world.dimension.biome;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public final class GSKOBiomeMaker {


    private static BiomeGenerationSettings.Builder makeDefaultBuilder() {
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

    private static BiomeGenerationSettings.Builder withBuilder(BiomeGenerationSettings.Builder settings, List<FeatureType> features) {
        DefaultBiomeFeatures.withStrongholdAndMineshaft(settings);
        DefaultBiomeFeatures.withCavesAndCanyons(settings);
        DefaultBiomeFeatures.withLavaAndWaterLakes(settings);
        DefaultBiomeFeatures.withMonsterRoom(settings);
        DefaultBiomeFeatures.withCommonOverworldBlocks(settings);
        DefaultBiomeFeatures.withOverworldOres(settings);
        DefaultBiomeFeatures.withDisks(settings);
        DefaultBiomeFeatures.withNormalMushroomGeneration(settings);
        DefaultBiomeFeatures.withLavaAndWaterSprings(settings);

        features.forEach(f -> {
            if (features.contains(FeatureType.PUMPKINS)) {
                DefaultBiomeFeatures.withSugarCaneAndPumpkins(settings);
            }
            if (features.contains(FeatureType.FOREST_GRASS)) {
                DefaultBiomeFeatures.withForestGrass(settings);
            }
            if (features.contains(FeatureType.ACACIA_TREE)) {
                DefaultBiomeFeatures.withSavannaTrees(settings);
            }
            if (features.contains(FeatureType.PLAINS_GRASS)) {
                DefaultBiomeFeatures.withPlainGrassVegetation(settings);
            }
            if (features.contains(FeatureType.NORMAL_GRASS_PATCH)) {
                DefaultBiomeFeatures.withNormalGrassPatch(settings);
            }
        });


        return settings;
    }

    private static BiomeGenerationSettings.Builder makeCustomBuilder() {
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

    private static BiomeGenerationSettings.Builder makeCustomSurface(final Supplier<ConfiguredSurfaceBuilder<?>> builder) {
        return (new BiomeGenerationSettings.Builder().withSurfaceBuilder(builder)
                .withCarver(GenerationStage.Carving.AIR, ConfiguredCarvers.CAVE));
    }

    // 比较好看的草色：.withGrassColor(0x59BA82)，
    public static Biome makeYatsugaTakeBiome(final Supplier<ConfiguredSurfaceBuilder<?>> builder) {
        List<FeatureType> features = new ArrayList<>();
        features.add(FeatureType.ACACIA_TREE);
        features.add(FeatureType.NORMAL_GRASS_PATCH);

        return new Biome.Builder()
                .depth(2.8f)
                .scale(1.5f)
                .downfall(0.2f)
                .temperature(0.6f)
                .category(Biome.Category.EXTREME_HILLS)
                .precipitation(Biome.RainType.SNOW)
                .withMobSpawnSettings(MobSpawnInfo.EMPTY)
                .withTemperatureModifier(Biome.TemperatureModifier.NONE)
                .withGenerationSettings(
                        withBuilder(makeCustomSurface(builder), features).build())
                .setEffects(new BiomeAmbience.Builder()
                        .setWaterColor(0x0DA7D6)
                        .setWaterFogColor(0x282E84)
                        .setFogColor(0xC0D8FF)
                        .withSkyColor(getSkyColor(0.7F))
                        .setMoodSound(MoodSoundAmbience.DEFAULT_CAVE)
                        .build())
                .build();
    }


    private static int getSkyColor(float temperature) {
        float shift = MathHelper.clamp(temperature / 3.0F, -1.0F, 1.0F);
        return MathHelper.hsvToRGB((224.0F / 360.0F) - shift * 0.05F, 0.5F + shift * 0.1F, 1.0F);
    }

    public enum FeatureType {
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
