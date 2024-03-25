package github.thelawf.gensokyoontology.client.gui.screen.script;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.client.layout.WidgetConfig;
import github.thelawf.gensokyoontology.client.gui.screen.widget.BlankWidget;
import github.thelawf.gensokyoontology.common.container.script.OneSlotContainer;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.CMergeScriptPacket;
import github.thelawf.gensokyoontology.common.util.EnumUtil;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuColor;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

// 30, 25
public class DanmakuBuilderScreen extends OneSlotContainerScreen {
    public static final String TYPE = "danmaku";
    private Button danTypeButton;
    private Button colorButton;
    private TextFieldWidget nameInput;
    private DanmakuType danmakuType;
    private DanmakuColor danmakuColor;
    private final ItemStack stack;
    private List<WidgetConfig> CONFIGS;
    private final CompoundNBT danmakuData = new CompoundNBT();
    public static final ResourceLocation TEXTURE = GensokyoOntology.withRL("textures/gui/one_slot_screen.png");
    private final WidgetConfig NAME_LABEL = WidgetConfig.of(new BlankWidget(0,0,0,0, withText("null")),0,0).isText(true);
    private final WidgetConfig TYPE_LABEL = WidgetConfig.of(new BlankWidget(0,0,0,0, withText("null")),0,0).isText(true);
    private final WidgetConfig COLOR_LABEL = WidgetConfig.of(new BlankWidget(0,0,0,0, withText("null")),0,0).isText(true);
    public static final ITextComponent DAN_TYPE_TEXT = GensokyoOntology.withTranslation("gui.", ".danmaku_builder.button.type");
    public static final ITextComponent COLOR_TEXT = GensokyoOntology.withTranslation("gui.", ".danmaku_builder.button.color");
    public static final ITextComponent NAME_TEXT = GensokyoOntology.withTranslation("gui.", ".danmaku_builder.button.name");

    public DanmakuBuilderScreen(OneSlotContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.playerInventoryTitleX = 30;
        this.playerInventoryTitleY = 114;
        this.danmakuColor = DanmakuColor.RED;
        this.danmakuType = DanmakuType.LARGE_SHOT;
        this.stack = ItemStack.EMPTY;
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
                WidgetConfig.of(this.nameInput, 100, 20).setXY(40, 60).withFont(this.font).withText(withText("")),
                WidgetConfig.of(danTypeButton, 70, 20).setXY(40, 25)
                        .withFont(this.font)
                        .withText(this.danmakuType.toTextComponent())
                        .withAction(this::danmakuTypeAction),
                WidgetConfig.of(colorButton, 70, 20).setXY(125, 25)
                        .withFont(this.font)
                        .withText(this.danmakuColor.toTextComponent())
                        .withAction(this::colorAction),
                WidgetConfig.of(this.saveBtn, 60, 20).setXY(140, 90)
                        .withFont(this.font)
                        .withText(this.saveText)
                        .withAction(this::saveAction));
        this.setRelativeToParent(CONFIGS, this.guiLeft, this.guiTop);

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

        GSKONetworking.CHANNEL.sendToServer(new CMergeScriptPacket(this.danmakuData));

    }

    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        if (this.minecraft != null) {
            this.renderRelativeToParent(CONFIGS, matrixStack, mouseX, mouseY, this.guiLeft, this.guiTop, partialTicks);
            this.nameInput.render(matrixStack, mouseX, mouseY, partialTicks);
            this.danTypeButton.render(matrixStack, mouseX, mouseY, partialTicks);
            this.colorButton.render(matrixStack, mouseX, mouseY, partialTicks);
        }
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(@NotNull MatrixStack matrixStack, float partialTicks, int x, int y) {
        if (this.minecraft == null) return;
        this.minecraft.getTextureManager().bindTexture(TEXTURE);
        this.blit(matrixStack, this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

}
