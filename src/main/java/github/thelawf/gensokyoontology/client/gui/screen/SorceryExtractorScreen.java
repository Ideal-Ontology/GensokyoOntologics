package github.thelawf.gensokyoontology.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.gui.container.SorceryExtractorContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class SorceryExtractorScreen extends ContainerScreen<SorceryExtractorContainer> {
    public static final ResourceLocation SORCERY_GUI_TEXTURE = new ResourceLocation(
            GensokyoOntology.MODID, "textures/gui/sorcery_extractor.png"
    );
    public SorceryExtractorScreen(SorceryExtractorContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {

    }
}
