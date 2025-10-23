package github.thelawf.gensokyoontology.common.util.math;

import net.minecraft.util.math.vector.Vector3d;
import org.jetbrains.annotations.NotNull;

public class DerivativeInfo {
    /** 零阶导，即原函数，这里用于获取过山车在轨道上的具体位置 */
    public final Vector3d position;

    /** 一阶导，即原函数在某一点的切线，这里用于获取过山车行驶至此应有的速度 */
    public final Vector3d tangent;

    /** 二阶导，即原函数的曲率/斜率，这里用于求过山车行驶到此处时应有的加速度 */
    public final Vector3d curvature;

    public static final DerivativeInfo ZERO = new DerivativeInfo(Vector3d.ZERO, Vector3d.ZERO, Vector3d.ZERO);

    public DerivativeInfo(@NotNull Vector3d position, @NotNull Vector3d tangent, Vector3d curvature) {
        this.position = position;
        this.tangent = tangent;
        this.curvature = curvature;
    }

    @Override
    public String toString() {
        return "{ pos = " + position + ", tangent = " + tangent + ", curvature = " + curvature + " }";
    }
}
