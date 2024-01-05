package github.thelawf.gensokyoontology.api.client.xmmui.data;

import org.dom4j.Element;

public class XMMUITextData extends XMMUIWidgetData {
    public int fontSize = 0;
    public int hexColor = 0;
    public boolean isBold = false;
    public boolean isItalic = false;
    public boolean isDel = false;

    public XMMUITextData(Element element) {
        super(element);
        this.fontSize = Integer.parseInt(element.attributeValue("font_size"));
        this.hexColor = parseRGBAColor(element.attributeValue("color"));
        this.isBold = Boolean.parseBoolean(element.attributeValue("bold"));
        this.isItalic = Boolean.parseBoolean(element.attributeValue("italic"));
        this.isDel = Boolean.parseBoolean(element.attributeValue("del"));
    }

    public int getFontSize() {
        return this.fontSize;
    }

    public XMMUITextData setFontSize(int fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public int getHexColor() {
        return this.hexColor;
    }

    public XMMUITextData setHexColor(int hexColor) {
        this.hexColor = hexColor;
        return this;
    }

    public boolean isBold() {
        return this.isBold;
    }

    public XMMUITextData setBold(boolean bold) {
        this.isBold = bold;
        return this;
    }

    public boolean isItalic() {
        return this.isItalic;
    }

    public XMMUITextData setItalic(boolean italic) {
        this.isItalic = italic;
        return this;
    }

    public boolean isDel() {
        return this.isDel;
    }

    public XMMUITextData setDel(boolean del) {
        this.isDel = del;
        return this;
    }
}
