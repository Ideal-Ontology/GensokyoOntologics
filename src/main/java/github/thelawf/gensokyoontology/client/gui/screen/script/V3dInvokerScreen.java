package github.thelawf.gensokyoontology.client.gui.screen.script;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.client.layout.WidgetConfig;
import github.thelawf.gensokyoontology.client.gui.screen.widget.BlankWidget;
import github.thelawf.gensokyoontology.common.container.script.V3dInvokerContainer;
import github.thelawf.gensokyoontology.common.nbt.script.V3dFunc;
import github.thelawf.gensokyoontology.common.util.EnumUtil;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class V3dInvokerScreen extends InvokerContainerScreen<V3dInvokerContainer> {
    private V3dFunc func;
    private final List<WidgetConfig> CONFIGS;
    public final WidgetConfig PARAM = WidgetConfig.of(new BlankWidget(0,0,0,0, withText("")),0,0).isText(true);
    public final ITextComponent PARAM_TEXT = withTranslation("gui","v3d_invoker.param");
    public static final ResourceLocation TEXTURE = GensokyoOntology.withRL("textures/gui/mapping_value_screen.png");
    public V3dInvokerScreen(V3dInvokerContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.func = V3dFunc.ADD;
        this.xSize = 186;
        this.ySize = 167;
        this.titleX = 6;
        this.titleY = 6;
        this.playerInventoryTitleX = 12;
        this.playerInventoryTitleY = 60;
        this.functionNameBtn = new Button(0,0,0,0, this.func.toTextComponent(), b -> {});
        CONFIGS = Lists.newArrayList(
                PARAMS_LABEL.setXY(30,40).withFont(this.font).withText(PARAM_TEXT),
                RETURN_LABEL.setXY(140,40).withFont(this.font).withText(RETURN_TEXT),
                WidgetConfig.of(this.functionNameBtn, 60, 20).setXY(61, 20)
                        .withFont(this.font)
                        .withText(this.func.toTextComponent())
                        .withAction(this::funcBtnAction),
                WidgetConfig.of(this.saveBtn, 60, 20).setXY(61, 50)
                        .withFont(this.font)
                        .withText(this.func.toTextComponent())
                        .withAction(this::saveBtnAction));

    }

    private void funcBtnAction(Button button) {
        this.func = EnumUtil.switchEnum(V3dFunc.class, this.func);
        button.setMessage(this.func.toTextComponent());
    }

    private void saveBtnAction(Button button) {
    }

    @Override
    protected void init() {
        super.init();
        setRelativeToParent(CONFIGS, this.guiLeft, this.guiTop);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderBackground(matrixStack);
        if (this.minecraft != null) {
            this.renderRelativeToParent(CONFIGS, matrixStack, mouseX, mouseY, this.guiLeft, this.guiTop,partialTicks);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(@NotNull MatrixStack matrixStack, float partialTicks, int x, int y) {
        if (this.minecraft == null) return;
        this.minecraft.getTextureManager().bindTexture(TEXTURE);
    }
}
