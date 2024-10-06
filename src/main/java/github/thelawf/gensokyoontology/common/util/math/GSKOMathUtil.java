package github.thelawf.gensokyoontology.common.util.math;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.common.util.math.function.CosineFunc;
import github.thelawf.gensokyoontology.common.util.math.function.SineFunc;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GSKOMathUtil {

    private GSKOMathUtil() {
    }

    /**
     * 数值周期约束算法，比一般的约束算法多了一个判断周期的条件。周期的判定如下：<br>
     * 当数值小于下限时，即number
     * %
     * max
     * -
     * min
     * ==
     * 0同时number / max <= 1时，返回number本身，否则，先获取数值除以上限之后的整数部分，表示数值是上限的多少倍，然后用上限乘以这个倍率，最后用数值与这个结果相减，得出数值在下限和上限约束的周期内的对应值。
     */
    public static double clampPeriod(double number, double min, double max) {
        return number % max - min == 0 && number / max <= 1 ? number :
                number - max * Math.floor(number / max);
    }

    public static float clampPeriod(float number, float min, float max) {
        return number % max - min == 0 && number / max <= 1 ? number :
                (float) (number - max * Math.floor(number / max));
    }

    public static int clampPeriod(int number, int min, int max) {
        return number % max - min == 0 && number / max <= 1 ? number :
                (int) (float) (number - max * Math.floor((double) number / max));
    }

    /**
     * 波动周期算法，用于处理周期长度为 max - min，且周期在最大值和最小值之间线性变化，当超过最大值后，函数开始线性递减，直到达到最小值，然后再次线性递增的算法
     */
    public static float wavyPeriod(float time, float min, float max) {
        float period = max - min;
        float mod = time % (period * 2);
        return mod <= period ? min + mod : max - (mod - period);
    }

    /**
     * 波动周期算法，用于处理周期长度为 max - min，且周期在最大值和最小值之间线性变化，当超过最大值后，函数开始线性递减，直到达到最小值，然后再次线性递增的算法
     */
    public static double wavyPeriod(double time, double max, double min) {
        double period = max - min;
        double mod = time % (period * 2);
        return mod <= period ? min + mod : max - (mod - period);
    }

    /**
     * 传入四个双精度浮点数
     *
     * @param x1 第一个点的x坐标
     * @param y1 第一个点的y坐标
     * @param x2 第二个点的x坐标
     * @param y2 第二个点的y坐标
     * @return 上述两点间的距离
     */
    public static double distanceOf2D(double x1, double y1, double x2, double y2) {
        return Math.sqrt(square(x1 - x2) + square(y1 - y2));
    }

    public static double distanceOf3D(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.pow(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 + z2, 2), 0.5);
    }

    public static ArrayList<Vector3d> getCirclePoints2D(Vector3d center,
                                                        double radius, int count) {

        ArrayList<Vector3d> coordinates = new ArrayList<>();
        double radians = (Math.PI / 180) * Math.round(360d / count);
        for (int i = 0; i < count; i++) {
            double x = center.getX() + radius * Math.sin(radians * i);
            double y = center.getY() + radius * Math.cos(radians * i);
            Vector3d coordinate = new Vector3d(x, y, 0);
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


    public static Vector3d bezier2(Vector3d start, Vector3d end, Vector3d p, float time) {
        return lerp(time, lerp(time, start, p), lerp(time, p, end));
    }

    /**
     * 用于小于等于90度的水平转弯
     *
     * @param start 起始点
     * @param p     控制点
     * @param end   终点
     * @param time  步长
     * @return 在控制点的作用下，以起止点为基准的下一个点在曲线上的位置
     */
    public static Vector3d bezier2(Vector3d start, Vector3d end, Vector3d p, double time) {
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


    public static Vector3d lerp(float progress, Vector3d start, Vector3d end) {
        return start.add(end.subtract(start).scale(progress));
    }

    public static BlockPos lerp(float progress, BlockPos start, BlockPos end) {
        int x = MathHelper.floor(MathHelper.lerp(progress, start.getX(), end.getX()));
        int y = MathHelper.floor(MathHelper.lerp(progress, start.getY(), end.getY()));
        int z = MathHelper.floor(MathHelper.lerp(progress, start.getZ(), end.getZ()));
        return new BlockPos(x, y, z);
    }

    // tickExisted / MAX_TICK => not very smooth
    // tickExisted / MAX_TICK => a value between zero and one
    // partialTicks => a value between zero and one and is called between each tick
    // tick: 0, 0.1, 0.2, 0.3 ... 1, 1.1, 1.2, 1.3 ... 2 ...
    // ∴ (tickEx + partial) / MAX_TICK => a very smooth approach.
    public static float lerpTicks(float partial, int maxTick, int presentTick, float minValue, float maxValue) {
        return MathHelper.lerp((presentTick + partial) / maxTick, minValue, maxValue);
    }

    public static float wavyLerpTicks(int presentTick, int monotonicPeriod, float partial, float min, float max) {
        float mod = (presentTick + partial) % (monotonicPeriod * 2);
        float lerpTick = lerpTicks(partial, monotonicPeriod, clampPeriod(presentTick, 0, monotonicPeriod * 2), min, max);
        return mod <= monotonicPeriod ? min + lerpTick : lerpTick - (mod - monotonicPeriod);
    }

    /**
     * 求三维向量模长的运算
     *
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
     *
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
     *
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
     *
     * @param circleDots    组成一个圆周的所有点的平面直角坐标
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
     * x = a * y;
     * <p>
     * a^2 * y^2 + y^2 = r^2;
     * <p>
     * y^2 * (a^2 + 1) = r^2;
     * <p>
     * ∴ y = √(z^2 / a^2 + 1)
     *
     * @param hypotenuse 斜边长，即空间向量在该平面上的投影线段
     * @param roll       斜边与y轴的夹角
     * @return 返回一个仅有可知的两边组成的平面向量坐标
     */
    public static Vector3d toRollCoordinate(double hypotenuse, double roll) {
        double x = Math.sqrt(Math.pow(hypotenuse, 2) / Math.pow(Math.tan(roll), 2) + 1);
        return new Vector3d(x, Math.tan(roll) * x, 0);
    }

    public static Vector3d toYawCoordinate(double hypotenuse, double yaw) {
        double z = Math.sqrt(Math.pow(hypotenuse, 2) / Math.pow(Math.tan(yaw), 2) + 1);
        return new Vector3d(Math.tan(yaw) * z, 0, z);
    }

    public static Vector3d toPitchCoordinate(double hypotenuse, double pitch) {
        double y = Math.sqrt(Math.pow(hypotenuse, 2) / Math.pow(Math.tan(pitch), 2) + 1);
        return new Vector3d(0, y, Math.tan(pitch) * y);
    }

    public static Vector3d vecCeil(Vector3d vec) {
        return new Vector3d(
                Math.ceil(vec.getX()),
                Math.ceil(vec.getY()),
                Math.ceil(vec.getZ()));
    }

    public static Vector3d vecFloor(Vector3d vec) {
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

    public static float randomRange(float min, float max) {
        Random random = new Random();
        float randomValue = random.nextFloat();
        return min + (randomValue * (max - min));
    }

    public static double randomRange(double min, double max) {
        Random random = new Random();
        double randomValue = random.nextDouble();
        return min + (randomValue * (max - min));
    }

    public static Vector3d randomVec(double min, double max) {
        return new Vector3d(randomRange(min, max), randomRange(min, max), randomRange(min, max));
    }

    /**
     * Random Spherical Range Algorithm, returns a pair of coordinates on the given surface of a sphere.
     */
    public static Pair<Vector3d, Vector3d> rsr(Vector3d orientation, float yawRange, float pitchRange) {
        double yaw = toYawPitch(orientation).x;
        double pitch = toYawPitch(orientation).y;
        if (yaw < yawRange || yaw >= yawRange) {
            if (pitch > pitchRange) {
                return Pair.of(fromYawPitch(yawRange, pitchRange), fromYawPitch(yawRange, pitchRange).inverse());
            } else if (pitch < pitchRange) {
                return Pair.of(fromYawPitch(yawRange, pitchRange), fromYawPitch(yawRange, pitchRange).inverse());
            } else {
                return Pair.of(fromYawPitch(yawRange, (float) pitch), fromYawPitch(yawRange, (float) pitch).inverse());
            }
        } else {
            if (pitch > pitchRange) {
                return Pair.of(fromYawPitch(yawRange, pitchRange), fromYawPitch(yawRange, pitchRange).inverse());
            } else if (pitch < pitchRange) {
                return Pair.of(fromYawPitch(yawRange, pitchRange), fromYawPitch(yawRange, pitchRange).inverse());
            }
            return Pair.of(orientation, orientation.inverse());
        }
    }

    public static <V> V rollByWeight(int total, int weight, V value) {
        return new Random().nextInt(total) < weight ? null : value;
    }

    public static Vector2f toYawPitch(Vector3d vector3d) {
        double yaw = Math.atan2(-vector3d.x, vector3d.z);
        double pitch = Math.atan2(vector3d.y, Math.sqrt(vector3d.x * vector3d.x + vector3d.z * vector3d.z));
        return new Vector2f((float) toDegree(yaw), (float) toDegree(pitch));
    }

    public static void rotateMatrixToLookVec(MatrixStack matrixStackIn, Vector3d rotationVec) {
        float f5 = (float)Math.acos(rotationVec.y);
        float f6 = (float)Math.atan2(rotationVec.z, rotationVec.x);
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(((float)Math.PI / 2 - f6) * (180 / (float)Math.PI)));
        matrixStackIn.rotate(Vector3f.XP.rotationDegrees(f5 * (180 / (float)Math.PI)));
    }

    public static Vector3d fromYawPitch(float yaw, float pitch) {
        return new Vector3d(Math.cos(yaw) * Math.cos(pitch), Math.sin(yaw) * Math.sin(pitch), Math.sin(pitch));
    }

    /**
     * 弧度值除以Math.PI的结果为【180度的几分之几】
     *
     * @param radIn 弧度值
     * @return 角度值
     */
    public static double toDegree(double radIn) {
        return radIn / Math.PI * 180d;
    }

    /**
     * Math.PI 除以180度的结果为【每一角度等于多少弧度】
     *
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

    public static double square(double base) {
        return base * base;
    }

    public static double cube(double base) {
        return base * base * base;
    }

    public static Vector3d rotatePitchYaw(Vector3d prev, float pitch, float yaw) {
        CosineFunc cf = new CosineFunc(prev.x, 1,0);
        SineFunc sf = new SineFunc(1,1,0);
        double x = 0;
        return new Vector3d(0,0,0);
    }

    /**
     * Generate this code field by ChatGPT
     *
     * @param world 世界
     * @param block 将要被放置的方块
     * @param start 起始点
     * @param end   终点
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

    public static Quaternion conjugate(Quaternion quaternionIn) {
        return new Quaternion(-quaternionIn.getX(), -quaternionIn.getY(), -quaternionIn.getZ(), quaternionIn.getW());
    }

    public static Quaternion vecToQuaternion(Vector3d vector3d) {
        double yaw = Math.atan2(vector3d.z, vector3d.x);
        double pitch = Math.asin(vector3d.y);

        // 将角度转为四元数
        Quaternion quaternion = new Quaternion(Vector3f.YP, (float) Math.toDegrees(-yaw), true);// 设置水平旋转
        quaternion.multiply(new Quaternion(Vector3f.XP, (float) Math.toDegrees(pitch), true)); // 设置垂直旋转

        return quaternion;
    }

    public static Quaternion vecToQuaternion(Vector3f vector3f) {
        double yaw = Math.atan2(vector3f.getZ(), vector3f.getX());
        double pitch = Math.asin(vector3f.getY());

        // 将角度转为四元数
        Quaternion quaternion = new Quaternion(Vector3f.YP, (float) Math.toDegrees(-yaw), true);// 设置水平旋转
        quaternion.multiply(new Quaternion(Vector3f.XP, (float) Math.toDegrees(pitch), true)); // 设置垂直旋转

        return quaternion;
    }

    public static boolean isBetween(int num, int min, int max) {
        return num >= min && num < max;
    }

    public static boolean isBetween(float num, float min, float max) {
        return num >= min && num < max;
    }

    public static boolean isBetween(double num, double min, double max) {
        return num >= min && num < max;
    }

}
