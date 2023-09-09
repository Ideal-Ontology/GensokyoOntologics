package github.thelawf.gensokyoontology.common.util.danmaku;

import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class DanmakuData {
    @NotNull
    public World world;
    @NotNull
    public LivingEntity shooter;

    @Nullable
    public LivingEntity target;
    public Vector3d initLocation;
    public Vector2f initRotation;
    public Vector3f shootVec;
    public float speed;

    public DanmakuData(@NotNull World world, @NotNull LivingEntity shooter, Vector3d initLocation, Vector2f initRotation,
                       Vector3f shootVec, float speed) {
        this.world = world;
        this.shooter = shooter;
        this.initLocation = initLocation;
        this.initRotation = initRotation;
        this.shootVec = shootVec;
        this.speed = speed;
    }

    public DanmakuData(World world, LivingEntity shooter, @Nullable LivingEntity target, Vector3d initLocation, Vector2f initRotation,
                       Vector3f shootVec, float speed) {
        this.world = world;
        this.shooter = shooter;
        this.target = target;
        this.initLocation = initLocation;
        this.initRotation = initRotation;
        this.shootVec = shootVec;
        this.speed = speed;
    }
}
