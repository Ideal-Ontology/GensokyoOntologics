package github.thelawf.gensokyoontology.common.libs.logoslib.syntax;

import java.awt.*;

public abstract class AbstractReservedWord implements IReversedWord{
    public static final Color highLightColor = new Color(0,0,255);
    public static final String colorStrHex = "#0000FF";
    public AbstractReservedWord(){

    }
    public Color getHighLightColor(Color colorIn) {
        return colorIn;
    }
}
