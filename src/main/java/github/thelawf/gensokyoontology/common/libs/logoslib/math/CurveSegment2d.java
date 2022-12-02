package github.thelawf.gensokyoontology.common.libs.logoslib.math;

import java.util.ArrayList;

public class CurveSegment2d {

    // 设置坐标轴x, y上的基矢量长度及其比值
    double iBasis;
    double jBasis;
    double ratioXY;

    // 设置x-y坐标轴的位置，只能为本地坐标
    RectangularCoordinate axisX;
    RectangularCoordinate axisY;

    // 设置曲线的起止点
    RectangularCoordinate start;
    RectangularCoordinate end;

    // 设置曲线的宽高
    LineSegment lineWidth2d;
    LineSegment lineHeight2d;
    // 设置切线交点的连线
    LineSegment lineBetween;

    // 设置一个列表存放用于帮助计算的辅助线
    ArrayList<LineSegment> auxiliaryLines = new ArrayList<>();
    // 设置一个列表，用于制造一条在两个坐标轴之间的切线，以此来达到平滑曲线的目的
    ArrayList<LineSegment> tangentLines = new ArrayList<>();
    // 设置一个列表存放切线与切线的交点，以此来达到平滑曲线的目的
    ArrayList<RectangularCoordinate> intersectPoints = new ArrayList<>();

    public CurveSegment2d(double iBasis, double ratioXY, RectangularCoordinate axisX,
                          RectangularCoordinate axisY, RectangularCoordinate start,
                          RectangularCoordinate end) {
        this.iBasis = iBasis;
        this.ratioXY = ratioXY;
        this.jBasis = this.iBasis * this.ratioXY;
        this.axisX = axisX;
        this.axisY = axisY;
        this.start = start;
        this.end = end;
    }

    public void addTangentLines() {
        int lineNum = 0;
        for (int i = 0; i < this.axisY.getY() / this.jBasis; i++) {
            LineSegment tanLine = new LineSegment(this.axisX.getX(), this.axisX.getY(),
                    this.axisY.getX() / this.jBasis * i, this.axisY.getY() / this.jBasis * i);
            this.tangentLines.add(tanLine);
        }
    }

    public void addTangentLine(LineSegment tangentLineIn) {
        this.tangentLines.add(tangentLineIn);
    }

    public void getAllIntersectPoints() {
        for (int i = 0; i < this.tangentLines.size(); i++) {
            if (this.tangentLines.size() % 2 == 0) {
                this.intersectPoints.add(MathCalculator.getIntersectPoint2D(
                        this.tangentLines.get(i), this.tangentLines.get(i+1)));
                i++;
            }
            else {
                break;
            }
        }
    }

    public void getVertical() {}


    public double getiBasis() {
        return iBasis;
    }

    public void setiBasis(double iBasis) {
        this.iBasis = iBasis;
    }

    public double getjBasis() {
        return jBasis;
    }

    public void setjBasis(double jBasis) {
        this.jBasis = jBasis;
    }

    public double getRatioXY() {
        return ratioXY;
    }

    public void setRatioXY(double ratioXY) {
        this.ratioXY = ratioXY;
    }

    public RectangularCoordinate getAxisX() {
        return axisX;
    }

    public void setAxisX(RectangularCoordinate axisX) {
        this.axisX = axisX;
    }

    public RectangularCoordinate getAxisY() {
        return axisY;
    }

    public void setAxisY(RectangularCoordinate axisY) {
        this.axisY = axisY;
    }

    public RectangularCoordinate getStart() {
        return start;
    }

    public void setStart(RectangularCoordinate start) {
        this.start = start;
    }

    public RectangularCoordinate getEnd() {
        return end;
    }

    public void setEnd(RectangularCoordinate end) {
        this.end = end;
    }

    public LineSegment getLineWidth2d() {
        return lineWidth2d;
    }

    public void setLineWidth2d(LineSegment lineWidth2d) {
        this.lineWidth2d = lineWidth2d;
    }

    public LineSegment getLineHeight2d() {
        return lineHeight2d;
    }

    public void setLineHeight2d(LineSegment lineHeight2d) {
        this.lineHeight2d = lineHeight2d;
    }

    public LineSegment getLineBetween() {
        return lineBetween;
    }

    public void setLineBetween(LineSegment lineBetween) {
        this.lineBetween = lineBetween;
    }

    public ArrayList<LineSegment> getAuxiliaryLines() {
        return auxiliaryLines;
    }

    public void setAuxiliaryLines(ArrayList<LineSegment> auxiliaryLines) {
        this.auxiliaryLines = auxiliaryLines;
    }

    public ArrayList<LineSegment> getTangentLines() {
        return tangentLines;
    }

    public void setTangentLines(ArrayList<LineSegment> tangentLines) {
        this.tangentLines = tangentLines;
    }

    public ArrayList<RectangularCoordinate> getIntersectPoints() {
        return intersectPoints;
    }

    public void setIntersectPoints(ArrayList<RectangularCoordinate> intersectPoints) {
        this.intersectPoints = intersectPoints;
    }
}
