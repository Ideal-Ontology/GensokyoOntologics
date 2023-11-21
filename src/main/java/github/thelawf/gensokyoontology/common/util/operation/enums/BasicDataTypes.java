package github.thelawf.gensokyoontology.common.util.operation.enums;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.util.text.ITextComponent;

public enum BasicDataTypes {
    INT(with("int")),
    SHORT(with("short")),
    LONG(with("long")),
    FLOAT(with("float")),
    DOUBLE(with("double")),
    STRING(with("string"));
    public final ITextComponent text;

    private static ITextComponent with(String id) {
        return GensokyoOntology.withTranslation("sc_data_types.", ".basic_data_type." + id);
    }

    BasicDataTypes(ITextComponent text) {
        this.text = text;
    }
}
