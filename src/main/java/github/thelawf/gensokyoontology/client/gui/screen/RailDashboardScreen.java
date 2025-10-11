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

    private Slider xHandle;
    private Slider yHandle;
    private Slider zHandle;

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

    private void onXHandleSlide(Slider slider) {
        double y = this.yHandle == null ? 0 : this.yHandle.getValue();
        double z = this.zHandle == null ? 0 : this.zHandle.getValue();

        this.eulerAngle = EulerAngle.of(z, y, slider.getValue());
        this.rotation = this.eulerAngle.toQuaternion();

        this.setSliderValue();
        this.sendPacketToServer();
    }

    private void onYHandleSlide(Slider slider) {
        double x = this.yHandle == null ? 0 : this.xHandle.getValue();
        double z = this.zHandle == null ? 0 : this.zHandle.getValue();
        this.eulerAngle = EulerAngle.of(z, slider.getValue(), x);
        this.rotation = this.eulerAngle.toQuaternion();

        this.setSliderValue();
        this.sendPacketToServer();
    }

    private void onZHandleSlide(Slider slider) {
        double y = this.yHandle == null ? 0 : this.yHandle.getValue();
        double x = this.zHandle == null ? 0 : this.xHandle.getValue();
        this.eulerAngle = EulerAngle.of(slider.getValue(), y, x);
        this.rotation = this.eulerAngle.toQuaternion();

        this.setSliderValue();
        this.sendPacketToServer();
    }

    private void onResetXSlide(Button btn) {
        this.xHandle.setValue(0);
        this.eulerAngle.setRoll(0);
    }
    private void onResetYSlide(Button btn) {
        this.yHandle.setValue(0);
        this.eulerAngle.setPitch(0);
    }
    private void onResetZSlide(Button btn) {
        this.zHandle.setValue(0);
        this.eulerAngle.setYaw(0);
    }

    @Override
    protected void init() {
        super.init();
        double x = this.initHandleValue.x;
        double y = this.initHandleValue.y;
        double z = this.initHandleValue.z;

        this.xHandle = new Slider(50, 20, 180, 20, QX, withText("°"),
                -180, 180, x,
                true, true, iPressable -> {}, this::onXHandleSlide);

        this.yHandle = new Slider(50, 45, 180, 20, QY, withText("°"),
                -180, 180, y,
                true, true, iPressable -> {}, this::onYHandleSlide);

        this.zHandle = new Slider(50, 70, 180, 20, QZ, withText("°"),
                -180, 180, z,
                true, true, iPressable -> {}, this::onZHandleSlide);

        this.resetX0 = new Button(250, 20, 60, 20, RX, this::onResetXSlide);
        this.resetY0 = new Button(250, 45, 60, 20, RY, this::onResetYSlide);
        this.resetZ0 = new Button(250, 70, 60, 20, RZ, this::onResetZSlide);

        this.addButton(this.xHandle);
        this.addButton(this.yHandle);
        this.addButton(this.zHandle);

        this.addButton(this.resetX0);
        this.addButton(this.resetY0);
        this.addButton(this.resetZ0);
    }

    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderBackground(matrixStack);

        this.xHandle.render(matrixStack, mouseX, mouseY, partialTicks);
        this.yHandle.render(matrixStack, mouseX, mouseY, partialTicks);
        this.zHandle.render(matrixStack, mouseX, mouseY, partialTicks);

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
        if (this.yHandle == null) return;
        if (this.zHandle == null) return;
        if (this.xHandle == null) return;
//
//        this.xHandle.setValue(MathHelper.wrapDegrees(this.eulerAngle.roll()));
//        this.yHandle.setValue(MathHelper.wrapDegrees(this.eulerAngle.pitch()));
//        this.zHandle.setValue(MathHelper.wrapDegrees(this.eulerAngle.yaw()));
    }

    private EulerAngle getEulerAngleFrom(Slider xHandle, Slider yHandle, Slider zHandle) {
        return EulerAngle.of(
                xHandle == null ? 0F : (float) xHandle.getValue(),
                yHandle == null ? 0F : (float) yHandle.getValue(),
                zHandle == null ? 0F : (float) zHandle.getValue());
    }
}
