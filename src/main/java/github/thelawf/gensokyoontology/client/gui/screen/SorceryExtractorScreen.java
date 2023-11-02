package github.thelawf.gensokyoontology.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.gui.container.SorceryExtractorContainer;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.command.impl.KickCommand;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import org.jetbrains.annotations.NotNull;

public class SorceryExtractorScreen extends ContainerScreen<SorceryExtractorContainer> {
    public static final ResourceLocation SORCERY_GUI_TEXTURE = new ResourceLocation(
            GensokyoOntology.MODID, "textures/gui/sorcery_extractor.png"
    );
    public SorceryExtractorScreen(SorceryExtractorContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.xSize = 217;
        this.ySize = 211;
        this.playerInventoryTitleX = 15;
        this.playerInventoryTitleY = 118;
    }

    @Override
    public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        if (this.minecraft == null) return;
        this.minecraft.getTextureManager().bindTexture(SORCERY_GUI_TEXTURE);

        int left = this.guiLeft;
        int top = this.guiTop;
        this.blit(matrixStack, left, top, 0,0, 256, 256);
    }
}
