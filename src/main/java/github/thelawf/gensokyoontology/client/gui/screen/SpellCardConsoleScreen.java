package github.thelawf.gensokyoontology.client.gui.screen;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.client.layout.WidgetConfig;
import github.thelawf.gensokyoontology.client.gui.screen.script.ScriptContainerScreen;
import github.thelawf.gensokyoontology.common.container.SpellCardConsoleContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import org.jline.utils.WCWidth;

import java.util.List;

// TODO: 面向硬核自定义符卡行为的玩家而写的符卡控制台面板，用于接受玩家的自定义行为
public class SpellCardConsoleScreen extends ScriptContainerScreen<SpellCardConsoleContainer> {

    public static final ResourceLocation BUTTONS_TEX = GensokyoOntology.withRL("textures/gui/widget/buttons.png");
    public static final ResourceLocation SCREEN_TEXTURE = GensokyoOntology.withRL("textures/gui/spell_card_console.png");
    private final ImageButton saveButton;
    private final ImageButton copyButton;
    private final List<WidgetConfig> CONFIGS;

    public SpellCardConsoleScreen(SpellCardConsoleContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.saveButton =  new ImageButton(0,0,28,28,0,0,0, BUTTONS_TEX, (press) -> {});
        this.copyButton =  new ImageButton(0,0,28,28,0,0,0, BUTTONS_TEX, (press) -> {});

        CONFIGS = Lists.newArrayList(
                WidgetConfig.of(this.saveButton, 28, 28).setXY(10, 167)
                        .withFont(this.font)
                        .withAction(this::saveButtonAction),
                WidgetConfig.of(this.copyButton, 28, 28).setXY(10, 204)
                        .withFont(this.font)
                        .withAction(this::copyButtonAction)
        );
    }

    private void saveButtonAction(Button button) {

    }
    private void copyButtonAction(Button button) {

    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        Minecraft mc = Minecraft.getInstance();
        mc.getTextureManager().bindTexture(SCREEN_TEXTURE);
        this.renderBackground(matrixStack);

    }

    private Button createOperationOptionBtn(int x, int y, ITextComponent title) {
        return new Button(x,y,60,20, title, button -> {});
    }

    private Button createInstanceOptionBtn(int x, int y, ITextComponent title) {
        return new Button(x,y,80,20, title, button -> {});
    }
}
