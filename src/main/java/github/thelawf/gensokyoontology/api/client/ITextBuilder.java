package github.thelawf.gensokyoontology.api.client;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public interface ITextBuilder {
    default ITextComponent ofTranslation(String prefix, String suffix){
        return new TranslationTextComponent(prefix + "." + "." + suffix);
    }

    default ITextComponent ofText(String text){
        return new StringTextComponent(text);
    }
}
