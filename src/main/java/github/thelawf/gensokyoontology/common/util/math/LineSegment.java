package github.thelawf.gensokyoontology.common.util.math;

import java.awt.*;

public class LineSegment {
    Point dotA;
    Point dotB;
    double length;

    double x1, y1, x2, y2;

    public LineSegment(Point dotA, Point dotB) {
        this.dotA = dotA;
        this.dotB = dotB;
        this.length = GSKOMathUtil.distanceOf2D(this.dotA.getX(),
                this.dotB.getX(), this.dotA.getY(), this.dotB.getY());
    }

    public LineSegment(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.length = GSKOMathUtil.distanceOf2D(this.x1, this.y1, this.x2, this.y2);
    }

    public double getLength() {
        return length;
    }
}
