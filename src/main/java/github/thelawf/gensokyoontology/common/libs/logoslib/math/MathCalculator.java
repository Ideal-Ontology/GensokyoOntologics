package github.thelawf.gensokyoontology.common.libs.logoslib.math;


import java.awt.*;

public class MathCalculator {

    private MathCalculator() {}

    /**
     * 这里的Point类是java.awt里面的类
     * @param p1 第一个点
     * @param p2 第二个点
     * @return 上述两点间的距离
     */
    public static double distanceBetweenPoints(Point p1, Point p2){
        return Math.pow(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2), 0.5);
    }

    /**
     * 传入四个双精度浮点数
     * @param x1 第一个点的x坐标
     * @param y1 第一个点的y坐标
     * @param x2 第二个点的x坐标
     * @param y2 第二个点的y坐标
     * @return 上述两点间的距离
     */
    public static double distanceBetweenPoints(double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public static double distanceBetweenPoints3D(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.pow(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 + z2, 2), 0.5);
    }

    /**
     * 这里不会抛出一个{@link TriangleNotUniqueException} 异常
     * @param vertexAIn 三角形∠A所对应的点的坐标
     * @param vertexBIn 三角形∠B所对应的点的坐标
     * @param vertexCIn 三角形∠C所对应的点的坐标
     * @return 返回一个平面三角形对象
     */
    public static Triangle2D solveTriangle(Point vertexAIn, Point vertexBIn, Point vertexCIn) {
        return new Triangle2D(vertexAIn, vertexBIn, vertexCIn);
    }

    /**
     * 求三维向量模长的运算
     * @param x 三维向量的x坐标
     * @param y 三维向量的y坐标
     * @param z 三维向量的z坐标
     * @return 返回三维向量的模长，或者立方体体对角线的长度
     */
    public static double toModulus3D(double x, double y, double z) {
        return Math.pow(x * x + y * y + z * z, 0.5);
    }

    public static double toModulus2D(double x, double y) {
        return Math.pow(x * x + y * y, 0.5);
    }

    /**
     * 球坐标转直角坐标，注意计算机图形学中的空间坐标与数学的空间坐标不同，x, z轴为水平轴，而y轴为竖直轴，
     * @param sc 球坐标对象，是一个三维的向量
     * @return 返回空间直角坐标系对象
     */
    public static RectangularCoordinate toRectCoordinate(SphericalCoordinate sc) {
        return new RectangularCoordinate(sc.radius * Math.sin(sc.theta) * Math.cos(sc.phi),
                sc.radius * Math.cos(sc.theta),
                sc.radius * Math.sin(sc.theta) * Math.sin(sc.phi));
    }

    /**
     * 直角坐标转球坐标，注意计算机图形学中的空间坐标与数学的空间坐标不同，x, z轴为水平轴，而y轴为竖直轴，
     * @param rc 空间直角坐标系对象，同样是一个三维的向量
     * @return 返回球坐标系对象
     */
    public static SphericalCoordinate toSphereCoordinate(RectangularCoordinate rc) {
        double r = MathCalculator.toModulus3D(rc.x, rc.y, rc.z);
        return new SphericalCoordinate(r, Math.acos(rc.y / r), Math.atan(rc.z / rc.x));
    }

    public static SphericalCoordinate toSphereCoordinate(double x, double y, double z) {
        return toSphereCoordinate(new RectangularCoordinate(x, y, z));
    }

    /**
     *
     * @param x 空间向量的x坐标
     * @param y 空间向量的y坐标
     * @param z 空间向量的z坐标
     * @return 返回一个空间向量在x-z平面上的投影同x轴的夹角， 在x-y平面上的投影同y轴的夹角，以及在z-y平面上的投影同z轴的夹角
     */
    public static double[] toRotations(double x, double y, double z) {
        return new double[]{Math.atan(x / z), Math.atan(y / x), Math.atan(z / y)};
    }

    /**
     * 计算方法：设斜边为r，两条直角边为x和y，斜边与y轴夹角为d，那么——
     * <p>
     * 1. 求出 tan(d) 的值，为一个常量，用a表示，由已知条件可得：tan()函数表示的是对边比邻边，即x比上y，且两条直角边的平方和等于斜边的平方，所以——
     * <p>
     * 2. 设方程① -- x / y = a;
     * <p>
     * 3. 设方程② -- x^2 + y^2 = r^2;
     * <p>
     * 4. 联立方程①②可得：
     * <p>
     *     x = a * y;
     * <p>
     *     a^2 * y^2 + y^2 = r^2;
     * <p>
     *     y^2 * (a^2 + 1) = r^2;
     * <p>
     *   ∴ y = √(z^2 / a^2 + 1)
     * @param hypotenuse 斜边长，即空间向量在该平面上的投影线段
     * @param roll 斜边与y轴的夹角
     * @return 返回一个仅有可知的两边组成的平面向量坐标
     */
    public static RectangularCoordinate toRollCoordinate(double hypotenuse, double roll) {
        double x = Math.sqrt(Math.pow(hypotenuse, 2) / Math.pow(Math.tan(roll), 2) + 1);
        return new RectangularCoordinate(x, Math.tan(roll) * x, 0);
    }

    public static RectangularCoordinate toYawCoordinate(double hypotenuse, double yaw) {
        double z = Math.sqrt(Math.pow(hypotenuse, 2) /  Math.pow(Math.tan(yaw), 2) + 1);
        return new RectangularCoordinate(Math.tan(yaw) * z, 0, z);
    }

    public static RectangularCoordinate toPitchCoordinate(double hypotenuse, double pitch) {
        double y = Math.sqrt(Math.pow(hypotenuse, 2) /  Math.pow(Math.tan(pitch), 2) + 1);
        return new RectangularCoordinate(0, y, Math.tan(pitch) * y);
    }

    /**
     * 弧度值除以Math.PI的结果为【180度的几分之几】
     * @param radIn 弧度值
     * @return 角度值
     */
    public static double toDegree(double radIn) {
        return radIn / Math.PI * 180d;
    }

    /**
     * Math.PI 除以180度的结果为【每一角度等于多少弧度】
     * @param degIn 角度值
     * @return 弧度值
     */
    public static double toRadian(double degIn) {
        return degIn * Math.PI / 180d;
    }
}
