package github.thelawf.gensokyoontology.common.nbt.script;

import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.util.text.ITextComponent;

public enum ConstType {
    INT("int"),
    LONG("long"),
    FLOAT("float"),
    DOUBLE("double"),
    BOOLEAN("boolean"),
    STRING("string");
    public final String key;

    ConstType(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    public ITextComponent toTextComponent() {
        return GSKOUtil.translate("gui.",".const_builder.button.constType." + getKey());
    }
}
