package github.thelawf.gensokyoontology.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.fonts.Font;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class GSKOMissionHud extends AbstractGui {
    private final int width;
    private final int height;
    private final Minecraft minecraft;
    private final ResourceLocation HUD = new ResourceLocation(GensokyoOntology.MODID, "textures/client/hud.png");
    private MatrixStack matrixStack;

    private String description;

    public GSKOMissionHud(MatrixStack matrixStack, String description) {
        this.width = Minecraft.getInstance().getMainWindow().getScaledWidth();
        this.height = Minecraft.getInstance().getMainWindow().getScaledHeight();
        this.minecraft = Minecraft.getInstance();
        this.matrixStack = matrixStack;
        this.description = description;
    }

    public void setMatrixStack(MatrixStack stack) {
        this.matrixStack = stack;
    }

    @SuppressWarnings("deprecation")
    public void render() {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(HUD);
        // drawCenteredString(matrixStack, this.minecraft.fontRenderer, new TranslationTextComponent(
        //         "hud." + GensokyoOntology.MODID + ".mission." + this.description), width / 2 , 85, 199999);
        blit(matrixStack, width / 2 - 16, height / 2 - 64, 0, 0, 32, 32, 32, 32);
    }
}
