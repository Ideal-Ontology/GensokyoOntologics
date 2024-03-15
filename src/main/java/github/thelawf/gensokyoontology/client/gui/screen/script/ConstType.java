package github.thelawf.gensokyoontology.client.gui.screen.script;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.nbt.IntNBT;
import net.minecraft.nbt.NumberNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.print.DocFlavor;
import java.util.concurrent.atomic.AtomicReference;

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
        return GensokyoOntology.withTranslation("gui.",".const_builder.button.constType." + getKey());
    }
}
