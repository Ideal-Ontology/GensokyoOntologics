package github.thelawf.gensokyoontology.common.util.danmaku;

import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**用于优化性能的弹幕实体对象池
 *
 */
public class DanmakuEntityPool<D extends AbstractDanmakuEntity> {
    public Queue<D> pool;
    private final World world;

    private final EntityType<? extends AbstractDanmakuEntity> entityType;

    private final LivingEntity thrower;

    public DanmakuType danmakuType;
    public DanmakuColor danmakuColor;

    public DanmakuEntityPool(EntityType<? extends AbstractDanmakuEntity> entityType, LivingEntity thrower,
                             World world, DanmakuType danmakuType, DanmakuColor danmakuColor) {
        this.thrower = thrower;
        this.pool = new ConcurrentLinkedQueue<>();
        this.world = world;
        this.entityType = entityType;
        this.danmakuType = danmakuType;
        this.danmakuColor = danmakuColor;
    }

    // public DanmakuEntityPool

    public D acquireProjectile(D danmaku, Vector3d positionVec, Vector2f rotationVec) {
        D entity = pool.poll();
        if (entity == null) {
            entity = danmaku; // 替换为你的自定义投掷物实体类
        } else {
            entity.setNoGravity(true);
            entity.setLocationAndAngles(positionVec.x, positionVec.y, positionVec.z, rotationVec.x, rotationVec.y);
        }
        return entity;
    }

    public void releaseProjectile(D entity) {
        pool.offer(entity);
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
