package github.thelawf.gensokyoontology.client.renderer.world;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraftforge.client.ISkyRenderHandler;
import net.minecraftforge.client.MinecraftForgeClient;

import static org.lwjgl.opengl.GL11.*;

public class BloodySkyRenderer implements ISkyRenderHandler {
    @Override
    @SuppressWarnings("deprecation")
    public void render(int ticks, float partialTicks, MatrixStack matrixStack, ClientWorld world, Minecraft mc) {
        RenderSystem.clearColor(1.0F, 0.0F, 0.0F, 1.0F);
        RenderSystem.clear(GL_COLOR_BUFFER_BIT, MinecraftForgeClient.getRenderLayer().isColoredOutlineBuffer());
        RenderSystem.matrixMode(GL_PROJECTION);
        RenderSystem.loadIdentity();
        float f = 30.0F;
        RenderSystem.ortho(-f, f, -f, f, 100.0F, 300.0F);
        RenderSystem.matrixMode(GL_MODELVIEW);
        RenderSystem.loadIdentity();
        RenderSystem.translatef(0.0F, 0.0F, -200.0F);
    }

    public static void startRender() {

    }

    public static void stopRender() {

    }
}
