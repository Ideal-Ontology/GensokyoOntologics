package github.thelawf.gensokyoontology.client.gui.screen.script;

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

@OnlyIn(Dist.CLIENT)
public class ConstBuilderScreen extends ScriptBuilderScreen {

    private Button constTypeBtn;
    private Button presetBtn;
    private ConstPreset constPreset;
    private ConstType constType;
    private TextFieldWidget nameInput;
    private TextFieldWidget valueInput;
    private final CompoundNBT numberValue = new CompoundNBT();
    private ITextComponent saveText = GensokyoOntology.withTranslation("gui.", ".button.save");
    private ITextComponent presetCustom = GensokyoOntology.withTranslation("gui.",".button.preset.custom");
    private ITextComponent presetDefault = GensokyoOntology.withTranslation("gui.",".button.preset.default");
    private ITextComponent intTypeText = GensokyoOntology.withTranslation("gui.",".button.constType.int");
    private ITextComponent longTypeText = GensokyoOntology.withTranslation("gui.",".button.constType.long");
    private ITextComponent floatTypeText = GensokyoOntology.withTranslation("gui.",".button.constType.float");
    private ITextComponent doubleTypeText = GensokyoOntology.withTranslation("gui.",".button.constType.double");
    // GensokyoOntology.withTranslation("screen.",".const_builder.title")
    public ConstBuilderScreen(ITextComponent titleIn, ItemStack stack) {
        super(titleIn, stack);
        this.stack = stack;
        this.constPreset = ConstPreset.NONE;
        this.constType = ConstType.INT;
    }

    @Override
    protected void init() {
        Minecraft mc = Minecraft.getInstance();
        if (this.constPreset == ConstPreset.NONE) {
            this.constTypeBtn = new Button(200, 60, 30, 30, this.intTypeText, (button) -> {
                this.constType = EnumUtil.switchEnum(ConstType.class, this.constType);
            });
        }
        this.presetBtn = new Button(0, 0, 100, 30, this.presetDefault, (button) -> {
            this.insertValue();
            this.constPreset = EnumUtil.switchEnum(ConstPreset.class, this.constPreset);
            this.children.remove(this.constTypeBtn);
        });

        this.nameInput = new TextFieldWidget(mc.fontRenderer, 0, 60, 100, 30, new StringTextComponent(""));
        this.valueInput = new TextFieldWidget(mc.fontRenderer, 120, 60, 100, 30, new StringTextComponent(""));
        this.children.add(nameInput);
        this.children.add(valueInput);

        this.saveBtn = new Button(0, 200, 30, 30, this.saveText, (button) -> {
            if (this.checkPresetForSave()) this.stack.setTag(this.numberValue);
        });
    }

    @Override
    public void onClose() {
        super.onClose();
        if (this.checkPresetForSave()) this.stack.setTag(this.numberValue);
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
