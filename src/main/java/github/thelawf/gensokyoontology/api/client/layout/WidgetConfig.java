package github.thelawf.gensokyoontology.api.client.layout;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.ITextComponent;

public class WidgetConfig {

    public int width;
    public int height;
    public int upInterval;
    public int downInterval;
    public int leftInterval;
    public int rightInterval;
    public Widget widget;
    public FontRenderer fontRenderer;
    public ITextComponent text;
    public Button.IPressable action;
    public boolean isText;

    public WidgetConfig(Widget widget, int width, int height) {
        this.widget = widget;
        this.width = width;
        this.height = height;
    }

    public static WidgetConfig of(Widget widget, int width, int height) {
        return new WidgetConfig(widget, width, height);
    }


    public WidgetConfig withFont(FontRenderer rendererIn) {
        this.fontRenderer = rendererIn;
        return this;
    }

    public WidgetConfig withAction(Button.IPressable actioIn) {
        this.action = actioIn;
        return this;
    }

    public WidgetConfig withText(ITextComponent titleIn) {
        this.text = titleIn;
        return this;
    }

    public WidgetConfig upInterval(int upIntervalIn) {
        this.upInterval = upIntervalIn;
        return this;
    }

    public WidgetConfig downInterval(int downIntervalIn) {
        this.downInterval = downIntervalIn;
        return this;
    }
    public WidgetConfig leftInterval(int leftInterval) {
        this.leftInterval = leftInterval;
        return this;
    }
    public WidgetConfig rightInterval(int rightIntervalIn) {
        this.rightInterval = rightIntervalIn;
        return this;
    }

    public WidgetConfig isText(boolean isText) {
        this.isText = isText;
        return this;
    }

    public enum Type{
        BUTTON,
        INTERVAL,
        TEXT_FIELD,
        LABEL;
    }

}
