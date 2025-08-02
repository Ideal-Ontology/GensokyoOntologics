package github.thelawf.gensokyoontology.client.renderer.world;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.world.GSKOWorldUtil;
import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ISkyRenderHandler;
import org.lwjgl.opengl.GL11;

@OnlyIn(Dist.CLIENT)
public class ScarletSkyRenderer implements ISkyRenderHandler {
    @Override
    @OnlyIn(Dist.CLIENT)
    @SuppressWarnings("deprecation")
    public void render(int ticks, float partialTicks, MatrixStack matrixStack, ClientWorld world, Minecraft mc) {
        PlayerEntity player = Minecraft.getInstance().player;
        if (player == null) return;
        this.renderTexture(ticks, partialTicks, matrixStack, world, mc);
    }

    public void renderTexture(int ticks, float partialTicks, MatrixStack matrixStack, ClientWorld world, Minecraft mc) {

        // 993312
        RenderSystem.disableTexture();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        RenderSystem.clearColor(0.93F, 0.0F, 0.0F, 0.5F); // 设置纯红色背景
        RenderSystem.clear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT, false); // 清除原有缓冲

        // 设置天空颜色
        float r = 0.93F;
        float g = 0.1F;
        float b = 0.12F;
        float a = 0.8F;

        // 绑定天空纹理
        // if(skyTexture != null) {
        //     RenderSystem.enableTexture();
        //     mc.getTextureManager().bind(skyTexture);
        // }

        // 构建天空网格
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        // 开始绘制天空
        buffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        matrixStack.push();
        for(int i = 0; i <= 8; i++) renderSkyColor(buffer, matrixStack, r, g, b, a);
        matrixStack.pop();
        tessellator.draw();

        // 恢复渲染状态
        RenderSystem.depthMask(true);
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
        RenderSystem.enableDepthTest();
    }

    private void renderSkyColor(BufferBuilder buffer, MatrixStack matrixStack,
                                float r, float g, float b, float a) {
        Matrix4f matrix = matrixStack.getLast().getMatrix();

        buffer.pos(matrix, -100F, -99F, -100F).color(r, g, b, a).endVertex();
        buffer.pos(matrix, -100F, -99F,  100F).color(r, g, b, a).endVertex();
        buffer.pos(matrix,  100F, -99F,  100F).color(r, g, b, a).endVertex();
        buffer.pos(matrix,  100F, -99F, -100F).color(r, g, b, a).endVertex();
    }

    private void renderSkyTexture(BufferBuilder buffer, MatrixStack matrixStack,
                                float offsetX, float offsetZ,
                                float r, float g, float b, float a) {
        Matrix4f matrix = matrixStack.getLast().getMatrix();
        float size = 32.0F;

        // 计算UV坐标 (纹理无缝衔接)
        float u0 = (offsetX < 0) ? 1.0F : 0.0F;
        float u1 = u0 + 0.125F; // 使用1/8纹理

        float v0 = (offsetZ < 0) ? 1.0F : 0.0F;
        float v1 = v0 + 0.125F;

        // buffer.pos(matrix, offsetX,        64.0F, offsetZ       ).tex(u0, v0).color(r, g, b, a).endVertex();
        // buffer.pos(matrix, offsetX + size, 64.0F, offsetZ       ).tex(u1, v0).color(r, g, b, a).endVertex();
        // buffer.pos(matrix, offsetX + size, 64.0F, offsetZ + size).tex(u1, v1).color(r, g, b, a).endVertex();
        // buffer.pos(matrix, offsetX,        64.0F, offsetZ + size).tex(u0, v1).color(r, g, b, a).endVertex();

        buffer.pos(matrix, -100F, -100F, -100F).color(r, g, b, a).endVertex();
        buffer.pos(matrix, -100F, -100F,  100F).color(r, g, b, a).endVertex();
        buffer.pos(matrix,  100F, -100F,  100F).color(r, g, b, a).endVertex();
        buffer.pos(matrix,  100F, -100F, -100F).color(r, g, b, a).endVertex();
    }

    /*
    private void drawSun(Minecraft mc, MatrixStack matrixStack, float partialTicks) {
        // 计算太阳位置
        float sunAngle = mc.level.getTimeOfDay(partialTicks);
        float sunSize = 30.0F;

        // 绑定太阳纹理
        mc.getTextureManager().bind(new ResourceLocation("textures/environment/sun.png"));

        matrixStack.pushPose();
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(-90.0F)); // 指向南
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(sunAngle * 360.0F)); // 根据时间旋转

        // 设置太阳颜色 (根据角度调整)
        float sunR = Math.min(1.0F, skyColor.x() + 0.5F);
        float sunG = Math.min(1.0F, skyColor.y() + 0.5F);
        float sunB = Math.min(1.0F, skyColor.z() + 0.5F);

        // 绘制太阳
        BufferBuilder buffer = Tessellator.getInstance().getBuilder();
        buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);

        buffer.vertex(-sunSize, 100.0, -sunSize).uv(0.0F, 0.0F).color(sunR, sunG, sunB, 1.0F).endVertex();
        buffer.vertex(sunSize, 100.0, -sunSize).uv(1.0F, 0.0F).color(sunR, sunG, sunB, 1.0F).endVertex();
        buffer.vertex(sunSize, 100.0, sunSize).uv(1.0F, 1.0F).color(sunR, sunG, sunB, 1.0F).endVertex();
        buffer.vertex(-sunSize, 100.0, sunSize).uv(0.0F, 1.0F).color(sunR, sunG, sunB, 1.0F).endVertex();

        buffer.end();
        BufferUploader.end(buffer);
        matrixStack.popPose();
    }

     */
}
