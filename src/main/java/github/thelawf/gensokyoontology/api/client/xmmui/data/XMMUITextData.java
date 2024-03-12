package github.thelawf.gensokyoontology.api.client.xmmui.data;

import org.dom4j.Element;

public class XMMUITextData extends XMMUIWidgetData {
    public int fontSize = 0;
    public int hexColor = 16777215;
    public boolean hasBold = false;
    public boolean hasItalic = false;
    public boolean hasDel = false;

    public XMMUITextData(Element element) {
        super(element);
        this.fontSize = Integer.parseInt(getOrDefault(element, "font_size", "20"));
        //this.hexColor = parseRGBAColor(getOrDefault(element, "color", "#FFFFFFFF"));
        this.hasBold = Boolean.parseBoolean(getOrDefault(element, "bold", "false"));
        this.hasItalic = Boolean.parseBoolean(getOrDefault(element, "italic", "false"));
        this.hasDel = Boolean.parseBoolean(getOrDefault(element, "del", "false"));
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

    public boolean isHasBold() {
        return this.hasBold;
    }

    public XMMUITextData setHasBold(boolean hasBold) {
        this.hasBold = hasBold;
        return this;
    }

    public boolean isHasItalic() {
        return this.hasItalic;
    }

    public XMMUITextData setHasItalic(boolean hasItalic) {
        this.hasItalic = hasItalic;
        return this;
    }

    public boolean isHasDel() {
        return this.hasDel;
    }

    public XMMUITextData setHasDel(boolean hasDel) {
        this.hasDel = hasDel;
        return this;
    }
}
