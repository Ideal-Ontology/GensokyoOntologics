package github.thelawf.gensokyoontology.client.gui.screen.script;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.client.layout.WidgetConfig;
import github.thelawf.gensokyoontology.client.gui.container.script.BinaryOperationContainer;
import github.thelawf.gensokyoontology.client.gui.container.script.ScriptBuilderContainer;
import github.thelawf.gensokyoontology.common.nbt.script.BinaryOperation;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.CMergeScriptPacket;
import github.thelawf.gensokyoontology.common.util.EnumUtil;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import org.jetbrains.annotations.NotNull;

import java.util.List;

// save button: x=150, y=56, width = 40, height = 18
// operate button: x=47, y=20, width = 54, height = 18
// left text: 20, 44
// right text: 20, 66
// left input: 48, 48
// right input: 48, 71
public class BinaryOperationScreen extends ScriptContainerScreen {
    CompoundNBT optData = new CompoundNBT();
    public static final ResourceLocation TEXTURE = GensokyoOntology.withRL("textures/gui/binary_operation_screen.png");
    private BinaryOperation operation;
    public static String FIELD_TYPE = "binary_operation";
    private TextFieldWidget leftInput;
    private TextFieldWidget rightInput;
    private final ITextComponent leftText = GensokyoOntology.withTranslation("gui.",".binary_operation.left.text");
    private final ITextComponent rightText = GensokyoOntology.withTranslation("gui.",".binary_operation.right.text");

    private final List<WidgetConfig> WIDGETS;
    public BinaryOperationScreen(ScriptBuilderContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.playerInventoryTitleX = 10;
        this.playerInventoryTitleY = 88;
        this.operation = BinaryOperation.NONE;

        Button operationBtn = new Button(0, 0, 0, 0, operation.toTextComponent(), (b1) -> {});
        this.saveBtn = new Button(0,0,0,0, saveText, (b2) -> {});

        WIDGETS = Lists.newArrayList(
                WidgetConfig.of(operationBtn, 54, 20).setXY(47, 20)
                        .withFont(this.font)
                        .withText(this.operation.toTextComponent())
                        .withAction(this::operationBtnAction),
                WidgetConfig.of(this.saveBtn, 40, 20).setXY(140, 56)
                        .withFont(this.font)
                        .withText(this.saveText)
                        .withAction(this::saveBtnAction));
    }

    @Override
    public void tick() {
        super.tick();
        this.leftInput.tick();
        this.rightInput.tick();
    }

    @Override
    protected void init() {
        super.init();
        if (this.minecraft == null) return;
        if (this.minecraft.player == null) return;

        int a = this.guiLeft;
        int b = this.guiTop;

        this.leftInput = new TextFieldWidget(this.font, a + 43, b + 48, 90, 18, withText(""));
        this.rightInput = new TextFieldWidget(this.font, a + 43, b + 71, 90, 18, withText(""));
        this.setRelativeToParent(WIDGETS, a, b);
    }

    private void operationBtnAction(Button button) {
        this.operation = EnumUtil.switchEnum(BinaryOperation.class, this.operation);
        button.setMessage(this.operation.toTextComponent());
    }

    private void saveBtnAction(Button button) {
        if (this.minecraft == null) return;
        if (this.minecraft.player == null) return;

        if (!(this.minecraft.player.openContainer instanceof BinaryOperationContainer)) return;
        BinaryOperationContainer container = (BinaryOperationContainer) this.minecraft.player.openContainer;

        CompoundNBT left = container.inventorySlots.get(0).getStack().getTag();
        CompoundNBT right = container.inventorySlots.get(1).getStack().getTag();
        CompoundNBT value = new CompoundNBT();

        if (left != null) value.put("left", left);
        if (right != null) value.put("right", right);
        value.putString("operation", this.operation.key);

        this.optData.putString("type", FIELD_TYPE);
        this.optData.put("value", value);

        GSKONetworking.CHANNEL.sendToServer(new CMergeScriptPacket(this.optData));
    }

    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        if (this.minecraft != null) {
            this.renderRelativeToParent(WIDGETS, matrixStack, mouseX, mouseY, this.guiLeft, this.guiTop, partialTicks);
            this.font.drawText(matrixStack, this.leftText, this.guiLeft + 20, this.guiTop + 50, DARK_GRAY);
            this.font.drawText(matrixStack,  this.rightText, this.guiLeft + 20, this.guiTop + 72, DARK_GRAY);
            // drawCenteredString(matrixStack, this.font, this.leftText, this.guiLeft + 20, this.guiTop + 50, DARK_GRAY);
            // drawCenteredString(matrixStack, this.font, this.rightText, this.guiLeft + 20, this.guiTop + 72, DARK_GRAY);
            this.leftInput.render(matrixStack, mouseX, mouseY, partialTicks);
            this.rightInput.render(matrixStack, mouseX, mouseY, partialTicks);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(@NotNull MatrixStack matrixStack, float partialTicks, int x, int y) {
        if (this.minecraft == null) return;
        this.minecraft.getTextureManager().bindTexture(TEXTURE);
        this.blit(matrixStack, this.guiLeft, this.guiTop, 0, 0, 201, 185);
    }

}
