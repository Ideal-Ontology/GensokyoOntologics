package github.thelawf.gensokyoontology.common.libs.logoslib.math;

import java.awt.*;
import java.util.Vector;

public class LineSegment {
    Point dotA;
    Point dotB;
    double length;

    public LineSegment(Point dotA, Point dotB) {
        this.dotA = dotA;
        this.dotB = dotB;
        this.length = MathCalculator.distanceBetweenPoints(this.dotA.getX(),
                this.dotB.getX(), this.dotA.getY(), this.dotB.getY());
    }

    public double getLength() {
        return length;
    }
}
