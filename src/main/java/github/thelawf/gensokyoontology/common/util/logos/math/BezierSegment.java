package github.thelawf.gensokyoontology.common.util.logos.math;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class BezierSegment {

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

    // public void drawBezier(Graphics g, Point2D[] p)
    // {
    //     for(double t = 0; t < 1; t+=0.002)
    //     {
    //         Point2D p1= cubicBezier(t,p);
    //         Point2D p2 = cubicBezier(t+0.001,p);
    //         g.drawLine((int)Math.round(p1.getX()),(int)Math.round(p1.getY()),(int)Math.round(p2.getX()),(int)Math.round(p2.getY()));
    //     }
    //}
}
