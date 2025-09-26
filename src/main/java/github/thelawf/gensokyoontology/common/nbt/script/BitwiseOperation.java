package github.thelawf.gensokyoontology.common.nbt.script;

import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.util.text.ITextComponent;

public enum BitwiseOperation {
    BITWISE_AND("BitwiseAnd"),
    BITWISE_OR("BitwiseOr"),
    BITWISE_NOT("BitwiseNot"),
    BITWISE_XOR("BitwiseXOR"),
    BITWISE_LEFT("BitwiseLeft"),
    BITWISE_RIGHT("BitwiseRight");


    public final String key;

    BitwiseOperation(String key) {
        this.key = key;
    }
    public ITextComponent toTextComponent() {
        return GSKOUtil.translateText("gui.",".operation_builder.button.math." + getKey());
    }

    private String getKey() {
        return this.key;
    }
}
