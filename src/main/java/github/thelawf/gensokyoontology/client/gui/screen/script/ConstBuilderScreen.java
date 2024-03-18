package github.thelawf.gensokyoontology.client.gui.screen.script;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.client.layout.WidgetConfig;
import github.thelawf.gensokyoontology.common.util.EnumUtil;
import github.thelawf.gensokyoontology.common.nbt.GSKONBTUtil;
import github.thelawf.gensokyoontology.common.nbt.script.ConstPreset;
import github.thelawf.gensokyoontology.common.nbt.script.ConstType;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.util.text.ITextComponent;
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
    private final CompoundNBT constData = new CompoundNBT();
    private final ITextComponent defaultName = GensokyoOntology.withTranslation("gui.",".default.set_name");
    private final ITextComponent defaultValue = GensokyoOntology.withTranslation("gui.",".default.set_value");
    private final ITextComponent presetDefault = GensokyoOntology.withTranslation("gui.",".const_builder.button.preset.none");
    private final ITextComponent intTypeText = GensokyoOntology.withTranslation("gui.",".const_builder.button.constType.int");
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
                WidgetConfig.of(this.blank, 0, 0).isText(true).upInterval(50).leftInterval(20)
                        .withFont(this.font)
                        .withText(this.fieldName),
                WidgetConfig.of(this.nameInput, 120, 20).leftInterval(10)
                        .withFont(this.font)
                        .withText(this.constPreset.toTextComponent()));

        VALUE_LINE = Lists.newArrayList(
                WidgetConfig.of(this.blank, 0, 0).isText(true).upInterval(80).leftInterval(20)
                        .withFont(this.font)
                        .withText(this.valueText),
                WidgetConfig.of(this.valueInput, 120, 20).leftInterval(10)
                        .withFont(this.font)
                        .withText(this.constPreset.toTextComponent()));

        BUTTONS = Lists.newArrayList(
                WidgetConfig.of(this.presetBtn, 100, 20).upInterval(20).leftInterval(20)
                        .withFont(this.font)
                        .withText(this.constPreset.toTextComponent())
                        .withAction(this::presetBtnAction),
                WidgetConfig.of(this.constTypeBtn, 100, 20).leftInterval(130)
                        .withFont(this.font)
                        .withText(this.constType.toTextComponent())
                        .withAction(this::constTypeBtnAction),
                WidgetConfig.of(this.saveBtn, 40, 20).upInterval(120).leftInterval(20)
                        .withFont(this.font)
                        .withText(this.saveText)
                        .withAction(this::saveBtnAction));

        setCenteredWidgets(NAME_LINE);
        setCenteredWidgets(VALUE_LINE);
        setCenteredWidgets(BUTTONS);

        if (this.stack.getTag() != null) {

            this.saveBtn.active = false;
            this.nameInput.active = false;
            this.valueInput.active = false;

            this.nameInput.setText(this.stack.getTag().getString("name"));
            String type = this.stack.getTag().getString("type");
            CompoundNBT nbt = this.stack.getTag();
            switch (type) {
                case "none":
                default:
                    this.valueInput.setText("NONE");
                    break;
                case "double":
                    this.valueInput.setText(String.valueOf(GSKONBTUtil.getAsNumber(nbt).getDouble()));
                    break;
                case "int":
                    this.valueInput.setText(String.valueOf(GSKONBTUtil.getAsNumber(nbt).getInt()));
                    break;
                case "float":
                    this.valueInput.setText(String.valueOf(GSKONBTUtil.getAsNumber(nbt).getFloat()));
                    break;
                case "long":
                    this.valueInput.setText(String.valueOf(GSKONBTUtil.getAsNumber(nbt).getLong()));
                    break;
                case "string":
                    this.valueInput.setText(GSKONBTUtil.getFromValue(nbt).getString());
                    break;
                case "boolean":
                    if (GSKONBTUtil.getFromValue(nbt) instanceof ByteNBT) {
                        ByteNBT byteNbt = (ByteNBT) GSKONBTUtil.getFromValue(nbt);
                        this.valueInput.setText(byteNbt.getInt() == 0 ? "false" : "true");
                    }
                    this.valueInput.setText("false");
                    break;
            }
        }
    }

    private void presetBtnAction(Button button) {
        this.insertNameAndValue();
        this.constPreset = EnumUtil.switchEnum(ConstPreset.class, this.constPreset);
        button.setMessage(EnumUtil.moveTo(ConstPreset.class, this.constPreset, -1).toTextComponent());
        if (this.constPreset == ConstPreset.NONE) this.constType = ConstType.STRING;
        else this.constType = ConstType.DOUBLE;

        this.constTypeBtn.setMessage(this.constType.toTextComponent());
        this.children.remove(this.constTypeBtn);
    }

    private void saveBtnAction(Button button) {
        if (this.checkPresetForSave()) {
            this.stack.setTag(this.constData);
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
        this.valueInput.setText("");
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
                this.constData.putString("type", this.constType.key);
                this.constData.putString("name", this.nameInput.getText());
                this.constData.put("value", DoubleNBT.valueOf(parseDouble(this.valueInput.getText())));
                return true;
            default:
                this.constData.putString("type", "undefined");
                this.constData.putString("name", this.nameInput.getText());
                this.constData.putString("value", this.valueInput.getText());
        }
        return true;
    }

    private void tryParse() {
        this.constData.putString("type", this.constType.key);
        this.constData.putString("name", this.nameInput.getText());
        switch (this.constType) {
            case INT:
                this.constData.put("value", IntNBT.valueOf(parseInt(this.valueInput.getText())));
                break;
            case LONG:
                this.constData.put("value", LongNBT.valueOf(parseLong(this.valueInput.getText())));
                break;
            case FLOAT:
                this.constData.put("value", FloatNBT.valueOf(parseFloat(this.valueInput.getText())));
                break;
            case DOUBLE:
                this.constData.put("value", DoubleNBT.valueOf(parseDouble(this.valueInput.getText())));
                break;
            case STRING:
                this.constData.put("value", StringNBT.valueOf(this.valueInput.getText()));
                break;
            case BOOLEAN:
                this.constData.put("value", ByteNBT.valueOf(Boolean.parseBoolean(this.valueInput.getText())));
                break;
        }
    }

    private void putIntInfo(String type, String name) {
        this.constData.putString("type", type);
        this.constData.putString("name", name);
    }

    public ItemStack getProcessedStack() {
        return this.stack;
    }
}
