package github.thelawf.gensokyoontology.client.gui.screen.widget;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class TexturedButton extends ImageButton {

    public TexturedButton(int x, int y, int width, int height, int xTexStart, int yTexStart,
                          ResourceLocation texture, Button.IPressable action,
                          Button.ITooltip tooltip, ITextComponent text){
        super(x, y, width, height, xTexStart, yTexStart, 0, texture, 256, 256, action, tooltip, text);
    }

    @Override
    public void renderWidget(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.renderWidget(matrixStack, mouseX, mouseY, partialTicks);
        this.blit(matrixStack, this.x, this.y, 0, 0, this.width, this.height);
    }
}
