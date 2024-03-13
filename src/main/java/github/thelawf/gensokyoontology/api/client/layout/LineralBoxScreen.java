package github.thelawf.gensokyoontology.api.client.layout;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;

import java.util.List;

public abstract class LineralBoxScreen extends Screen{

    public List<List<WidgetConfig>> configs;

    public LineralBoxScreen(ITextComponent title, List<List<WidgetConfig>> configs) {
        super(title);
        this.configs = configs;
    }
}
