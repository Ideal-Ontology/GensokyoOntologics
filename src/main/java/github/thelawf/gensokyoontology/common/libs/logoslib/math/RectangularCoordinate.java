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
}
