package github.thelawf.gensokyoontology.common.util.math;

import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;

public class Rot2f extends Vector2f {
    public static final Rot2f ZERO = new Rot2f(0,0);
    public Rot2f(float yaw, float pitch) {
        super(yaw, pitch);
    }
    public Rot2f(Vector3d vector3d){
        super(GSKOMathUtil.toYawPitch(vector3d).x, GSKOMathUtil.toYawPitch(vector3d).y);
    }

    public static Rot2f from(Vector3d vector3d){
        return new Rot2f(vector3d);
    }
    public static Rot2f of(Vector2f vector2f){
        return new Rot2f(vector2f.x, vector2f.y);
    }

    public static Rot2f of(float yaw, float pitch){
        return new Rot2f(yaw, pitch);
    }

    public static Rot2f clip(Vector3d vector3d){
        return new Rot2f((float) vector3d.x, (float) vector3d.z);
    }
    public float yaw(){
        return this.x;
    }

    public float pitch(){
        return this.y;
    }
}
