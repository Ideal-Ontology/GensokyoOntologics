package github.thelawf.gensokyoontology.client.gui.screen;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.client.layout.WidgetConfig;
import github.thelawf.gensokyoontology.client.gui.screen.script.LineralLayoutScreen;
import github.thelawf.gensokyoontology.client.gui.screen.widget.BlankWidget;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.CheckboxButton;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.client.gui.widget.Slider;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RailDashboardScreen extends LineralLayoutScreen {
    private Vector3f rotation;
    private BlockPos targetPos;
    private Slider yawSlider;
    private Slider pitchSlider;
    private Slider rollSlider;

    private TextFieldWidget yawText;
    private TextFieldWidget pitchText;
    private TextFieldWidget rollText;
    private CheckboxButton shouldRender;
    private List<WidgetConfig> WIDGETS;
    private static final StringTextComponent ZERO_DEG = new StringTextComponent("0°");
    private static final StringTextComponent FULL_DEG = new StringTextComponent("360°");
    private static final ITextComponent YAW_LABEL    = GensokyoOntology.fromLocaleKey("gui.", ".label.yaw");
    private static final ITextComponent PITCH_LABEL  = GensokyoOntology.fromLocaleKey("gui.", ".label.pitch") ;
    private static final ITextComponent ROLL_LABEL   = GensokyoOntology.fromLocaleKey("gui.", ".label.roll") ;
    private static final ITextComponent TARGET_LABEL = GensokyoOntology.fromLocaleKey("gui.", ".label.rail_target_pos");
    public static final ITextComponent TITLE = GensokyoOntology.fromLocaleKey("gui.", ".rail_dashboard.title");
    public RailDashboardScreen(float roll, float yaw, float pitch) {
        super(TITLE);
        this.rotation = new Vector3f(roll, yaw, pitch);
        this.targetPos = BlockPos.ZERO;
    }


    private void onRollSlide(Slider slider) {
        this.rollText.setText(slider.sliderValue * 360F + "°");
    }
    private void onYawSlide(Slider slider) {
        this.yawText.setText(slider.sliderValue * 360F + "°");
    }
    private void onPitchSlide(Slider slider) {
        this.pitchText.setText(slider.sliderValue * 360F + "°");
    }

    @Override
    protected void init() {
        super.init();

        this.rollSlider = new Slider(110, 30, 100, 25, ZERO_DEG, FULL_DEG, 0, 360, this.rotation.getX(), true, true, iPressable -> {}, this::onRollSlide);
        this.yawSlider = new Slider(110, 60, 100, 25, ZERO_DEG, FULL_DEG, 0, 360, this.rotation.getY(), true, true, iPressable -> {}, this::onYawSlide);
        this.pitchSlider = new Slider(110, 90, 100, 25, ZERO_DEG, FULL_DEG, 0, 360, this.rotation.getZ(), true, true, iPressable -> {}, this::onPitchSlide );

        this.rollText = new TextFieldWidget(this.font,50,30,50,25, withText(this.rollText + "°"));
        this.yawText = new TextFieldWidget(this.font,50,60,50,25, withText(this.yawText + "°"));
        this.pitchText = new TextFieldWidget(this.font,50,90,50,25, withText(this.pitchText + "°"));

        WIDGETS = Lists.newArrayList(
                WidgetConfig.of(this.rollText, 50, 25).setXY(50, 30).withFont(this.font),
                WidgetConfig.of(this.rollSlider, 100, 25).setXY(110, 30),

                WidgetConfig.of(this.yawText, 50, 25).setXY(50, 60).withFont(this.font),
                WidgetConfig.of(this.yawSlider, 100, 25).setXY(110, 60),

                WidgetConfig.of(this.pitchText, 50, 25).setXY(50, 90).withFont(this.font),
                WidgetConfig.of(this.pitchSlider, 100, 25).setXY(110, 90));

        setCenteredWidgets(WIDGETS);
        this.rollSlider.sliderValue = this.rotation.getX() / 360F;
        this.pitchSlider.sliderValue = this.rotation.getY() / 360F;
        this.yawSlider.sliderValue = this.rotation.getZ() / 360F;
    }

    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderAbsoluteXY(WIDGETS, matrixStack, mouseX, mouseY, partialTicks);
        drawCenteredString(matrixStack, this.font, new StringTextComponent(this.rollText.getText()), 0, 30, WHITE);
        drawCenteredString(matrixStack, this.font, new StringTextComponent(this.yawText.getText()), 0, 60, WHITE);
        drawCenteredString(matrixStack, this.font, new StringTextComponent(this.pitchText.getText()), 0, 90, WHITE);

    }

    @Override
    public void tick() {

        float roll = parseFloat(this.rollText.getText().replace("°",""));
        float yaw = parseFloat(this.yawText.getText().replace("°",""));
        float pitch = parseFloat(this.rollText.getText().replace("°",""));

        this.rotation = new Vector3f(roll, yaw, pitch);
    }
}
