package github.thelawf.gensokyoontology.client.gui.screen.script;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.client.layout.WidgetConfig;
import github.thelawf.gensokyoontology.client.gui.screen.widget.BlankWidget;
import github.thelawf.gensokyoontology.common.util.EnumUtil;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DanmakuBuilderScreen extends ScriptBuilderScreen {
    public static final String TYPE = "entity.gensokyoontology.danmaku_entity";
    private Button danTypeButton;
    private Button colorButton;
    private TextFieldWidget nameInput;
    private DanmakuType danmakuType;
    private DanmakuColor danmakuColor;
    private final ItemStack stack;
    private List<WidgetConfig> CONFIGS;
    private final CompoundNBT danmakuData = new CompoundNBT();
    private final WidgetConfig NAME_LABEL = WidgetConfig.of(new BlankWidget(0,0,0,0, withText("null")),0,0).isText(true);
    private final WidgetConfig TYPE_LABEL = WidgetConfig.of(new BlankWidget(0,0,0,0, withText("null")),0,0).isText(true);
    private final WidgetConfig COLOR_LABEL = WidgetConfig.of(new BlankWidget(0,0,0,0, withText("null")),0,0).isText(true);
    public static final ITextComponent DAN_TYPE_TEXT = GensokyoOntology.withTranslation("gui.", ".danmaku_builder.button.type");
    public static final ITextComponent COLOR_TEXT = GensokyoOntology.withTranslation("gui.", ".danmaku_builder.button.color");
    public static final ITextComponent NAME_TEXT = GensokyoOntology.withTranslation("gui.", ".danmaku_builder.button.name");
    public DanmakuBuilderScreen(ITextComponent titleIn, ItemStack stack) {
        super(titleIn, stack);
        this.danmakuColor = DanmakuColor.RED;
        this.danmakuType = DanmakuType.LARGE_SHOT;
        this.stack = stack;
    }

    @Override
    public void tick() {
        super.tick();
        this.nameInput.tick();
    }

    @Override
    protected void init() {
        super.init();
        this.nameInput = new TextFieldWidget(this.font, 0,0,0,0, withText(""));
        this.danTypeButton = new Button(0, 0, 0, 0, withText(""), b -> {});
        this.colorButton = new Button(0, 0, 0, 0, withText(""), b -> {});
        this.saveBtn = new Button(0,0,0,0, withText(""), b -> {});

        CONFIGS = Lists.newArrayList(
                NAME_LABEL.upLeft(20, 20).withFont(this.font).withText(NAME_TEXT),
                TYPE_LABEL.upLeft(50, 20).withFont(this.font).withText(DAN_TYPE_TEXT),
                COLOR_LABEL.upLeft(80, 20).withFont(this.font).withText(COLOR_TEXT),
                WidgetConfig.of(this.nameInput, 100, 20).upLeft(20, 90).withFont(this.font).withText(withText("")),
                WidgetConfig.of(danTypeButton, 80, 20).upLeft(50, 90)
                        .withFont(this.font)
                        .withText(this.danmakuType.toTextComponent())
                        .withAction(this::danmakuTypeAction),
                WidgetConfig.of(colorButton, 80, 20).upLeft(80, 90)
                        .withFont(this.font)
                        .withText(this.danmakuColor.toTextComponent())
                        .withAction(this::colorAction),
                WidgetConfig.of(this.saveBtn, 60, 20).upLeft(110, 90)
                        .withFont(this.font)
                        .withText(this.saveText)
                        .withAction(this::saveAction));
        this.setAbsoluteXY(CONFIGS);

        if (this.stack.getTag() != null) {
            CompoundNBT tag = this.stack.getTag();
            for (DanmakuType dt : DanmakuType.values()) {
                if (dt.name.equals(tag.getString("danmakuType"))) {
                    this.danmakuType = dt;
                }
            }
            for (DanmakuColor dc : DanmakuColor.values()) {
                if (dc.name().toLowerCase().equals(tag.getString("danmakuType"))) {
                    this.danmakuColor = dc;
                }
            }
            if (tag.contains("name")) {
                this.nameInput.setText(tag.getString("name"));
            }
        }
    }

    private void danmakuTypeAction(Button button) {
        this.danmakuType = EnumUtil.switchEnum(DanmakuType.class, this.danmakuType);
        button.setMessage(this.danmakuType.toTextComponent());
    }

    private void colorAction(Button button) {
        this.danmakuColor = EnumUtil.switchEnum(DanmakuColor.class, this.danmakuColor);
        button.setMessage(this.danmakuColor.toTextComponent());
    }

    private void saveAction(Button button) {
        if (this.minecraft == null) return;
        if (this.minecraft.player == null) return;
        this.danmakuData.putString("type", TYPE);
        this.danmakuData.putString("danmakuType", this.danmakuType.name);
        this.danmakuData.putString("danmakuColor", this.danmakuColor.name().toLowerCase());
        if (!this.nameInput.getText().equals("")) {
            this.danmakuData.putString("name", this.nameInput.getText());
        }

        this.stack.setTag(this.danmakuData);
        ItemStack itemStack = this.stack.copy();

        this.stack.shrink(1);
        this.minecraft.player.inventory.addItemStackToInventory(itemStack);
    }

    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        if (this.minecraft != null) {
            this.renderAbsoluteXY(CONFIGS, matrixStack, mouseX, mouseY, partialTicks);
            this.danTypeButton.setMessage(this.danmakuType.toTextComponent());
            this.colorButton.setMessage(this.danmakuColor.toTextComponent());

            this.danTypeButton.render(matrixStack, mouseX, mouseY, partialTicks);
            this.colorButton.render(matrixStack, mouseX, mouseY, partialTicks);
        }
    }
}
