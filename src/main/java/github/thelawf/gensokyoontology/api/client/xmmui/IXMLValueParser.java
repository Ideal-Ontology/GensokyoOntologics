package github.thelawf.gensokyoontology.api.client.xmmui;

import java.util.Arrays;
import java.util.List;

public interface IXMLValueParser {
    /**
     * @param value 字符串，为XML属性的值
     * @return 将原始XML属性值字符串按照分号分割之后的列表
     */
    default List<String> separateBySemicolon(String value) {
        return Arrays.asList(value.split(";"));
    }

    default List<String> separateByComma(String value) {
        return Arrays.asList(value.split(","));
    }

    default boolean getBoolValue(String value) {
        return Boolean.parseBoolean(value.split("=")[1]);
    }

    default <T extends Enum<T>> T getEnumValue(String value, Class<T> enumClass) {
        return Enum.valueOf(enumClass, value.split("=")[1]);
    }

    default int getIntValue(String value) {
        return Integer.parseInt(value);
    }
}
