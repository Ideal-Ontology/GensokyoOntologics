package github.thelawf.gensokyoontology.common.world.feature;

import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomes;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class GSKOFeatureGeneration {
    public static void generateOverworldTrees(final BiomeLoadingEvent event) {
        if (event.getName() == null) return;

        RegistryKey<Biome> biomeKey = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biomeKey);

        if (event.getName().equals(GSKOBiomes.SAKURA_FOREST.getRegistryName())) {
            List<Supplier<ConfiguredFeature<?,?>>> base = event.getGeneration().getFeatures(
                    GenerationStage.Decoration.VEGETAL_DECORATION
            );

            base.add(() -> GSKOFeatures.SAKURA_TREE
                    .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                    .withPlacement(Placement.COUNT_EXTRA.configure(
                            new AtSurfaceWithExtraConfig(1, 0.65f, 2)))
            );
            base.add(() -> Features.OAK
                    .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                    .withPlacement(Placement.COUNT_EXTRA.configure(
                            new AtSurfaceWithExtraConfig(1,0.05f, 2)
                    )));
            base.add(() -> GSKOFeatures.MAPLE_TREE
                    .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                    .withPlacement(Placement.COUNT_EXTRA.configure(
                            new AtSurfaceWithExtraConfig(1, 0.3f, 2)
                    )));
        }

    }

    public static void generateGensokyoTrees(final BiomeLoadingEvent event) {
        if (event.getName() == null) return;

        if (event.getName().equals(GSKOBiomes.GSKO_FOREST_KEY.getRegistryName())) {
            List<Supplier<ConfiguredFeature<?,?>>> base = event.getGeneration().getFeatures(
                    GenerationStage.Decoration.VEGETAL_DECORATION
            );

            base.add(() -> GSKOFeatures.SAKURA_TREE
                    .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                    .withPlacement(Placement.COUNT_EXTRA.configure(
                            new AtSurfaceWithExtraConfig(1, 0.15f, 2)))
            );

            base.add(() -> Features.OAK
                    .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                    .withPlacement(Placement.COUNT_EXTRA.configure(
                            new AtSurfaceWithExtraConfig(1,0.23f, 2)
                    )));

            base.add(() -> Features.FANCY_OAK
                    .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                    .withPlacement(Placement.COUNT_EXTRA.configure(
                            new AtSurfaceWithExtraConfig(1,0.45f, 2)
                    )));
        }

        if (event.getName().equals(GSKOBiomes.BAMBOO_FOREST_LOST_KEY.getRegistryName())) {
            List<Supplier<ConfiguredFeature<?,?>>> base = event.getGeneration().getFeatures(
                    GenerationStage.Decoration.VEGETAL_DECORATION
            );

            base.add(() -> Features.BAMBOO_VEGETATION
                    .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                    .withPlacement(Placement.COUNT_EXTRA.configure(
                            new AtSurfaceWithExtraConfig(1, 0.9f, 2)))
            );
        }
    }

    public static void generateFlowers(final BiomeLoadingEvent event) {
        if (event.getName() == null) return;

        RegistryKey<Biome> biomeKey = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biomeKey);

        if (types.contains(BiomeDictionary.Type.WET) || types.contains(BiomeDictionary.Type.FOREST) ||
        types.contains(BiomeDictionary.Type.SWAMP)) {
            List<Supplier<ConfiguredFeature<?,?>>> base = event.getGeneration().getFeatures(
                    GenerationStage.Decoration.VEGETAL_DECORATION
            );
            base.add(() -> GSKOFeatures.LYCORIS);
        }

    }

    public static void generateHerbPlants(final BiomeLoadingEvent event) {
        if (event.getName() == null) return;

        RegistryKey<Biome> biomeKey = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biomeKey);

        if (types.contains(BiomeDictionary.Type.WET)) {
            List<Supplier<ConfiguredFeature<?,?>>> base = event.getGeneration().getFeatures(
                    GenerationStage.Decoration.VEGETAL_DECORATION
            );
            base.add(() -> GSKOFeatures.WASABI);
        }
    }

}
