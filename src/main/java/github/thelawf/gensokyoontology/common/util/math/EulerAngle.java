package github.thelawf.gensokyoontology.common.util.math;

import net.minecraft.util.math.vector.Quaternion;

public class EulerAngle {
    private float xAngle;
    private float yAngle;
    private float zAngle;

    public EulerAngle(float xAngle, float yAngle, float zAngle) {
        this.xAngle = xAngle;
        this.yAngle = yAngle;
        this.zAngle = zAngle;
    }


    public static EulerAngle of(float pitch, float yaw, float roll, boolean isDegree) {
        return new EulerAngle(pitch, yaw, roll);
    }

    public Quaternion toRotation() {
        return GSKOMathUtil.fromEulerAngle(this);
    }

    public EulerAngle handleLock(){
        if (this.pitch() > 89.9){
            this.setYaw(this.yaw() + this.roll());
            this.setRoll(0);
        }
        else if (this.pitch() < -89.9){
            this.setYaw(this.yaw() - this.roll());
            this.setRoll(0);
        }
        return this;
    }

    public float roll(){
        return this.xAngle;
    }
    public float pitch(){
        return this.yAngle;
    }
    public float yaw(){
        return this.zAngle;
    }

    public float getXAngle() {
        return this.xAngle;
    }
    public float getYAngle() {
        return this.yAngle;
    }
    public float getZAngle() {
        return this.zAngle;
    }

    public void setRoll(float roll){
        this.xAngle = roll;
    }
    public void setPitch(float pitch){
        this.yAngle = pitch;
    }
    public void setYaw(float yaw){
        this.zAngle = yaw;
    }

    public void setxAngle(float xAngle) {
        this.xAngle = xAngle;
    }

    public void setyAngle(float yAngle) {
        this.yAngle = yAngle;
    }

    public void setzAngle(float zAngle) {
        this.zAngle = zAngle;
    }
}
