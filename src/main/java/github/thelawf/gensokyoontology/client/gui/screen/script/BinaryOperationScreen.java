package github.thelawf.gensokyoontology.client.gui.screen.script;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.client.layout.WidgetConfig;
import github.thelawf.gensokyoontology.client.gui.container.script.BinaryOperationContainer;
import github.thelawf.gensokyoontology.client.gui.container.script.ScriptBuilderContainer;
import github.thelawf.gensokyoontology.client.gui.screen.widget.SlotWidget;
import github.thelawf.gensokyoontology.common.nbt.script.BinaryOperation;
import github.thelawf.gensokyoontology.common.nbt.script.OperationType;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

// type button: 56, 0
// operate button: 56, 20
// left text: 20, 46
// right text: 20, 69
// left input: 60, 46
// right input: 60, 69
public class BinaryOperationScreen extends ScriptContainerScreen {
    public static final ResourceLocation TEXTURE = GensokyoOntology.withRL("textures/gui/binary_operation_screen.png");
    private Button typeBtn;
    private Button operationBtn;
    private SlotWidget leftSlot = new SlotWidget(0,0,0,0,ofText("left"));
    private SlotWidget rightSlot = new SlotWidget(0,0,0,0,ofText("right"));
    private SlotWidget outputSLot = new SlotWidget(0,0,0,0,ofText("output"));
    private TextFieldWidget leftInput;
    private TextFieldWidget rightInput;
    private OperationType type;
    private BinaryOperation operation;
    public static final String FIELD_TYPE = "binary_operation";
    private final ITextComponent leftText = GensokyoOntology.withTranslation("gui.",".binary_operation.left.text");
    private final ITextComponent rightText = GensokyoOntology.withTranslation("gui.",".binary_operation.right.text");

    private List<WidgetConfig> WIDGETS;
    public BinaryOperationScreen(ScriptBuilderContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
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

        this.typeBtn = new Button(0,0,0,0, type.toTextComponent(), (b) -> {});
        this.operationBtn = new Button(0,0,0,0, operation.toTextComponent(), (b) -> {});
        this.saveBtn = new Button(0,0,0,0, saveText, (b) -> {});

        WIDGETS = Lists.newArrayList(
                WidgetConfig.of(this.typeBtn, 60, 20).upLeft(0, 56)
                        .withText(this.type.toTextComponent())
                        .withFont(this.font)
                        .withAction(this::typeBtnAction),
                WidgetConfig.of(this.leftSlot, 0,0).upLeft(20, 20),
                WidgetConfig.of(this.operationBtn, 60, 20).upLeft(56, 20)
                        .withText(this.operation.toTextComponent())
                        .withFont(this.font)
                        .withAction(this::operationBtnAction),
                WidgetConfig.of(this.rightSlot, 0,0).upLeft(20, 20),

                WidgetConfig.of(this.leftInput, 100, 20).upLeft(60, 46),
                WidgetConfig.of(this.rightInput, 100, 20).upLeft(60, 69),
                WidgetConfig.of(this.outputSLot, 0,0).upLeft(164, 54),
                WidgetConfig.of(this.saveBtn, 40, 20).upLeft(60, 100)
                        .withText(this.saveText)
                        .withFont(this.font)
                        .withAction(this::saveBtnAction));

        setAbsoluteXY(WIDGETS);
    }

    private void typeBtnAction(Button button) {

    }
    private void operationBtnAction(Button button) {

    }

    private void saveBtnAction(Button button) {
        if (this.minecraft == null) return;
        if (this.minecraft.player == null) return;

        if (!(this.minecraft.player.openContainer instanceof BinaryOperationContainer)) return;
        BinaryOperationContainer container = (BinaryOperationContainer) this.minecraft.player.openContainer;
        // container.operationSlots.setInventorySlotContents(2, new ItemStack());

        this.closeScreen();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {

    }
}
