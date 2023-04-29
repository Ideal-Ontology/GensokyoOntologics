package github.thelawf.gensokyoontology.common.libs.logoslib.syntax;

import github.thelawf.gensokyoontology.api.IReversedWord;

import java.awt.*;

public class ReturnTypes extends AbstractReservedWord implements IReversedWord {
    public static final String voidType = "void";
    public static final String intType = "int";
    public static final String longType = "long";
    public static final String shortType = "short";
    public static final String floatType = "float";
    public static final String doubleType = "double";
    public static final String booleanType = "boolean";
    public static final Object classInstanceType = null;

    @Override
    public Color getHighLightColor(Color colorIn) {
        return super.getHighLightColor(colorIn);
    }
}
