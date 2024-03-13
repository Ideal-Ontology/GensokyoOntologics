package github.thelawf.gensokyoontology.client.gui.screen.script;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class ScriptBuilderScreen extends LineralLayoutScreen {
    protected Button saveBtn;
    protected Button closeBtn;
    protected Button addBtn;
    protected Button resetBtn;
    protected Button renameBtn;
    protected ItemStack stack;
    protected ITextComponent saveText = GensokyoOntology.withTranslation("gui.", ".script.button.save");

    public ScriptBuilderScreen(ITextComponent titleIn, ItemStack stack) {
        super(titleIn);
        this.stack = stack;
    }

}
