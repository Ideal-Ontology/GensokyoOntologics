package github.thelawf.gensokyoontology.client.gui.screen.script;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

public class VariableBuilderScreen extends ConstBuilderScreen {
    public VariableBuilderScreen(ITextComponent titleIn, ItemStack stack) {
        super(titleIn, stack);
    }

    @Override
    protected void init() {
        if (this.minecraft == null) return;
        if (this.minecraft.player == null) return;
    }
}