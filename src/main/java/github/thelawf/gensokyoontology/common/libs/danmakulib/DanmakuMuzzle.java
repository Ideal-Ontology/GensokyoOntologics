package github.thelawf.gensokyoontology.common.libs.danmakulib;

import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DanmakuMuzzle<D extends AbstractDanmakuEntity> {
    private TransformFunction func;
    public List<TransformFunction> futureTransform = new ArrayList<>();

    private D danmaku;

    private Consumer<TransformFunction> consumer = (function) -> {};
    public DanmakuMuzzle(TransformFunction func, List<TransformFunction> futureTransform, D danmaku) {
        this.func = func;
        this.danmaku = danmaku;
        this.futureTransform = futureTransform;
    }

    public DanmakuMuzzle(TransformFunction func, D danmaku) {
        this.func = func;
        this.danmaku = danmaku;
    }

    public DanmakuMuzzle(Consumer<TransformFunction> consumer, D danmaku) {
        this.consumer = consumer;
        this.danmaku = danmaku;
    }

    public TransformFunction getFunc() {
        return func;
    }

    public D getDanmaku() {
        return danmaku;
    }

    public void run () {
        consumer.accept(func);
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

    public void setFutureTransform (TransformFunction function) {
        this.futureTransform.add(function);
    }

}
