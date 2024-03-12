package github.thelawf.gensokyoontology.client.gui.screen.script;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import org.jetbrains.annotations.NotNull;

public class Vector3dBuilderScreen extends ScriptBuilderScreen{
    private CompoundNBT vector3dValue = new CompoundNBT();
    private CompoundNBT variableName = new CompoundNBT();
    private TextFieldWidget nameInput;
    private TextFieldWidget xInput;
    private TextFieldWidget yInput;
    private TextFieldWidget zInput;
    private final ITextComponent tipName = GensokyoOntology.withTranslation("gui.",".vector3d.builder.name");
    private final StringTextComponent tipXValue = new StringTextComponent("X: ");
    private final StringTextComponent tipYValue = new StringTextComponent("Y: ");
    private final StringTextComponent tipZValue = new StringTextComponent("Z: ");
    private final ITextComponent saveText = GensokyoOntology.withTranslation("gui.", ".button.save");
    protected Vector3dBuilderScreen(ITextComponent titleIn, ItemStack stack) {
        super(titleIn, stack);
        this.vector3dValue.putDouble("x", 0);
        this.vector3dValue.putDouble("y", 0);
        this.vector3dValue.putDouble("z", 0);
    }

    @Override
    protected void init() {
        super.init();
        Minecraft mc = Minecraft.getInstance();
        this.xInput = new TextFieldWidget(mc.fontRenderer, 0, 50, 100, 30, new StringTextComponent(""));
        this.yInput = new TextFieldWidget(mc.fontRenderer, 60, 50, 100, 30, new StringTextComponent(""));
        this.zInput = new TextFieldWidget(mc.fontRenderer, 120, 50, 100, 30, new StringTextComponent(""));
        this.children.add(this.xInput);
        this.children.add(this.yInput);
        this.children.add(this.zInput);

        this.saveBtn = new Button(0, 200, 30, 30, this.saveText, (button) -> {
            if (this.checkPresetForSave()) this.stack.setTag(this.vector3dValue);
        });
    }

    private boolean checkPresetForSave() {
        if (this.nameInput.getText().equals("") || this.xInput.getText().equals("") || this.yInput.getText().equals("") || this.zInput.getText().equals("")) return false;

        this.vector3dValue = new CompoundNBT();
        this.vector3dValue.putDouble("x", Double.parseDouble(this.xInput.getText()));
        this.vector3dValue.putDouble("y", Double.parseDouble(this.yInput.getText()));
        this.vector3dValue.putDouble("z", Double.parseDouble(this.zInput.getText()));

        this.variableName.put(this.nameInput.getText(), this.vector3dValue);
        return true;
    }

    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        Minecraft mc = Minecraft.getInstance();
        drawString(matrixStack, mc.fontRenderer, this.tipXValue, 0, 0, 16777215);
        drawString(matrixStack, mc.fontRenderer, this.tipYValue, 0, 60, 16777215);
        drawString(matrixStack, mc.fontRenderer, this.tipZValue, 0, 120, 16777215);
    }

    public CompoundNBT getVector3dNBT() {
        return this.vector3dValue;
    }

    public Vector3d getAsVector3d() {
        return new Vector3d(this.vector3dValue.getDouble("x"), this.vector3dValue.getDouble("y"), this.vector3dValue.getDouble("z"));
    }
}
