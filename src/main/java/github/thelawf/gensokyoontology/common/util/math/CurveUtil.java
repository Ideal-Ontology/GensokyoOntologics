package github.thelawf.gensokyoontology.common.util.math;

import com.mojang.datafixers.util.Pair;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class CurveUtil {
    /**
     * @param time    线性插值的步长，小于1，起点一般为0.001，用于曲线精度的测定
     * @param pointIn 定义的曲线起点和终点
     * @return 用于将直线绘制成曲线的二维节点
     */
    public static Point2D getSmoothSegment2d(double time, ArrayList<Point2D> pointIn) {
        Point2D[] points = new Point2D[pointIn.size()];
        for (int k = 0; k < pointIn.size(); k++)
            points[k] = pointIn.get(k);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4 - i - 1; j++) {
                double x = (1 - time) * points[j].getX() + time * points[j + 1].getX();
                double y = (1 - time) * points[j].getY() + time * points[j + 1].getY();
                points[j] = new Point2D.Double(x, y);
            }
        }
        return points[0];
    }

    @OnlyIn(Dist.CLIENT)
    public static List<Vector3d> getBezierPos(List<Vector3d> bezierPositions, Vector3d start, Vector3d end, Vector3d p, float time) {
        for (float i = 0; i < 1; i += time) {
            if (bezierPositions.size() > 1F / time) break;
            bezierPositions.add(GSKOMathUtil.bezier2(start, end, p, i));
        }
        return bezierPositions;
    }

    public static List<Vector3d> getBezierPos(List<Vector3d> bezierPositions, Vector3d start, Vector3d end, Vector3d ctrl1, Vector3d ctrl2, float time) {
        for (float i = 0; i < 1; i += time) {
            if (bezierPositions.size() > 1F / time) break;
            bezierPositions.add(GSKOMathUtil.bezier3(start, end, ctrl1, ctrl2, i));
        }
        return bezierPositions;
    }

    public static List<Vector3d> getBezierNormal(List<Vector3d> bezierPositions, Vector3d start, Vector3d end,  Vector3d ctrl1, Vector3d ctrl2, float time) {
        for (float i = 0; i < 1; i += time) {
            if (bezierPositions.size() > 1F / time) break;
            bezierPositions.add(GSKOMathUtil.bezier3Derivative(start, end, ctrl1, ctrl2, i));
        }
        return bezierPositions;
    }

    /**
     * 计算法线向量
     * @param tangent 切线向量
     * @return 法线向量
     */
    private static Vector3d calculateNormal(Vector3d tangent) {
        // 选择参考上向量（通常是世界向上或曲线平面法线）
        Vector3d upVector = new Vector3d(0, 1, 0);

        // 计算法线（垂直于切线和上向量）
        Vector3d binormal = tangent.crossProduct(upVector).normalize();

        // 如果法线太小（切线接近垂直），使用备用上向量
        if (binormal.lengthSquared() < 0.01) {
            upVector = new Vector3d(0, 0, 1);
            binormal = tangent.crossProduct(upVector).normalize();
        }

        return binormal;
    }

    public static List<Vector3d> getBezierNormal(List<Vector3d> bezierPositions, Vector3d start, Vector3d ctrl,  Vector3d end, float time) {
        for (float i = 0; i < 1; i += time) {
            if (bezierPositions.size() > 1F / time) break;
            bezierPositions.add(GSKOMathUtil.bezier2Derivative(start, ctrl, end, i));
        }
        return bezierPositions;
    }


    public static Pair<Vector3d, Vector3d> getParallelDotAt(Vector3d start, Vector3d end, Vector3d ctrl1, Vector3d ctrl2, int maxStep, int currentStep) {
        float unitStep = 1F / maxStep;
        if (currentStep > maxStep) currentStep = maxStep - 1;
        else if (currentStep < 0) currentStep = 0;

        // 1. 计算曲线上的点
        Vector3d curvePoint = GSKOMathUtil.bezier3(start, end, ctrl1, ctrl2, unitStep);

        // 2. 计算切线向量（曲线方向）
        Vector3d tangent = GSKOMathUtil.bezier3Derivative(start, end, ctrl1, ctrl2, unitStep).normalize();

        // 3. 计算法线向量（垂直于曲线）
        Vector3d normal = calculateNormal(tangent);

        // 4. 计算偏移点
        Vector3d leftRail = curvePoint.add(normal.scale(1.2F / 2));
        Vector3d rightRail = curvePoint.subtract(normal.scale(1.2F / 2));

        return Pair.of(leftRail, rightRail);
    }

    /**
     * 自动计算控制点
     * @param start 起点位置
     * @param end 终点位置
     * @param startRotation 起点旋转
     * @param endRotation 终点旋转
     * @return 控制点位置
     */
    public static Vector3d defaultCtrlDot(Vector3d start, Vector3d end,
                                          Quaternion startRotation, Quaternion endRotation) {
        // 计算中点
        Vector3d midPoint = start.add(end).scale(0.5);

        // 计算起点方向
        Vector3d startDirection = GSKOMathUtil.rotateVector(startRotation, new Vector3d(0, 0, 1));
        // 计算终点方向
        Vector3d endDirection = GSKOMathUtil.rotateVector(endRotation, new Vector3d(0, 0, 1));
        // 计算平均方向
        Vector3d avgDirection = startDirection.add(endDirection).normalize();

        // 计算偏移距离
        double distance = start.distanceTo(end);
        double offset = distance * 0.5;

        // 计算控制点
        return midPoint.add(avgDirection.scale(offset));
    }

    public static Vector3d catmullRom(Vector3d start, Vector3d ctrl1, Vector3d end,  Vector3d ctrl2, float time) {
        double t2 = time * time;
        double t3 = t2 * time;

        return new Vector3d(
                0.5 * ((2 * ctrl1.x) + (-start.x + end.x) * time +
                        (2 * start.x - 5 * ctrl1.x + 4 * end.x - ctrl2.x) * t2 +
                        (-start.x + 3 * ctrl1.x - 3 * end.x + ctrl2.x) * t3),
                0.5 * ((2 * ctrl1.y) + (-start.y + end.y) * time +
                        (2 * start.y - 5 * ctrl1.y + 4 * end.y - ctrl2.y) * t2 +
                        (-start.y + 3 * ctrl1.y - 3 * end.y + ctrl2.y) * t3),
                0.5 * ((2 * ctrl1.z) + (-start.z + end.z) * time +
                        (2 * start.z - 5 * ctrl1.z + 4 * end.z - ctrl2.z) * t2 +
                        (-start.z + 3 * ctrl1.z - 3 * end.z + ctrl2.z) * t3)
        );
    }

    /**
     * 计算切线向量
     * @param t 参数 (0.0 - 1.0)
     * @return 切线向量
     */
    public static Vector3d catmullRomTangent(Vector3d start, Vector3d ctrl1, Vector3d end,  Vector3d ctrl2, double t) {
        Vector3d p0 = start;
        Vector3d p1 = ctrl1;
        Vector3d p2 = end;
        Vector3d p3 = ctrl2;

        double t2 = t * t;

        return new Vector3d(
                0.5 * ((-p0.x + p2.x) +
                        2 * (2 * p0.x - 5 * p1.x + 4 * p2.x - p3.x) * t +
                        3 * (-p0.x + 3 * p1.x - 3 * p2.x + p3.x) * t2),
                0.5 * ((-p0.y + p2.y) +
                        2 * (2 * p0.y - 5 * p1.y + 4 * p2.y - p3.y) * t +
                        3 * (-p0.y + 3 * p1.y - 3 * p2.y + p3.y) * t2),
                0.5 * ((-p0.z + p2.z) +
                        2 * (2 * p0.z - 5 * p1.z + 4 * p2.z - p3.z) * t +
                        3 * (-p0.z + 3 * p1.z - 3 * p2.z + p3.z) * t2)
        ).normalize();
    }

    public static Vector3d catmullRomNormal(Vector3d tangent) {
        Vector3d up = new Vector3d(0, 1, 0);
        Vector3d binormal = tangent.crossProduct(up).normalize();
        return binormal.crossProduct(tangent).normalize();
    }
}
