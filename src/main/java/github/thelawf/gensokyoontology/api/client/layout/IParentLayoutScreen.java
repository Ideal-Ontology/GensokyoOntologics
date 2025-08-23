package github.thelawf.gensokyoontology.api.client.layout;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.text.ITextComponent;

public interface IParentLayoutScreen {

    default void setCenteredString(MatrixStack matrixStack, FontRenderer fontRenderer,
                                  ITextComponent text, int screenWidth, int screenHeight, int color) {
        int stringWidth = fontRenderer.getStringWidth(text.getString());
        int stringHeight = fontRenderer.FONT_HEIGHT;
        int stringX = (screenWidth - fontRenderer.getStringWidth(text.getString())) / 2;
        int stringY = (screenHeight - fontRenderer.FONT_HEIGHT) / 2;
        fontRenderer.drawTextWithShadow(matrixStack, text, stringX, stringY, color);
    }

    default int getCenteredStringX(FontRenderer fontRenderer, ITextComponent text, int screenWidth) {
        fontRenderer.getStringWidth(text.getString());
        return (screenWidth - fontRenderer.getStringWidth(text.getString())) / 2;
    }

    default int getCenteredStringY(FontRenderer fontRenderer, int screenHeight) {
        return (screenHeight - fontRenderer.FONT_HEIGHT) / 2;
    }
}
