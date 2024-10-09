package github.thelawf.gensokyoontology.client.gui.screen;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.client.layout.WidgetConfig;
import github.thelawf.gensokyoontology.client.gui.screen.script.LineralLayoutScreen;
import net.minecraft.client.gui.widget.button.CheckboxButton;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.client.gui.widget.Slider;

import java.util.ArrayList;
import java.util.List;

public class RailDashboardScreen extends LineralLayoutScreen {
    private Slider yawSlider;
    private Slider pitchSlider;
    private Slider rollSlider;
    private CheckboxButton shouldRender;
    private ITextComponent targetLabel;
    private final List<WidgetConfig> WIDGETS;
    private static final ITextComponent TARGET_TITLE = GensokyoOntology.withTranslation("gui.", ".rail_target.title");
    public static final ITextComponent TITLE = GensokyoOntology.withTranslation("gui.", ".rail_dashboard.title");
    public RailDashboardScreen() {
        super(TITLE);
        this.yawSlider = new Slider(0, 0, 0, 0, null, null, 0, 360, 0, true, true, iPressable -> {}, iSlider -> {});
        this.pitchSlider = new Slider(0, 0, 0, 0, null, null, 0, 360, 0, true, true, iPressable -> {}, iSlider -> {});
        this.rollSlider = new Slider(0, 0, 0, 0, null, null, 0, 360, 0, true, true, iPressable -> {}, iSlider -> {});
        WIDGETS = Lists.newArrayList(
                WidgetConfig.of(this.yawSlider, 100, 25).setXY(0, 30),
                WidgetConfig.of(this.pitchSlider, 100, 25).setXY(0, 40),
                WidgetConfig.of(this.rollSlider, 100, 25).setXY(0, 50));
    }

    @Override
    protected void init() {
        super.init();
        setCenteredWidgets(WIDGETS);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        WIDGETS.forEach(config -> config.widget.render(matrixStack, mouseX, mouseY, partialTicks));
    }
}
