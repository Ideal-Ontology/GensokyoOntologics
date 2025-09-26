package github.thelawf.gensokyoontology.client.gui.screen.script;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.api.client.layout.WidgetConfig;
import github.thelawf.gensokyoontology.client.gui.screen.widget.BlankWidget;
import github.thelawf.gensokyoontology.common.container.script.OneSlotContainer;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.CMergeScriptPacket;
import github.thelawf.gensokyoontology.common.util.EnumUtil;
import github.thelawf.gensokyoontology.common.nbt.GSKONBTUtil;
import github.thelawf.gensokyoontology.common.nbt.script.ConstPreset;
import github.thelawf.gensokyoontology.common.nbt.script.ConstType;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.List;

// "name text": xy: 10, 30
// "value text": xy: 10, 62
// [preset] 25, 26 [type] 115, 26
// [name input] xy: 50, 60; wh: 90, 20
// [value input] xy: 50, 90; wh: 90, 20
// [save btn] xy: 50, 88; wh: 60, 20
// [player inv] xy: 32, 130
@OnlyIn(Dist.CLIENT)
public class ConstBuilderScreen extends OneSlotContainerScreen {
    private Button constTypeBtn;
    private Button presetBtn;
    private ConstPreset constPreset;
    private ConstType constType;
    private TextFieldWidget nameInput;
    private TextFieldWidget valueInput;
    private final CompoundNBT constData = new CompoundNBT();
    public static final ResourceLocation TEXTURE = GSKOUtil.withRL("textures/gui/one_slot_screen_const.png");
    private final WidgetConfig NAME_LABEL = WidgetConfig.of(new BlankWidget(0,0,0,0, withText("null")),0,0).isText(true);
    private final WidgetConfig VALUE_LABEL = WidgetConfig.of(new BlankWidget(0,0,0,0, withText("null")),0,0).isText(true);
    private final ITextComponent defaultName = GSKOUtil.translate("gui.",".default.set_name");
    private final ITextComponent defaultValue = GSKOUtil.translate("gui.",".default.set_value");
    private final ITextComponent presetDefault = GSKOUtil.translate("gui.",".const_builder.button.preset.none");
    private final ITextComponent intTypeText = GSKOUtil.translate("gui.",".const_builder.button.constType.int");
    private final ITextComponent valueText = GSKOUtil.translate("gui.", ".const_builder.tip.valueInput");

    public List<WidgetConfig> WIDGETS;

    // GensokyoOntology.translate("screen.",".const_builder.title")
    public ConstBuilderScreen(OneSlotContainer container, PlayerInventory playerInventory, ITextComponent titleIn) {
        super(container, playerInventory, titleIn);
        this.titleX = 6;
        this.titleY = 6;
        this.playerInventoryTitleX = 30;
        this.playerInventoryTitleY = 114;
        this.xSize = 223;
        this.ySize = 223;
        this.constPreset = ConstPreset.NONE;
        this.constType = ConstType.INT;

        this.stack = playerInventory.player.getHeldItemMainhand();
    }

    @Override
    public void tick() {
        super.tick();
        this.nameInput.tick();
        this.valueInput.tick();
    }

    @Override
    protected void init() {
        super.init();
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

        WIDGETS = Lists.newArrayList(
                NAME_LABEL.setXY(10, 62)
                        .withFont(this.font)
                        .withText(this.fieldName),
                VALUE_LABEL.setXY(10, 92)
                        .withFont(this.font)
                        .withText(this.valueText),
                WidgetConfig.of(this.nameInput, 100, 20).setXY(40, 60)
                        .withFont(this.font)
                        .withText(this.constPreset.toTextComponent()),
                WidgetConfig.of(this.valueInput, 100, 20).setXY(40, 90)
                        .withFont(this.font)
                        .withText(this.constPreset.toTextComponent()),
                WidgetConfig.of(this.presetBtn, 90, 20).setXY(25, 25)
                        .withFont(this.font)
                        .withText(this.constPreset.toTextComponent())
                        .withAction(this::presetBtnAction),
                WidgetConfig.of(this.constTypeBtn, 90, 20).setXY(115, 25)
                        .withFont(this.font)
                        .withText(this.constType.toTextComponent())
                        .withAction(this::constTypeBtnAction),
                WidgetConfig.of(this.saveBtn, 60, 20).setXY(150, 90)
                        .withFont(this.font)
                        .withText(this.saveText)
                        .withAction(this::saveBtnAction));

        setRelativeToParent(WIDGETS, this.guiLeft, this.guiTop);

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
            GSKONetworking.CHANNEL.sendToServer(new CMergeScriptPacket(this.constData));
        }
    }
    private void constTypeBtnAction(Button button) {
        this.constType = EnumUtil.switchEnum(ConstType.class, this.constType);
        button.setMessage(this.constType.toTextComponent());
        this.nameInput.setText(this.constType.getKey());
        this.valueInput.setText("");
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(@NotNull MatrixStack matrixStack, float partialTicks, int x, int y) {
        if (this.minecraft == null) return;
        if (this.minecraft.player == null) return;
        this.minecraft.getTextureManager().bindTexture(TEXTURE);
        this.blit(matrixStack, this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        if (this.minecraft != null) {
            this.renderRelativeToParent(WIDGETS, matrixStack, mouseX, mouseY, this.guiLeft, this.guiTop, partialTicks);
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

}
