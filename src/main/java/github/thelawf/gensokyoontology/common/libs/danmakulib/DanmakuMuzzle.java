package github.thelawf.gensokyoontology.common.libs.danmakulib;

import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import java.util.ArrayList;

public class DanmakuMuzzle<D extends AbstractDanmakuEntity> {
    private TransformFunction func;
    private ArrayList<TransformFunction> motions;

    private final D danmaku;


    public DanmakuMuzzle(TransformFunction func, D danmaku) {
        this.func = func;
        this.danmaku = danmaku;
    }

    public DanmakuMuzzle(D danmaku, int lifespan) {
        this.danmaku = danmaku;
    }

    public TransformFunction getFunc() {
        return func;
    }

    public D getDanmaku() {
        return danmaku;
    }

    public double getX() {
        return func.initLocation.x;
    }

    public double getY() {
        return func.initLocation.y;
    }

    public double getZ() {
        return func.initLocation.z;
    }

    public float getYaw() {
        return (float) func.shootVector.y;
    }

    public float getPitch () {
        return (float) func.shootVector.z;
    }

    public PlayerEntity getPlayer() {
        return func.getPlayer();
    }

    public World getWorld() {
        return func.getPlayer().getEntityWorld();
    }

}
