package github.thelawf.gensokyoontology.client.gui.screen.script;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.client.layout.WidgetConfig;
import github.thelawf.gensokyoontology.common.container.script.ScriptBuilderContainer;
import github.thelawf.gensokyoontology.client.gui.screen.widget.BlankWidget;
import github.thelawf.gensokyoontology.client.gui.screen.widget.SlotWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

// player inventory: 12, 80
// self slot: 30, 16
// ref name input: 30, 55
public class AssignInstanceScreen extends ScriptContainerScreen {
    public static final String TYPE = "assignment";
    public static final ResourceLocation TEXTURE = GensokyoOntology.withRL("textures/gui/assign_screen.png");
    private final CompoundNBT assignData = new CompoundNBT();
    private TextFieldWidget nameInput;
    private SlotWidget slotWidget;
    private final WidgetConfig NAME_LABEL = WidgetConfig.of(new BlankWidget(0,0,0,0, withText("null")),0,0).isText(true);
    private List<WidgetConfig> WIDGETS;

    public AssignInstanceScreen(ScriptBuilderContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected void init() {
        if (this.minecraft == null) return;
        if (this.minecraft.player == null) return;

        this.nameInput = new TextFieldWidget(this.font, 0,0,0,0, this.title);
        this.slotWidget = new SlotWidget(0,0,0,0, withText("slot"));
        this.saveBtn = new Button(0, 0, 30, 30, this.saveText, (button) -> {});

        WIDGETS = Lists.newArrayList(
                NAME_LABEL.upLeft(20, 20).withText(this.fieldName).withFont(this.font),
                WidgetConfig.of(this.nameInput, 100, 20).upLeft(20, 60)
                        .withFont(this.font)
                        .withText(withText("name")),
                WidgetConfig.of(this.saveBtn, 60, 20).upLeft(50, 20)
                        .withFont(this.font)
                        .withText(this.saveText)
                        .withAction((b) -> {}),
                WidgetConfig.of(this.slotWidget, 0,0).upLeft(20, 180));

        setAbsoluteXY(WIDGETS);
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {

    }

}
