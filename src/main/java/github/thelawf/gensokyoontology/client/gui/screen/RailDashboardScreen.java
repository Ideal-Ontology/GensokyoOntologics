package github.thelawf.gensokyoontology.client.gui.screen;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.client.layout.WidgetConfig;
import github.thelawf.gensokyoontology.client.gui.screen.script.LineralLayoutScreen;
import github.thelawf.gensokyoontology.client.gui.screen.widget.BlankWidget;
import github.thelawf.gensokyoontology.common.container.RailAdjustGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.CheckboxButton;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.client.gui.widget.Slider;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;
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
    private final List<WidgetConfig> WIDGETS;
    private static final ITextComponent YAW_LABEL    = GensokyoOntology.withTranslation("gui.", ".label.yaw");
    private static final ITextComponent PITCH_LABEL  = GensokyoOntology.withTranslation("gui.", ".label.pitch") ;
    private static final ITextComponent ROLL_LABEL   = GensokyoOntology.withTranslation("gui.", ".label.roll") ;
    private static final ITextComponent TARGET_LABEL = GensokyoOntology.withTranslation("gui.", ".label.rail_target_pos");
    public static final ITextComponent TITLE = GensokyoOntology.withTranslation("gui.", ".rail_dashboard.title");
    public RailDashboardScreen() {
        super(TITLE);
        this.rotation = Vector3f.XP;
        this.targetPos = BlockPos.ZERO;

        this.yawSlider = new Slider(0, 0, 0, 0, null, null, 0, 360, 0, true, true, iPressable -> {}, iSlider -> {});
        this.pitchSlider = new Slider(0, 0, 0, 0, null, null, 0, 360, 0, true, true, iPressable -> {}, iSlider -> {});
        this.rollSlider = new Slider(0, 0, 0, 0, null, null, 0, 360, 0, true, true, iPressable -> {}, iSlider -> {});
        WIDGETS = Lists.newArrayList(
                WidgetConfig.of(null).isText(true).withText(ROLL_LABEL).setXY(0, 30).withFont(this.font),
                WidgetConfig.of(this.rollSlider, 100, 25).setXY(50, 90),

                WidgetConfig.of(null).isText(true).withText(YAW_LABEL).setXY(0, 30).withFont(this.font),
                WidgetConfig.of(this.yawSlider, 100, 25).setXY(50, 30),

                WidgetConfig.of(null).isText(true).withText(PITCH_LABEL).setXY(0, 30).withFont(this.font),
                WidgetConfig.of(this.pitchSlider, 100, 25).setXY(50, 60));

    }

    @Override
    protected void init() {
        super.init();
        setCenteredWidgets(WIDGETS);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderWidgets(WIDGETS, matrixStack, mouseX, mouseY, partialTicks);
    }
}
