package github.thelawf.gensokyoontology.common.entity.misc;

import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.common.util.GSKODamageSource;
import github.thelawf.gensokyoontology.common.util.math.CurveUtil;
import github.thelawf.gensokyoontology.data.CatmullRomSpline;
import github.thelawf.gensokyoontology.data.GSKOSerializers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 曲线激光使用和PS相同的Catmull-Rom曲线进行插值
 */
public class CurveLaser extends Laser {

    public static final DataParameter<Float> DATA_SPEED = EntityDataManager.createKey(
            CurveLaser.class, DataSerializers.FLOAT);
    public static final DataParameter<Integer> DATA_TAIL_DELAY = EntityDataManager.createKey(
            CurveLaser.class, DataSerializers.VARINT);
    public static final DataParameter<CatmullRomSpline> DATA_SPLINE = EntityDataManager.createKey(
            CurveLaser.class, GSKOSerializers.CATMULL_ROM);

    public CurveLaser(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public CurveLaser(World worldIn, Entity owner, CatmullRomSpline spline, float speed, int tailDelay) {
        super(worldIn, owner);
    }

    public static CurveLaser create(World world, Entity owner, CatmullRomSpline spline, float speed, int tailDelay) {
        return new CurveLaser(world, owner, spline, speed, tailDelay);
    }

    public List<Pair<Vector3d, Vector3d>> getSegments(float unitTime) {
        List<Vector3d> vertexes = new ArrayList<>();
        List<Pair<Vector3d, Vector3d>> segments = new ArrayList<>();
        CatmullRomSpline spline = this.getSpline();
        for (float t = 0; t < 1; t += unitTime) {
            vertexes.add(CurveUtil.catmullRom(spline, t));
        }

        for (int i = vertexes.size() - 1; i <= vertexes.size(); i++) {
            Vector3d start = vertexes.get(i);
            Vector3d end = vertexes.get(i + 1);
            segments.add(Pair.of(start, end));
        }
        return segments;
    }

    @Override
    public void tick() {
        super.tick();
        float endProgress = this.getSpeed() * ticksExisted >= 100 ? 1F : this.getSpeed() * ticksExisted / 100F;
        Vector3d end = CurveUtil.catmullRom(this.getSpline(), endProgress);
        Vector3d start = this.getSpline().getStart();
        Vector3d ctrl1 = this.getSpline().getCtrl1();
        Vector3d ctrl2 = this.getSpline().getCtrl2();

        this.setSpline(CatmullRomSpline.create(ctrl1, start, end, ctrl2));

        List<Pair<Vector3d, Vector3d>> segments = this.getSegments(0.02F);
        segments.forEach(pair ->
                this.rayTrace(this.world, this, pair.getFirst(), pair.getSecond()).ifPresent(entity -> {
                    if (this.getOwner() == null) return;
                    if (!(entity instanceof LivingEntity)) return;
                    if (entity.getUniqueID().equals(this.getOwner().getUniqueID())) return;
                    LivingEntity living = (LivingEntity) entity;
                    living.attackEntityFrom(GSKODamageSource.LASER, 4);
        }));
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(DATA_SPEED, 0.0f);
        this.dataManager.register(DATA_TAIL_DELAY, 40);
        this.dataManager.register(DATA_SPLINE, CatmullRomSpline.EMPTY);
    }

    public float getSpeed() {
        return this.dataManager.get(DATA_SPEED);
    }
    public int getTailDelay() {
        return this.dataManager.get(DATA_TAIL_DELAY);
    }
    public CatmullRomSpline getSpline() {
        return this.dataManager.get(DATA_SPLINE);
    }

    /**
     * 速度的单位是百分比每刻（%/tick）<br>
     * 即，每 1 游戏刻的时间内激光头部前进的距离比上曲线的全长<br>
     * 例如：传入2.3，即 2.3%/tick。在第一个游戏刻，激光尾位于 0% 的位置，激光头位于 2.3% 的位置；在第二个游戏刻，激光尾位于 0% 的位置，激光头位于 4.6% 的位置
     */
    public void setSpeed(float speed) {
        this.dataManager.set(DATA_SPEED, speed);
    }
    public void setTailDelay(int tailDelay) {
        this.dataManager.set(DATA_TAIL_DELAY, tailDelay);
    }
    public void setSpline(CatmullRomSpline spline) {
        this.dataManager.set(DATA_SPLINE, spline);
    }

    @Override
    protected void readAdditional(@NotNull CompoundNBT compound) {
        super.readAdditional(compound);
        this.setSpeed(compound.getFloat("speed"));
        this.setTailDelay(compound.getInt("tailDelay"));
        this.setSpline(CatmullRomSpline.deserializeNBT(compound.getCompound("Spline")));
    }

    @Override
    protected void writeAdditional(@NotNull CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putFloat("speed", this.getSpeed());
        compound.putInt("tailDelay", this.getTailDelay());
        compound.put("Spline", CatmullRomSpline.serializeNBT(this.getSpline()));
    }
}
