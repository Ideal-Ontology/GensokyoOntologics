package github.thelawf.gensokyoontology.client.screen.container;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.ToggleWidget;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class DanmakuShootCreatorGUI extends Screen {

    private static final ResourceLocation MAIN = new ResourceLocation(GensokyoOntology.MODID +
            "textures/gui/dan_creator_main.png");

    private static final ResourceLocation SIDE_BAR = new ResourceLocation(GensokyoOntology.MODID +
            "textures/gui/dan_creator_sidebar.png");

    private static final ResourceLocation TOOL_TAB = new ResourceLocation(GensokyoOntology.MODID +
            "textures/gui/dan_creator_tab.png");

    private static final ResourceLocation SCRIPT_TYPE = new ResourceLocation(GensokyoOntology.MODID +
            "textures/gui/script_type.png");

    private static final ResourceLocation BUTTON = new ResourceLocation(GensokyoOntology.MODID +
            "textures/gui/dan_creator_buttons.png");

    private ImageButton loop;
    private ImageButton condition;
    private ImageButton field;
    private ImageButton invoker;

    private TextFieldWidget initLocation;
    private TextFieldWidget shootVec;
    private TextFieldWidget speed;
    private TextFieldWidget acceleration;
    private TextFieldWidget incrementAngle;

    private ToggleWidget trueOrFalse;

    protected DanmakuShootCreatorGUI(ITextComponent titleIn) {
        super(new TranslationTextComponent("gui."+ GensokyoOntology.MODID +
                "dan_creator.title"));
    }
}
