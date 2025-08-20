package github.thelawf.gensokyoontology.common.util.math;

import net.minecraft.util.math.vector.Vector3f;

public class V3f {
    public static Vector3f one() {
        return new Vector3f(1, 1, 1);
    }
    public static Vector3f zero() {
        return new Vector3f(0, 0, 0);
    }

    public static Vector3f of(float x, float y, float z) {
        return new Vector3f(x, y, z);
    }
}
