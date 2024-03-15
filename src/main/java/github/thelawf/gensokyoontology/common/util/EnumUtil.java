package github.thelawf.gensokyoontology.common.util;

public class EnumUtil {
    public static <T extends Enum<T>> T switchEnum(Class<T> enumClass, T value) {
        T[] values = enumClass.getEnumConstants();
        int nextIndex = (value.ordinal() + 1) % values.length;
        return values[nextIndex];
    }

    public static <T extends Enum<T>> T moveTo(Class<T> enumClass, T value, int index) {
        T[] values = enumClass.getEnumConstants();
        int currentIndex = value.ordinal();

        // 根据参数规定移动至values[]的对应位置
        int targetIndex = (currentIndex + index) % values.length;
        if (targetIndex < 0) {
            targetIndex += values.length; // 如果超出索引则从尾部继续
        }
        return values[targetIndex];
    }
}
