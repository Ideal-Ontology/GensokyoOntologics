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
        return Math.pow(Math.pow(x1 - x2, 2) + Math.pow(x2 - y2, 2), 0.5);
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
     * 球坐标转直角坐标
     * @param sc 球坐标对象，是一个三维的向量
     * @return 返回空间直角坐标系对象
     */
    public static RectangularCoordinate toRectCoordinate(SphericalCoordinate sc) {
        return new RectangularCoordinate(sc.radius * Math.sin(sc.theta) * Math.cos(sc.phi),
                sc.radius * Math.sin(sc.theta) * Math.sin(sc.phi),
                sc.radius * Math.cos(sc.theta));
    }

    /**
     * 直角坐标转球坐标
     * @param rc 空间直角坐标系对象，同样是一个三维的向量
     * @return 返回球坐标系对象
     */
    public static SphericalCoordinate toSphereCoordinate(RectangularCoordinate rc) {
        double r = MathCalculator.toModulus3D(rc.x, rc.y, rc.z);
        return new SphericalCoordinate(r, Math.acos(rc.z / r), Math.atan(rc.y / rc.x));
    }
}
