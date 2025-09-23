package github.thelawf.gensokyoontology.common.util.math;

import net.minecraft.client.Minecraft;
import org.joml.*;
import org.joml.Vector3f;

import javax.annotation.Nullable;

/**
 * Copy from <a href="https://github.com/FoundationGames/Splinecart/blob/1.21/src/main/java/io/github/foundationgames/splinecart/util/Pose.java">
 *     SplineCart: Pose.java </a>
 */
@Deprecated
public class Pose {
    public final Vector3d translation;
    public final Matrix3d basis;

    public Pose(Vector3d translation, Matrix3d basis) {
        this.translation = translation;
        this.basis = basis;
    }

    public Pose(net.minecraft.util.math.vector.Vector3d translation, Matrix3d basis) {
        this.translation = new Vector3d(translation.x, translation.y, translation.z);
        this.basis = basis;
    }

    public void interpolate2(Pose other, double t, Vector3d translation, Vector3d gradient) {
        Vector3d point0 = translation.set(this.translation);
        Vector3d point1 = new Vector3d(other.translation);
        Vector3d grad0 = new Vector3d(0, 0, 1).mul(this.basis); // 使用当前姿态的basis作为初始切线

        quadraticHermiteSpline(t, point0, grad0, point1, translation);

        if (gradient != null) {
            gradient.set(grad0).mul(t * (1 - t)); // 计算插值中的速度（梯度）
        }
    }

    public void interpolate3(Pose other, double t, Vector3d translation, Matrix3d basis, Vector3d gradient) {
        double factor = this.translation.distance(other.translation);
        interpolate3(other, t, factor, translation, basis, gradient);
    }

    public void interpolate3(Pose other, double t, double factor, Vector3d translation, Matrix3d basis, Vector3d gradient) {
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

    public Vector3d getInterpolatePos(Pose other, double t, double factor, Vector3d gradient) {
        return cubicHermiteSpline(t, factor, this.translation, new Vector3d(0, 0, 1).mul(this.basis),
                other.translation, new Vector3d(0, 0, 1).mul(other.basis), new Vector3d(), gradient);
    }

    public static Vector3d quadraticHermiteSpline(double t, Vector3dc p0, Vector3dc m0, Vector3dc p1, Vector3d pOut) {
        // 计算差值向量
        Vector3d diff = new Vector3d(p1).sub(p0);

        // 计算每个权重
        double h0 = 2 * t * t - 3 * t + 1;   // 起点权重
        double h1 = -2 * t * t + 3 * t;      // 终点权重
        double h2 = t * (t - 1);             // 起点切线权重

        // 计算最终插值结果
        return pOut.set(p0)
                .mul(h0)
                .add(new Vector3d(diff).mul(h1))
                .add(new Vector3d(m0).mul(h2));
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