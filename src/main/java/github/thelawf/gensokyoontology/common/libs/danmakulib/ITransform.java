package github.thelawf.gensokyoontology.common.libs.danmakulib;

import net.minecraft.util.math.vector.Vector3d;

public interface ITransform {

    double getRotationYaw();
    double getRotationPitch();

    // rotate()函数接受所有的旋转操作
    void rotate(double... amountIn);
    void rotate(Vector3d v3d);

    // displace()函数接受所有的位移操作
    void displace(double... amountIn);
    void displace(Vector3d v3d);

    // transform()函数接受所有的变换操作
    void transform(double... amountIn);
    void transform(double x, double y, double z, double yaw, double pitch, double roll);

    void updateRotationAndPosition();
}
