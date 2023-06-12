package github.thelawf.gensokyoontology.common.libs.logoslib.math;

import net.minecraft.util.math.vector.Vector3d;

public class RectangularCoordinate {

    public double x;
    public double y;
    public double z;

    public RectangularCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3d toVector3D() {
        return new Vector3d(this.x, this.y, this.z);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public DirectionUtil directionOf(RectangularCoordinate center) {
        double distance = GSKOMathUtil.distanceOf2D(this.x, this.z, center.x, center.z);
        return null;
    }

    public double distanceOf(RectangularCoordinate coordinate) {
        return GSKOMathUtil.distanceOf3D(this.x, this.y, this.z, coordinate.x, coordinate.y, coordinate.z);
    }

    public enum DirectionUtil {
        E(0f),
        S(0f),
        W(0f),
        N(0f),
        ES(0f),
        EN(0f),
        SE(0f),
        NE(0f),
        WS(0f),
        WN(0f),
        SW(0f),
        NW(0f);

        public float degAngle;

        DirectionUtil(float degAngle) {
            this.degAngle = degAngle;
        }

        public void setDegAngle(float degAngleIn) {
            this.degAngle = degAngleIn;
        }

        public DirectionUtil setAngle(float angleIn) {
            this.degAngle = angleIn;
            return this;
        }
    }
}
