package github.thelawf.gensokyoontology.common.nbt.script;

import com.google.common.collect.Lists;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public enum V3dFunc {
    ADD("add", Lists.newArrayList(InstanceType.VECTOR3D.name), InstanceType.VECTOR3D.name),
    SUBTRACT("subtract", Lists.newArrayList(InstanceType.VECTOR3D.name), InstanceType.VECTOR3D.name),
    SCALE("scale", Lists.newArrayList(InstanceType.VECTOR3D.name), InstanceType.VECTOR3D.name),
    DOT_PRODUCT("dot_product", Lists.newArrayList(InstanceType.VECTOR3D.name), InstanceType.VECTOR3D.name),
    CROSS_PRODUCT("cross_product", Lists.newArrayList(InstanceType.VECTOR3D.name), InstanceType.VECTOR3D.name),
    ROTATE_YAW("rotate_yaw", Lists.newArrayList(ConstType.FLOAT.key), InstanceType.VECTOR3D.name),
    ROTATE_PITCH("rotate_pitch", Lists.newArrayList(ConstType.FLOAT.key), InstanceType.VECTOR3D.name),
    INVERSE("inverse", Lists.newArrayList(), InstanceType.VECTOR3D.name),
    LENGTH("length", Lists.newArrayList(), InstanceType.VECTOR3D.name),
    NORMALIZE("normalize", Lists.newArrayList(), InstanceType.VECTOR3D.name);

    public final int paramCount;
    public final String methodName;
    public final String returnType;
    public final List<String> paramTypes;

    V3dFunc(String methodName, List<String> paramTypes, String returnType) {
        this.methodName = methodName;
        this.returnType = returnType;
        this.paramTypes = paramTypes;
        this.paramCount = paramTypes.size();
    }

    public ITextComponent toTextComponent() {
        return GSKOUtil.translate("gui.",".func_invoker.vector3d." + this.methodName);
    }
}
