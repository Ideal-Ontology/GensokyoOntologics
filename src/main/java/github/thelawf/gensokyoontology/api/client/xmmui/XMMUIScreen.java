package github.thelawf.gensokyoontology.api.client.xmmui;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.api.client.xmmui.data.XMMUIData;
import github.thelawf.gensokyoontology.api.client.xmmui.data.XMMUITextData;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@OnlyIn(Dist.CLIENT)
public abstract class XMMUIScreen extends Screen implements IXMLValueParser {
    private HashMap<String, Widget> widgetSet;
    private List<XMMUITextData> textData = new ArrayList<>();
    public Document document;
    private XMMUIData xmmui;

    /**
     * 获取获取根标签，获取XMMUI的反序列化数据<br>
     * this.textData can be fetched by turning root.elements()
     * into java.Stream and then filter the result by element name.
     * If the name is equivalent to text_label,
     * then map the element by providing the element into XMMUITextData constructor
     *
     * @param titleIn 标题
     * @param xmlText xml文档的内容
     * @throws DocumentException 文档解析异常
     */
    protected XMMUIScreen(ITextComponent titleIn, String xmlText) throws DocumentException {
        super(titleIn);
        document = DocumentHelper.parseText(xmlText);

        Element root = document.getRootElement();
        // this.xmmui = new XMMUIData(root);
        this.textData = root.elements().stream().filter(element -> element.getName().equals("text_label"))
                .map(XMMUITextData::new).collect(Collectors.toList());
    }

    @Override
    protected void init() {
        super.init();
        // 获取所有表单元素及其下方的组件元素，根据组件元素的标签将其内部的属性值赋给TextFieldWidget或者Button
    }

    /**
     * MCP的反混淆有问题，drawString最后三个整型形参的名称应该为 int x, int y, int hexColor.<br>
     * 即 drawString(MatrixStack matrixStack, FontRenderer font, String text, int x, int y, int hexColor);
     */
    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.textData.forEach(data -> drawString(matrixStack, this.font, new TranslationTextComponent(data.text),
                data.x, data.y, data.hexColor));
    }

    public Widget getComponentById(String id){
        return widgetSet.get(id);
    }

    public void drawTranslatedString(MatrixStack matrixStack, FontRenderer font, ITextComponent text, int x, int y, int hexColor) {
        IReorderingProcessor processor = text.func_241878_f();
        font.drawTextWithShadow(matrixStack, processor, (float)(x - font.func_243245_a(processor) / 2), (float)y, hexColor);
    }
}
