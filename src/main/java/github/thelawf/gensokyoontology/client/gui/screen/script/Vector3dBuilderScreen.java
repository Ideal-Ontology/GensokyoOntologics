package github.thelawf.gensokyoontology.client.gui.screen.script;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.client.layout.WidgetConfig;
import github.thelawf.gensokyoontology.client.gui.screen.BlankWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@OnlyIn(value = Dist.CLIENT, _interface = ScriptBuilderScreen.class)
public class Vector3dBuilderScreen extends ScriptBuilderScreen {
    private final CompoundNBT vector3dValue = new CompoundNBT();
    private final CompoundNBT vector3dVariable = new CompoundNBT();
    private TextFieldWidget nameInput;
    private TextFieldWidget xInput;
    private TextFieldWidget yInput;
    private TextFieldWidget zInput;
    private final WidgetConfig TEXT_LABEL1 = WidgetConfig.of(new BlankWidget(0,0,0,0, ofText("null")),0,0).isText(true);
    private final WidgetConfig TEXT_LABEL2 = WidgetConfig.of(new BlankWidget(0,0,0,0, ofText("null")),0,0).isText(true);
    private final WidgetConfig TEXT_LABEL3 = WidgetConfig.of(new BlankWidget(0,0,0,0, ofText("null")),0,0).isText(true);
    private final WidgetConfig TEXT_LABEL4 = WidgetConfig.of(new BlankWidget(0,0,0,0, ofText("null")),0,0).isText(true);


    private final ITextComponent tipName = GensokyoOntology.withTranslation("gui.",".vector3d.builder.name");
    private List<WidgetConfig> WIDGETS;
    public Vector3dBuilderScreen(ITextComponent titleIn, ItemStack stack) {
        super(titleIn, stack);
    }

    @Override
    public void tick() {
        super.tick();
        this.nameInput.tick();
        this.xInput.tick();
        this.yInput.tick();
        this.zInput.tick();
    }

    @Override
    protected void init() {
        if (this.minecraft == null) return;
        if (this.minecraft.player == null) return;

        this.nameInput = new TextFieldWidget(this.minecraft.fontRenderer, 0,0,0,0, this.title);
        this.xInput = new TextFieldWidget(this.minecraft.fontRenderer, 0, 50, 100, 30, new StringTextComponent(""));
        this.yInput = new TextFieldWidget(this.minecraft.fontRenderer, 60, 50, 100, 30, new StringTextComponent(""));
        this.zInput = new TextFieldWidget(this.minecraft.fontRenderer, 120, 50, 100, 30, new StringTextComponent(""));

        this.saveBtn = new Button(0, 200, 30, 30, this.saveText, (button) -> {
            if (this.checkPresetForSave()) this.stack.setTag(this.vector3dValue);
        });

        WIDGETS = Lists.newArrayList(
                TEXT_LABEL1.upLeft(50, 20).withText(this.fieldName).withFont(this.font),
                WidgetConfig.of(this.nameInput, 100, 20).upLeft(50, 60)
                        .withFont(this.font)
                        .withText(ofText("input here")),

                TEXT_LABEL2.upLeft(90, 20).withText(ofText("X: ")).withFont(this.font),
                WidgetConfig.of(this.xInput, 100, 20).upLeft(85, 60)
                        .withFont(this.font)
                        .withText(ofText("0")),

                TEXT_LABEL3.upLeft(120, 20).withText(ofText("Y: ")).withFont(this.font),
                WidgetConfig.of(this.yInput, 100, 20).upLeft(115, 60)
                        .withFont(this.font)
                        .withText(ofText("0")),

                TEXT_LABEL4.upLeft(150, 20).withText(ofText("Z: ")).withFont(this.font),
                WidgetConfig.of(this.zInput, 100, 20).upLeft(145, 60)
                        .withFont(this.font)
                        .withText(ofText("0")),

                WidgetConfig.of(this.saveBtn, 40, 20).upLeft(190, 20)
                        .withText(this.saveText)
                        .withFont(this.font)
                        .withAction((button) -> {}));

        this.setAbsoluteXY(WIDGETS);

    }

    private boolean checkPresetForSave() {
        if (this.nameInput.getText().equals("") || this.xInput.getText().equals("") || this.yInput.getText().equals("") || this.zInput.getText().equals("")) return false;

        this.vector3dValue.putDouble("x", Double.parseDouble(this.xInput.getText()));
        this.vector3dValue.putDouble("y", Double.parseDouble(this.yInput.getText()));
        this.vector3dValue.putDouble("z", Double.parseDouble(this.zInput.getText()));

        this.vector3dVariable.put(this.nameInput.getText(), this.vector3dValue);
        this.stack.setTag(this.vector3dVariable);
        return true;
    }


    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        // this.renderBackground(matrixStack);
        // super.render(matrixStack, mouseX, mouseY, partialTicks);
        if (this.minecraft != null) {
            this.renderAbsoluteXY(WIDGETS, matrixStack, mouseX, mouseY, partialTicks);
        }
        // super.render(matrixStack, mouseX, mouseY, partialTicks);
        // drawCenteredText(WIDGETS, matrixStack, mouseX, mouseY, partialTicks);
        // renderWidgets(WIDGETS, matrixStack, mouseX, mouseY, partialTicks);
        // if (this.minecraft != null) {
        //     drawCenteredText(WIDGETS, matrixStack, mouseX, mouseY, partialTicks);
        //     this.nameInput.render(matrixStack, mouseX, mouseY, partialTicks);
        //     this.xInput.render(matrixStack, mouseX, mouseY, partialTicks);
        //     this.yInput.render(matrixStack, mouseX, mouseY, partialTicks);
        //     this.zInput.render(matrixStack, mouseX, mouseY, partialTicks);
        // }
    }

    public CompoundNBT getVector3dNBT() {
        return this.vector3dValue;
    }

    public Vector3d getAsVector3d() {
        return new Vector3d(this.vector3dValue.getDouble("x"), this.vector3dValue.getDouble("y"), this.vector3dValue.getDouble("z"));
    }
}
