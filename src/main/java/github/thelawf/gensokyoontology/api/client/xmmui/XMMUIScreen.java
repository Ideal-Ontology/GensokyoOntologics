package github.thelawf.gensokyoontology.api.client.xmmui;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.EditBookScreen;
import net.minecraft.client.gui.screen.IngameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.resources.FallbackResourceManager;
import net.minecraft.resources.IResourceManager;
import net.minecraft.resources.ResourcePackType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@OnlyIn(Dist.CLIENT)
public abstract class XMMUIScreen extends Screen implements IXMLValueParser {
    private HashMap<String, Widget> widgetSet;
    private List<Button> xmlButtons;
    private List<TextFieldWidget> xmlTextFields;
    private List<Attribute> xmlAttrs;
    private List<Element> forms;
    private final List<ITextComponent> textLabels = new ArrayList<>();
    private ResourceLocation xmlPath;
    public Document document;
    private XMLData xmmui;
    public final boolean hasTileEntity;
    public final boolean isContainerScreen;
    private final ResourceLocation texture;
    private File file;
    private int titleX;
    private int titleY;
    protected XMMUIScreen(ITextComponent titleIn, ResourceLocation xmlPath) {
        super(titleIn);
        this.xmlPath = xmlPath;
        SAXReader reader = new SAXReader();

        try {
            FallbackResourceManager manager = new FallbackResourceManager(ResourcePackType.CLIENT_RESOURCES, GensokyoOntology.MODID);
            document = reader.read((manager.getResource(xmlPath).getInputStream()));
        } catch (IOException | DocumentException e) {
            throw new RuntimeException(e);
        }
        Element root = document.getRootElement();
        String s1 = root.attributeValue("has_tile_Entity");
        String s2 = root.attributeValue("is_container_screen");
        String tex = root.attributeValue("texture_src");

        this.hasTileEntity = Boolean.parseBoolean(s1);
        this.isContainerScreen = Boolean.parseBoolean(s2);
        this.texture = new ResourceLocation(tex);

    }

    protected XMMUIScreen(ITextComponent titleIn, File xmlFile) {
        super(titleIn);
        this.file = xmlFile;
        SAXReader reader = new SAXReader();

        try {
            FallbackResourceManager manager = new FallbackResourceManager(ResourcePackType.CLIENT_RESOURCES, GensokyoOntology.MODID);
            document = reader.read(file);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        Element root = document.getRootElement();
        Element textLabel = root.element("text_label");
        String s1 = root.attributeValue("has_tile_Entity");
        String s2 = root.attributeValue("is_container_screen");
        String tex = root.attributeValue("texture_src");

        this.hasTileEntity = Boolean.parseBoolean(s1);
        this.isContainerScreen = Boolean.parseBoolean(s2);
        texture = new ResourceLocation(tex);

        this.titleX = getIntValue(textLabel.attributeValue("x"));
        this.titleY = getIntValue(textLabel.attributeValue("y"));
        if (textLabel.attributeValue("type").equals("translation")) {
            textLabels.add(new TranslationTextComponent(textLabel.getText().trim()));
        }
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

    public ResourceLocation getXmlPath() {
        return this.xmlPath;
    }
}
