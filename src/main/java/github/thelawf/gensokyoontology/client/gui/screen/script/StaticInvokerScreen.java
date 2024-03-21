package github.thelawf.gensokyoontology.client.gui.screen.script;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.client.layout.WidgetConfig;
import github.thelawf.gensokyoontology.common.container.script.StaticInvokerContainer;
import github.thelawf.gensokyoontology.common.container.script.V3dInvokerContainer;
import github.thelawf.gensokyoontology.common.nbt.script.StaticFunc;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.CInvokeFunctionPacket;
import github.thelawf.gensokyoontology.common.util.EnumUtil;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

// 13, 27 -> PARAMS_TEXT.x,y
public class StaticInvokerScreen extends InvokerContainerScreen<StaticInvokerContainer> {
    private StaticFunc func;
    public static final String TYPE = "static_invoker";
    private CompoundNBT funcData = new CompoundNBT();
    private final List<WidgetConfig> CONFIGS;
    public static final ResourceLocation TEXTURE = GensokyoOntology.withRL("textures/gui/static_invoker_screen.png");
    public StaticInvokerScreen(StaticInvokerContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.func = StaticFunc.SHOOT;
        this.xSize = 223;
        this.ySize = 191;
        this.titleX = 6;
        this.titleY = 6;
        this.playerInventoryTitleX = 31;
        this.playerInventoryTitleY = 102;
        this.functionNameBtn = new Button(0,0,0,0, this.func.toTextComponent(), b -> {});
        this.saveBtn = new Button(0,0,0,0, this.saveText, b -> {});
        CONFIGS = Lists.newArrayList(
                WidgetConfig.of(this.functionNameBtn, 70, 20).setXY(22, 62)
                        .withFont(this.font)
                        .withText(this.func.toTextComponent())
                        .withAction(this::funcBtnAction),
                WidgetConfig.of(this.saveBtn, 70, 20).setXY(131, 62)
                        .withFont(this.font)
                        .withText(this.saveText)
                        .withAction(this::saveBtnAction));

    }

    private void funcBtnAction(Button button) {
        this.func = EnumUtil.switchEnum(StaticFunc.class, this.func);
        button.setMessage(this.func.toTextComponent());
    }

    private void saveBtnAction(Button button) {
        if (this.minecraft == null) return;
        if (this.minecraft.player == null) return;
        if (!(this.minecraft.player.openContainer instanceof StaticInvokerContainer)) return;

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
            this.font.drawText(matrixStack, PARAMS_TEXT, this.guiLeft + 13, this.guiTop + 18, DARK_GRAY);
            this.font.drawText(matrixStack, RETURN_TEXT, this.guiLeft + 103, this.guiTop + 90, DARK_GRAY);
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
