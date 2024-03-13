package github.thelawf.gensokyoontology.client.gui.screen.script;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.client.layout.WidgetConfig;
import github.thelawf.gensokyoontology.client.gui.screen.IntervalWidget;
import github.thelawf.gensokyoontology.common.util.EnumUtil;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ConstBuilderScreen extends ScriptBuilderScreen {

    private Button constTypeBtn;
    private Button presetBtn;
    private ConstPreset constPreset;
    private ConstType constType;
    private TextFieldWidget nameInput;
    private TextFieldWidget valueInput;
    private IntervalWidget interval;
    private final CompoundNBT numberValue = new CompoundNBT();
    private ITextComponent presetCustom = GensokyoOntology.withTranslation("gui.",".button.preset.custom");
    private ITextComponent presetDefault = GensokyoOntology.withTranslation("gui.",".const_builder.button.preset.default");
    private ITextComponent intTypeText = GensokyoOntology.withTranslation("gui.",".const_builder.button.constType.int");
    private ITextComponent longTypeText = GensokyoOntology.withTranslation("gui.",".const_builder.button.constType.long");
    private ITextComponent floatTypeText = GensokyoOntology.withTranslation("gui.",".const_builder.button.constType.float");
    private ITextComponent doubleTypeText = GensokyoOntology.withTranslation("gui.",".const_builder.button.constType.double");
    private ITextComponent nameText = GensokyoOntology.withTranslation("gui.", ".const_builder.tip.nameInput");
    private ITextComponent valueText = GensokyoOntology.withTranslation("gui.", ".const_builder.tip.valueInput");

    public List<WidgetConfig> NAME_LINE;
    public List<WidgetConfig> VALUE_LINE;

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
        if (this.minecraft.player == null) return;
        if (this.constPreset == ConstPreset.NONE) {

            this.constTypeBtn = new Button(200, 60, 20, 20, this.intTypeText, (button) -> {
                this.constType = EnumUtil.switchEnum(ConstType.class, this.constType);
            });
        }
        this.presetBtn = new Button(0, 0, 100, 20, this.presetDefault, (button) -> {
            this.insertValue();
            this.constPreset = EnumUtil.switchEnum(ConstPreset.class, this.constPreset);
            this.children.remove(this.constTypeBtn);
        });

        this.nameInput = new TextFieldWidget(this.minecraft.fontRenderer, 30, 30, 100, 20, new StringTextComponent(""));
        this.valueInput = new TextFieldWidget(this.minecraft.fontRenderer, 160, 30, 100, 20, new StringTextComponent(""));

        NAME_LINE = Lists.newArrayList(
                WidgetConfig.of(this.interval, 0, 0).isText(true).upInterval(30).leftInterval(20)
                        .withFont(this.minecraft.fontRenderer)
                        .withText(this.nameText),
                WidgetConfig.of(this.nameInput, 100, 20).leftInterval(10)
                        .withFont(this.minecraft.fontRenderer)
                        .withText(new TranslationTextComponent("")));

        VALUE_LINE = Lists.newArrayList(
                WidgetConfig.of(this.interval, 0, 0).isText(true).upInterval(60).leftInterval(20)
                        .withFont(this.minecraft.fontRenderer)
                        .withText(this.valueText),
                WidgetConfig.of(this.valueInput, 100, 20).leftInterval(10)
                        .withFont(this.minecraft.fontRenderer)
                        .withText(new TranslationTextComponent("")));

        setCenteredWidgets(NAME_LINE);
        setCenteredWidgets(VALUE_LINE);

        this.saveBtn = new Button(0, 200, 20, 20, this.saveText, (button) -> {
            if (this.checkPresetForSave()) {
                this.stack.setTag(this.numberValue);
                ItemStack itemStack = this.stack.copy();
                this.stack.shrink(1);
                this.minecraft.player.inventory.addItemStackToInventory(itemStack);
            }
        });

        this.addButton(this.presetBtn);
        this.addButton(this.saveBtn);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
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
            // drawString(matrixStack, this.minecraft.fontRenderer, this.nameText, 0, 30, 16777215);
            drawCenteredText(NAME_LINE, matrixStack, mouseX, mouseY, partialTicks);
            drawCenteredText(VALUE_LINE, matrixStack, mouseX, mouseY, partialTicks);

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
