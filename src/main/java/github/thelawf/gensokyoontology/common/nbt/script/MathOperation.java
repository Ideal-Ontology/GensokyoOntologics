package github.thelawf.gensokyoontology.common.nbt.script;

import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.util.text.ITextComponent;

public enum MathOperation {
    PLUS("Plus"),
    SUBTRACT("Subtract"),
    MULTIPLE("Multiple"),
    DIVIDE("Divide"),
    MODULUS("Modulus");
    public final String key;

    MathOperation(String key) {
        this.key = key;
    }
    public ITextComponent toTextComponent() {
        return GSKOUtil.translateText("gui.",".operation_builder.button.math." + getKey());
    }

    private String getKey() {
        return this.key;
    }
}
