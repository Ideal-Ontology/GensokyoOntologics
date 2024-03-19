package github.thelawf.gensokyoontology.client.gui.screen.widget;

import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class PanelWidget extends Widget {
    private final ResourceLocation PANEL_TEXTURE;
    private int u;
    private int v;
    public PanelWidget(int x, int y, int width, int height, ITextComponent title, ResourceLocation texture, int u, int v) {
        super(x, y, width, height, title);
        this.PANEL_TEXTURE = texture;
        this.u = u;
        this.v = v;
    }
}
