package github.thelawf.gensokyoontology.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.gui.screen.script.LineralLayoutScreen;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.CAdjustRailPacket;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.common.util.math.Rot2f;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.client.gui.widget.Slider;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RailDashboardScreen extends LineralLayoutScreen {
    private Vector3f selfFacing;
    private Quaternion rotation;
    private BlockPos targetPos;

    private Slider yawSlider;
    private Slider qzSlider;
    private Slider pitchSlider;
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

    public RailDashboardScreen(BlockPos pos, Vector3f facing) {
        super(TITLE);
        this.targetPos = pos;
        this.selfFacing = facing;
    }

    private void onPitchSlide(Slider slider) {
        float pitch = (float) slider.getValue();
        float yaw = this.to3Digits((float) this.yawSlider.getValue());
        slider.setValue(this.to3Digits(pitch));
        this.selfFacing = new Vector3f(Vector3d.fromPitchYaw(pitch, yaw));
        this.sendPacketToServer();
    }

    private void onYawSlide(Slider slider) {
        float pitch = (float) this.pitchSlider.getValue();
        float yaw = this.to3Digits((float) this.yawSlider.getValue());
        this.selfFacing = new Vector3f(Vector3d.fromPitchYaw(pitch, yaw));
        this.sendPacketToServer();
    }

    @Override
    protected void init() {
        super.init();
        Rot2f rot2f = Rot2f.from(new Vector3d(this.selfFacing));
        float pitch = rot2f.pitch();
        float yaw = rot2f.yaw();
        this.pitchSlider = new Slider(50, 20, 180, 20, QX, withText("°"),
                -90, 90, pitch,
                true, true, iPressable -> {}, this::onPitchSlide);

        this.yawSlider = new Slider(50, 45, 180, 20, QY, withText("°"),
                0, 360, yaw,
                true, true, iPressable -> {}, this::onYawSlide);

        this.addButton(this.pitchSlider);
        this.addButton(this.yawSlider);
    }

    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        // this.renderAbsoluteXY(WIDGETS, matrixStack, mouseX, mouseY, partialTicks);
        this.renderBackground(matrixStack);
//
//        drawString(matrixStack, this.font, CX1_LABEL, 10, 120, WHITE);
//        drawString(matrixStack, this.font, CY1_LABEL, 10, 145, WHITE);
//        drawString(matrixStack, this.font, CZ1_LABEL, 10, 170, WHITE);
//
//        drawString(matrixStack, this.font, CX2_LABEL, 180, 120, WHITE);
//        drawString(matrixStack, this.font, CY2_LABEL, 180, 145, WHITE);
//        drawString(matrixStack, this.font, CZ2_LABEL, 180, 170, WHITE);

        this.pitchSlider.render(matrixStack, mouseX, mouseY, partialTicks);
        this.yawSlider.render(matrixStack, mouseX, mouseY, partialTicks);
//        this.qzSlider.render(matrixStack, mouseX, mouseY, partialTicks);
//        this.qwSlider.render(matrixStack, mouseX, mouseY, partialTicks);
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
        GSKONetworking.CHANNEL.sendToServer(new CAdjustRailPacket(this.targetPos, this.selfFacing));
    }

    private float value(Slider slider) {
        if (slider == null) return 0;
        slider.setValue(this.to3Digits((float) slider.getValue()));
        return GSKOMathUtil.normalize(this.to3Digits((float) slider.getValue()), -180, 180);
    }
}
