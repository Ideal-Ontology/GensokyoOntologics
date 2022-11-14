package github.thelawf.gensokyoontology.common.libs.danmakulib;

import github.thelawf.gensokyoontology.common.entity.DanmakuEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.UUID;

public class TransformFunction extends ITransform.AbstractTransform {

    private World worldIn;

    private PlayerEntity playerIn;

    // -------------Initialize Rotations and Locations--------------//
    public double yaw = 0.D;
    public double pitch = 0.D;
    public double roll = 0.D;
    public double posX = 0.D;
    public double posY = 0.D;
    public double posZ = 0.D;
    public Vector3d rotationVec = new Vector3d(0D,0D,0D);
    public Vector3d locationVec = new Vector3d(0d,0d,0d);

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
    public HashMap<String,HashMap<BlockPos,BlockPos>> toNewBlockPos = new HashMap<>();

    // /**
    //  * 在变换函数实例化时传入初始参数。
    //  * @param rotationVec 初始化旋转角度
    //  * @param locationVec 初始化坐标，此坐标默认为枢轴点坐标
    //  */
    // public TransformFunction(Vector3d rotationVec, @NotNull Vector3d locationVec){
    //     this.rotationVec = rotationVec;
    //     this.locationVec = locationVec;
    //     this.resultantSpeed = Math.pow(Math.pow(locationVec.x,2) + Math.pow(locationVec.y,2) +
    //             Math.pow(locationVec.z,2),0.5);
    // }
//
    // public TransformFunction(double yaw, double pitch, double roll,
    //                          double posX, double posY, double posZ) {
    //     this.yaw = yaw;
    //     this.pitch = pitch;
    //     this.roll = roll;
    //     this.posX = posX;
    //     this.posY = posY;
    //     this.posZ = posZ;
    //     this.resultantSpeed = Math.pow(posX * posX + posY * posY + posZ * posZ, 0.5);
    // }
//
    // /**
    //  * 使用for循环配合Entity.setLocationAndAngles()方法循环生成弹幕，循环次数为此函数生成的弹幕总量，使用形参 lifeSpan / shootInterval * executeTimes 来获得本函数生成的弹幕总量。
    //  * @param rotationVec 初始化旋转角度
    //  * @param locationVec 初始化枢轴点位置
    //  * @param executeTimes 函数的执行次数
    //  * @param lifeSpan 函数每次执行时的生命周期
    //  * @param shootInterval 函数每次生命周期内发射弹幕的间隔
    //  * @param executeInterval 所有执行次数结束后执行其他函数的间隔
    //  * @param executePriority 函数的执行优先级
    //  * @param aimingAt 是否瞄准某个实体
    //  * @param resultantSpeed 弹幕的合成速度
    //  */
    // public TransformFunction(Vector3d rotationVec, Vector3d locationVec, int executeTimes,
    //                          double lifeSpan, double shootInterval, double executeInterval,
    //                          int executePriority, UUID aimingAt, double resultantSpeed) {
    //     this.rotationVec = rotationVec;
    //     this.locationVec = locationVec;
    //     this.executeTimes = executeTimes;
    //     this.lifeSpan = lifeSpan;
    //     this.shootInterval = shootInterval;
    //     this.executeInterval = executeInterval;
    //     this.executePriority = executePriority;
    //     this.aimingAt = aimingAt;
    //     this.resultantSpeed = resultantSpeed;
    // }

    public static TransformFunction create() {
        return new TransformFunction();
    }

    public TransformFunction setAcceleration(Vector3d acceleration) {
        this.acceleration = acceleration;
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

    public TransformFunction setPosX(double posX) {
        this.posX = posX;
        return this;
    }

    public TransformFunction setPosY(double posY) {
        this.posY = posY;
        return this;
    }

    public TransformFunction setSpeedV3(Vector3d speedV3) {
        this.speedV3 = speedV3;
        return this;
    }

    public TransformFunction setPosZ(double posZ) {
        this.posZ = posZ;
        return this;
    }

    public TransformFunction setRotationVec(Vector3d rotationVec) {
        this.rotationVec = rotationVec;
        return this;
    }

    public TransformFunction setLocationVec(Vector3d locationVec) {
        this.locationVec = locationVec;
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


    public TransformFunction setToNewBlockPos(HashMap<String, HashMap<BlockPos, BlockPos>> toNewBlockPos) {
        this.toNewBlockPos = toNewBlockPos;
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
    public void rotate(Vector3d v3d) {

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
                danmaku.shoot(this.locationVec.x + speedV3.x,
                        this.locationVec.y + speedV3.y,
                        this.locationVec.z + speedV3.z,
                        0.1f, 0.5f);
                worldIn.addEntity(danmaku);
            }
        }
    }

    @Override
    public Vector3d accelerate(Vector3d acceleration) {
        return this.acceleration = acceleration;
    }

}
