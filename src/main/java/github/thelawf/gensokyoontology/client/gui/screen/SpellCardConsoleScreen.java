package github.thelawf.gensokyoontology.client.gui.screen;

import com.google.gson.Gson;
import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.gui.container.SpellCardConsoleContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

import java.util.List;
import java.util.Stack;

// TODO: 面向硬核自定义符卡行为的玩家而写的符卡控制台面板，用于接受玩家的自定义行为
public class SpellCardConsoleScreen extends ContainerScreen<SpellCardConsoleContainer> {

    public SpellCardConsoleScreen(SpellCardConsoleContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected void init() {
        super.init();

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {

    }

    private Button createOperationOptionBtn(int x, int y, ITextComponent title) {
        return new Button(x,y,60,20, title, button -> {});
    }

    private Button createInstanceOptionBtn(int x, int y, ITextComponent title) {
        return new Button(x,y,80,20, title, button -> {});
    }
}
