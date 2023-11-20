package github.thelawf.gensokyoontology.common.util.operation;

import net.minecraft.util.math.vector.Vector3d;

public class RotateVec3dOperation extends Operation<Vector3d> {
    public final Operation<String> eulerAngle;
    public final Operation<Integer> rotation;
    public final ReferenceOperation refId;
    public final String varId;

    public RotateVec3dOperation(Operation<String> eulerAngle, Operation<Integer> rotation, ReferenceOperation refId, String varId) {
        this.eulerAngle = eulerAngle;
        this.rotation = rotation;
        this.refId = refId;
        this.varId = varId;
    }
}
