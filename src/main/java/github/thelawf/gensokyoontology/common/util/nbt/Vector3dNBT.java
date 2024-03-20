package github.thelawf.gensokyoontology.common.util.nbt;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.math.vector.Vector3d;
import org.jetbrains.annotations.NotNull;

public class Vector3dNBT extends CompoundNBT{
    public static final String TYPE = "vector3d";
    private final StringNBT name;
    private final CompoundNBT value = new CompoundNBT();
    public final double x;
    public final double y;
    public final double z;

    public Vector3dNBT(String name, Vector3d vector3d) {
        this.name = StringNBT.valueOf(name);
        this.x = vector3d.x;
        this.y = vector3d.y;
        this.z = vector3d.z;
        this.value.putDouble("x", this.x);
        this.value.putDouble("y", this.y);
        this.value.putDouble("z", this.z);
    }

    public String getName() {
        return this.name.getString();
    }

    public CompoundNBT getValue() {
        return this.value;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public Vector3d toVector3d() {
        return new Vector3d(this.x, this.y, this.z);
    }
}
