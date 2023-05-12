package github.thelawf.gensokyoontology.common.libs.danmakulib;

import net.minecraft.util.math.vector.Vector3d;

public class DanmakuMuzzle extends TransformComponent{

    @Override
    public void rotate(Vector3d vecPrev, Vector3d rotationVec) {

    }

    @Override
    public void transform(double x, double y, double z, double yaw, double pitch, double roll) {

    }

    @Override
    public Vector3d translate(double x, double y, double z) {
        return new Vector3d(pivotX, pivotY, pivotZ);
    }

    @Override
    public Vector3d accelerate(Vector3d v3dIn) {
        return null;
    }


    @Override
    public Vector3d rotateRollYaw(double degreesRoll, double degreesYaw) {
        return null;
    }

    @Override
    public Vector3d rotateRollPitch(double degreeRoll, double degreePitch) {
        return null;
    }

    @Override
    public Vector3d rotateYawPitch(double degreeYaw, double degreePitch) {
        return null;
    }
}
