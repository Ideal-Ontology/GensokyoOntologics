package github.thelawf.gensokyoontology.api.client.layout;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.client.gui.screen.widget.BlankWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public class WidgetConfig {

    public int width;
    public int height;
    public int upInterval;
    public int downInterval;
    public int leftInterval;
    public int rightInterval;
    public Widget widget;
    public FontRenderer fontRenderer = Minecraft.getInstance().fontRenderer;
    public ITextComponent text;
    public Button.IPressable action;
    public boolean isText;

    public static final WidgetConfig DEFAULT = new WidgetConfig(BlankWidget.INSTANCE, 0,0);
    public static final WidgetConfig TEXT = new WidgetConfig(BlankWidget.INSTANCE, 0,0).isText(true);

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

    public WidgetConfig withAction(Button.IPressable actionIn) {
        this.action = actionIn;
        return this;
    }

    public WidgetConfig withText(ITextComponent titleIn) {
        this.text = titleIn;
        return this;
    }

    /**
     * 表示该行组件与其上一行组件的垂直距离，如果没有上一行组件则该值为组件的y坐标<br>
     * The vertical distance from this line of widgets to the previous one.
     * If there is no previous widget line in that widget list, this value will be
     * considered as the x coordinate of this widget.
     */
    public WidgetConfig upInterval(int upIntervalIn) {
        this.upInterval = upIntervalIn;
        return this;
    }

    public WidgetConfig downInterval(int downIntervalIn) {
        this.downInterval = downIntervalIn;
        return this;
    }

    /**
     * 表示该组件与其上一个组件的水平距离，如果没有上一个组件则该值为组件的x坐标<br>
     * The horizontal distance from this widget to the previous one.
     * If there is no previous widget in that widget list, this value will be
     * considered as the x coordinate of this widget.
     */
    public WidgetConfig leftInterval(int leftInterval) {
        this.leftInterval = leftInterval;
        return this;
    }

    public WidgetConfig upLeft(int upInterval, int leftInterval) {
        this.upInterval = upInterval;
        this.leftInterval = leftInterval;
        return this;
    }

    public WidgetConfig rightInterval(int rightIntervalIn) {
        this.rightInterval = rightIntervalIn;
        return this;
    }

    /**
     * 用于判断该组件是否是文本标签组件，如果为真则会在{@link github.thelawf.gensokyoontology.client.gui.screen.script.LineralLayoutScreen#initByConfig(List, int, int) 设置组件初始化位置方法}
     * 中跳过该组件的初始化设置。同时，{@link github.thelawf.gensokyoontology.client.gui.screen.script.LineralLayoutScreen#drawCenteredText(List, MatrixStack, int, int, float) drawCenteredText}
     * 方法会根据这个字段的属性选择是否渲染文字。<br>
     * Used to check whether this widget is text widget.
     * If this is set
     * as true
     * as the {@link github.thelawf.gensokyoontology.client.gui.screen.script.LineralLayoutScreen#initByConfig(List, int, int) initByConfig} method will skip
     * the initialization of this widget.
     * Meanwhile, the
     * {@link github.thelawf.gensokyoontology.client.gui.screen.script.LineralLayoutScreen#drawCenteredText(List, MatrixStack, int, int, float) drawCenteredText}
     * method will render the text if this field is set as true.
     */
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
