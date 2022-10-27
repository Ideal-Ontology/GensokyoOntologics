package github.thelawf.gensokyoontology.common.libs.logoslib;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.libs.logoslib.syntax.AbstractReservedWord;
import github.thelawf.gensokyoontology.common.libs.logoslib.syntax.AccessModifiers;
import github.thelawf.gensokyoontology.common.libs.logoslib.syntax.ClassInstanceKeyWords;
import github.thelawf.gensokyoontology.common.libs.logoslib.syntax.ReturnTypes;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class BlockCodeFormatter extends JBlockCode{

    public String htmlTypeColor;
    public String scripts;
    public AbstractReservedWord wordIn;

    /**
     * I don't know how to highLight GUI text in Minecraft, so just to make a formatter to
     * generate HTML format file.<br>我暂时不知道怎么把 Minecraft 的 GUI文本高亮，所以就做了这样一个生成HTML格式文本的生成器。
     * @param scriptsIn The code text that needs to be formatted. （传入你需要格式化的代码串）
     * @return HTML format text （HTML格式的文本）
     */
    public String highlightTextAsHTML(String scriptsIn) {
        StringBuilder strFormat = new StringBuilder();
        AccessModifiers am = new AccessModifiers();
        ClassInstanceKeyWords cWords = new ClassInstanceKeyWords();
        ReturnTypes types = new ReturnTypes();

        // 渲染开始，首先将原字符串以空格拆分为数组，再将数组以回车符号拆分为二维列表
        // 列表的长度表示原代码的行数，列表内列表的元素个数为代码单元；
        ArrayList<ArrayList<String>> scriptLines = new ArrayList<>();

        for (int i = 0; i < scriptsIn.split("\n").length; i++) {

            String[] elements = scriptsIn.split("\n")[i].split(" ");
            ArrayList<String> htmls = new ArrayList<>();
            for (int j = 0; j < elements.length; j++) {
                // 获取代码元素，格式化渲染开始
                String copy = elements[j];
                elements[j] = "<font size=\"6\" color=\"black\">" + copy + " ";
                htmls.add(elements[j]);

                for (int k = 0; k < am.getAllModifiers().length; k++) {
                    // 判断元素是否为访问修饰符，且是否为本行第一个元素
                    if (Objects.equals(copy, am.getAllModifiers()[k]) && j == 0) {
                        String html = "<p><font size=\"6\" color=\"blue\">" + copy + "</font> ";
                        htmls.set(j,html);
                    }
                    else if (Objects.equals(copy, am.getAllModifiers()[k]) && j != 0) {
                        String html = "<font size=\"6\" color=\"blue\">" + copy + "</font> ";
                        htmls.set(j,html);
                    }
                }

                for (int k = 0; k < cWords.getAllKeyWords().length; k++) {
                    if (Objects.equals(copy, cWords.getAllKeyWords()[k]) && j == 0) {
                        String html = "<p><font size=\"6\" color=\"blue\">" + copy + "</font> ";
                        htmls.set(j,html);
                    }
                    else if (Objects.equals(copy, cWords.getAllKeyWords()[k]) && j != 0) {
                        String html = "<font size=\"6\" color=\"blue\">" + copy + "</font> ";
                        htmls.set(j,html);
                    }
                }
            }
            // 二维列表内的一维列表循环结束后，为一维列表最后一个字符加上HTML段落结束标记 -- "</p>"
            htmls.set(htmls.size()-1, htmls.get(htmls.size()-1) + "</p>\n");
            scriptLines.add(htmls);
        }

        for (ArrayList<String> list : scriptLines) {
            for (String each : list) {
                strFormat.append(each);
            }
        }

        return strFormat.toString();
    }

    public void writeFile(String scriptsIn) {
        ResourceLocation location = new ResourceLocation(GensokyoOntology.MODID,"misc/statistics/highlighter.html");
    }

    @NotNull
    @Override
    public String getCode() {
        return scripts;
    }

    @Override
    public int getJava() {
        return java;
    }

    @Override
    public boolean isJava() {
        return true;
    }
}
