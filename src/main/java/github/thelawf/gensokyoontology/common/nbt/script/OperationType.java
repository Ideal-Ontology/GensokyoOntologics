package github.thelawf.gensokyoontology.common.nbt.script;

import github.thelawf.gensokyoontology.GensokyoOntology;
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
        return GensokyoOntology.fromLocaleKey("gui.",".operation_builder.button.type." + getKey());
    }

    private String getKey() {
        return this.key;
    }
}
