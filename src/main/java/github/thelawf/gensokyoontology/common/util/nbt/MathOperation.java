package github.thelawf.gensokyoontology.common.util.nbt;

import github.thelawf.gensokyoontology.GensokyoOntology;
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
        return GensokyoOntology.withTranslation("gui.",".operation_builder.button.math." + getKey());
    }

    private String getKey() {
        return this.key;
    }
}
