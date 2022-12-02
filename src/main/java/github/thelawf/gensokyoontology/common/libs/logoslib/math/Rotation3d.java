package github.thelawf.gensokyoontology.common.libs.logoslib.math;

public class Rotation3d {
    public double roll;
    public double yaw;
    public double pitch;

    public Rotation3d(double roll, double yaw, double pitch) {
        this.roll = roll;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public double getRoll() {
        return roll;
    }

    public void setRoll(double roll) {
        this.roll = roll;
    }

    public double getYaw() {
        return yaw;
    }

    public void setYaw(double yaw) {
        this.yaw = yaw;
    }

    public double getPitch() {
        return pitch;
    }

    public void setPitch(double pitch) {
        this.pitch = pitch;
    }

}
