package github.thelawf.gensokyoontology.client.gui.screen.script;

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

    protected ScriptBuilderScreen(ITextComponent titleIn, ItemStack stack) {
        super(titleIn);
        this.stack = stack;
    }

}
