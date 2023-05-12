package github.thelawf.gensokyoontology.common.libs.danmakulib;

import github.thelawf.gensokyoontology.common.entity.projectile.DanmakuShotEntity;
import github.thelawf.gensokyoontology.common.libs.logoslib.math.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

public class TransformFunction extends ITransform.AbstractTransform {
    public static final Logger LOGGER = LogManager.getLogger();

    private World worldIn;

    private PlayerEntity playerIn;

    // -------------Initialize Rotations and Locations--------------//

    /** 设置实体的枢轴点坐标 */
    public double pivotX, pivotY, pivotZ = 0.D;

    /** 设置实体的朝向 */
    public double yaw, pitch, roll = 0.D;

    public double x, y, z = 0.D;
    public Vector3d initLocation = new Vector3d(0d,0d,0d);
    public Vector3d shootVector = new Vector3d(0d,0d,0d);

    // --------------------Function settings---------------------//

    /** 设定函数在每次释放符卡时被执行的次数 */
    public int executeTimes = 0;

    /** 函数的生命周期，即每次执行时执行的时长 */
    public int lifeSpan = 0;

    /** 设置本函数在应用于发射口时，下一枚子弹发射的间隔，0代表同时发射 */
    public double shootInterval = 0.d;

    /** 设置本函数所有执行次数完成后间隔多久执行另外的函数 */
    public double executeInterval = 0.d;

    /** 设置本函数的执行优先级，若某个发射口应用了多个函数则调用该优先级，同等优先级的函数会随机进行调用 */
    public int executePriority = 0;

     /** 设置本函数应用于的发射口发出的弹幕是否瞄准某个实体 */
    public UUID aimingAt = null;

    public int delayedExeTicks = 0;

    // -------------Parameters to Set Future Motions--------------//

    /** 实体的瞬时矢量速度 */
    public Vector3d speedV3 = new Vector3d(0,0,0);

    /** 实体的瞬时合成速度 */
    public double resultantSpeed;

    /** 实体的最大速度 */
    public static final double maxResultantSpeed = 5.d;

    /** 角度的线性增加量  */
    public double increment = 0.D;

    /** 角度的非线性增加量 */
    public Vector3d increment3D = new Vector3d(0d,0d,0d);

    /** （角度/位移/速度）的非线性增加量 */
    public Vector3d acceleration = new Vector3d(0,0,0);

    // -------------Constructors, Builders, and Implements------------- //

    public TransformFunction getInstance() {
        return this;
    }

    public static class Builder extends TransformFunction {
        public static TransformFunction create() {
            return new TransformFunction();
        }
    }

    public TransformFunction() {
    }

    public TransformFunction(double yaw, double pitch, double roll,
                             double posX, double posY, double posZ) {
        this.yaw = yaw;
        this.pitch = pitch;
        this.roll = roll;
        this.x = posX;
        this.y = posY;
        this.z = posZ;
        this.resultantSpeed = GSKOMathUtil.toModulus3D(posX, posY, posZ);
    }
    /**
     * 使用for循环配合Entity.setLocationAndAngles()方法循环生成弹幕，循环次数为此函数生成的弹幕总量，使用形参 lifeSpan / shootInterval * executeTimes 来获得本函数生成的弹幕总量。
     * @param locationVec 初始化枢轴点位置
     * @param executeTimes 函数的执行次数
     * @param lifeSpan 函数每次执行时的生命周期
     * @param shootInterval 函数每次生命周期内发射弹幕的间隔
     * @param executeInterval 所有执行次数结束后执行其他函数的间隔
     * @param executePriority 函数的执行优先级
     * @param aimingAt 是否瞄准某个实体
     * @param resultantSpeed 弹幕的合成速度
     */
    public TransformFunction(Vector3d rotationVec, Vector3d locationVec, int executeTimes,
                             int lifeSpan, double shootInterval, double executeInterval,
                             int executePriority, UUID aimingAt, double resultantSpeed) {
        this.roll = rotationVec.x;
        this.yaw = rotationVec.y;
        this.pitch = rotationVec.z;
        this.initLocation = locationVec;
        this.executeTimes = executeTimes;
        this.lifeSpan = lifeSpan;
        this.shootInterval = shootInterval;
        this.executeInterval = executeInterval;
        this.executePriority = executePriority;
        this.aimingAt = aimingAt;
        this.resultantSpeed = resultantSpeed;
    }

    public TransformFunction setAimingAt(UUID aimingAt) {
        this.aimingAt = aimingAt;
        return this;
    }

    public TransformFunction setShootVector(Vector3d shootVector) {
        this.shootVector = shootVector;
        return this;
    }

    public TransformFunction setAcceleration(Vector3d acceleration) {
        this.acceleration = acceleration;
        return this;
    }


    public TransformFunction setShootInterval(double shootInterval) {
        this.shootInterval = shootInterval;
        return this;
    }

    public TransformFunction setDelayedExeTicks(int delayedExeTicks) {
        this.delayedExeTicks = delayedExeTicks;
        return this;
    }

    public TransformFunction setExecuteInterval(double executeInterval) {
        this.executeInterval = executeInterval;
        return this;
    }

    public TransformFunction setExecuteTimes(int executeTimes) {
        this.executeTimes = executeTimes;
        return this;
    }

    public TransformFunction setLifeSpan(int lifeSpan) {
        this.lifeSpan = lifeSpan;
        return this;
    }

    public TransformFunction setWorld(World worldIn) {
        this.worldIn = worldIn;
        return this;
    }

    public Vector3d increaseYaw(float yawIncrement) {
        float f = MathHelper.cos(yawIncrement);
        float f1 = MathHelper.sin(yawIncrement);
        double d0 = this.x * (double)f + this.z * (double)f1;
        double d1 = this.y;
        double d2 = this.z * (double)f - this.x * (double)f1;
        this.speedV3 = new Vector3d(this.speedV3.x + d0, this.speedV3.y, this.speedV3.z + d2);
        return new Vector3d(d0, d1, d2);
    }

    public Vector3d increasePitch(float pitchIncrement) {
        float f = MathHelper.cos(pitchIncrement);
        float f1 = MathHelper.sin(pitchIncrement);
        double d0 = this.x;
        double d1 = this.y * (double)f + this.z * (double)f1;
        double d2 = this.z * (double)f - this.y * (double)f1;
        this.speedV3 = new Vector3d(this.speedV3.x, this.speedV3.y + d1, this.speedV3.z + d2);
        return new Vector3d(d0, d1, d2);
    }

    @OnlyIn(Dist.CLIENT)
    public Vector3d increaseRoll(float rollIncrement) {
        float f = MathHelper.cos(rollIncrement);
        float f1 = MathHelper.sin(rollIncrement);
        double d0 = this.x * (double)f + this.y * (double)f1;
        double d1 = this.y * (double)f - this.x * (double)f1;
        double d2 = this.z;
        this.speedV3 = new Vector3d(this.speedV3.x + d0, this.speedV3.y + d1, this.speedV3.z);
        return new Vector3d(d0, d1, d2);
    }


    public TransformFunction setInitLocation(Vector3d initLocation) {
        this.initLocation = initLocation;
        this.x = initLocation.x;
        this.y = initLocation.y;
        this.z = initLocation.z;
        return this;
    }

    public TransformFunction setResultantSpeed(double resultantSpeed) {
        this.resultantSpeed = resultantSpeed;
        return this;
    }
    public TransformFunction setPlayer(PlayerEntity playerIn) {
        this.playerIn = playerIn;
        return this;
    }


    public TransformFunction setIncrement(double increment) {
        this.increment = increment;
        return this;
    }

    public PlayerEntity getPlayer () {
        return this.playerIn;
    }

    public int getExecuteTimes() {
        return executeTimes;
    }

    public double getLifeSpan() {
        return lifeSpan;
    }

    public double getShootInterval() {
        return shootInterval;
    }

    public double getExecuteInterval() {
        return executeInterval;
    }

    public int getExecutePriority() {
        return executePriority;
    }

    public double getPivotX() {
        return pivotX;
    }

    public double getPivotY() {
        return pivotY;
    }

    public double getPivotZ() {
        return pivotZ;
    }

    public Vector3d getInitLocation() {
        return initLocation;
    }

    public Vector3d getShootVector() {
        return shootVector;
    }

    /**
     * 通过给定的组件坐标和枢轴点坐标旋转该组件
     * @param vecPrev 之前的向量坐标方位
     * @param incrementV3 三维向量增加量。vector内的三个参数分别对应roll，yaw和pitch，单位是角度值
     */
    @Override
    public void rotate(Vector3d vecPrev, Vector3d incrementV3) {
        double incrementRoll = incrementV3.x;
        double incrementYaw = incrementV3.y;
        double incrementPitch = incrementV3.z;
    }

    @Deprecated
    public Vector3d rotate(Vector3d prevPos, Vector3d pivotLocation, Vector3d rotationV3) {
        double radius = GSKOMathUtil.distanceOf3D(prevPos.x, prevPos.y, prevPos.z,
                pivotLocation.x, pivotLocation.y, pivotLocation.z);
        double incrementX = 0;
        double incrementY = 0;
        double incrementZ = 0;

        if (rotationV3.x != 0) {
            LineSegment hypotenuse = new LineSegment3D(pivotLocation.x, pivotLocation.y,0d, prevPos.x, prevPos.y,0d);
            RectangularCoordinate rc = GSKOMathUtil.toRollCoordinate(hypotenuse.getLength(), rotationV3.x);
            incrementX = rc.x;
            incrementY = rc.y;
        }
        if (rotationV3.y != 0) {
            LineSegment hypotenuse = new LineSegment3D(pivotLocation.x,0d , pivotLocation.z, prevPos.x, 0d, prevPos.z);
            RectangularCoordinate rc = GSKOMathUtil.toYawCoordinate(hypotenuse.getLength(), rotationV3.y);
            incrementX = rc.x;;
            incrementZ = rc.z;
        }
        if (rotationV3.z != 0) {
            LineSegment hypotenuse = new LineSegment3D(0d, pivotLocation.y , pivotLocation.z, 0d, prevPos.y, prevPos.z);
            RectangularCoordinate rc = GSKOMathUtil.toPitchCoordinate(hypotenuse.getLength(), rotationV3.z);
            incrementY = rc.y;
            incrementZ = rc.z;
        }
        this.x += incrementX;
        this.y += incrementY;
        this.z += incrementZ;
        return new Vector3d(prevPos.x + incrementX, prevPos.y + incrementY, prevPos.z + incrementZ);
    }

    public void transform(double x, double y, double z, double yaw, double pitch, double roll) {

    }

    @Override
    public Vector3d translate(double pivotX, double pivotY, double pivotZ) {
        this.pivotX = pivotX;
        this.pivotY = pivotY;
        this.pivotZ = pivotZ;
        return new Vector3d(pivotX, pivotY, pivotZ);
    }

    /**
     * 将旋转角度的增加值转为三维向量的速度，并使用如下方法计算三维坐标——
     * <br>方法一，直接解三角形；
     * <br>方法二，使用球坐标和平面直角坐标的转换，θ-方位角 φ-天顶角 r-径向距离（在本例中是弹幕的合成速度）：
     * <br> x = r * sin(θ) * cos(φ)
     * <br> y = r * sin(θ) * sin(φ)
     * <br> z = r * cos(θ)
     * @param incrementYawIn yaw轴的旋转角度的增加值，即方位角 θ
     * @param incrementPitchIn pitch轴的旋转角度的增加值，即天顶角 φ
     * @return 返回一个三维向量(xSpeed,ySpeed,zSpeed)，注意x和z才是水平方向，而y是垂直方向。
     */
    @Deprecated
    public Vector3d angleToVector(double incrementYawIn, double incrementPitchIn) {
        return new Vector3d(this.resultantSpeed * Math.sin(90D - incrementYawIn),
                this.resultantSpeed * Math.sin(incrementPitchIn) / Math.sin(90D - incrementPitchIn),
                this.resultantSpeed * Math.sin(incrementYawIn) / Math.sin(90D));
    }

    /**
     * 将旋转角度的增加值转为三维向量的速度，旋转角度增加值可以用360 / 弹幕总量得到，并使用如下方法计算三维坐标——
     * <br>方法一，直接解三角形；
     * <br>方法二，使用球坐标和平面直角坐标的转换，θ-方位角 φ-天顶角 r-径向距离（在本例中是弹幕的合成速度）：
     * <br> x = r * sin(θ) * cos(φ)
     * <br> y = r * sin(θ) * sin(φ)
     * <br> z = r * cos(θ)
     * @deprecated 现在请使用 {@link github.thelawf.gensokyoontology.common.libs.logoslib.math.RectangularCoordinate} 来将球坐标角度转为直角坐标向量
     * @param resultantSpeedIn 弹幕的合成速度
     * @param incrementYawIn yaw轴每tick的旋转角度，即方位角 θ
     * @param incrementPitchIn pitch轴每tick的旋转角度，即天顶角 φ
     * @return 返回一个三维向量(xSpeed,ySpeed,zSpeed)，注意x和z才是水平方向，而y是垂直方向。
     */
    @Deprecated
    public Vector3d angleToVector(double resultantSpeedIn, double incrementYawIn, double incrementPitchIn) {
        return new Vector3d(resultantSpeedIn * Math.sin(90D - incrementYawIn),
                resultantSpeedIn * Math.sin(incrementPitchIn) / Math.sin(90D - incrementPitchIn),
                resultantSpeedIn * Math.sin(incrementYawIn) / Math.sin(90D));
    }

    @Override
    public Vector3d accelerate(Vector3d acceleration) {
        return this.acceleration = acceleration;
    }

}
