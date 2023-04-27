package github.thelawf.gensokyoontology.common.events;

import com.google.common.collect.ImmutableList;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = GensokyoOntology.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
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
                8,3,10));
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

        serverWorld.getChunkProvider().getChunkGenerator().getBiomeProvider().
                getBiomes().forEach(biome -> spawnEntityIn(biome, biomeIds, classification));
    }

    private static void spawnEntityIn (Biome biome, List<ResourceLocation> biomeIds,
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
