package github.thelawf.gensokyoontology.common.libs.logoslib.math;


public class SphericalCoordinate {
    // 设对象面朝的本地坐标轴为 z轴，对象左右手的本地坐标轴为 x轴，对象上下的本地坐标轴为 y轴
    // 那么用欧拉角表示物体的旋转为：
    // 绕 y轴旋转 - yaw：偏航角；绕 z轴旋转 - pitch：俯仰角；绕 x轴旋转 - roll：翻滚角

    // 可以去查看ProjectileEntity 里面的shoot()，setDirectionAndMovement()和
    // updatePitchAndYaw()方法, 还可以查看 ThrowableEntity 的 tick()方法，
    // Entity类里面的moveToBlockPosAndAngles(),setLocationAndAngles()方法，setPositionAndRotation()方法

    /**
     * 设置圆周运动动画的思路：
     * <p>
     * 1. 计算机是按帧数刷新率来显示图像的，在代码上体现为 for循环循环每一帧画面时改变对象的位置或速度。
     * <p>
     * 2. 在 Minecraft中，设每一个游戏刻为 t，设实体的生命周期为 l。在此例中重写实体的tick()方法，每一个游戏刻执行一次。
     * <p>
     * 3. 圆周运动是变加速运动，结合思路 1.2.可知：如果采用 for循环，则每一次迭代时对象的速度都会发生改变且很难与游戏刻 t发生关联
     * <p>
     * 4. 圆周运动需要有一个中心，在计算机图形学的惯例中，这个中心被称为枢轴点。于是我们可建立一个以枢轴点为坐标中心的球坐标系和笛卡尔坐标系。
     * <p>
     * 5. 综上，我们采用三角函数来计算对象每一个游戏刻的 x，z，y坐标。设对象距离枢轴点的初始距离为 R，圆周运动总共转过的角度为 A，每一帧的 x坐标增量的计算方法为：x = R / sin(A / l * t)
     */
    public final double radius;
    public final double theta;
    public final double phi;


    public SphericalCoordinate(double radius, double theta, double phi) {
        this.radius = radius;
        this.theta = theta;
        this.phi = phi;
    }
}
