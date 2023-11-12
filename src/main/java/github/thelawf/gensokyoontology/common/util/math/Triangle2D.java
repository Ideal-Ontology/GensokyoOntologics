package github.thelawf.gensokyoontology.common.util.math;


import java.awt.*;

public class Triangle2D {
    Point vertexA;
    Point vertexB;
    Point vertexC;

    LineSegment sideA;
    LineSegment sideB;
    LineSegment sideC;

    double sideLength;
    double area;

    double angleAlpha;
    double angelBeta;
    double angelGamma;

    public Triangle2D(Point vertexA, Point vertexB, Point vertexC) {
        this.vertexA = vertexA;
        this.vertexB = vertexB;
        this.vertexC = vertexC;

        this.sideA = new LineSegment(vertexB, vertexC);
        this.sideB = new LineSegment(vertexA, vertexC);
        this.sideC = new LineSegment(vertexA, vertexB);

        this.sideLength = this.sideA.length + this.sideB.length + this.sideC.length;
    }

    public Triangle2D(LineSegment sideA, LineSegment sideB, LineSegment sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    public Triangle2D(double angleAlpha, double angelBeta, double angelGamma) {
        this.angleAlpha = angleAlpha;
        this.angelBeta = angelBeta;
        this.angelGamma = angelGamma;
    }

    public Triangle2D(double angleAIn, double angleBIn, LineSegment anySideIn) {

    }

    public Triangle2D(LineSegment sideAIn, double includeAngleIn, LineSegment sideBIn) throws TriangleNotUniqueException {

    }

    public double getAngleAlpha() {
        return angleAlpha;
    }

    public double getAngelBeta() {
        return angelBeta;
    }

    public double getAngelGamma() {
        return angelGamma;
    }

    public Point getVertexA() {
        return vertexA;
    }

    public Point getVertexB() {
        return vertexB;
    }

    public Point getVertexC() {
        return vertexC;
    }

    public LineSegment getSideA() {
        return sideA;
    }

    public LineSegment getSideB() {
        return sideB;
    }

    public LineSegment getSideC() {
        return sideC;
    }

    public double getArea() {
        return area;
    }

    public double getSideLength() {
        return sideLength;
    }
}
