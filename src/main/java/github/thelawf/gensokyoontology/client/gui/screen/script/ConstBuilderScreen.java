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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
    private final ITextComponent defaultName = GensokyoOntology.withTranslation("gui.",".default.set_name");
    private final ITextComponent defaultValue = GensokyoOntology.withTranslation("gui.",".default.set_value");
    private final ITextComponent presetDefault = GensokyoOntology.withTranslation("gui.",".const_builder.button.preset.none");
    private final ITextComponent intTypeText = GensokyoOntology.withTranslation("gui.",".const_builder.button.constType.int");
    private final ITextComponent longTypeText = GensokyoOntology.withTranslation("gui.",".const_builder.button.constType.long");
    private final ITextComponent floatTypeText = GensokyoOntology.withTranslation("gui.",".const_builder.button.constType.float");
    private final ITextComponent doubleTypeText = GensokyoOntology.withTranslation("gui.",".const_builder.button.constType.double");
    private final ITextComponent nameText = GensokyoOntology.withTranslation("gui.", ".const_builder.tip.nameInput");
    private final ITextComponent valueText = GensokyoOntology.withTranslation("gui.", ".const_builder.tip.valueInput");

    public List<WidgetConfig> NAME_LINE;
    public List<WidgetConfig> VALUE_LINE;
    public List<WidgetConfig> BUTTONS;

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
            this.insertNameAndValue();
            this.constPreset = EnumUtil.switchEnum(ConstPreset.class, this.constPreset);
            this.children.remove(this.constTypeBtn);
        });

        this.nameInput = new TextFieldWidget(this.minecraft.fontRenderer, 30, 30, 100, 20, this.defaultName);
        this.valueInput = new TextFieldWidget(this.minecraft.fontRenderer, 160, 30, 100, 20, this.defaultValue);
        this.saveBtn = new Button(0, 200, 20, 20, this.saveText, this::saveBtnAction);

        NAME_LINE = Lists.newArrayList(
                WidgetConfig.of(this.interval, 0, 0).isText(true).upInterval(50).leftInterval(20)
                        .withFont(this.minecraft.fontRenderer)
                        .withText(this.nameText),
                WidgetConfig.of(this.nameInput, 120, 20).leftInterval(10)
                        .withFont(this.minecraft.fontRenderer)
                        .withText(this.constPreset.toTextComponent()));

        VALUE_LINE = Lists.newArrayList(
                WidgetConfig.of(this.interval, 0, 0).isText(true).upInterval(80).leftInterval(20)
                        .withFont(this.minecraft.fontRenderer)
                        .withText(this.valueText),
                WidgetConfig.of(this.valueInput, 120, 20).leftInterval(10)
                        .withFont(this.minecraft.fontRenderer)
                        .withText(this.constPreset.toTextComponent()));

        BUTTONS = Lists.newArrayList(
                WidgetConfig.of(this.presetBtn, 100, 20).upInterval(20).leftInterval(20)
                        .withFont(this.minecraft.fontRenderer)
                        .withText(this.constPreset.toTextComponent())
                        .withAction(this::presetBtnAction),
                WidgetConfig.of(this.constTypeBtn, 100, 20).leftInterval(130)
                        .withFont(this.minecraft.fontRenderer)
                        .withText(this.constType.toTextComponent())
                        .withAction(this::constTypeBtnAction),
                WidgetConfig.of(this.saveBtn, 40, 20).upInterval(120).leftInterval(20)
                        .withFont(this.minecraft.fontRenderer)
                        .withText(this.saveText)
                        .withAction(this::saveBtnAction));

        setCenteredWidgets(NAME_LINE);
        setCenteredWidgets(VALUE_LINE);
        setCenteredWidgets(BUTTONS);

        if (this.stack.getTag() != null) {
            String key;
            AtomicReference<String> stringRef = new AtomicReference<>("");
            this.stack.getTag().keySet().stream().findFirst().ifPresent(stringRef::set);
            key = stringRef.get();

            if (key.equals("none")) {
                this.nameInput.setText(key);
                this.valueInput.setText("NONE");
            }
            else if (key.equals("double")) {
                this.nameInput.setText(key);
                this.valueInput.setText(String.valueOf(this.stack.getTag().getDouble("double")));
            }
        }
    }

    private void presetBtnAction(Button button) {
        this.insertNameAndValue();
        this.constPreset = EnumUtil.switchEnum(ConstPreset.class, this.constPreset);
        button.setMessage(EnumUtil.moveTo(ConstPreset.class, this.constPreset, -1).toTextComponent());
        if (this.constPreset == ConstPreset.NONE) this.constType = ConstType.STRING;
        else this.constType = ConstType.DOUBLE;
        this.children.remove(this.constTypeBtn);
    }

    private void saveBtnAction(Button button) {
        if (this.checkPresetForSave()) {
            this.stack.setTag(this.numberValue);
            ItemStack itemStack = this.stack.copy();
            if (this.minecraft != null && this.minecraft.player != null) {
                this.stack.shrink(1);
                this.minecraft.player.inventory.addItemStackToInventory(itemStack);
            }
        }
        this.closeScreen();
    }
    private void constTypeBtnAction(Button button) {
        this.constType = EnumUtil.switchEnum(ConstType.class, this.constType);
        button.setMessage(this.constType.toTextComponent());
        this.nameInput.setText(this.constType.getKey());
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
        }

    }

    private void insertNameAndValue() {
        this.checkPresetForInsert();
    }

    private void checkPresetForInsert() {
        switch (this.constPreset) {
            case NONE:
                this.nameInput.setText("undefined");
                this.valueInput.setText("NONE");
                break;
            case TWO_PI:
                this.nameInput.setText("double");
                this.valueInput.setText(String.valueOf(Math.PI * 2));
                break;
            case PI:
                this.nameInput.setText("double");
                this.valueInput.setText(String.valueOf(Math.PI));
                break;
            case E:
                this.nameInput.setText("double");
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
