package github.thelawf.gensokyoontology.common.util.math;

import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.common.entity.projectile.Danmaku;
import net.minecraft.util.math.vector.Vector2f;
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

    public static List<Vector3d> getBezierNormal(List<Vector3d> bezierPositions, Vector3d start, Vector3d ctrl,  Vector3d end, float time) {
        for (float i = 0; i < 1; i += time) {
            if (bezierPositions.size() > 1F / time) break;
            bezierPositions.add(GSKOMathUtil.bezier2Derivative(start, ctrl, end, i));
        }
        return bezierPositions;
    }

    public static Pair<Vector3d, Vector3d> getParallelDotAt(Vector3d start, Vector3d end, Vector3d intersection, int maxStep, int currentStep) {
        float unitStep = 1F / maxStep;
        if (currentStep > maxStep) currentStep = maxStep - 1;
        else if (currentStep < 0) currentStep = 0;

        Vector3d startPos = getBezierNormal(new ArrayList<>(), start, intersection, end, unitStep)
                .get(currentStep).rotateYaw(Danmaku.rad(90));
        Vector3d endPos = getBezierNormal(new ArrayList<>(), start, intersection, end, unitStep)
                .get(currentStep).rotateYaw(Danmaku.rad(-90));
        return new Pair<>(startPos.normalize(), endPos.normalize());
    }

    public static Pair<Vector3d, Vector3d> getParallelDotAt(Vector3d start, Vector3d end, Vector3d ctrl1, Vector3d ctrl2, int maxStep, int currentStep) {
        float unitStep = 1F / maxStep;
        if (currentStep > maxStep) currentStep = maxStep - 1;
        else if (currentStep < 0) currentStep = 0;

        Vector3d startPos = getBezierNormal(new ArrayList<>(), start, end, ctrl1, ctrl2, unitStep)
                .get(currentStep).rotateYaw(Danmaku.rad(90));
        Vector3d endPos = getBezierNormal(new ArrayList<>(), start, end, ctrl1, ctrl2, unitStep)
                .get(currentStep).rotateYaw(Danmaku.rad(90));
        return new Pair<>(startPos.normalize(), endPos.normalize());
    }

    public static Pair<Vector3d, Vector3d> getParallelDotAt(Vector3d start, Vector3d end, Vector3d ctrl1, Vector3d ctrl2, float time) {

        Vector3d startPos = GSKOMathUtil.bezier3Derivative(start, end, ctrl1, ctrl2, time)
                .rotateYaw(Danmaku.rad(90)).scale(0.5F);
        Vector3d endPos = GSKOMathUtil.bezier3Derivative(start, end, ctrl1, ctrl2, time)
                .rotateYaw(Danmaku.rad(-90)).scale(0.5F);
        return new Pair<>(startPos.normalize(), endPos.normalize());
    }

    public static Vector3d getStartCtrlDot(Vector3d startPos, Vector2f startRot, float scale) {
        return Vector3d.fromPitchYaw(startRot).scale(scale).add(startPos);
    }

    public static Vector3d getEndCtrlDot(Vector3d endPos, Vector2f endRot, float scale) {
        return Vector3d.fromPitchYaw(endRot).scale(-scale).add(endPos);
    }

}
