package github.thelawf.gensokyoontology.api.client.xmmui;

import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.text.ITextComponent;

import java.lang.reflect.Method;

public abstract class XMMUIWidget extends Widget {

    public Method onHoverMethod;
    public XMMUIWidget(int x, int y, int width, int height, ITextComponent title, String onHover) {
        super(x, y, width, height, title);
    }

}
