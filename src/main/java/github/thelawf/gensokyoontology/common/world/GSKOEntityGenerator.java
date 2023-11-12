package github.thelawf.gensokyoontology.common.world;

import github.thelawf.gensokyoontology.common.entity.monster.LilyWhiteEntity;
import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomes;
import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class GSKOEntityGenerator {

    @SafeVarargs
    public static void addEntityExceptBiomes(final BiomeLoadingEvent event, EntityType<?> type,
                                             int weight, int minCount, int maxCount, RegistryKey<Biome>... biomes) {
        boolean isSelected = Arrays.stream(biomes).map(RegistryKey::getLocation).map(Objects::toString)
                .anyMatch(obj -> obj.equals(event.getName().toString()));

        if (!isSelected) addEntityGeneration(event, type, weight, minCount, maxCount);
    }

    @SafeVarargs
    public static void addEntityToBiomes(final BiomeLoadingEvent event, EntityType<?> type,
                                         int weight, int minCount, int maxCount, RegistryKey<Biome>... biomes) {
        boolean isSelected = Arrays.stream(biomes).map(RegistryKey::getLocation).map(Objects::toString)
                .anyMatch(obj -> obj.equals(event.getName().toString()));

        if (isSelected) addEntityGeneration(event, type, weight, minCount, maxCount);
    }

    public static void addEntityGeneration(final BiomeLoadingEvent event, EntityType<?> type,
                                           int weight, int minCount, int maxCount) {
        List<MobSpawnInfo.Spawners> spawners = event.getSpawns().getSpawner(
                type.getClassification());

        spawners.add(new MobSpawnInfo.Spawners(type, weight, minCount, maxCount));
    }

    public static void trySpawnLilyWhite(WorldEvent.PotentialSpawns event) {
        if (event.getWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) event.getWorld();

            Random random = new Random();
            ResourceLocation biomeRegistryName = serverWorld.getBiome(event.getPos()).getRegistryName();
            if (random.nextInt(100) == 0 && biomeRegistryName != null) {
                if (biomeRegistryName.equals(GSKOBiomes.HAKUREI_SHRINE_PRECINCTS_KEY.getRegistryName()) &&
                        serverWorld.getEntities().noneMatch(entity -> entity.getType() == EntityRegistry.LILY_WHITE_ENTITY.get())) {

                    EntityType<LilyWhiteEntity> entityType = EntityRegistry.LILY_WHITE_ENTITY.get();
                    entityType.spawn(serverWorld, null, null, event.getPos(), SpawnReason.EVENT, false, false);
                    // LilyWhiteEntity lilyWhite = new LilyWhiteEntity(LilyWhiteEntity.LILY_WHITE, (World) event.getWorld());
                    // lilyWhite.setPosition(event.getPos().getX(), event.getPos().getY(), event.getPos().getZ());
                    // event.getWorld().addEntity(lilyWhite);
                }
            }
        }
    }

}
