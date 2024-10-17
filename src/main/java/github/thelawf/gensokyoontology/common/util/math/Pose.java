package github.thelawf.gensokyoontology.common.util.math;

import org.joml.*;

import javax.annotation.Nullable;

/**
 * Copy from <a href="https://github.com/FoundationGames/Splinecart/blob/1.21/src/main/java/io/github/foundationgames/splinecart/util/Pose.java">
 *     SplineCart: Pose.java </a>
 */

public class Pose {
    public final Vector3dc translation;
    public final Matrix3dc basis;

    public Pose(Vector3d translation, Matrix3d basis) {
        this.translation = translation;
        this.basis = basis;
    }

    public void interpolate(Pose other, double t, Vector3d translation, Matrix3d basis, Vector3d gradient) {
        double factor = this.translation.distance(other.translation);
        interpolate(other, t, factor, translation, basis, gradient);
    }

    public void interpolate(Pose other, double t, double factor, Vector3d translation, Matrix3d basis, Vector3d gradient) {
        Vector3dc point0 = translation.set(this.translation);
        Vector3dc point1 = new Vector3d(other.translation);
        Vector3dc grad0 = new Vector3d(0, 0, 1).mul(this.basis);
        Vector3dc grad1 = new Vector3d(0, 0, 1).mul(other.basis);

        cubicHermiteSpline(t, factor, point0, grad0, point1, grad1, translation, gradient);
        Vector3dc ngrad = gradient.normalize(new Vector3d());
        Quaterniond rot0 = this.basis.getNormalizedRotation(new Quaterniond());
        Quaterniond rot1 = other.basis.getNormalizedRotation(new Quaterniond());
        Quaterniond rotT = rot0.nlerp(rot1, t, new Quaterniond());
        basis.set(rotT);

        Vector3dc basisGrad = new Vector3d(0, 0, 1).mul(basis);
        Vector3d axis = ngrad.cross(basisGrad, new Vector3d());

        if (axis.length() > 0) {
            axis.normalize();
            double angleToNewBasis = basisGrad.angleSigned(ngrad, axis);
            if (angleToNewBasis != 0) {
                new Matrix3d().identity().rotate(angleToNewBasis, axis)
                        .mul(basis, basis).normal();
            }
        }
    }

    public static Vector3d cubicHermiteSpline(double t, double factor, Vector3dc p0, Vector3dc m0, Vector3dc p1, Vector3dc m1,
                                              Vector3d pOut, @Nullable Vector3d mOut) {
        Vector3d temp = new Vector3d();
        Vector3d diff = new Vector3d(p1).sub(p0);

        if (mOut != null) {
            mOut.set(temp.set(diff).mul(6*t - 6*t*t))
                    .add(temp.set(m0).mul(3*t*t - 4*t + 1).mul(factor))
                    .add(temp.set(m1).mul(3*t*t - 2*t).mul(factor));
        }

        return pOut.set(p0)
                .add(temp.set(m0).mul(t*t*t - 2*t*t + t).mul(factor))
                .add(temp.set(diff).mul(-2*t*t*t + 3*t*t))
                .add(temp.set(m1).mul(t*t*t - t*t).mul(factor));
    }
}