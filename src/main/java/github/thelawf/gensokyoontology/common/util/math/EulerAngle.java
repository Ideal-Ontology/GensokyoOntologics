package github.thelawf.gensokyoontology.common.util.math;

import net.minecraft.util.math.vector.Quaternion;

public class EulerAngle {
    private float pitch;
    private float yaw;
    private float roll;

    public EulerAngle(float yaw, float pitch, float roll) {
        this.yaw = yaw;
        this.pitch = pitch;
        this.roll = roll;
    }

    public static EulerAngle of(float yaw, float pitch,  float roll) {
        return new EulerAngle(yaw, pitch, roll);
    }
    public static EulerAngle of(double yaw, double pitch,  double roll) {
        return new EulerAngle((float) yaw, (float) pitch,  (float) roll);
    }

    public static EulerAngle from(Quaternion quaternion) {
        return GSKOMathUtil.getEulerAngle(quaternion);
    }

    public Quaternion toRotation() {
        return GSKOMathUtil.toQuaternion(this);
    }

    public Quaternion toQuaternion() {
        return this.toRotation();
    }

    public EulerAngle handleLock() {
        // 万向锁条件：绕Y轴（Pitch）接近±90°
        if (Math.abs(pitch) >= 89.9f) {
            this.yaw += this.roll;
            this.roll = 0;    // 固定Roll为0，避免歧义
        }
        return this;
    }

    public float yaw(){
        return this.yaw;
    }
    public float pitch(){
        return this.pitch;
    }
    public float roll(){
        return this.roll;
    }

    public void setRoll(float roll){
        this.pitch = roll;
    }
    public void setPitch(float pitch){
        this.roll = pitch;
    }
    public void setYaw(float yaw){
        this.yaw = yaw;
    }

}
