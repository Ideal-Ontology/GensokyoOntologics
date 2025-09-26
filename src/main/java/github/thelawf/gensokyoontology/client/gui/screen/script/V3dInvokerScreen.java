package github.thelawf.gensokyoontology.client.gui.screen.script;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.api.client.layout.WidgetConfig;
import github.thelawf.gensokyoontology.common.container.script.V3dInvokerContainer;
import github.thelawf.gensokyoontology.common.nbt.script.V3dFunc;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.CInvokeFunctionPacket;
import github.thelawf.gensokyoontology.common.util.EnumUtil;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class V3dInvokerScreen extends InvokerContainerScreen<V3dInvokerContainer> {
    private V3dFunc func;
    public static final String TYPE = "vector3d_invoker";
    private CompoundNBT funcData = new CompoundNBT();
    private final List<WidgetConfig> CONFIGS;
    public final ITextComponent PARAM_TEXT = withTranslation("gui","v3d_invoker.param");
    public static final ResourceLocation TEXTURE = GSKOUtil.withRL("textures/gui/v3d_invoker_screen.png");
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
        this.saveBtn = new Button(0,0,0,0, this.saveText, b -> {});
        CONFIGS = Lists.newArrayList(
                WidgetConfig.of(this.functionNameBtn, 70, 20).setXY(64, 20)
                        .withFont(this.font)
                        .withText(this.func.toTextComponent())
                        .withAction(this::funcBtnAction),
                WidgetConfig.of(this.saveBtn, 70, 20).setXY(64, 50)
                        .withFont(this.font)
                        .withText(this.saveText)
                        .withAction(this::saveBtnAction));

    }

    private void funcBtnAction(Button button) {
        this.func = EnumUtil.switchEnum(V3dFunc.class, this.func);
        button.setMessage(this.func.toTextComponent());
    }

    private void saveBtnAction(Button button) {
        if (this.minecraft == null) return;
        if (this.minecraft.player == null) return;
        if (!(this.minecraft.player.openContainer instanceof V3dInvokerContainer)) return;

        this.funcData.putString("type", TYPE);
        this.funcData.putString("methodName", this.func.methodName);
        this.funcData.putString("return", this.func.returnType);
        GSKONetworking.CHANNEL.sendToServer(new CInvokeFunctionPacket(this.funcData));
    }

    @Override
    protected void init() {
        super.init();
        setRelativeToParent(CONFIGS, this.guiLeft, this.guiTop);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        if (this.minecraft != null) {
            this.font.drawText(matrixStack, PARAM_TEXT, this.guiLeft + 30, this.guiTop + 40, DARK_GRAY);
            this.font.drawText(matrixStack, RETURN_TEXT, this.guiLeft + 140, this.guiTop + 40, DARK_GRAY);
            this.renderRelativeToParent(CONFIGS, matrixStack, mouseX, mouseY, this.guiLeft, this.guiTop,partialTicks);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(@NotNull MatrixStack matrixStack, float partialTicks, int x, int y) {
        if (this.minecraft == null) return;
        this.minecraft.getTextureManager().bindTexture(TEXTURE);
        this.blit(matrixStack, this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }
}
