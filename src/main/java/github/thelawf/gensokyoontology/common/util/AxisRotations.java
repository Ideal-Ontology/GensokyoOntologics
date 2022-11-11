package github.thelawf.gensokyoontology.common.util;

import net.minecraft.util.IStringSerializable;

public enum AxisRotations implements IStringSerializable {

    PITCH_22_5("pitch_22_5", 22.5F),
    PITCH_45("pitch_45", 45),
    ROLL_22_5("roll_22_5", 22.5F);

    private AxisRotations(String registryName, float rotation){

    }

    @Override
    public String getString() {
        return null;
    }
}
