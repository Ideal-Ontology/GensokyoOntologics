package github.thelawf.gensokyoontology.common.libs.logoslib;

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
}
