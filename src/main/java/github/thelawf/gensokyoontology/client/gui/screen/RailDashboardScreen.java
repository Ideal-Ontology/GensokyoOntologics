package github.thelawf.gensokyoontology.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.client.gui.screen.script.LineralLayoutScreen;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.CAdjustRailPacket;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.math.EulerAngle;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.common.util.math.RotMatrix;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.client.gui.widget.Slider;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RailDashboardScreen extends LineralLayoutScreen {
    private int startEntityId;
    private Quaternion rotation;
    private BlockPos targetPos;
    private EulerAngle eulerAngle;

    private final Vector3d initHandleValue;
    private Vector3d nextHandleValue;

    private Slider pitchHandle;
    private Slider yawHandle;
    private Slider rollHandle;

    private Button resetX0;
    private Button resetY0;
    private Button resetZ0;

    private static final TranslationTextComponent QX = GSKOUtil.fromLocaleKey("gui.", ".silder_prefix.qx");
    private static final TranslationTextComponent QY = GSKOUtil.fromLocaleKey("gui.", ".silder_prefix.qy");
    private static final TranslationTextComponent QZ = GSKOUtil.fromLocaleKey("gui.", ".silder_prefix.qz");

    private static final TranslationTextComponent RX = GSKOUtil.fromLocaleKey("gui.", ".button.reset_x");
    private static final TranslationTextComponent RY = GSKOUtil.fromLocaleKey("gui.", ".button.reset_y");
    private static final TranslationTextComponent RZ = GSKOUtil.fromLocaleKey("gui.", ".button.reset_z");

    public static final ITextComponent TITLE = GSKOUtil.translateText("gui.", ".rail_dashboard.title");

    public RailDashboardScreen(BlockPos pos, Quaternion rotation, int startEntityId) {
        super(TITLE);
        this.targetPos = pos;
        this.rotation = rotation;
        this.eulerAngle = EulerAngle.from(this.rotation);
        this.startEntityId = startEntityId;

        this.initHandleValue = new RotMatrix(this.rotation).toHandleValue();
        this.nextHandleValue = new Vector3d(this.initHandleValue.x, this.initHandleValue.y, this.initHandleValue.z);
    }

    public RailDashboardScreen(BlockPos pos, RotMatrix matrix, int startEntityId) {
        super(TITLE);
        this.targetPos = pos;
        this.rotation = matrix.toQuaternion();
        this.startEntityId = startEntityId;

        this.initHandleValue = matrix.toHandleValue();
        this.nextHandleValue = new Vector3d(this.initHandleValue.x, this.initHandleValue.y, this.initHandleValue.z);
    }

    private void onPitchSlide(Slider slider) {
        double yaw = this.yawHandle == null ? 0 : this.yawHandle.getValue();
        double roll = this.rollHandle == null ? 0 : this.rollHandle.getValue();

        this.eulerAngle = EulerAngle.of(yaw, slider.getValue(), roll);
        this.rotation = this.eulerAngle.toQuaternion();

        this.setSliderValue();
        this.sendPacketToServer();
    }

    private void onYawSlide(Slider slider) {
        double pitch = this.yawHandle == null ? 0 : this.pitchHandle.getValue();
        double roll = this.rollHandle == null ? 0 : this.rollHandle.getValue();
        this.eulerAngle = EulerAngle.of(slider.getValue(), pitch, roll);
        this.rotation = this.eulerAngle.toQuaternion();

        this.setSliderValue();
        this.sendPacketToServer();
    }

    private void onRollSlide(Slider slider) {
        double yaw = this.yawHandle == null ? 0 : this.yawHandle.getValue();
        double pitch = this.rollHandle == null ? 0 : this.pitchHandle.getValue();
        this.eulerAngle = EulerAngle.of(yaw, pitch, slider.getValue());
        this.rotation = this.eulerAngle.toQuaternion();

        this.setSliderValue();
        this.sendPacketToServer();
    }

    private void onResetXSlide(Button btn) {
        this.pitchHandle.setValue(0);
        this.eulerAngle.setRoll(0);
    }
    private void onResetYSlide(Button btn) {
        this.yawHandle.setValue(0);
        this.eulerAngle.setPitch(0);
    }
    private void onResetZSlide(Button btn) {
        this.rollHandle.setValue(0);
        this.eulerAngle.setYaw(0);
    }

    @Override
    protected void init() {
        super.init();
        double x = this.initHandleValue.x;
        double y = this.initHandleValue.y;
        double z = this.initHandleValue.z;

        this.pitchHandle = new Slider(50, 20, 180, 20, QX, withText("°"),
                -180, 180, x,
                true, true, iPressable -> {}, this::onPitchSlide);

        this.yawHandle = new Slider(50, 45, 180, 20, QY, withText("°"),
                -180, 180, y,
                true, true, iPressable -> {}, this::onYawSlide);

        this.rollHandle = new Slider(50, 70, 180, 20, QZ, withText("°"),
                -180, 180, z,
                true, true, iPressable -> {}, this::onRollSlide);

        this.resetX0 = new Button(250, 20, 60, 20, RX, this::onResetXSlide);
        this.resetY0 = new Button(250, 45, 60, 20, RY, this::onResetYSlide);
        this.resetZ0 = new Button(250, 70, 60, 20, RZ, this::onResetZSlide);

        this.addButton(this.pitchHandle);
        this.addButton(this.yawHandle);
        this.addButton(this.rollHandle);

        this.addButton(this.resetX0);
        this.addButton(this.resetY0);
        this.addButton(this.resetZ0);
    }

    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderBackground(matrixStack);

        this.pitchHandle.render(matrixStack, mouseX, mouseY, partialTicks);
        this.yawHandle.render(matrixStack, mouseX, mouseY, partialTicks);
        this.rollHandle.render(matrixStack, mouseX, mouseY, partialTicks);

        this.resetX0.render(matrixStack, mouseX, mouseY, partialTicks);
        this.resetY0.render(matrixStack, mouseX, mouseY, partialTicks);
        this.resetZ0.render(matrixStack, mouseX, mouseY, partialTicks);
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
        GSKONetworking.CHANNEL.sendToServer(new CAdjustRailPacket(this.targetPos, this.rotation, this.startEntityId));
    }

    private float value(Slider slider) {
        if (slider == null) return 0;
        slider.setValue(this.to3Digits((float) slider.getValue()));
        return GSKOMathUtil.normalize(this.to3Digits((float) slider.getValue()), -180, 180);
    }

    private void setSliderValue() {
        if (this.yawHandle == null) return;
        if (this.rollHandle == null) return;
        if (this.pitchHandle == null) return;
//
        this.pitchHandle.setValue(MathHelper.wrapDegrees(this.eulerAngle.pitch()));
        this.yawHandle.setValue(MathHelper.wrapDegrees(this.eulerAngle.yaw()));
        this.rollHandle.setValue(MathHelper.wrapDegrees(this.eulerAngle.roll()));
    }

    private EulerAngle getEulerAngleFrom(Slider xHandle, Slider yHandle, Slider zHandle) {
        return EulerAngle.of(
                xHandle == null ? 0F : (float) xHandle.getValue(),
                yHandle == null ? 0F : (float) yHandle.getValue(),
                zHandle == null ? 0F : (float) zHandle.getValue());
    }
}
