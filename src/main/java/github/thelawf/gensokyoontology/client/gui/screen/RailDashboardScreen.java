package github.thelawf.gensokyoontology.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.client.layout.WidgetConfig;
import github.thelawf.gensokyoontology.client.gui.screen.script.LineralLayoutScreen;
import github.thelawf.gensokyoontology.common.network.GSKONetworking;
import github.thelawf.gensokyoontology.common.network.packet.CAdjustRailPacket;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.CheckboxButton;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.client.gui.widget.Slider;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RailDashboardScreen extends LineralLayoutScreen {
    private Vector3f rotation;
    private BlockPos railPos;
    private Slider yawSlider;
    private Slider pitchSlider;
    private Slider rollSlider;

    private TextFieldWidget yawInput;
    private TextFieldWidget pitchInput;
    private TextFieldWidget rollInput;
    private CheckboxButton shouldRender;
    private static final TranslationTextComponent ANGLE_X = GSKOUtil.fromLocaleKey("gui.", ".silder_prefix.angle_x");
    private static final TranslationTextComponent ANGLE_Y = GSKOUtil.fromLocaleKey("gui.", ".silder_prefix.angle_y");
    private static final TranslationTextComponent ANGLE_Z = GSKOUtil.fromLocaleKey("gui.", ".silder_prefix.angle_z");
    private static final ITextComponent ROLL_LABEL   = GensokyoOntology.fromLocaleKey("gui.", ".label.roll") ;
    private static final ITextComponent YAW_LABEL    = GensokyoOntology.fromLocaleKey("gui.", ".label.yaw");
    private static final ITextComponent PITCH_LABEL  = GensokyoOntology.fromLocaleKey("gui.", ".label.pitch") ;
    private static final ITextComponent TARGET_LABEL = GensokyoOntology.fromLocaleKey("gui.", ".label.rail_target_pos");
    public static final ITextComponent TITLE = GensokyoOntology.fromLocaleKey("gui.", ".rail_dashboard.title");
    public RailDashboardScreen(BlockPos pos, float roll, float yaw, float pitch) {
        super(TITLE);
        this.rotation = new Vector3f(roll, yaw, pitch);
        this.railPos = pos;
    }


    private void onRollSlide(Slider slider) {
        this.rollInput.setText(formatAs(slider.getValue()));
        this.rotation.setX((float) slider.getValue());
        this.saveAndSync();
    }
    private void onYawSlide(Slider slider) {
        this.yawInput.setText(formatAs(slider.getValue()));
        this.rotation.setY((float) slider.getValue());
        this.saveAndSync();
    }
    private void onPitchSlide(Slider slider) {
        this.pitchInput.setText(formatAs(slider.getValue()));
        this.rotation.setZ((float) slider.getValue());
        this.saveAndSync();
    }

    @Override
    protected void init() {
        super.init();

        this.rollInput = new TextFieldWidget(this.font, 50, 30, 40, 25, withText(this.rollInput + "°"));
        this.yawInput = new TextFieldWidget(this.font, 50, 60, 40, 25, withText(this.yawInput + "°"));
        this.pitchInput = new TextFieldWidget(this.font, 50, 90, 40, 25, withText(this.pitchInput + "°"));

        this.rollSlider = new Slider(100, 30, 120, 25, ANGLE_X, withText(formatAs(this.rotation.getX()).replace("0°", "°")),
                0, 360, this.rotation.getX(), true, true, iPressable -> {}, this::onRollSlide);
        this.yawSlider = new Slider(100, 60, 120, 25, ANGLE_Y, withText(formatAs(this.rotation.getY()).replace("0°", "°")),
                0, 360, this.rotation.getY(), true, true, iPressable -> {}, this::onYawSlide);
        this.pitchSlider = new Slider(100, 90, 120, 25, ANGLE_Z, withText(formatAs(this.rotation.getZ()).replace("0°", "°")),
                0, 360, this.rotation.getZ(), true, true, iPressable -> {}, this::onPitchSlide);

        this.children.add(this.rollInput);
        this.children.add(this.yawInput);
        this.children.add(this.pitchInput);

        this.addButton(this.rollSlider);
        this.addButton(this.yawSlider);
        this.addButton(this.pitchSlider);

        this.rollSlider.showDecimal = false;
        this.pitchSlider.showDecimal = false;
        this.yawSlider.showDecimal = false;

    }

    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        // this.renderAbsoluteXY(WIDGETS, matrixStack, mouseX, mouseY, partialTicks);
        this.renderBackground(matrixStack);
        drawString(matrixStack, this.font, ROLL_LABEL, 10, 40, WHITE);
        drawString(matrixStack, this.font, YAW_LABEL, 10, 70, WHITE);
        drawString(matrixStack, this.font, PITCH_LABEL, 10, 100, WHITE);

        this.rollInput.render(matrixStack, mouseX, mouseY, partialTicks);
        this.yawInput.render(matrixStack, mouseX, mouseY, partialTicks);
        this.pitchInput.render(matrixStack, mouseX, mouseY, partialTicks);

        this.rollSlider.render(matrixStack, mouseX, mouseY, partialTicks);
        this.yawSlider.render(matrixStack, mouseX, mouseY, partialTicks);
        this.pitchSlider.render(matrixStack, mouseX, mouseY, partialTicks);

    }

    @Override
    public void tick() {
        super.tick();
        this.rollInput.setText(formatAs(this.rollInput.getText()));
        this.yawInput.setText(formatAs(this.yawInput.getText()));
        this.pitchInput.setText(formatAs(this.pitchInput.getText()));

        this.rollSlider.setValue(parseDouble(this.rollInput.getText()));
        this.yawSlider.setValue(parseDouble(this.yawInput.getText()));
        this.pitchSlider.setValue(parseDouble(this.pitchInput.getText()));

        this.rotation = new Vector3f(
                parseFloat(this.rollInput.getText()),
                parseFloat(this.yawInput.getText()),
                parseFloat(this.pitchInput.getText())
        );

        this.saveAndSync();
    }

    public String formatAs(double original) {
        return (int) original + "°";
    }

    public String formatAs(String original) {
        return (int) parseFloat(original) + "°";
    }

    @Override
    public void renderBackground(@NotNull MatrixStack matrixStack) {

    }

    public void setRotation(float roll, float yaw, float pitch) {
        this.rotation = new Vector3f(roll, yaw, pitch);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
    private void saveAndSync() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putLong("railPos", this.railPos.toLong());
        nbt.putFloat("roll", this.rotation.getX());
        nbt.putFloat("yaw", this.rotation.getY());
        nbt.putFloat("pitch", this.rotation.getZ());
        GSKONetworking.CHANNEL.sendToServer(new CAdjustRailPacket(nbt));
    }
}
