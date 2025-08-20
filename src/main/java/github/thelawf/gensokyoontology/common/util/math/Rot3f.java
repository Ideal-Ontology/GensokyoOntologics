package github.thelawf.gensokyoontology.common.util.math;

import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;

public class Rot3f {
    public final Vector3f axis;
    public final float angleDegrees;

    public Rot3f(Vector3f axis, float angleDegrees) {
        this.axis = axis;
        this.angleDegrees = angleDegrees;
    }

    public Quaternion applyRotation() {
        return axis.rotationDegrees(this.angleDegrees);
    }

    public static Rot3f of(Vector3f axis, float angleDegrees) {
        return new Rot3f(axis, angleDegrees);
    }

    public static Quaternion identity() {
        return Quaternion.ONE;
    }
}
