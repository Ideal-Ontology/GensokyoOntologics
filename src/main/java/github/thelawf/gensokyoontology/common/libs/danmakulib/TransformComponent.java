package github.thelawf.gensokyoontology.common.libs.danmakulib;

import net.minecraft.util.math.vector.Vector3d;

public abstract class TransformComponent extends ITransform.AbstractTransform{
    public double pivotX;
    public double pivotY;
    public double pivotZ;

    public abstract Vector3d rotateRollYaw(double degreesRoll, double degreesYaw);
    public abstract Vector3d rotateRollPitch(double degreeRoll, double degreePitch);
    public abstract Vector3d rotateYawPitch(double degreeYaw, double degreePitch);
}
