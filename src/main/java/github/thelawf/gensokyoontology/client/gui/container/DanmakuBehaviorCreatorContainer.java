package github.thelawf.gensokyoontology.client.gui.container;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.ListCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.ToggleWidget;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.jetbrains.annotations.Nullable;

public class DanmakuBehaviorCreatorContainer extends Container {

    private static final ResourceLocation MAIN = new ResourceLocation(GensokyoOntology.MODID +
            "textures/client/dan_creator_main.png");

    private static final ResourceLocation SIDE_BAR = new ResourceLocation(GensokyoOntology.MODID +
            "textures/client/dan_creator_sidebar.png");

    private static final ResourceLocation TOOL_TAB = new ResourceLocation(GensokyoOntology.MODID +
            "textures/client/dan_creator_tab.png");

    private static final ResourceLocation SCRIPT_TYPE = new ResourceLocation(GensokyoOntology.MODID +
            "textures/client/script_type.png");

    private static final ResourceLocation BUTTON = new ResourceLocation(GensokyoOntology.MODID +
            "textures/client/dan_creator_buttons.png");

    private ImageButton whileLoop;
    private ImageButton foriLoop;
    private ImageButton ifBranch;
    private ImageButton elseBranch;
    private ImageButton elseIfBranch;

    private TextFieldWidget assignVector3f;
    private TextFieldWidget assignNumber;
    private TextFieldWidget assignList;
    private TextFieldWidget assignMap;
    private TextFieldWidget addComment;

    private ToggleWidget trueOrFalse;

    private String operation;

    protected DanmakuBehaviorCreatorContainer(@Nullable ContainerType<?> type, int id) {
        super(type, id);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return false;
    }
}
