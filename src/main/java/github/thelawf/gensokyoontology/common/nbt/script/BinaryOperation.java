package github.thelawf.gensokyoontology.common.nbt.script;

import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.util.text.ITextComponent;

public enum BinaryOperation {
    NONE("None"),
    AND("And"),
    OR("Or"),
    NOT("Not"),
    PLUS("Plus"),
    SUBTRACT("Subtract"),
    MULTIPLE("Multiple"),
    DIVIDE("Divide"),
    MODULUS("Modulus"),
    BITWISE_AND("BitwiseAnd"),
    BITWISE_OR("BitwiseOr"),
    BITWISE_NOT("BitwiseNot"),
    BITWISE_XOR("BitwiseXOR"),
    BITWISE_LEFT("BitwiseLeft"),
    BITWISE_RIGHT("BitwiseRight");
    public final String key;

    BinaryOperation(String key) {
        this.key = key;
    }
    public ITextComponent toTextComponent() {
        return GSKOUtil.translateText("gui.",".binary_operation.button.operation." + getKey());
    }

    private String getKey() {
        return this.key;
    }
}
