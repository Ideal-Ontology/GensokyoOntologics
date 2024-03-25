package github.thelawf.gensokyoontology.client.gui.screen.script;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

public class VariableBuilderScreen extends Screen {
    public VariableBuilderScreen(ITextComponent titleIn) {
        super(titleIn);
    }

    @Override
    protected void init() {
        if (this.minecraft == null) return;
        if (this.minecraft.player == null) return;
    }
}
