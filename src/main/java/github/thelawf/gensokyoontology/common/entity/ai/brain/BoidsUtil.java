package github.thelawf.gensokyoontology.common.entity.ai.brain;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.feature.structure.VillageStructure;
import net.minecraft.world.gen.feature.structure.VillagesPools;
import net.minecraft.world.spawner.WorldEntitySpawner;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BoidsUtil {
    public static <E extends MobEntity> void separate(World world, List<E> boids, E mob, double distance) {
        Vector3d seperation = Vector3d.ZERO;
        for (MobEntity boid : boids) {
            world.getEntitiesWithinAABB(mob.getClass(),
                    boid.getBoundingBox().expand(new Vector3d(distance, 5, distance)))
                    .stream().map(e -> e.getPositionVec().subtract(boid.getPositionVec()).inverse())
                    .forEach(seperation::add);

            List<Double> distances = mapEntityNearby(world, boid, distance, e -> boid.getPositionVec()
                    .distanceTo(e.getPositionVec()));

            double sum = distances.stream().mapToDouble(Double::doubleValue).sum();
            double average = sum / distances.size();

            double ratio = average / distance;
            double diff = 1 / (ratio * ratio);

            seperation = seperation.scale(diff);
            Vector3d targetPos = boid.getPositionVec().add(seperation);

            boid.setAIMoveSpeed((float) diff);
            boid.getNavigator().pathfind(targetPos.x,targetPos.y, targetPos.z, 1);
        }
    }

    public static <R, E extends Entity> List<R> mapEntityNearby(World world, E mob, double distance, Function<Entity, R> mapper) {
        return world.getEntitiesWithinAABB(mob.getClass(),
                        mob.getBoundingBox().expand(new Vector3d(distance, 5, distance)))
                .stream().map(mapper).collect(Collectors.toList());

    }

    public static void align(List<Entity> boids, float distance) {

    }

    public static void cohere(List<Entity> boids, float distance, Vector3d offset) {

    }

}
