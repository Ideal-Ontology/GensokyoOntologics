package github.thelawf.gensokyoontology.common.libs.danmakulib;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.living.EntityTeleportEvent;

import java.util.HashMap;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

public class TransformFunction{

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

    /* 设定函数在每次释放符卡时被执行的次数 */
    public double executeTimes = 0.d;

    /* 函数的生命周期，即每次执行时执行的时长 */
    public double lifeSpan = 0.d;

    /* 设置本函数在应用于发射口时，下一枚子弹发射的间隔，0代表同时发射 */
    public double shootInterval = 0.d;

    /* 设置本函数所有执行次数完成后间隔多久执行另外的函数 */
    public double executeInterval = 0.d;

    /** 设置本函数的执行优先级，若某个发射口应用了多个函数则调用该优先级，同等优先级的函数会随机进行调用 */
    public int executePriority = 0;

     /* 设置本函数应用于的发射口发出的弹幕是否瞄准某个实体 */
    public UUID aimingAt = null;

    // -------------Parameters to Set Future Motions--------------//
    public double resultantSpeed = 0.D;
    public Vector3d speedVec = new Vector3d(0,0,0);
    public Vector3d rotateSpeedVec = new Vector3d(0,0,0);
    public HashMap<String,HashMap<BlockPos,BlockPos>> toNewBlockPos = new HashMap<>();

    public TransformFunction(Vector3d rotationVec, Vector3d locationVec){
        this.rotationVec = rotationVec;
        this.locationVec = locationVec;
    }

    public TransformFunction(double yaw, double pitch, double roll,
                             double speedX, double speedY, double speedZ) {
        this.yaw = yaw;
        this.pitch = pitch;
        this.roll = roll;
        this.posX = speedX;
        this.posY = speedY;
        this.posZ = speedZ;
        this.resultantSpeed = Math.pow(speedX * speedX + speedY * speedY + speedZ * speedZ, 0.5);
    }

    public TransformFunction(double speedX, double speedY, double speedZ){
        this.posX = speedX;
        this.posY = speedY;
        this.posZ = speedZ;
        this.resultantSpeed = Math.pow(speedX * speedX + speedY * speedY + speedZ * speedZ, 0.5);
    }

    public void transform(double... amountIn) {

    }

    public void transform(double x, double y, double z, double yaw, double pitch, double roll) {

    }

}
