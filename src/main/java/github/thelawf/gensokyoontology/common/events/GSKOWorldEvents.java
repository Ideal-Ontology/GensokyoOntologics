package github.thelawf.gensokyoontology.common.events;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.world.GSKODimensions;
import github.thelawf.gensokyoontology.common.world.GSKOEntityGenerator;
import github.thelawf.gensokyoontology.common.world.feature.GSKOFeatureGenerator;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.StructureRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.apache.logging.log4j.LogManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = GensokyoOntology.MODID)
public class GSKOWorldEvents {

    @SubscribeEvent
    public static void onLivingSpawn(BiomeLoadingEvent event) {
        if (event.getCategory().equals(Biome.Category.THEEND) ||
                event.getCategory().equals(Biome.Category.NETHER)) {
            return;
        }
        List<MobSpawnInfo.Spawners> spawners = event.getSpawns().getSpawner(
                EntityRegistry.FAIRY_ENTITY.get().getClassification());

        spawners.add(new MobSpawnInfo.Spawners(EntityRegistry.FAIRY_ENTITY.get(),
                38, 2, 4));
    }

    @SubscribeEvent
    public static void trySpawnBoss(WorldEvent.PotentialSpawns event) {
        if (event.getWorld().isRemote()) return;

        ServerWorld serverWorld = (ServerWorld) event.getWorld();
        GSKOEntityGenerator.trySpawnLilyWhite(event);

    }

    @SubscribeEvent
    public static void onChunkLoad(ChunkEvent.Load event) {
        IChunk chunk = event.getChunk();
        if (chunk.getWorldForge() instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) chunk.getWorldForge();
            if (serverWorld.getDimensionKey().equals(GSKODimensions.GENSOKYO)) {
                Biome biome = serverWorld.getBiome(chunk.getPos().asBlockPos());
                final String modid = GensokyoOntology.MODID;
            }
        }
    }

    @SubscribeEvent
    public static void addDimensionSpacing(final WorldEvent.Load event) {
        if (event.getWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) event.getWorld();
            Method GET_CODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "func_230347_a_");
            try {
                ResourceLocation location = Registry.CHUNK_GENERATOR_CODEC.getKey(
                        (Codec<? extends ChunkGenerator>) GET_CODEC_METHOD.invoke(
                                serverWorld.getChunkProvider().getChunkGenerator()));
                if (location != null && location.getNamespace().equals("terraforged")) {
                    return;
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                LogManager.getLogger().error("Was unable to check if " + serverWorld.getDimensionKey()
                        + " is using Terraforged's ChunkGenerator.");
            }
            // 放止建筑在超平坦世界生成
            if (serverWorld.getChunkProvider().generator instanceof FlatChunkGenerator &&
                    serverWorld.getDimensionKey().equals(World.OVERWORLD)) {
                return;
            }

            // 将我们的建筑添加到建筑生成地图中，反混淆映射如下：
            //        srg名：               反混淆名：                                类型/返回值：                                             作用：
            //   func_235957_b()     getDimensionSettings()         return -> DimensionStructuresSettings                              获取世界维度生成设置
            //   field_236193_d_          structures                Map<Structure<?>, StructureSeparationSettings>             存放建筑结构和建筑生成设置的映射Map
            //   field_236191_b_       structureSettings       ImmutableMap<Structure<?>, StructureSeparationSettings>      存放建筑结构和建筑生成设置的不可变映射Map
            //   field_236268_b_            feature                           F extends Structure<FC>                            泛型：一切继承于建筑结构的类
            Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(
                    serverWorld.getChunkProvider().generator.func_235957_b_().func_236195_a_());

            tempMap.putIfAbsent(StructureRegistry.MYSTIA_IZAKAYA.get(),
                    DimensionStructuresSettings.field_236191_b_.get(StructureRegistry.MYSTIA_IZAKAYA.get()));

            tempMap.putIfAbsent(StructureRegistry.HAKUREI_SHRINE.get(),
                    DimensionStructuresSettings.field_236191_b_.get(StructureRegistry.HAKUREI_SHRINE.get()));

            tempMap.putIfAbsent(StructureRegistry.CIRNO_ICE_HOUSE.get(),
                    DimensionStructuresSettings.field_236191_b_.get(StructureRegistry.CIRNO_ICE_HOUSE.get()));

            tempMap.putIfAbsent(StructureRegistry.CHIREIDEN.get(),
                    DimensionStructuresSettings.field_236191_b_.get(StructureRegistry.CHIREIDEN.get()));

            tempMap.putIfAbsent(StructureRegistry.BEAST_PATHWAY.get(),
                    DimensionStructuresSettings.field_236191_b_.get(StructureRegistry.BEAST_PATHWAY.get()));

            tempMap.putIfAbsent(StructureRegistry.HUMAN_VILLAGE.get(),
                    DimensionStructuresSettings.field_236191_b_.get(StructureRegistry.HUMAN_VILLAGE.get()));

            serverWorld.getChunkProvider().generator.func_235957_b_().field_236193_d_ = tempMap;
        }
    }

    @SubscribeEvent
    public static void onBiomeLoad(final BiomeLoadingEvent event) {
        GSKOFeatureGenerator.generateOverworldOre(event);
        GSKOFeatureGenerator.generateGesokyoOres(event);
    }


    private static void spawnEntityIn(ServerWorld serverWorld, EntityClassification classification,
                                      WorldEvent.PotentialSpawns event) {

        List<ResourceLocation> biomeIds = Arrays.asList(
                new ResourceLocation("minecraft:plains"),
                new ResourceLocation("minecraft:desert"),
                new ResourceLocation("minecraft:forest"),
                new ResourceLocation("miencraft:taiga"),
                new ResourceLocation("minecraft:mountains"),
                new ResourceLocation("minecraft:snowy_tundra"),
                new ResourceLocation("minecraft:snowy_mountains"),
                new ResourceLocation("minecraft:jungle"),
                new ResourceLocation("minecraft:birch_forest"),
                new ResourceLocation("minecraft:savanna"),
                new ResourceLocation("minecraft:savanna_plateau"),
                new ResourceLocation("minecraft:dark_forest"),
                new ResourceLocation("minecraft:bamboo_jungle"),
                new ResourceLocation("minecraft:giant_spruce_taiga")
        );

        serverWorld.getChunkProvider().getChunkGenerator().getBiomeProvider()
                .getBiomes().forEach(biome -> spawnEntityIn(biome, biomeIds, classification));
    }

    private static <FC extends IFeatureConfig, F extends Feature<FC>> void generateFeatureIn(Biome biome, ConfiguredFeature<FC, F> feature) {


        // List<Supplier<ConfiguredFeature<?,?>>> base = biome.getGenerationSettings().getFeatures()
        //         .get(GenerationStage.Decoration.VEGETAL_DECORATION.ordinal());
//
        // base.add(GenerationStage.Decoration.VEGETAL_DECORATION.ordinal(),
        //         () -> feature.withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
        //         .withPlacement(Placement.COUNT_EXTRA.configure(
        //                 new AtSurfaceWithExtraConfig(1, 0.8f, 2))));
    }

    private static void spawnEntityIn(Biome biome, List<ResourceLocation> biomeIds,
                                      EntityClassification classification) {
        List<EntityType<?>> entityTypes = ImmutableList.of(
                EntityRegistry.FAIRY_ENTITY.get()
        );

        biomeIds.forEach(resourceLocation -> {
            if (biome.getRegistryName() != null && biome.getRegistryName().equals(resourceLocation)) {
                int weight = 8;
                int minCount = 3;
                int maxCount = 8;


                // entityTypes.forEach(entityType -> biome.getMobSpawnInfo().getSpawners(classification)
                //         .add(new MobSpawnInfo.Spawners(EntityRegistry.FAIRY_ENTITY.get(),
                //                 weight, minCount, maxCount)));
            }
        });
    }
}
