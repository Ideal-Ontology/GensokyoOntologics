package github.thelawf.gensokyoontology.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.gui.screen.script.LineralLayoutScreen;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.CAdjustRailPacket;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.client.gui.widget.Slider;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RailDashboardScreen extends LineralLayoutScreen {
    private Vector3f ctrl1;
    private Vector3f ctrl2;
    private Quaternion rotation;
    private BlockPos targetPos;

    private Slider qySlider;
    private Slider qzSlider;
    private Slider qxSlider;
    private Slider qwSlider;

    private TextFieldWidget x1Input;
    private TextFieldWidget y1Input;
    private TextFieldWidget z1Input;

    private TextFieldWidget x2Input;
    private TextFieldWidget y2Input;
    private TextFieldWidget z2Input;

    private static final TranslationTextComponent QX = GSKOUtil.fromLocaleKey("gui.", ".silder_prefix.qx");
    private static final TranslationTextComponent QY = GSKOUtil.fromLocaleKey("gui.", ".silder_prefix.qy");
    private static final TranslationTextComponent QZ = GSKOUtil.fromLocaleKey("gui.", ".silder_prefix.qz");
    private static final TranslationTextComponent QW = GSKOUtil.fromLocaleKey("gui.", ".silder_prefix.qw");

    private static final ITextComponent QX_LABEL = GensokyoOntology.translate("gui.", ".label.qx") ;
    private static final ITextComponent QY_LABEL = GensokyoOntology.translate("gui.", ".label.qy");
    private static final ITextComponent QZ_LABEL = GensokyoOntology.translate("gui.", ".label.qx") ;
    private static final ITextComponent QW_LABEL = GensokyoOntology.translate("gui.", ".label.qw");

    private static final ITextComponent CX1_LABEL = GensokyoOntology.translate("gui.", ".label.cx1");
    private static final ITextComponent CY1_LABEL = GensokyoOntology.translate("gui.", ".label.cy1");
    private static final ITextComponent CZ1_LABEL = GensokyoOntology.translate("gui.", ".label.cz1");

    private static final ITextComponent CX2_LABEL = GensokyoOntology.translate("gui.", ".label.cx2");
    private static final ITextComponent CY2_LABEL = GensokyoOntology.translate("gui.", ".label.cy2");
    private static final ITextComponent CZ2_LABEL = GensokyoOntology.translate("gui.", ".label.cz2");

    public static final ITextComponent TITLE = GensokyoOntology.translate("gui.", ".rail_dashboard.title");

    public RailDashboardScreen(BlockPos pos, Quaternion rotation) {
        super(TITLE);
        this.targetPos = pos;
        this.rotation = rotation;
    }

    private void updateRotation(Slider slider) {
        float x = this.value(this.qxSlider.getValue());
        float y = this.value(this.qySlider.getValue());
        float z = this.value(this.qzSlider.getValue());
        this.rotation = new Quaternion(x, y, z, (float) this.qwSlider.sliderValue);
        this.sendPacketToServer();
    }

    @Override
    protected void init() {
        super.init();

        this.qxSlider = new Slider(50, 20, 180, 20, QX, withText("°"),
                -180, 180, 0,
                true, true, iPressable -> {}, this::updateRotation);

        this.qySlider = new Slider(50, 45, 180, 20, QY, withText("°"),
                -180, 180, 0,
                true, true, iPressable -> {}, this::updateRotation);

        this.qzSlider = new Slider(50, 70, 180, 20, QZ, withText("°"),
                -180, 180, 0,
                true, true, iPressable -> {}, this::updateRotation);

        this.qwSlider = new Slider(50, 95, 180, 20, QW, withText(""),
                0, 1, 1,
                true, true, iPressable -> {}, this::updateRotation);

        this.x1Input = new TextFieldWidget(this.font, 80, 120, 60, 20, CX1_LABEL);
        this.y1Input = new TextFieldWidget(this.font, 80, 145, 60, 20, CY1_LABEL);
        this.z1Input = new TextFieldWidget(this.font, 80, 170, 60, 20, CZ1_LABEL);

        this.x2Input = new TextFieldWidget(this.font, 230, 120, 60, 20, CX2_LABEL);
        this.y2Input = new TextFieldWidget(this.font, 230, 145, 60, 20, CY2_LABEL);
        this.z2Input = new TextFieldWidget(this.font, 230, 170, 60, 20, CZ2_LABEL);

        this.addButton(this.qxSlider);
        this.addButton(this.qySlider);
        this.addButton(this.qzSlider);
        this.addButton(this.qwSlider);

        this.children.add(this.x1Input);
        this.children.add(this.y1Input);
        this.children.add(this.z1Input);

        this.children.add(this.x2Input);
        this.children.add(this.y2Input);
        this.children.add(this.z2Input);
    }

    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        // this.renderAbsoluteXY(WIDGETS, matrixStack, mouseX, mouseY, partialTicks);
        this.renderBackground(matrixStack);
//        drawString(matrixStack, this.font, QX_LABEL, 10, 20, WHITE);
//        drawString(matrixStack, this.font, QY_LABEL, 10, 45, WHITE);
//        drawString(matrixStack, this.font, QZ_LABEL, 10, 70, WHITE);
//        drawString(matrixStack, this.font, QW_LABEL, 10, 95, WHITE);

        drawString(matrixStack, this.font, CX1_LABEL, 10, 120, WHITE);
        drawString(matrixStack, this.font, CY1_LABEL, 10, 145, WHITE);
        drawString(matrixStack, this.font, CZ1_LABEL, 10, 170, WHITE);

        drawString(matrixStack, this.font, CX2_LABEL, 180, 120, WHITE);
        drawString(matrixStack, this.font, CY2_LABEL, 180, 145, WHITE);
        drawString(matrixStack, this.font, CZ2_LABEL, 180, 170, WHITE);

        this.x1Input.render(matrixStack, mouseX, mouseY, partialTicks);
        this.y1Input.render(matrixStack, mouseX, mouseY, partialTicks);
        this.z1Input.render(matrixStack, mouseX, mouseY, partialTicks);

        this.x2Input.render(matrixStack, mouseX, mouseY, partialTicks);
        this.y2Input.render(matrixStack, mouseX, mouseY, partialTicks);
        this.z2Input.render(matrixStack, mouseX, mouseY, partialTicks);

        this.qxSlider.render(matrixStack, mouseX, mouseY, partialTicks);
        this.qySlider.render(matrixStack, mouseX, mouseY, partialTicks);
        this.qzSlider.render(matrixStack, mouseX, mouseY, partialTicks);
        this.qwSlider.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void renderBackground(@NotNull MatrixStack matrixStack) {

    }

    private float to3Digits(float value) {
        BigDecimal b = new BigDecimal(value);
        return b.setScale(2, RoundingMode.HALF_UP).floatValue();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private void sendPacketToServer() {
        GSKONetworking.CHANNEL.sendToServer(new CAdjustRailPacket(this.targetPos, this.rotation));
    }

    private float value(double value) {
        return GSKOMathUtil.normalize(this.to3Digits((float) value), -180, 180);
    }
}
