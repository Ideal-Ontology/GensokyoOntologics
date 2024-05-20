package github.thelawf.gensokyoontology.client.gui.screen.skill;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class MultiSelectScreen extends Screen {
    protected MultiSelectScreen(ITextComponent titleIn) {
        super(titleIn);
    }
}
