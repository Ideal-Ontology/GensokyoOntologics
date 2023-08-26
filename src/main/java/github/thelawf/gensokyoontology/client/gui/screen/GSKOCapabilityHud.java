package github.thelawf.gensokyoontology.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

public class GSKOCapabilityHud extends AbstractGui {
    private final int width;
    private final int height;
    private final Minecraft minecraft;
    private final ResourceLocation HUD = new ResourceLocation(GensokyoOntology.MODID, "textures/gui/hud.png");
    private MatrixStack matrixStack;
    public GSKOCapabilityHud(MatrixStack matrixStack) {
        this.minecraft = Minecraft.getInstance();
        this.width = Minecraft.getInstance().getMainWindow().getScaledWidth();
        this.height = Minecraft.getInstance().getMainWindow().getScaledHeight();
        this.matrixStack = matrixStack;
    }

    public void setMatrixStack(MatrixStack matrixStack) {
        this.matrixStack = matrixStack;
    }

    @SuppressWarnings("deprecation")
    public void render() {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(HUD);

        float fx = this.width * 0.9f;
        float fy = this.height * 0.9f;

        drawString(matrixStack, this.minecraft.fontRenderer, "10", 20, (int) fx, (int) fy);
        // blit(matrixStack, width / 2 - 16, height / 2 - 64, 0, 0, 32, 32, 32, 32);
    }
}
