package github.thelawf.gensokyoontology.client.gui.screen;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.text.ITextComponent;

public class BlankWidget extends Widget {
    public static final BlankWidget INSTANCE = new BlankWidget(0,0,0,0, GensokyoOntology.withTranslation("gui.", "default.title"));
    public BlankWidget(int x, int y, int width, int height, ITextComponent title) {
        super(x, y, width, height, title);
    }
}
