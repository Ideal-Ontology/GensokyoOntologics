package github.thelawf.gensokyoontology.common.util.danmaku;

import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 用于优化性能的弹幕实体对象池
 */
public class DanmakuPool<D extends AbstractDanmakuEntity> extends DanmakuUtil {
    public Deque<D> pool;
    private final World world;

    private final EntityType<? extends AbstractDanmakuEntity> entityType;

    private final LivingEntity thrower;


    public DanmakuPool(EntityType<? extends AbstractDanmakuEntity> entityType, LivingEntity thrower, World world) {
        this.thrower = thrower;
        this.pool = new LinkedList<>();
        this.world = world;
        this.entityType = entityType;
    }

    // public DanmakuPool

    public D acquire(D danmaku, Vector3d positionVec, Vector2f rotationVec) {
        D entity = pool.pollFirst();
        if (entity == null) {
            entity = danmaku; // 替换为你的自定义投掷物实体类
        } else {
            entity.setNoGravity(true);
            entity.setLocationAndAngles(positionVec.x, positionVec.y, positionVec.z, rotationVec.x, rotationVec.y);
        }
        return entity;
    }

    public void release(D entity) {
        pool.offerLast(entity);
    }

    public EntityType<? extends AbstractDanmakuEntity> getEntityType() {
        return entityType;
    }

    public World getWorld() {
        return world;
    }

    public LivingEntity getOwner() {
        return thrower;
    }
}
