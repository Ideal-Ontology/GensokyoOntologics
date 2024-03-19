package github.thelawf.gensokyoontology.client.gui.screen.script;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.container.script.ScriptBuilderContainer;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

public abstract class ScriptContainerScreen extends LineralContainerScreen {
    protected Button saveBtn;

    protected ItemStack stack;
    protected final ITextComponent fieldName = GensokyoOntology.withTranslation("gui.", ".script_builder.fieldName");
    protected ITextComponent saveText = GensokyoOntology.withTranslation("gui.", ".script.button.save");
    public ScriptContainerScreen(ScriptBuilderContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    public boolean isPauseScreen() {
        return super.isPauseScreen();
    }
}
