package github.thelawf.gensokyoontology.api.util;

import github.thelawf.gensokyoontology.common.util.logos.math.GSKOMathUtil;
import net.minecraft.entity.Entity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface IRayTraceReader {
    
    default List<List<AxisAlignedBB>> getRayTraceBox(Vector3d globalPos, Vector3d rayDirection, int length, float size) {
        List<List<AxisAlignedBB>> boxes = new ArrayList<>();
        List<AxisAlignedBB> aabb = new ArrayList<>();
        for (int i = 0; i < 50; i++) {

            Vector3d posRow = new Vector3d(rayDirection.x > 0 ? Vector3f.XP : Vector3f.XN);
            Vector3d posColumn = new Vector3d(rayDirection.z > 0 ? Vector3f.ZP : Vector3f.ZN);
            Vector3d posVertical = new Vector3d(rayDirection.y > 0 ? Vector3f.YP : Vector3f.YN);

            Vector3d rayPos = globalPos.add(rayDirection);

            AxisAlignedBB aabb0 = new AxisAlignedBB(GSKOMathUtil.vecFloor(rayPos),
                    GSKOMathUtil.vecCeil(rayPos));
            AxisAlignedBB aabb1 = new AxisAlignedBB(GSKOMathUtil.vecFloor(rayPos.add(posRow)),
                    GSKOMathUtil.vecCeil(rayPos.add(posRow)));
            AxisAlignedBB aabb2 = new AxisAlignedBB(GSKOMathUtil.vecFloor(rayPos.add(posColumn)),
                    GSKOMathUtil.vecCeil(rayPos.add(posColumn)));
            AxisAlignedBB aabb3 = new AxisAlignedBB(GSKOMathUtil.vecFloor(rayPos.add(posVertical)),
                    GSKOMathUtil.vecCeil(rayPos.add(posVertical)));

            aabb.add(aabb0.grow(size));
            aabb.add(aabb1.grow(size));
            aabb.add(aabb2.grow(size));
            aabb.add(aabb3.grow(size));

            boxes.add(aabb);
        }
        return boxes;
    }

    default <T extends Entity> List<T> getEntityWithin(World worldIn, Class<? extends T> entityClass, AxisAlignedBB aabb,
                                                        @Nullable Predicate<? super T> predicate) {
        if (predicate != null) {
            return worldIn.getEntitiesWithinAABB(entityClass, aabb).stream()
                    .filter(predicate).collect(Collectors.toList());
        }
        else {
            return worldIn.getEntitiesWithinAABB(entityClass, aabb);
        }
    }

    default boolean isIntersecting(Vector3d start, Vector3d end, Vector3d boxMin, Vector3d boxMax) {
        // 计算射线的参数
        double tMin = (boxMin.x - start.x) / (end.x - start.x);
        double tMax = (boxMax.x - start.x) / (end.x - start.x);

        if (tMin > tMax) {
            double temp = tMin;
            tMin = tMax;
            tMax = temp;
        }

        double tyMin = (boxMin.y - start.y) / (end.y - start.y);
        double tyMax = (boxMax.y - start.y) / (end.y - start.y);

        if (tyMin > tyMax) {
            double temp = tyMin;
            tyMin = tyMax;
            tyMax = temp;
        }

        if ((tMin > tyMax) || (tyMin > tMax)) {
            return false;
        }

        if (tyMin > tMin) {
            tMin = tyMin;
        }

        if (tyMax < tMax) {
            tMax = tyMax;
        }

        double tzMin = (boxMin.z - start.z) / (end.z - start.z);
        double tzMax = (boxMax.z - start.z) / (end.z - start.z);

        if (tzMin > tzMax) {
            double temp = tzMin;
            tzMin = tzMax;
            tzMax = temp;
        }

        return (!(tMin > tzMax)) && (!(tzMin > tMax));
    }

    default boolean isIntersecting(Vector3d start, Vector3d end, AxisAlignedBB aabb) {
        return isIntersecting(start, end,
                new Vector3d(aabb.minX, aabb.minY, aabb.minZ),
                new Vector3d(aabb.maxX, aabb.maxY, aabb.maxZ));
    }

    default boolean isIntersecting(Vector3d start, Vector3d direction, double distance, AxisAlignedBB aabb) {
        return isIntersecting(start, start.add(direction).scale(distance),
                new Vector3d(aabb.minX, aabb.minY, aabb.minZ),
                new Vector3d(aabb.maxX, aabb.maxY, aabb.maxZ));
    }

    default <T extends Entity> List<T> getSphericalTrace(World worldIn, Class<? extends T> entityClass,
                                                         AxisAlignedBB aabb, float radius) {
        return worldIn.getEntitiesWithinAABB(entityClass, aabb).stream()
                .filter(t -> aabb.getCenter().distanceTo(t.getPositionVec()) <= radius)
                .collect(Collectors.toList());
    }

    default <T extends Entity> List<T> getSphericalTrace(World worldIn, Class<? extends T> entityClass,
                                                         Predicate<? super T> predicate, AxisAlignedBB aabb, float radius) {
        return worldIn.getEntitiesWithinAABB(entityClass, aabb).stream()
                .filter(t -> aabb.getCenter().distanceTo(t.getPositionVec()) <= radius && predicate.test(t))
                .collect(Collectors.toList());
    }
}
