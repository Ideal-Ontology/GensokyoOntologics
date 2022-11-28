package github.thelawf.gensokyoontology.common.util;

import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public enum AxisRotations implements IStringSerializable {

    NONE("none",0F),
    ROLL_22_5("roll_22_5", 22.5F),
    ROLL_45("roll_45",45F),
    ROLL_67_5("roll_67_5", 67.5F),
    ROLL_90("roll_90",90F),
    ROLL_112_5("roll_112_5", 112.5F),
    ROLL_135("roll_135",135F),
    ROLL_157_5("roll_157_5", 157.5F),

    YAW_22_5("ywa_22_5", 22.5F),
    YAW_45("yaw_45", 45F),

    PITCH_22_5("pitch_22_5", 22.5F),
    PITCH_45("pitch_45", 45F),
    PITCH_67_5("pitch_45", 67.5F),
    PITCH_112_5("pitch_45", 112.5F),
    PITCH_135("pitch_45", 135F),
    PITCH_157_5("pitch_45", 157.5F);

    public final String registryName;
    public final float rotation;

    AxisRotations(String registryName, float rotation){
        this.registryName = registryName;
        this.rotation = rotation;
    }

    @Override
    @NotNull
    public String getString() {
        return registryName;
    }

    @Override
    public String toString() {
        return getString();
    }

    public enum AlignedPlane {
        SIDED_E("east_side", true,Direction.EAST),
        SIDED_W("west_side", true,Direction.WEST),
        SIDED_S("south_side", true,Direction.SOUTH),
        SIDED_N("north_side", true,Direction.NORTH),
        TOP("top_side", true, null),
        BOTTOM("bottom_side", true, null);

        public final String registryName;
        public final boolean isAligned;
        AlignedPlane(String registryName, boolean isAligned, @Nullable Direction sidedPlane){
            this.registryName = registryName;
            this.isAligned = isAligned;
        }
    }
}
