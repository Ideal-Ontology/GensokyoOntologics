package github.thelawf.gensokyoontology.client.gui.screen.script;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.api.client.IInputParser;
import github.thelawf.gensokyoontology.api.client.ITextBuilder;
import github.thelawf.gensokyoontology.api.client.layout.WidgetConfig;
import github.thelawf.gensokyoontology.common.container.script.ScriptBuilderContainer;
import github.thelawf.gensokyoontology.client.gui.screen.widget.SlotWidget;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public abstract class LineralContainerScreen<C extends ScriptBuilderContainer> extends ContainerScreen<C> implements IInputParser, ITextBuilder {
    protected static final int WHITE = 16777215;
    protected static final int DARK_GRAY = 5592405;
    public LineralContainerScreen(C screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    public void initByConfig(List<WidgetConfig> configs, int x, int y) {
        for (WidgetConfig config : configs) {
            x += config.leftInterval;
            y += config.upInterval;
            addWidget(x, y, config);
        }
    }

    public void setCenteredWidgets(List<WidgetConfig> configs) {
        int width = 0;
        int parentWidth = 255;
        for (WidgetConfig config : configs) width += config.width;
        int x = (parentWidth - width) / 2, y = 0;
        initByConfig(configs, x, y);
    }

    public void setAbsoluteXY(List<WidgetConfig> configs) {
        int x,y;
        for (WidgetConfig config : configs) {
            x = config.leftInterval;
            y = config.upInterval;
            addWidget(x, y, config);
        }
    }

    public void setRelativeToParent(List<WidgetConfig> configs, int parentLeft, int parentTop) {
        int x,y;
        for (WidgetConfig config : configs) {
            x = config.leftInterval;
            y = config.upInterval;
            addWidget(x + parentLeft, y + parentTop, config);
        }
    }

    private void addWidget(int x, int y, WidgetConfig config) {
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

    @OnlyIn(Dist.CLIENT)
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

    @OnlyIn(Dist.CLIENT)
    public void renderWidgets(List<WidgetConfig> configs, MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        int width = 0;
        int parentWidth = 255;
        for (WidgetConfig config : configs) width += config.width;
        int x = (parentWidth - width) / 2, y = 0;
        for (WidgetConfig config : configs) {
            x += config.width;
            y += config.height;
            y += config.upInterval;

            if (config.isText) drawString(matrixStack, this.font, config.text, x, y, 16777215);
            else config.widget.render(matrixStack, mouseX, mouseY, partialTicks);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void renderRelativeXY(List<WidgetConfig> configs, MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        int x, y;
        for (WidgetConfig config : configs) {
            x = config.leftInterval;
            y = config.upInterval;

            if (config.isText) drawString(matrixStack, config.fontRenderer, config.text, x, y, 16777215);
            else if (config.widget instanceof SlotWidget) return;
            else config.widget.render(matrixStack, mouseX, mouseY, partialTicks);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void renderCenterRelative(List<WidgetConfig> configs, MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        for (WidgetConfig config : configs) {
            if (config.isText) drawString(matrixStack, config.fontRenderer, config.text, config.leftInterval, config.upInterval, 16777215);
            else if (config.widget instanceof SlotWidget) return;
            else config.widget.render(matrixStack, mouseX, mouseY, partialTicks);
        }

    }

    @OnlyIn(Dist.CLIENT)
    public void renderRelativeToParent(List<WidgetConfig> configs, MatrixStack matrixStack, int mouseX, int mouseY,
                                       int parentLeft, int parentTop, float partialTicks) {
        for (WidgetConfig config : configs) {
            if (config.isText) drawString(matrixStack, config.fontRenderer, config.text, parentLeft + config.leftInterval, parentTop + config.upInterval, 16777215);
            else if (config.widget instanceof SlotWidget) return;
            else config.widget.render(matrixStack, mouseX, mouseY, partialTicks);
        }

    }

}
