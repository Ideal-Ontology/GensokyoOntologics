package github.thelawf.gensokyoontology.api.client.xmmui.data;

import org.dom4j.Element;

public class XMMUITextData extends XMMUIWidgetData {
    public int fontSize = 0;
    public int hexColor = 16777215;
    public boolean isBold = false;
    public boolean isItalic = false;
    public boolean isDel = false;

    public XMMUITextData(Element element) {
        super(element);
        this.fontSize = Integer.parseInt(getOrDefault(element, "font_size", "20"));
        //this.hexColor = parseRGBAColor(getOrDefault(element, "color", "#FFFFFFFF"));
        this.isBold = Boolean.parseBoolean(getOrDefault(element, "bold", "false"));
        this.isItalic = Boolean.parseBoolean(getOrDefault(element, "italic", "false"));
        this.isDel = Boolean.parseBoolean(getOrDefault(element, "del", "false"));
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
