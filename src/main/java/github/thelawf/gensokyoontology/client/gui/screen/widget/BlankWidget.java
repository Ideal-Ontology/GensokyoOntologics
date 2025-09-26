package github.thelawf.gensokyoontology.client.gui.screen.widget;

import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.text.ITextComponent;

public class BlankWidget extends Widget {
    public static final BlankWidget INSTANCE = new BlankWidget(0,0,0,0, GSKOUtil.translateText("gui.", "default.title"));
    public BlankWidget(int x, int y, int width, int height, ITextComponent title) {
        super(x, y, width, height, title);
    }
}
