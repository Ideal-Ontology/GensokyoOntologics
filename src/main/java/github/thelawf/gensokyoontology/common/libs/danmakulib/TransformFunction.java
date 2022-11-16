package github.thelawf.gensokyoontology.common.libs.danmakulib;

import github.thelawf.gensokyoontology.common.entity.projectile.DanmakuEntity;
import github.thelawf.gensokyoontology.common.libs.logoslib.math.MathCalculator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

public class TransformFunction extends ITransform.AbstractTransform {
    public static final Logger LOGGER = LogManager.getLogger();

    private World worldIn;

    private PlayerEntity playerIn;

    // -------------Initialize Rotations and Locations--------------//
    public double yaw, pitch, roll = 0.D;
    public double x, y, z = 0.D;
    public Vector3d initLocation = new Vector3d(0d,0d,0d);
    public Vector3d initRotation = new Vector3d(0d,0d,0d);

    // --------------------Function settings---------------------//

    /** 设定函数在每次释放符卡时被执行的次数 */
    public int executeTimes = 0;

    /** 函数的生命周期，即每次执行时执行的时长 */
    public double lifeSpan = 0.d;

    /** 设置本函数在应用于发射口时，下一枚子弹发射的间隔，0代表同时发射 */
    public double shootInterval = 0.d;

    /** 设置本函数所有执行次数完成后间隔多久执行另外的函数 */
    public double executeInterval = 0.d;

    /** 设置本函数的执行优先级，若某个发射口应用了多个函数则调用该优先级，同等优先级的函数会随机进行调用 */
    public int executePriority = 0;

     /** 设置本函数应用于的发射口发出的弹幕是否瞄准某个实体 */
    public UUID aimingAt = null;

    // -------------Parameters to Set Future Motions--------------//
    public double resultantSpeed;
    public static final double maxResultantSpeed = 5.d;
    public Vector3d acceleration = new Vector3d(0,0,0);
    public Vector3d speedV3 = new Vector3d(0,0,0);

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
        this.resultantSpeed = MathCalculator.toModulus3D(posX, posY, posZ);
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
                             double lifeSpan, double shootInterval, double executeInterval,
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

    public TransformFunction setRotation(Vector3d initRotation) {
        this.initRotation = initRotation;
        this.roll = initRotation.x;
        this.yaw = initRotation.y;
        this.pitch = initRotation.z;
        return this;
    }

    public TransformFunction setAcceleration(Vector3d acceleration) {
        this.acceleration = acceleration;
        this.x += this.acceleration.x;
        this.y += this.acceleration.y;
        this.z += this.acceleration.z;
        return this;
    }

    public TransformFunction setAimingAt(UUID aimingAt) {
        this.aimingAt = aimingAt;
        return this;
    }

    public TransformFunction setExecuteInterval(double executeInterval) {
        this.executeInterval = executeInterval;
        return this;
    }

    public TransformFunction setExecutePriority(int executePriority) {
        this.executePriority = executePriority;
        return this;
    }

    public TransformFunction setExecuteTimes(int executeTimes) {
        this.executeTimes = executeTimes;
        return this;
    }

    public TransformFunction setLifeSpan(double lifeSpan) {
        this.lifeSpan = lifeSpan;
        return this;
    }

    public TransformFunction setWorld(World worldIn) {
        this.worldIn = worldIn;
        return this;
    }

    public TransformFunction setYaw(double yaw) {
        this.yaw = yaw;
        return this;
    }

    public TransformFunction setPitch(double pitch) {
        this.pitch = pitch;
        return this;
    }

    public TransformFunction setRoll(double roll) {
        this.roll = roll;
        return this;
    }

    public TransformFunction setX(double x) {
        this.x = x;
        return this;
    }

    public TransformFunction setY(double y) {
        this.y = y;
        return this;
    }

    public TransformFunction setSpeedV3(Vector3d speedV3) {
        this.speedV3 = speedV3;
        this.resultantSpeed = MathCalculator.toModulus3D(speedV3.x, speedV3.y, speedV3.z);
        return this;
    }

    public TransformFunction setZ(double z) {
        this.z = z;
        return this;
    }

    public TransformFunction setInitLocation(Vector3d initLocation) {
        this.initLocation = initLocation;
        this.x = initLocation.x;
        this.y = initLocation.y;
        this.z = initLocation.z;
        return this;
    }

    public TransformFunction setShootInterval(double shootInterval) {
        this.shootInterval = shootInterval;
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

    @Override
    public TransformFunction rotate(Vector3d v3d) {
        this.roll = v3d.x;
        this.yaw = v3d.y;
        this.pitch = v3d.z;
        return this;
    }


    public void transform(double x, double y, double z, double yaw, double pitch, double roll) {

    }

    @Override
    public void translate() {

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
     * @param resultantSpeedIn 弹幕的合成速度
     * @param incrementYawIn yaw轴每tick的旋转角度，即方位角 θ
     * @param incrementPitchIn pitch轴每tick的旋转角度，即天顶角 φ
     * @return 返回一个三维向量(xSpeed,ySpeed,zSpeed)，注意x和z才是水平方向，而y是垂直方向。
     */
    public Vector3d angleToVector(double resultantSpeedIn, double incrementYawIn, double incrementPitchIn) {
        return new Vector3d(resultantSpeedIn * Math.sin(90D - incrementYawIn),
                resultantSpeedIn * Math.sin(incrementPitchIn) / Math.sin(90D - incrementPitchIn),
                resultantSpeedIn * Math.sin(incrementYawIn) / Math.sin(90D));
    }

    public void circleStyleShoot() {
        DanmakuEntity danmaku = new DanmakuEntity(playerIn, worldIn);
        if (worldIn.isRemote) {
            for (int i = 0; i < 20 * this.executeTimes; i++) {
                danmaku.shoot(this.initLocation.x + speedV3.x,
                        this.initLocation.y + speedV3.y,
                        this.initLocation.z + speedV3.z,
                        0.1f, 0.5f);
                worldIn.addEntity(danmaku);
            }
        }
    }


    @Override
    public Vector3d accelerate(Vector3d acceleration) {
        return this.acceleration = acceleration;
    }

    @Override
    public void shoot() {
        DanmakuEntity danmaku = new DanmakuEntity(playerIn, worldIn);
        if (worldIn.isRemote) {
            for (int i = 0; i < this.lifeSpan / shootInterval; i++) {

                this.resultantSpeed += MathCalculator.toModulus3D(this.acceleration.x, this.acceleration.y, this.acceleration.z);
                danmaku.setLocationAndAngles(this.x,this.y,this.z,
                        (float) this.yaw, (float) this.pitch);

                Vector3d towards = playerIn.getLookVec();
                danmaku.shoot(towards.x, towards.y, towards.z,
                        (float) this.resultantSpeed, 0.2f);
                worldIn.addEntity(danmaku);
            }
            LOGGER.info("Function executed");
        }
    }

}
