package github.thelawf.gensokyoontology.common.libs.logoslib.syntax;

public class VarietyModifiers extends AbstractReservedWord{
    public static final String nullType = "null";
    public static final String trueType = "true";
    public static final String falseType = "false";
    public static final String staticKeyword = "static";
    public static final String finalKeyword = "final";
    public static final String[] allModifiers = {nullType,trueType,falseType,
    staticKeyword,finalKeyword};

    public String[] getAllModifiers() {
        return allModifiers;
    }
}
