package github.thelawf.gensokyoontology.common.util.nbt;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.util.text.ITextComponent;

public enum LogicOperation {
    AND("And"),
    OR("Or"),
    NOT("Not");
    public final String key;

    LogicOperation(String key) {
        this.key = key;
    }
    public ITextComponent toTextComponent() {
        return GensokyoOntology.withTranslation("gui.",".operation_builder.button.math." + getKey());
    }

    private String getKey() {
        return this.key;
    }
}
