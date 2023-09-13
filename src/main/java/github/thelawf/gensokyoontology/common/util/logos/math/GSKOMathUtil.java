package github.thelawf.gensokyoontology.common.util.logos.math;


import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GSKOMathUtil {

    private GSKOMathUtil() {}

    /**
     * 这里的Point类是java.awt里面的类
     * @param p1 第一个点
     * @param p2 第二个点
     * @return 上述两点间的距离
     */
    public static double distanceOf2D(Point p1, Point p2) {
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
    public static double distanceOf2D(double x1, double y1, double x2, double y2){
        return Math.sqrt(square(x1 - x2) + square(y1 - y2));
    }

    public static double distanceOf3D(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.pow(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 + z2, 2), 0.5);
    }

    public static Vector3d getIntersectVec(Vector3d p1, Vector3d v1,
                                           Vector3d p2, Vector3d v2) {
        Vector3d intersection = Vector3d.ZERO;
        if (v1.dotProduct(v2) == 1)
        {
            // 两线平行
            return null;
        }

        Vector3d startPointSeg = p2.subtract(p1);
        Vector3d vecS1 = v1.crossProduct(v2);            // 有向面积1
        Vector3d vecS2 = startPointSeg.crossProduct(v2); // 有向面积2
        double num = startPointSeg.dotProduct(vecS1);

        // 判断两这直线是否共面
        if (num >= 1E-05f || num <= -1E-05f)
        {
            return null;
        }

        // 有向面积比值，利用点乘是因为结果可能是正数或者负数
        double num2 = vecS2.dotProduct(vecS1) / vecS1.lengthSquared();

        Vector3d vector3d = new Vector3d(v1.x * num2, v1.y * num2, v1.z* num2 );
        intersection = p1.add(vector3d);

        return intersection;
    }


    public static ArrayList<Vector3d> getCirclePoints2D(Vector3d center,
                                                           double radius, int count) {

        ArrayList<Vector3d> coordinates = new ArrayList<>();
        double radians = (Math.PI / 180) * Math.round(360d / count);
        for (int i = 0; i < count; i++) {
            double x = center.getX() + radius * Math.sin(radians * i);
            double y = center.getY() + radius * Math.cos(radians * i);
            Vector3d coordinate = new Vector3d(x,y,0);
            coordinates.add(coordinate);
        }
        return coordinates;
    }

    public static Vector3d getPointOnCircle(Vector3d center,
                                            double radius, double angle) {
        return new Vector3d(
                center.getX() + radius * Math.cos(Math.toDegrees(angle)),
                0, center.getZ() + radius * Math.sin(Math.toDegrees(angle)));
    }

    public static Vector3d getPointOnOvalByAngle(Vector3d center,
                                                 double lengthX, double lengthY, double angle) {
        return new Vector3d(
                center.getX() + lengthX * Math.cos(Math.toDegrees(angle)),
                0, center.getY() + lengthY * Math.sin(Math.toDegrees(angle)));
    }

    public static Vector3d getPointOnOval(Vector3d center,
                                          double lengthX, double lengthY, double angle) {
        if (lengthX > lengthY) {
            double x = (lengthX * lengthY) / Math.sqrt(square(lengthX) + square(lengthY) + square(Math.tan(angle)));
            double y = (lengthX * lengthY * Math.tan(angle)) / Math.sqrt(square(lengthX) + square(lengthY) + square(Math.tan(angle)));
            return new Vector3d(center.getX() + x, center.getY() + y, 0);
        }
        else if (lengthX < lengthY) {
            double y = (lengthX * lengthY) / Math.sqrt(square(lengthX) + square(lengthY) + square(Math.tan(angle)));
            double x = (lengthX * lengthY * Math.tan(angle)) / Math.sqrt(square(lengthX) + square(lengthY) + square(Math.tan(angle)));
            return new Vector3d(center.getX() + x, center.getY() + y, 0);

        }
        else {
            return getPointOnCircle(center, lengthX, toDegree(angle));
        }
    }

    public static Vector3d bezier2(Vector3d start, Vector3d end, Vector3d p, float time) {
        return lerp(time, lerp(time, start, p), lerp(time, p, end));
    }

    /**
     * 用于小于等于90度的水平转弯
     * @param start 起始点
     * @param p 控制点
     * @param end 终点
     * @param time 步长
     * @return 在控制点的作用下，以起止点为基准的下一个点在曲线上的位置
     */
    public static Vector3d bezier2(Vector3d start, Vector3d end,  Vector3d p, double time) {
        if (time > 1 || time < 0)
            return null;
        Vector3d v1 = start.scale(pow2(1 - time));
        Vector3d v2 = p.scale(2 * time * (1 - time));
        Vector3d v3 = end.scale(pow2(time));

        return v1.add(v2.add(v3));
    }

    public static Vector3d bezier3(Vector3d p1, Vector3d p2, Vector3d q1, Vector3d q2, float time) {
        if (time > 1 || time < 0)
            return null;
        Vector3d v1 = lerp(time, p1, q1);
        Vector3d v2 = lerp(time, q1, q2);
        Vector3d v3 = lerp(time, q2, p2);
        Vector3d inner1 = lerp(time, v1, v2);
        Vector3d inner2 = lerp(time, v2, v3);
        return lerp(time, inner1, inner2);
    }

    public static Vector3d bezierDerivative(Vector3d p1, Vector3d p2, Vector3d q1, Vector3d q2, float t) {
        return p1.scale(-3 * t * t + 6 * t - 3)
                .add(q1.scale(9 * t * t - 12 * t + 3))
                .add(q2.scale(-9 * t * t + 6 * t))
                .add(p2.scale(3 * t * t));
    }

    public static Vector3d lerp(float p, Vector3d from, Vector3d to) {
        return from.add(to.subtract(from).scale(p));
    }

    public static BlockPos lerp(float progress, BlockPos start, BlockPos end) {
        int x = MathHelper.floor(MathHelper.lerp(progress, start.getX(), end.getX()));
        int y = MathHelper.floor(MathHelper.lerp(progress, start.getY(), end.getY()));
        int z = MathHelper.floor(MathHelper.lerp(progress, start.getZ(), end.getZ()));
        return new BlockPos(x,y,z);
    }

    /**
     *
     * @param start 本地坐标系的起点向量
     * @param end 本地坐标系的终点向量
     * @param startRotationRad 起点向量基于自身的本地坐标系的旋转角度（弧度值）
     * @param endRotationRad 终点向量基于自身的本地坐标系的旋转角度（弧度值）
     * @return 起点到终点的弧长
     */
    public static double getArcLengthFormTo(Vector3d start,
                                            Vector3d end,
                                            double startRotationRad,
                                            double endRotationRad) {
        double middle = Math.abs(start.x - end.x) / 2;
        double centerAngle = Math.PI / 4 - Math.abs(startRotationRad - endRotationRad);
        return Math.PI * 2 * middle * (centerAngle / (Math.PI / 2));
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
     * @param sc 球坐标的三维的向量，成员属性 x 为球坐标半径Radius，y 为球坐标天顶角theta，z为球坐标方位角phi
     * @return 返回空间直角坐标系的三维向量
     */
    public static Vector3d toRectVec(Vector3d sc) {
        return new Vector3d(sc.x * Math.sin(sc.y) * Math.cos(sc.z),
                sc.x * Math.sin(sc.y) * Math.sin(sc.z),
                sc.x * Math.cos(sc.y));
    }

    /**
     * 直角坐标转球坐标
     * @param rc 空间直角坐标系的三维向量
     * @return 返回球坐标系的三维向量
     */
    public static Vector3d toSphereVec(Vector3d rc) {
        double r = GSKOMathUtil.toModulus3D(rc.x, rc.y, rc.z);
        return new Vector3d(r, Math.acos(rc.z / r), Math.atan(rc.y / rc.x));
    }

    public static Vector3d toSphereVec(double x, double y, double z) {
        return toSphereVec(new Vector3d(x, y, z));
    }

    /**
     * 思路是：先将一个圆的所有点的集合传进来，这个集合中存放的点是使用的平面直角坐标系，然后，将这些点转为球坐标系表示，并执行球坐标上的天顶角和方位角旋转。
     * @param circleDots 组成一个圆周的所有点的平面直角坐标
     * @param thetaRotation 天顶角的旋转度数，取值为 0 ~ π
     * @return 旋转之后该圆周的每个新的点的平面直角坐标
     */
    public static List<Vector3d> rotateCircle(List<Vector3d> circleDots, double thetaRotation, double phiRotation) {
        List<Vector3d> nextCircleDots = new ArrayList<>();
        for (Vector3d dotOnCircle : circleDots) {

            Vector3d prevSphereVec = toSphereVec(dotOnCircle);
            Vector3d nextSphereVec = new Vector3d(prevSphereVec.x, prevSphereVec.y + thetaRotation, prevSphereVec.z + phiRotation);
            nextCircleDots.add(toRectVec(nextSphereVec));
        }
        return nextCircleDots;
    }

    public static Vector3d rotateCircleDot(Vector3d rectVec, double thetaRotation, double phiRotation) {
        Vector3d sphereVec = toSphereVec(rectVec);
        return new Vector3d(sphereVec.x, sphereVec.y + thetaRotation, sphereVec.z + phiRotation);
    }

    public static Vector3d toLocalCoordinate(Vector3d newOriginIn, Vector3d globalIn) {
        return new Vector3d(globalIn.getX() - newOriginIn.getX(),
                globalIn.getY() - newOriginIn.getY(),
                globalIn.getZ() - newOriginIn.getZ());

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

    public static Vector3d rotateZXY(Vector3d prevVec, Vector3d rotation) {
        return prevVec.rotatePitch((float) rotation.z).rotateRoll((float) rotation.x).rotateYaw((float) rotation.y);
    }

    public static Vector3d rotateZXY(Vector3d prevVec, float roll, float yaw, float pitch) {
        return rotateZXY(prevVec, new Vector3d(roll, yaw, pitch));
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
    public static Vector3d toRollCoordinate(double hypotenuse, double roll) {
        double x = Math.sqrt(Math.pow(hypotenuse, 2) / Math.pow(Math.tan(roll), 2) + 1);
        return new Vector3d(x, Math.tan(roll) * x, 0);
    }

    public static Vector3d toYawCoordinate(double hypotenuse, double yaw) {
        double z = Math.sqrt(Math.pow(hypotenuse, 2) /  Math.pow(Math.tan(yaw), 2) + 1);
        return new Vector3d(Math.tan(yaw) * z, 0, z);
    }

    public static Vector3d toPitchCoordinate(double hypotenuse, double pitch) {
        double y = Math.sqrt(Math.pow(hypotenuse, 2) /  Math.pow(Math.tan(pitch), 2) + 1);
        return new Vector3d(0, y, Math.tan(pitch) * y);
    }

    public static Vector3d vecCeil(Vector3d vec) {
        return new Vector3d(
                Math.ceil(vec.getX()),
                Math.ceil(vec.getY()),
                Math.ceil(vec.getZ()));
    }

    public static Vector3d vecFloor (Vector3d vec) {
        return new Vector3d(
                Math.floor(vec.getX()),
                Math.floor(vec.getY()),
                Math.floor(vec.getZ()));
    }

    public static Vector2f getEulerAngle(Vector3d vectorA, Vector3d vectorB) {
        // 计算旋转矩阵的第一行
        double m11 = vectorA.x * vectorB.x + vectorA.y * vectorB.y;
        double m12 = vectorA.x * vectorB.y - vectorA.y * vectorB.x;
        double m13 = vectorA.z * vectorB.x;

        // 计算旋转矩阵的第三行
        double m31 = -vectorA.y;
        double m32 = vectorA.x;
        double m33 = 0;

        // 计算 yaw、pitch 和 roll 欧拉角
        double yaw = Math.atan2(m12, m11);
        double pitch = Math.asin(m31);

        return new Vector2f((float) yaw, (float) pitch);
    }

    public static int randomRange(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    public static <V> V rollByWeight(int total, int weight, V value) {
        return new Random().nextInt(total) < weight ? null : value;
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

    public static double pow2(double base) {
        return square(base);
    }

    public static double pow3(double base) {
        return cube(base);
    }

    public static double square(double base){
        return base * base;
    }

    public static double cube(double base) {
        return base * base * base;
    }

    /**
     * 快速傅里叶变换算法
     */
    public static List<Complex> fft (List<Complex> complexes) {
        int n = complexes.size();

        List<Complex> list = new ArrayList<>();
        if (n == 1) {

            list.add(complexes.get(0));
        }
        return list;
    }

    /** Generate this code field by ChatGPT
     * @param world 世界
     * @param block 将要被放置的方块
     * @param start 起始点
     * @param end 终点
     */
    public static void generateBlockOnBezierCurve(World world, Block block, BlockPos start, BlockPos end) {
        // 获取起点和终点之间的距离
        double dist = start.distanceSq(end);

        // 计算中点和控制点的坐标
        double mx = (start.getX() + end.getX()) / 2.0;
        double my = (start.getY() + end.getY()) / 2.0 + (dist / 16.0);
        double mz = (start.getZ() + end.getZ()) / 2.0;
        double cx = (start.getX() + end.getX() + my - start.getY()) / 2.0;
        double cz = (start.getZ() + end.getZ() + my - start.getY()) / 2.0;

        // 获取贝塞尔曲线上的坐标，并在每个坐标处放置草方块
        int numPoints = (int) Math.sqrt(dist) * 2;
        for (int i = 0; i <= numPoints; i++) {
            double t = (double) i / (double) numPoints;
            double x = Math.pow(1 - t, 2) * start.getX() + 2 * (1 - t) * t * cx + Math.pow(t, 2) * end.getX();
            double y = Math.pow(1 - t, 2) * start.getY() + 2 * (1 - t) * t * my + Math.pow(t, 2) * end.getY();
            double z = Math.pow(1 - t, 2) * start.getZ() + 2 * (1 - t) * t * cz + Math.pow(t, 2) * end.getZ();

            BlockPos pos = new BlockPos(x, y, z);
            if (world.getBlockState(pos).getBlock() == Blocks.AIR) {
                world.setBlockState(pos, block.getDefaultState());
            }
        }
    }

    public static ArrayList<Vector3d> getBlocksOnBezierCurve(BlockPos start, BlockPos end) {
        // 获取起点和终点之间的距离
        double dist = start.distanceSq(end);
        ArrayList<Vector3d> vecList = new ArrayList<>();

        // 计算中点和控制点的坐标
        double mx = (start.getX() + end.getX()) / 2.0;
        double my = (start.getY() + end.getY()) / 2.0 + (dist / 16.0);
        double mz = (start.getZ() + end.getZ()) / 2.0;
        double cx = (start.getX() + end.getX() + my - start.getY()) / 2.0;
        double cz = (start.getZ() + end.getZ() + my - start.getY()) / 2.0;

        // 获取贝塞尔曲线上的坐标，并在每个坐标处放置草方块
        int numPoints = (int) Math.sqrt(dist) * 2;
        for (int i = 0; i <= numPoints; i++) {
            double t = (double) i / (double) numPoints;
            double x = Math.pow(1 - t, 2) * start.getX() + 2 * (1 - t) * t * cx + Math.pow(t, 2) * end.getX();
            double y = Math.pow(1 - t, 2) * start.getY() + 2 * (1 - t) * t * my + Math.pow(t, 2) * end.getY();
            double z = Math.pow(1 - t, 2) * start.getZ() + 2 * (1 - t) * t * cz + Math.pow(t, 2) * end.getZ();

            vecList.add(new Vector3d(x, y, z));
        }
        return vecList;
    }
}
