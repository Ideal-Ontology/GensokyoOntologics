package github.thelawf.gensokyoontology.api.client.xmmui;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.api.client.xmmui.data.XMMUIData;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.dom4j.*;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public abstract class XMMUIScreen extends Screen implements IXMLValueParser {
    private HashMap<String, Widget> widgetSet;
    private List<Button> xmlButtons;
    private final List<ITextComponent> textLabels = new ArrayList<>();
    public Document document;
    private XMMUIData xmmui;

    protected XMMUIScreen(ITextComponent titleIn, String xmlText) {
        super(titleIn);

        try {
            document = DocumentHelper.parseText(xmlText);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

        Element root = document.getRootElement();
        Element textLabel = root.element("text_label");

        this.xmmui = new XMMUIData(root);
        textLabels.add(new TranslationTextComponent(textLabel.getText().trim()));

    }

    @Override
    protected void init() {
        super.init();
        // 获取所有表单元素及其下方的组件元素，根据组件元素的标签将其内部的属性值赋给TextFieldWidget或者Button
    }

    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        drawCenteredString(matrixStack, this.font, textLabels.get(0), this.width / 2, 40, 16777215);
    }

    public Widget getComponentById(String id){
        return widgetSet.get(id);
    }
}
