package github.thelawf.gensokyoontology.common.util.math;

import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class BezierUtil {

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

}
