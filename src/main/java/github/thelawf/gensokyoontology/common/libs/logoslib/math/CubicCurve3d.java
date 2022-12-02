package github.thelawf.gensokyoontology.common.libs.logoslib.math;


import java.util.ArrayList;

public class CubicCurve3d extends CurveSegment2d {
    double kBasis;
    double ratioXZ;

    RectangularCoordinate axisZ;

    LineSegment3D lineLength3d;
    LineSegment3D lineWidth3d;
    LineSegment3D lineHeight3d;

    // 设置一个列表存放用于帮助计算的辅助线
    ArrayList<LineSegment3D> auxiliaryLines = new ArrayList<>();
    // 设置一个列表，用于制造一条在两个坐标轴之间的切线，以此来达到平滑曲线的目的
    ArrayList<LineSegment3D> tangentLines = new ArrayList<>();

    public CubicCurve3d(double iBasis, double ratioXY, RectangularCoordinate axisX, RectangularCoordinate axisY, RectangularCoordinate start, RectangularCoordinate end) {
        super(iBasis, ratioXY, axisX, axisY, start, end);
    }

    public CubicCurve3d(double iBasis, double ratioXY, double ratioXZ,
                        RectangularCoordinate axisX, RectangularCoordinate axisY, RectangularCoordinate axisZ,
                        RectangularCoordinate start, RectangularCoordinate end) {
        super(iBasis, ratioXY, axisX, axisY, start, end);
        this.ratioXZ = ratioXZ;
        this.axisZ = axisZ;

    }

    @Override
    public void getAllIntersectPoints() {

    }
}
