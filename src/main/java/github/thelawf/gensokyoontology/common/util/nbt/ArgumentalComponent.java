package github.thelawf.gensokyoontology.common.util.nbt;

import github.thelawf.gensokyoontology.api.client.IInputParser;
import net.minecraft.nbt.*;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.TextComponent;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ArgumentalComponent extends TextComponent implements IFormattableTextComponent, IInputParser {
    private final HashMap<String, INBT> arguments = new HashMap<>();
    public static final HashMap<Class<? extends INBT>, Function<? extends INBT, String>> SERIALIZE_MAPPING = serializeMapping();
    public static final HashMap<String, BiFunction<String, HashMap<String, INBT>, ? extends INBT>> DESERIALIZE_MAPPING = deserializeMapping();
    public static final String ARG_REGEX = "(\\$\\{.*?})";
    public static final String FLOAT_REGEX = "\\d*?.\\d*?";
    public static final String INT_REGEX = "\\d*?";
    public static final String STR_REGEX = "\\w*";
    public static final String BOOL_REGEX = "true|false";

    private static HashMap<Class<? extends INBT>, Function<? extends INBT, String>> serializeMapping() {
        HashMap<Class<? extends INBT>, Function<? extends INBT, String>> mapping = new HashMap<>();
        mapping.put(INBT.class, INBT::toString);
        return mapping;
    }

    private static HashMap<String, BiFunction<String, HashMap<String, INBT>, ? extends INBT>> deserializeMapping() {
        HashMap<String, BiFunction<String, HashMap<String, INBT>, ? extends INBT>> mapping = new HashMap<>();
        mapping.put(BOOL_REGEX, (s, map) -> map.put(s, ByteNBT.valueOf(s.equals("true"))));
        mapping.put(FLOAT_REGEX, (s, map) -> map.put(s, FloatNBT.valueOf(Float.parseFloat(s))));
        mapping.put(INT_REGEX, (s, map) -> map.put(s, IntNBT.valueOf(Integer.parseInt(s))));
        mapping.put(STR_REGEX, (s, map) -> map.put(s, StringNBT.valueOf(s)));
        return mapping;
    }

    public ArgumentalComponent() {
    }

    public ArgumentalComponent(String formatStr) {
        String[] matchList = formatStr.split(ARG_REGEX);
        List<String> list = Arrays.stream(matchList).collect(Collectors.toList());
        list.forEach(s -> DESERIALIZE_MAPPING.forEach((str, func) -> { if (s.matches(str)) func.apply(s, this.arguments); }));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String,? extends INBT> entry : arguments.entrySet()) {
            INBT inbt = entry.getValue();
            sb.append(inbt.getString());
        }
        return sb.toString();
    }

    public <T extends INBT> ArgumentalComponent argument(String key, T nbt) {
        this.arguments.put(key, nbt);
        return this;
    }

    @Override
    @NotNull
    public TextComponent copyRaw() {
        ArgumentalComponent ac = new ArgumentalComponent();
        ac.arguments.putAll(this.arguments);
        return ac;
    }
}
