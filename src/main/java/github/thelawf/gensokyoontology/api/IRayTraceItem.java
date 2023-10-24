package github.thelawf.gensokyoontology.api;

import github.thelawf.gensokyoontology.common.util.logos.math.GSKOMathUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.Explosion;
import net.minecraftforge.common.extensions.IForgeItem;

import java.util.ArrayList;
import java.util.List;

public interface IRayTraceItem {
    
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

}
