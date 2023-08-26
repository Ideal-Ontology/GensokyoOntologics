package github.thelawf.gensokyoontology.client.gui.screen;


import net.minecraft.client.gui.IGuiEventListener;
import net.minecraft.item.WritableBookItem;
import net.minecraft.util.text.*;

public class GSKOTextComponent extends StringTextComponent implements IGuiEventListener {

    public static final ITextComponent EMPTY = new StringTextComponent("");
    private final String text;

    public GSKOTextComponent(String msg) {
        super(msg);
        this.text = msg;
    }


    @Override
    public IFormattableTextComponent mergeStyle(TextFormatting... formats) {
        return super.mergeStyle(formats);
    }

    @Override
    public IFormattableTextComponent mergeStyle(Style style) {
        return super.mergeStyle(style);
    }
}
