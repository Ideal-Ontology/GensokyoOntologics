package github.thelawf.gensokyoontology.common.nbt.script;

import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.util.text.ITextComponent;

public enum OperationType {
    BITWISE("bitwise"),
    LOGIC("logic"),
    MATH("math");
    public final String key;

    OperationType(String key) {
        this.key = key;
    }
    public ITextComponent toTextComponent() {
        return GSKOUtil.translate("gui.",".operation_builder.button.type." + getKey());
    }

    private String getKey() {
        return this.key;
    }
}
