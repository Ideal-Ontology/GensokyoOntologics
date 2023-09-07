package github.thelawf.gensokyoontology.common.util.danmaku;

import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;

public class DanmakuData<D extends AbstractDanmakuEntity> {
    public World world;
    public D danmaku;
    public LivingEntity shooter;
    public Vector3d initLocation;
    public Vector2f initRotation;
    public Vector3f shootVec;
    public float speed;

    public DanmakuData(World world, D danmaku, LivingEntity shooter, Vector3d initLocation, Vector2f initRotation,
                       Vector3f shootVec, float speed) {
        this.world = world;
        this.danmaku = danmaku;
        this.shooter = shooter;
        this.initLocation = initLocation;
        this.initRotation = initRotation;
        this.shootVec = shootVec;
        this.speed = speed;
    }
}
