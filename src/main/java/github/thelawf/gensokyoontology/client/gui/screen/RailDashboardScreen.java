package github.thelawf.gensokyoontology.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.client.layout.WidgetConfig;
import github.thelawf.gensokyoontology.client.gui.screen.script.LineralLayoutScreen;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.CheckboxButton;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.client.gui.widget.Slider;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.system.CallbackI;

import java.util.List;

public class RailDashboardScreen extends LineralLayoutScreen {
    private Vector3f rotation;
    private BlockPos targetPos;
    private Slider yawSlider;
    private Slider pitchSlider;
    private Slider rollSlider;

    private TextFieldWidget yawInput;
    private TextFieldWidget pitchInput;
    private TextFieldWidget rollInput;
    private CheckboxButton shouldRender;
    private List<WidgetConfig> WIDGETS;
    private static final TranslationTextComponent ANGLE = GSKOUtil.fromLocaleKey("gui.", ".silder_prefix.angle");
    private static final ITextComponent ROLL_LABEL   = GensokyoOntology.fromLocaleKey("gui.", ".label.roll") ;
    private static final ITextComponent YAW_LABEL    = GensokyoOntology.fromLocaleKey("gui.", ".label.yaw");
    private static final ITextComponent PITCH_LABEL  = GensokyoOntology.fromLocaleKey("gui.", ".label.pitch") ;
    private static final ITextComponent TARGET_LABEL = GensokyoOntology.fromLocaleKey("gui.", ".label.rail_target_pos");
    public static final ITextComponent TITLE = GensokyoOntology.fromLocaleKey("gui.", ".rail_dashboard.title");
    public RailDashboardScreen(float roll, float yaw, float pitch) {
        super(TITLE);
        this.rotation = new Vector3f(roll, yaw, pitch);
        this.targetPos = BlockPos.ZERO;
    }


    private void onRollSlide(Slider slider) {
        this.rollInput.setText(slider.getValue() + "°");
        this.rotation.setX((float) slider.getValue());
        slider.suffix = new StringTextComponent(formatAs(this.rollInput.getText()));
    }
    private void onYawSlide(Slider slider) {
        this.yawInput.setText(slider.getValue() + "°");
        this.rotation.setY((float) slider.getValue());
        slider.suffix = new StringTextComponent(formatAs(this.yawInput.getText()));
    }
    private void onPitchSlide(Slider slider) {
        this.pitchInput.setText(formatAs(slider.getValue()));
        this.rotation.setZ((float) slider.getValue());
        slider.suffix = new StringTextComponent(formatAs(this.pitchInput.getText()));
    }

    @Override
    protected void init() {
        super.init();

        this.rollInput = new TextFieldWidget(this.font, 50, 30, 50, 25, withText(this.rollInput + "°"));
        this.yawInput = new TextFieldWidget(this.font, 50, 60, 50, 25, withText(this.yawInput + "°"));
        this.pitchInput = new TextFieldWidget(this.font, 50, 90, 50, 25, withText(this.pitchInput + "°"));

        this.rollSlider = new Slider(110, 30, 100, 25, ANGLE, withText(String.valueOf(this.rotation.getX())),
                0, 360, this.rotation.getX(), true, true, iPressable -> {}, this::onRollSlide);
        this.yawSlider = new Slider(110, 60, 100, 25, ANGLE, withText(String.valueOf(this.rotation.getY())),
                0, 360, this.rotation.getY(), true, true, iPressable -> {}, this::onYawSlide);
        this.pitchSlider = new Slider(110, 90, 100, 25, ANGLE, withText(String.valueOf(this.rotation.getZ())),
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

        LOGGER.info(this.rollSlider.getValue());
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

        this.rotation = new Vector3f(
                parseFloat(this.rollInput.getText()),
                parseFloat(this.yawInput.getText()),
                parseFloat(this.pitchInput.getText())
        );
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

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
