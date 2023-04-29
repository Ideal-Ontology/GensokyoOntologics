package github.thelawf.gensokyoontology.common.libs.logoslib.syntax;

import github.thelawf.gensokyoontology.api.IReversedWord;

public class ClassInstanceKeyWords extends AbstractReservedWord implements IReversedWord {
    public static final String importKeyWord = "import";
    public static final String packageKeyWord = "package";
    public static final String classKeyWord = "class";
    public static final String interfaceKeyWord = "interface";
    public static final String thisKeyWord = "this";
    public static final String enumKeyWord = "enum";
    public static final String newKeyWord = "new";
    public static final String extendsKeyWord = "extends";
    public static final String implementsKeyWord = "implements";
    //public static final Object object = null;
    public static final String[] allKeyWords = {importKeyWord,packageKeyWord,
    classKeyWord,interfaceKeyWord,enumKeyWord,thisKeyWord,newKeyWord,extendsKeyWord,
            implementsKeyWord};

    public Object[] getAllKeyWords() {
        return allKeyWords;
    }
}
