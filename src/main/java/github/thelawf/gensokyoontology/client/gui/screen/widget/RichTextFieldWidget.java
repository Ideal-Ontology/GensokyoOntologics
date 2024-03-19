package github.thelawf.gensokyoontology.client.gui.screen.widget;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class RichTextFieldWidget extends TextFieldWidget {
    private final ResourceLocation TEXT_FILED_TEXTURE;
    public RichTextFieldWidget(FontRenderer fontRenderer, int x, int y, int width, int height, ITextComponent title, ResourceLocation texture, int u, int v) {
        super(fontRenderer, x, y, width, height, title);
        this.TEXT_FILED_TEXTURE = texture;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
