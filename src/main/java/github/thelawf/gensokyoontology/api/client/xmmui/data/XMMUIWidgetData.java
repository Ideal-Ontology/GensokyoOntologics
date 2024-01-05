package github.thelawf.gensokyoontology.api.client.xmmui.data;

import github.thelawf.gensokyoontology.api.client.xmmui.IXMLValueParser;
import org.dom4j.Element;

public class XMMUIWidgetData implements IXMLValueParser {
    public int x;
    public int y;
    public int width = 100;
    public int height = 30;
    public String id = "";
    public String text = "";
    public WidgetAligns align = WidgetAligns.NONE;

    public XMMUIWidgetData(Element element) {
        this.id = element.attributeValue("id");
        this.text = element.getText();
        this.x = Integer.parseInt(element.attributeValue("x"));
        this.y = Integer.parseInt(element.attributeValue("y"));
        this.width = Integer.parseInt(getOrDefault(element, "width", "100"));
        this.height = Integer.parseInt(getOrDefault(element, "height", "30"));
        this.align = getEnumValue(getOrDefault(element, "align", "NONE"), WidgetAligns.class);
    }

    public int getX() {
        return this.x;
    }

    public XMMUIWidgetData setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return this.y;
    }

    public XMMUIWidgetData setY(int y) {
        this.y = y;
        return this;
    }

    public int getWidth() {
        return this.width;
    }

    public XMMUIWidgetData setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeight() {
        return this.height;
    }

    public XMMUIWidgetData setHeight(int height) {
        this.height = height;
        return this;
    }

    public WidgetAligns getAlign() {
        return this.align;
    }

    public XMMUIWidgetData setAlign(WidgetAligns align) {
        this.align = align;
        return this;
    }
    
    
}
