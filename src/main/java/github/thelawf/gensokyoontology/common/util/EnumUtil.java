package github.thelawf.gensokyoontology.common.util;

import github.thelawf.gensokyoontology.client.gui.screen.script.ConstType;

public class EnumUtil {
    public static <T extends Enum<T>> T switchEnum(Class<T> enumClass, T value) {
        T[] values = enumClass.getEnumConstants();
        int nextIndex = (value.ordinal() + 1) % values.length;
        return values[nextIndex];
    }
}
