package github.thelawf.gensokyoontology.client.gui.screen.script;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.api.client.layout.WidgetConfig;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public abstract class LineralLayoutScreen extends Screen {
    protected LineralLayoutScreen(ITextComponent titleIn) {
        super(titleIn);
    }

    protected void initByConfig(List<WidgetConfig> configs, int x, int y) {
        for (WidgetConfig config : configs) {
            x += config.leftInterval;
            y += config.upInterval;

            if (config.widget instanceof Button) {
                config.widget = new Button(x, y, config.width, config.height, config.text, config.action);
                this.addButton(config.widget);
            }
            if (config.widget instanceof TextFieldWidget) {
                config.widget.x = x;
                config.widget.y = y;
                config.widget.setWidth(config.width);
                config.widget.setHeight(config.height);
                config.widget.setMessage(config.text);
                this.children.add(config.widget);
            }
        }
    }

    public void setCenteredWidgets(List<WidgetConfig> configs) {
        int width = 0;
        int parentWidth = 255;
        for (WidgetConfig config : configs) width += config.width;
        int x = (parentWidth - width) / 2, y = 0;
        initByConfig(configs, x, y);
    }

    public void drawCenteredText(List<WidgetConfig> configs, MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        int width = 0;
        int parentWidth = 255;
        for (WidgetConfig config : configs) width += config.width;
        int x = (parentWidth - width) / 2, y = 0;
        for (WidgetConfig config : configs) {
            x += config.width;
            y += config.height;
            y += config.upInterval;

            if (config.isText) drawString(matrixStack, config.fontRenderer, config.text, x, y, 16777215);
        }
    }
}
