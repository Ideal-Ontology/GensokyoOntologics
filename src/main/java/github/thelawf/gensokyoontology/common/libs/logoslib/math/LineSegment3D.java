package github.thelawf.gensokyoontology.common.libs.logoslib.math;

public class LineSegment3D extends LineSegment{
    double z1, z2 = 0d;
    public LineSegment3D(double x1, double y1, double x2, double y2) {
        super(x1, y1, x2, y2);
    }

    public LineSegment3D(double x1, double y1, double z1, double x2, double y2, double z2) {
        super(x1, y1, x2, y2);
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.z1 = z1;
        this.z2 = z2;
        this.length = GSKOMathUtil.distanceOf3D(this.x1, this.y1, this.z1,
                this.x2, this.y2, this.z2);
    }
}
