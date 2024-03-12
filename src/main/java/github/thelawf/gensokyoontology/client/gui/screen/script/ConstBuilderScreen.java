package github.thelawf.gensokyoontology.client.gui.screen.script;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.util.EnumUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class ConstBuilderScreen extends ScriptBuilderScreen {

    private Button constTypeBtn;
    private Button presetBtn;
    private ConstPreset constPreset;
    private ConstType constType;
    private TextFieldWidget nameInput;
    private TextFieldWidget valueInput;
    private final CompoundNBT numberValue = new CompoundNBT();

    private ITextComponent saveText = GensokyoOntology.withTranslation("gui.", ".script.button.save");
    private ITextComponent presetCustom = GensokyoOntology.withTranslation("gui.",".button.preset.custom");
    private ITextComponent presetDefault = GensokyoOntology.withTranslation("gui.",".const_builder.button.preset.default");
    private ITextComponent intTypeText = GensokyoOntology.withTranslation("gui.",".const_builder.button.constType.int");
    private ITextComponent longTypeText = GensokyoOntology.withTranslation("gui.",".const_builder.button.constType.long");
    private ITextComponent floatTypeText = GensokyoOntology.withTranslation("gui.",".const_builder.button.constType.float");
    private ITextComponent doubleTypeText = GensokyoOntology.withTranslation("gui.",".const_builder.button.constType.double");
    private ITextComponent nameText = GensokyoOntology.withTranslation("gui.", ".const_builder.tip.nameInput");
    private ITextComponent valueText = GensokyoOntology.withTranslation("gui.", ".const_builder.tip.valueInput");
    // GensokyoOntology.withTranslation("screen.",".const_builder.title")
    public ConstBuilderScreen(ITextComponent titleIn, ItemStack stack) {
        super(titleIn, stack);
        this.stack = stack;
        this.constPreset = ConstPreset.NONE;
        this.constType = ConstType.INT;
    }

    @Override
    public void tick() {
        super.tick();
        this.nameInput.tick();
        this.valueInput.tick();
    }

    @Override
    protected void init() {
        if (this.minecraft == null) return;
        if (this.constPreset == ConstPreset.NONE) {
            this.constTypeBtn = new Button(200, 60, 16, 16, this.intTypeText, (button) -> {
                this.constType = EnumUtil.switchEnum(ConstType.class, this.constType);
            });
        }
        this.presetBtn = new Button(0, 0, 100, 16, this.presetDefault, (button) -> {
            this.insertValue();
            this.constPreset = EnumUtil.switchEnum(ConstPreset.class, this.constPreset);
            this.children.remove(this.constTypeBtn);
        });

        this.nameInput = new TextFieldWidget(this.minecraft.fontRenderer, 30, 30, 100, 16, new StringTextComponent(""));
        this.valueInput = new TextFieldWidget(this.minecraft.fontRenderer, 160, 30, 100, 16, new StringTextComponent(""));
        this.children.add(nameInput);
        this.children.add(valueInput);

        this.saveBtn = new Button(0, 200, 16, 16, this.saveText, (button) -> {
            if (this.checkPresetForSave()) this.stack.setTag(this.numberValue);
        });
    }

    @Override
    public void onClose() {
        super.onClose();
        // if (this.checkPresetForSave()) this.stack.setTag(this.numberValue);
    }

    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);

        if (this.minecraft != null) {
            drawString(matrixStack, this.minecraft.fontRenderer, this.nameText, 30, 0, 16777215);
            this.valueInput.render(matrixStack, mouseX, mouseY, partialTicks);
            this.nameInput.render(matrixStack, mouseX, mouseY, partialTicks);

            this.saveBtn.render(matrixStack, mouseX, mouseY, partialTicks);
            this.presetBtn.render(matrixStack, mouseX, mouseY, partialTicks);
        }

    }

    private void insertValue() {
        this.checkPresetForInsert();
    }

    private void checkPresetForInsert() {
        switch (this.constPreset) {
            case NONE:
                break;
            case TWO_PI:
                this.valueInput.setText(String.valueOf(Math.PI * 2));
                break;
            case PI:
                this.valueInput.setText(String.valueOf(Math.PI));
                break;
            case E:
                this.valueInput.setText(String.valueOf(Math.E));
                break;
        }
    }

    private boolean checkPresetForSave() {
        if (this.nameInput.getText().equals("") || this.valueInput.getText().equals("")) return false;
        switch (this.constPreset) {
            case NONE:
                tryParse();
                return true;
            case TWO_PI:
            case PI:
            case E:
                this.numberValue.putDouble(this.constType.key, Double.parseDouble(this.valueInput.getText()));
                return true;
        }
        return true;
    }

    private void tryParse() {
        switch (this.constType) {
            case INT:
                this.numberValue.putInt(this.constType.key, Integer.parseInt(this.valueInput.getText()));
                break;
            case LONG:
                this.numberValue.putLong(this.constType.key, Long.parseLong(this.valueInput.getText()));
                break;
            case FLOAT:
                this.numberValue.putFloat(this.constType.key, Float.parseFloat(this.valueInput.getText()));
                break;
            case DOUBLE:
                this.numberValue.putDouble(this.constType.key, Double.parseDouble(this.valueInput.getText()));
                break;
        }
    }

    public ItemStack getProcessedStack() {
        return this.stack;
    }
}
