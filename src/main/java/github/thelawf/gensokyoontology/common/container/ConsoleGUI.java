package github.thelawf.gensokyoontology.common.container;


import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.client.MainWindow;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nonnull;
import java.util.Objects;

public class ConsoleGUI extends Screen {
    TextFieldWidget codeField;
    Button compileButton;
    Button runButton;
    TranslationTextComponent titleText = new TranslationTextComponent("client." +
            GensokyoOntology.MODID + ".title");

    final ResourceLocation CONSOLE_TEX = new ResourceLocation(GensokyoOntology.MODID,
            "textures/client/console.png");

    public ConsoleGUI() {
        super(new TranslationTextComponent("client." + GensokyoOntology.MODID +
                "console_gui.title"));
    }

    @Override
    protected void init() {
        // 原版 NarratorChatListener 类里面有监听聊天框文本输入的API
        MainWindow window = getMinecraft().getMainWindow();
        Objects.requireNonNull(this.minecraft).keyboardListener.enableRepeatEvents(true);

        this.codeField = new TextFieldWidget(this.font, (window.getWidth() - this.width) / 2
                , (window.getHeight() - this.height) / 2, 200, 136, new TranslationTextComponent(
                "client." + GensokyoOntology.MODID + ".code_field.content"));
        this.children.add(codeField);

        this.compileButton = new Button((window.getWidth() - this.width) / 2 + this.width / 2,
                (window.getHeight() - this.height) / 2 + this.height / 2, 80, 20,
                new TranslationTextComponent("client." + GensokyoOntology.MODID + ".compile"), (button) -> {
            String code = codeField.getText();
        });

        this.addButton(compileButton);
        super.init();

    }

    @Override
    public void render(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        Objects.requireNonNull(this.minecraft).getTextureManager().bindTexture(CONSOLE_TEX);
        int texWid = 640;
        int texHei = 480;
        /* 在这里加上一个判断，调用逻各斯通用库的 BlockCodeRenderer类和原版的 FontRenderer类
        下面的 drawText(...,int color)方法，需要使用同样位于 FontRenderer类下面的getStringpropertyWidth()
        方法获取文本的长度。

        super.render(matrixStack, mouseX, mouseY, partialTicks);

         */
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

}

