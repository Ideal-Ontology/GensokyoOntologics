package github.thelawf.gensokyoontology.common.libs.danmakulib;

import net.minecraft.util.math.vector.Vector3d;

public interface ITransform {

    // rotate()函数接受所有的旋转操作
    void rotate(Vector3d vecPrev, Vector3d rotationVec);

    // transform()函数接受所有的变换操作
    void transform(double x, double y, double z, double yaw, double pitch, double roll);

    // 设置对象旋转的枢轴点坐标
    Vector3d translate(double pivotX, double pivotY, double pivotZ);

    abstract class AbstractTransform implements ITransform {
        public abstract Vector3d accelerate(Vector3d v3dIn);

        public abstract void shoot();
    }
}
