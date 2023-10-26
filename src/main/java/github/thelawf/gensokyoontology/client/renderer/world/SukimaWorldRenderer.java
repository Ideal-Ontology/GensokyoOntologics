package github.thelawf.gensokyoontology.client.renderer.world;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ISkyRenderHandler;

@OnlyIn(Dist.CLIENT)
public class SukimaWorldRenderer implements ISkyRenderHandler {

    public static final ResourceLocation SUKIMA_SKY_TEX = new ResourceLocation(GensokyoOntology.MODID,
            "textures/environment/sukima_sky.png");

    @SuppressWarnings("deprecation")
    private void renderSukimaSky(MatrixStack matrixStackIn, Minecraft mc) {
        RenderSystem.disableAlphaTest();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.depthMask(false);
        mc.getTextureManager().bindTexture(SUKIMA_SKY_TEX);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();

        for(int i = 0; i < 6; ++i) {
            matrixStackIn.push();
            if (i == 1) {
                matrixStackIn.rotate(Vector3f.XP.rotationDegrees(90.0F));
            }

            if (i == 2) {
                matrixStackIn.rotate(Vector3f.XP.rotationDegrees(-90.0F));
            }

            if (i == 3) {
                matrixStackIn.rotate(Vector3f.XP.rotationDegrees(180.0F));
            }

            if (i == 4) {
                matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90.0F));
            }

            if (i == 5) {
                matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(-90.0F));
            }

            Matrix4f matrix4f = matrixStackIn.getLast().getMatrix();
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            bufferbuilder.pos(matrix4f, -100.0F, -100.0F, -100.0F).tex(0.0F, 0.0F).color(40, 40, 40, 255).endVertex();
            bufferbuilder.pos(matrix4f, -100.0F, -100.0F, 100.0F).tex(0.0F, 16.0F).color(40, 40, 40, 255).endVertex();
            bufferbuilder.pos(matrix4f, 100.0F, -100.0F, 100.0F).tex(16.0F, 16.0F).color(40, 40, 40, 255).endVertex();
            bufferbuilder.pos(matrix4f, 100.0F, -100.0F, -100.0F).tex(16.0F, 0.0F).color(40, 40, 40, 255).endVertex();
            tessellator.draw();
            matrixStackIn.pop();
        }

        RenderSystem.depthMask(true);
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
        RenderSystem.enableAlphaTest();
    }

    @Override
    public void render(int ticks, float partialTicks, MatrixStack matrixStack, ClientWorld world, Minecraft mc) {
        renderSukimaSky(matrixStack, mc);
    }
}
