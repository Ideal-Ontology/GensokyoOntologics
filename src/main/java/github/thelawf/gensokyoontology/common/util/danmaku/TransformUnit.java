package github.thelawf.gensokyoontology.common.util.danmaku;

import net.minecraft.util.math.vector.Vector3d;

public class TransformUnit {
    private final float yaw, roll, pitch;
    private final float scaling;
    private final Vector3d acceleration, subtraction;

    public TransformUnit(float yaw, float roll, float pitch, float scaling, Vector3d acceleration, Vector3d subtraction) {
        this.yaw = yaw;
        this.roll = roll;
        this.pitch = pitch;
        this.scaling = scaling;
        this.acceleration = acceleration;
        this.subtraction = subtraction;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }

    public float getPitch() {
        return pitch;
    }

    public float getScaling() {
        return scaling;
    }

    public Vector3d getAcceleration() {
        return acceleration;
    }

    public Vector3d getSubtraction() {
        return subtraction;
    }
}
