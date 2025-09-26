package github.thelawf.gensokyoontology.client.gui.screen.script;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.common.container.script.ScriptBuilderContainer;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

public abstract class ScriptContainerScreen<C extends ScriptBuilderContainer> extends LineralContainerScreen<C> {
    protected Button saveBtn;

    protected ItemStack stack;
    protected final ITextComponent fieldName = GSKOUtil.translateText("gui.", ".script_builder.fieldName");
    protected ITextComponent saveText = GSKOUtil.translateText("gui.", ".script.button.save");
    public ScriptContainerScreen(C screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    public boolean isPauseScreen() {
        return super.isPauseScreen();
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        if (hoveredSlot != null && hoveredSlot.getHasStack()) {
            ItemStack stack = hoveredSlot.getStack();
            renderTooltip(matrixStack, stack, mouseX, mouseY);
        }
    }
}
