package github.thelawf.gensokyoontology.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.gui.screen.script.LineralLayoutScreen;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.CAdjustRailPacket;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.CheckboxButton;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.math.vector.Vector4f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.client.gui.widget.Slider;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class RailDashboardScreen extends LineralLayoutScreen {
    private Vector3f controlPoint;
    private BlockPos railPos;
    private Vector4f rotation;
    private float radius;
    private Slider yawSlider;
    private Slider pitchSlider;
    private Slider rollSlider;

    private TextFieldWidget xInput;
    private TextFieldWidget yInput;
    private TextFieldWidget zInput;
    private CheckboxButton shouldRender;
    private static final TranslationTextComponent ANGLE_X = GSKOUtil.fromLocaleKey("gui.", ".silder_prefix.angle_x");
    private static final TranslationTextComponent ANGLE_Y = GSKOUtil.fromLocaleKey("gui.", ".silder_prefix.angle_y");
    private static final TranslationTextComponent ANGLE_Z = GSKOUtil.fromLocaleKey("gui.", ".silder_prefix.angle_z");
    private static final ITextComponent ROLL_LABEL   = GensokyoOntology.translate("gui.", ".label.roll") ;
    private static final ITextComponent YAW_LABEL    = GensokyoOntology.translate("gui.", ".label.yaw");
    private static final ITextComponent PITCH_LABEL  = GensokyoOntology.translate("gui.", ".label.pitch") ;
    private static final ITextComponent X_LABEL = GensokyoOntology.translate("gui.", ".label.x");
    private static final ITextComponent Y_LABEL = GensokyoOntology.translate("gui.", ".label.y");
    private static final ITextComponent Z_LABEL = GensokyoOntology.translate("gui.", ".label.z");
    public static final ITextComponent TITLE = GensokyoOntology.translate("gui.", ".rail_dashboard.title");

    public RailDashboardScreen(BlockPos pos, float roll, float yaw, float pitch, float w) {
        super(TITLE);
        this.railPos = pos;
        this.rotation = new Vector4f(roll, yaw, pitch, w);
    }

    // public RailDashboardScreen(RailAdjustContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
    //     super(screenContainer, inv, titleIn);
    //     this.rotation = screenContainer.getRotation();
    //     this.railPos = screenContainer.getRailPos();
    // }

    private void onRollSlide(Slider slider) {
        // this.rollInput.setText(formatAs(slider.getValue()));
        this.rotation.setX((float) slider.getValue());
        this.sendPacketToServer();
    }
    private void onYawSlide(Slider slider) {
        // this.yawInput.setText(formatAs(slider.getValue()));
        this.rotation.setY((float) slider.getValue());
        this.sendPacketToServer();
    }
    private void onPitchSlide(Slider slider) {
        // this.pitchInput.setText(formatAs(slider.getValue()));
        this.rotation.setZ((float) slider.getValue());
        this.sendPacketToServer();
    }

    private void onXInput(String input) {
        // this.pitchInput.setText(formatAs(slider.getValue()));
        if (Objects.equals(input, "")) return;
        float value;
        try {
            value = Float.parseFloat(input);
        }catch (Exception ignored){
            return;
        }
        this.controlPoint.setX(value);
        this.sendPacketToServer();
    }

    private void onYInput(String input) {
        // this.pitchInput.setText(formatAs(slider.getValue()));
        if (Objects.equals(input, "")) return;
        float value;
        try {
            value = Float.parseFloat(input);
        }catch (Exception ignored){
            return;
        }
        this.controlPoint.setY(value);
        this.sendPacketToServer();
    }

    private void onZInput(String input) {
        // this.pitchInput.setText(formatAs(slider.getValue()));
        if (Objects.equals(input, "")) return;
        float value;
        try {
            value = Float.parseFloat(input);
        }catch (Exception ignored){
            return;
        }
        this.controlPoint.setZ(value);
        this.sendPacketToServer();
    }

    @Override
    protected void init() {
        super.init();

        this.rollSlider = new Slider(50, 30, 180, 25, ANGLE_X, withText("°"),
                -180, 180, (int) this.rotation.getX(), true, true, iPressable -> {}, this::onRollSlide);
        this.yawSlider = new Slider(50, 60, 180, 25, ANGLE_Y, withText("°"),
                -180, 180, (int) this.rotation.getY(), true, true, iPressable -> {}, this::onYawSlide);
        this.pitchSlider = new Slider(50, 90, 180, 25, ANGLE_Z, withText("°"),
                -180, 180, (int) this.rotation.getZ(), true, true, iPressable -> {}, this::onPitchSlide);
//        this.xInput = new TextFieldWidget(this.font, 50, 120, 120, 25, withText(String.valueOf(controlPoint.getX())));
//        this.yInput = new TextFieldWidget(this.font, 50, 150, 120, 25, withText(String.valueOf(controlPoint.getY())));
//        this.zInput = new TextFieldWidget(this.font, 50, 180, 120, 25, withText(String.valueOf(controlPoint.getZ())));
//        this.xInput.setResponder(this::onXInput);
//        this.yInput.setResponder(this::onYInput);
//        this.zInput.setResponder(this::onZInput);

        this.rollSlider.showDecimal = false;
        this.pitchSlider.showDecimal = false;
        this.yawSlider.showDecimal = false;

        this.addButton(this.rollSlider);
        this.addButton(this.yawSlider);
        this.addButton(this.pitchSlider);
//        this.children.add(this.xInput);
//        this.children.add(this.yInput);
//        this.children.add(this.zInput);
//
//         this.rollInput = new TextFieldWidget(this.font, 50, 30, 40, 25, withText(this.rollSlider.getValueInt() + "°"));
//         this.yawInput = new TextFieldWidget(this.font, 50, 60, 40, 25, withText(this.yawSlider.getValueInt() + "°"));
//         this.pitchInput = new TextFieldWidget(this.font, 50, 90, 40, 25, withText(this.pitchSlider.getValueInt() + "°"));
//
//         this.rollInput.setResponder(this::onRollInputChanged);
//         this.yawInput.setResponder(this::onYawInputChanged);
//         this.pitchInput.setResponder(this::onPitchInputChanged);

        // this.children.add(this.rollInput);
        // this.children.add(this.yawInput);
        // this.children.add(this.pitchInput);
    }

    private void onRollInputChanged(String text) {
        this.rollSlider.setValue((int) parseDouble(text));
        this.sendPacketToServer();
    }

    private void onYawInputChanged(String text) {
        this.yawSlider.setValue((int) parseDouble(text));
        this.sendPacketToServer();
    }

    private void onPitchInputChanged(String text) {
        this.pitchSlider.setValue((int) parseDouble(text));
        this.sendPacketToServer();
    }

    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        // this.renderAbsoluteXY(WIDGETS, matrixStack, mouseX, mouseY, partialTicks);
        this.renderBackground(matrixStack);
        drawString(matrixStack, this.font, ROLL_LABEL, 10, 40, WHITE);
        drawString(matrixStack, this.font, YAW_LABEL, 10, 70, WHITE);
        drawString(matrixStack, this.font, PITCH_LABEL, 10, 100, WHITE);

        // this.rollInput.render(matrixStack, mouseX, mouseY, partialTicks);
        // this.yawInput.render(matrixStack, mouseX, mouseY, partialTicks);
        // this.pitchInput.render(matrixStack, mouseX, mouseY, partialTicks);

        this.rollSlider.render(matrixStack, mouseX, mouseY, partialTicks);
        this.yawSlider.render(matrixStack, mouseX, mouseY, partialTicks);
        this.pitchSlider.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    public String formatAs(double original) {
        return (int) original + "°";
    }

    public String formatAs(float original) {
        return (int) original + "°";
    }

    public String formatAs(String original) {
        return (int) parseFloat(original) + "°";
    }

    @Override
    public void renderBackground(@NotNull MatrixStack matrixStack) {

    }

    public void setRotation(float roll, float yaw, float pitch, float w) {
        this.rotation = new Vector4f(roll, yaw, pitch, w);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private void sendPacketToServer() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putLong("pos", this.railPos.toLong());
        nbt.putFloat("roll", this.rotation.getX());
        nbt.putFloat("yaw", this.rotation.getY());
        nbt.putFloat("pitch", this.rotation.getZ());
        nbt.putFloat("w", this.rotation.getW());

        GSKONetworking.CHANNEL.sendToServer(new CAdjustRailPacket(
                this.railPos,
                this.rotation.getY(),
                this.rotation.getZ(),
                this.rotation.getX(),
                this.rotation.getW()));
    }

    private void sendPacketToClient () {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putBoolean("shouldRender", true);

        nbt.putFloat("roll", this.rotation.getX());
        nbt.putFloat("yaw", this.rotation.getY());
        nbt.putFloat("pitch", this.rotation.getZ());


    }
}
