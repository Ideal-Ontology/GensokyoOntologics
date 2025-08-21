package github.thelawf.gensokyoontology.client.gui.screen.skill;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;

public abstract class ModeSwitchScreen extends Screen {

    protected int guiLeft = 0;
    protected int guiTop = 0;
    protected int xSize;
    protected int ySize;

    protected ModeSwitchScreen(ITextComponent titleIn) {
        super(titleIn);
    }

    @Override
    protected void init() {
        super.init();
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public abstract void switchMode(int index);

}
