package github.thelawf.gensokyoontology.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.client.gui.screen.script.LineralLayoutScreen;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.CAdjustRailPacket;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.math.EulerAngle;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.common.util.math.RotMatrix;
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

    private static final TranslationTextComponent QX = GSKOUtil.fromLocaleKey("gui.", ".silder_prefix.qx");
    private static final TranslationTextComponent QY = GSKOUtil.fromLocaleKey("gui.", ".silder_prefix.qy");
    private static final TranslationTextComponent QZ = GSKOUtil.fromLocaleKey("gui.", ".silder_prefix.qz");
    private static final TranslationTextComponent QW = GSKOUtil.fromLocaleKey("gui.", ".silder_prefix.qw");

    public static final ITextComponent TITLE = GSKOUtil.translate("gui.", ".rail_dashboard.title");

    public RailDashboardScreen(BlockPos pos, Quaternion rotation, int startEntityId) {
        super(TITLE);
        this.targetPos = pos;
        this.rotation = rotation;
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
        this.eulerAngle = EulerAngle.of(slider.getValue(), 0, 0);
        this.rotation = this.eulerAngle.toQuaternion();
        this.setSliderValue();
        this.sendPacketToServer();
    }

    private void onYHandleSlide(Slider slider) {
        this.eulerAngle = EulerAngle.of(0, slider.getValue(), 0);
        this.rotation = this.eulerAngle.toQuaternion();
        this.setSliderValue();
        this.sendPacketToServer();
    }

    private void onZHandleSlide(Slider slider) {
        this.eulerAngle = EulerAngle.of(0, 0, slider.getValue());
        this.rotation = this.eulerAngle.toQuaternion();
        this.setSliderValue();
        this.sendPacketToServer();
    }


    @Override
    protected void init() {
        super.init();
        double x = this.initHandleValue.x;
        double y = this.initHandleValue.y;
        double z = this.initHandleValue.z;

        this.xHandle = new Slider(50, 20, 180, 20, QX, withText("°"),
                -90, 90, x,
                true, true, iPressable -> {}, this::onXHandleSlide);

        this.yHandle = new Slider(50, 45, 180, 20, QY, withText("°"),
                -180, 180, y,
                true, true, iPressable -> {}, this::onYHandleSlide);

        this.zHandle = new Slider(50, 70, 180, 20, QZ, withText("°"),
                -180, 180, z,
                true, true, iPressable -> {}, this::onZHandleSlide);

        this.addButton(this.xHandle);
        this.addButton(this.yHandle);
        this.addButton(this.zHandle);
    }

    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderBackground(matrixStack);

        this.xHandle.render(matrixStack, mouseX, mouseY, partialTicks);
        this.yHandle.render(matrixStack, mouseX, mouseY, partialTicks);
        this.zHandle.render(matrixStack, mouseX, mouseY, partialTicks);
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

        this.xHandle.setValue(MathHelper.wrapDegrees(this.eulerAngle.pitch()));
        this.yHandle.setValue(MathHelper.wrapDegrees(this.eulerAngle.yaw()));
        this.zHandle.setValue(MathHelper.wrapDegrees(this.eulerAngle.roll()));
    }

    private EulerAngle getEulerAngleFrom(Slider xHandle, Slider yHandle, Slider zHandle) {
        return EulerAngle.of(
                xHandle == null ? 0F : (float) xHandle.getValue(),
                yHandle == null ? 0F : (float) yHandle.getValue(),
                zHandle == null ? 0F : (float) zHandle.getValue());
    }
}
