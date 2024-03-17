package github.thelawf.gensokyoontology.client.gui.screen.script;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.client.layout.WidgetConfig;
import github.thelawf.gensokyoontology.client.gui.container.script.BinaryOperationContainer;
import github.thelawf.gensokyoontology.client.gui.container.script.ScriptBuilderContainer;
import github.thelawf.gensokyoontology.client.gui.screen.widget.SlotWidget;
import github.thelawf.gensokyoontology.common.nbt.script.BinaryOperation;
import github.thelawf.gensokyoontology.common.util.EnumUtil;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

// type button: 56, 0
// operate button: 56, 20
// left text: 20, 46
// right text: 20, 69
// left input: 60, 46
// right input: 60, 69
public class BinaryOperationScreen extends ScriptContainerScreen {
    CompoundNBT optData = new CompoundNBT();
    public static final ResourceLocation TEXTURE = GensokyoOntology.withRL("textures/gui/binary_operation_screen.png");
    private Button typeBtn;
    private Button operationBtn;
    private SlotWidget leftSlot = new SlotWidget(0,0,0,0,ofText("left"));
    private SlotWidget rightSlot = new SlotWidget(0,0,0,0,ofText("right"));
    private SlotWidget outputSLot = new SlotWidget(0,0,0,0,ofText("output"));
    private TextFieldWidget leftInput;
    private TextFieldWidget rightInput;
    private BinaryOperation operation;
    public static String FIELD_TYPE = "binary_operation";
    private final ITextComponent leftText = GensokyoOntology.withTranslation("gui.",".binary_operation.left.text");
    private final ITextComponent rightText = GensokyoOntology.withTranslation("gui.",".binary_operation.right.text");

    private final List<WidgetConfig> WIDGETS;
    public BinaryOperationScreen(ScriptBuilderContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        operation = BinaryOperation.NONE;

        this.typeBtn = new Button(0,0,0,0, ofText("null"), (b) -> {});
        this.operationBtn = new Button(0,0,0,0, operation.toTextComponent(), (b) -> {});
        this.saveBtn = new Button(0,0,0,0, saveText, (b) -> {});

        // this.leftInput = new TextFieldWidget(this.font, 0,0,0,0, ofText(""));
        // this.rightInput = new TextFieldWidget(this.font, 0,0,0,0, ofText(""));

        WIDGETS = Lists.newArrayList(
                WidgetConfig.of(this.leftSlot, 0,0).upLeft(20, 20),
                WidgetConfig.of(this.operationBtn, 60, 20).upLeft(56, 20)
                        .withText(this.operation.toTextComponent())
                        .withFont(this.font)
                        .withAction(this::operationBtnAction),
                WidgetConfig.of(this.rightSlot, 0,0).upLeft(20, 20),
                WidgetConfig.of(this.outputSLot, 0,0).upLeft(164, 54),
                WidgetConfig.of(this.saveBtn, 40, 20).upLeft(60, 100)
                        .withText(this.saveText)
                        .withFont(this.font)
                        .withAction(this::saveBtnAction));
        setAbsoluteXY(WIDGETS);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    protected void init() {
        super.init();
        if (this.minecraft == null) return;
        if (this.minecraft.player == null) return;

        this.setAbsoluteXY(WIDGETS);
    }

    private void typeBtnAction(Button button) {

    }
    private void operationBtnAction(Button button) {
        this.operation = EnumUtil.switchEnum(BinaryOperation.class, this.operation);
        button.setMessage(EnumUtil.moveTo(BinaryOperation.class, this.operation, -1).toTextComponent());
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
        ItemStack itemStack = new ItemStack(ItemRegistry.BINARY_OPERATION_BUILDER.get());
        itemStack.setTag(this.optData);
        this.minecraft.player.addItemStackToInventory(itemStack);
    }

    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        // this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        if (this.minecraft != null) {
            this.renderAbsoluteXY(WIDGETS, matrixStack, mouseX, mouseY, partialTicks);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        if (this.minecraft == null) return;
        this.minecraft.getTextureManager().bindTexture(TEXTURE);

        int left = this.guiLeft;
        int top = this.guiTop;
        this.blit(matrixStack, left, top, 0, 0, 256, 256);
    }
}
