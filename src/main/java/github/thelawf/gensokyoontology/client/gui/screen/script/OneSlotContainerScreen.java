package github.thelawf.gensokyoontology.client.gui.screen.script;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.container.script.OneSlotContainer;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

public abstract class OneSlotContainerScreen extends LineralContainerScreen<OneSlotContainer> {
    protected ItemStack stack;
    protected Button saveBtn;
    protected final ITextComponent fieldName = GensokyoOntology.withTranslation("gui.", ".script_builder.fieldName");
    protected ITextComponent saveText = GensokyoOntology.withTranslation("gui.", ".script.button.save");
    public OneSlotContainerScreen(OneSlotContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        this.blit(matrixStack, this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }
}
