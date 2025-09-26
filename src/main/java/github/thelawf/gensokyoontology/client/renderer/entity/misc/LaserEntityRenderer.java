package github.thelawf.gensokyoontology.client.renderer.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.client.GSKORenderTypes;
import github.thelawf.gensokyoontology.common.entity.misc.Laser;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// 种子：-8902379324748255918 坐标：1519 76 441 维度：幻想乡 群系：博丽神社周边
@OnlyIn(Dist.CLIENT)
public class LaserEntityRenderer extends EntityRenderer<Laser> {

    public static final ResourceLocation LASER_SOURCE_TEX = GSKOUtil.withRL("textures/entity/laser_source.png");
    public static final ResourceLocation LASER_BEAM_TEX = GSKOUtil.withRL("textures/entity/laser_beam_1.png");
    public static final RenderType LASER_BEAM = RenderType.getEntityTranslucent(LASER_BEAM_TEX);

    public LaserEntityRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    @NotNull
    public ResourceLocation getEntityTexture(@NotNull Laser entity) {
        return LASER_SOURCE_TEX;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void render(@NotNull Laser entityIn, float entityYaw, float partialTicks, @NotNull MatrixStack matrixStackIn, @NotNull IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);

        // TODO: 实现激光源头的贴图渲染
        TextureAtlasSprite sprite = Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(LASER_SOURCE_TEX);
        IRenderTypeBuffer.Impl buffer = Minecraft.getInstance().getRenderTypeBuffers().getBufferSource();
        IVertexBuilder builder = buffer.getBuffer(GSKORenderTypes.LASER_LINE);
        float length = entityIn.getRange();
        matrixStackIn.push();
        matrixStackIn.translate(0.0D, entityIn.getEyeHeight(), 0.0D);
        Vector3d vector3d2 = entityIn.getLookVec();
        GSKOMathUtil.rotateMatrixToLookVec(matrixStackIn, vector3d2.scale(-1));

        if (entityIn.ticksExisted <= entityIn.getPreparation()) {
            drawLaser(bufferIn.getBuffer(RenderType.getLightning()), matrixStackIn,
                    length, 1.0f, 1.0F, 1.0F, 0.7F, 0.02f);
            matrixStackIn.pop();
            return;
        }

        drawLaser(bufferIn.getBuffer(RenderType.getLightning()), matrixStackIn,
                length, red(entityIn), green(entityIn), blue(entityIn), alpha(entityIn), 0.15f);
        drawLaser(bufferIn.getBuffer(RenderType.getLightning()), matrixStackIn,
                length, 1.0f, 1.0f, 1.0f, 1.0f, 0.05f);

        matrixStackIn.pop();

    }


    @Override
    public boolean shouldRender(Laser livingEntityIn, ClippingHelper camera, double camX, double camY, double camZ) {
        return super.shouldRender(livingEntityIn, camera, camX, camY, camZ);
    }

    private static void drawSprite(IVertexBuilder builder, Matrix4f matrix4f, TextureAtlasSprite sprite) {
        builder.pos(matrix4f, 0,0,1).color(255, 255, 255, 255).tex(sprite.getMinU(), sprite.getMinV()).lightmap(0, 240).endVertex();
        builder.pos(matrix4f, 1,0,1).color(255, 255, 255, 255).tex(sprite.getMaxU(), sprite.getMinV()).lightmap(0, 240).endVertex();
        builder.pos(matrix4f, 1,1,1).color(255, 255, 255, 255).tex(sprite.getMaxU(), sprite.getMaxV()).lightmap(0, 240).endVertex();
        builder.pos(matrix4f, 0,1,1).color(255, 255, 255, 255).tex(sprite.getMinU(), sprite.getMaxV()).lightmap(0, 240).endVertex();
    }

    private static void drawLaser(IVertexBuilder builder, Matrix4f matrix4f, Matrix3f matrix3f,
                                  float dx1, float dy1, float dz1, int r, int g, int b, float u, float v) {
        builder.pos(matrix4f, dx1, dy1, dz1).color(r, g, b, 255).tex(u, v).overlay(OverlayTexture.NO_OVERLAY).lightmap(15728880).normal(matrix3f, 0.0F, 1.0F, 0.0F).endVertex();
    }

    private void drawLaser(IVertexBuilder vertexBuilder, MatrixStack matrixStack, float length, float red, float green, float blue, float alpha, float thickness) {

        // Front face
        vertex(matrixStack, vertexBuilder, -thickness, 0.0F, -thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, thickness, 0.0F,  -thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, thickness, 0.0F, thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, -thickness, 0.0F, thickness, red, green, blue, alpha);

        vertex(matrixStack, vertexBuilder, -thickness, 0.0F, thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, thickness, 0.0F, thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, thickness, 0.0F, -thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, -thickness, 0.0F, -thickness, red, green, blue, alpha);

        // Back face
        vertex(matrixStack, vertexBuilder, -thickness, length, thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, thickness, length, -thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, thickness, length, thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, -thickness, length, thickness, red, green, blue, alpha);

        vertex(matrixStack, vertexBuilder, -thickness, length, thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, thickness, length, thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, thickness, length, -thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, -thickness, length, -thickness, red, green, blue, alpha);

        // Left face
        vertex(matrixStack, vertexBuilder, -thickness, 0.0F, -thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, -thickness, length, -thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, -thickness, length, thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, -thickness, 0.0F, thickness, red, green, blue, alpha);

        vertex(matrixStack, vertexBuilder, -thickness, 0.0F, thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, -thickness, length, thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, -thickness, length, -thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, -thickness, 0.0F, -thickness, red, green, blue, alpha);

        // Right face
        vertex(matrixStack, vertexBuilder, thickness, 0.0F, -thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, thickness, length, -thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, thickness, length, thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, thickness, 0.0F, thickness, red, green, blue, alpha);

        vertex(matrixStack, vertexBuilder, thickness, 0.0F, thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, thickness, length, thickness,  red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, thickness, length, -thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, thickness, 0.0F, -thickness, red, green, blue, alpha);

        // Top face
        vertex(matrixStack, vertexBuilder, -thickness, 0.0F, thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, thickness, 0.0F, thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, thickness, length, thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, -thickness, length, thickness, red, green, blue, alpha);

        vertex(matrixStack, vertexBuilder, -thickness, length, thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, thickness, length, thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, thickness, 0.0F, thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, -thickness, 0.0F, thickness, red, green, blue, alpha);

        // Bottom face
        vertex(matrixStack, vertexBuilder, -thickness, 0.0F, -thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, thickness, 0.0F, -thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, thickness, length, -thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, -thickness, length, -thickness, red, green, blue, alpha);

        vertex(matrixStack, vertexBuilder, -thickness, length, -thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, thickness, length, -thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, thickness, 0.0F, -thickness, red, green, blue, alpha);
        vertex(matrixStack, vertexBuilder, -thickness, 0.0F, -thickness, red, green, blue, alpha);
    }

    private void vertex(MatrixStack matrixStack, IVertexBuilder vertexBuilder, float x, float y, float z, float red, float green, float blue, float alpha) {
        vertexBuilder.pos(matrixStack.getLast().getMatrix(), x, y, z)
                .color(red, green, blue, alpha)
                .endVertex();
    }


    private void draw(float f4, int j, int k, int l, float f19, float f20, float f21, float f22, float f29, float f30, IVertexBuilder ivertexbuilder, Matrix4f matrix4f, Matrix3f matrix3f) {
        drawLaser(ivertexbuilder, matrix4f, matrix3f, f19, f4, f20, j, k, l, 0.4999F, f30);
        drawLaser(ivertexbuilder, matrix4f, matrix3f, f19, 0.0F, f20, j, k, l, 0.4999F, f29);
        drawLaser(ivertexbuilder, matrix4f, matrix3f, f21, 0.0F, f22, j, k, l, 0.0F, f29);
        drawLaser(ivertexbuilder, matrix4f, matrix3f, f21, f4, f22, j, k, l, 0.0F, f30);
    }

    private float red(Laser entityIn) {
        return (float) entityIn.getRed() / 255;
    }

    private float green(Laser entityIn) {
        return (float) entityIn.getGreen() / 255;
    }

    private float blue(Laser entityIn) {
        return (float) entityIn.getBlue() / 255;
    }

    private float alpha(Laser entityIn) {
        return (float) entityIn.getAlpha() / 255;
    }

    /** Render laser using Mojang's shit. <br>
     * Copy from {@link net.minecraft.client.renderer.entity.GuardianRenderer}. Mojang official uses these codes below only for rendering
     * Guardian Entity's laser.
     */
    @Deprecated
    private void renderLaserUsingGardianLaser(Laser entityIn, @Nullable Vector3d lookVec, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn){
        //LivingEntity livingentity = entityIn.getTargetedEntity();
        IRenderTypeBuffer.Impl buffer = Minecraft.getInstance().getRenderTypeBuffers().getBufferSource();
        IVertexBuilder builder = buffer.getBuffer(RenderType.getLightning());

        float scale = 0.5f;
        float f1 = (float)entityIn.world.getGameTime() + partialTicks;
        float f2 = f1 * 0.5F % 1.0F;
        float eyeHeight = entityIn.getEyeHeight();
        matrixStackIn.push();
        buffer.finish(RenderType.getLightning());
        matrixStackIn.translate(0.0D, eyeHeight, 0.0D);
        Vector3d vector3d = entityIn.getPositionVec();

        Vector3d vector3d1 = entityIn.getLookVec().scale(entityIn.getRange());
        float f4 = (float)(vector3d1.length() + 1.0D);

        // 获取激光光束的发射方向，根据这个方向旋转matrixStack到相应的位置进行渲染
        Vector3d vector3d2 = entityIn.getLookVec();
        float f5 = (float)Math.acos(vector3d2.y);
        float f6 = (float)Math.atan2(vector3d2.z, vector3d2.x);
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(((float)Math.PI / 2 - f6) * (180 / (float)Math.PI)));
        matrixStackIn.rotate(Vector3f.XP.rotationDegrees(f5 * (180 / (float)Math.PI)));

        float f7 = f1 * 0.05F * -1.5F;
        float f8 = scale * scale;

        int j = 255;
        int k = 255;
        int l = 255;

        int r = entityIn.getRed();
        int g = entityIn.getGreen();
        int b = entityIn.getBlue();
        int a = entityIn.getAlpha();

        float f11 = MathHelper.cos(f7 + 2.3561945F) * 0.282F;
        float f12 = MathHelper.sin(f7 + 2.3561945F) * 0.282F;
        float f13 = MathHelper.cos(f7 + ((float)Math.PI / 4F)) * 0.282F;
        float f14 = MathHelper.sin(f7 + ((float)Math.PI / 4F)) * 0.282F;
        float f15 = MathHelper.cos(f7 + 3.926991F) * 0.282F;
        float f16 = MathHelper.sin(f7 + 3.926991F) * 0.282F;
        float f17 = MathHelper.cos(f7 + 5.4977875F) * 0.282F;
        float f18 = MathHelper.sin(f7 + 5.4977875F) * 0.282F;
        float f19 = MathHelper.cos(f7 + (float)Math.PI) * 0.2F;
        float f20 = MathHelper.sin(f7 + (float)Math.PI) * 0.2F;
        float f21 = MathHelper.cos(f7 + 0.0F) * 0.2F;
        float f22 = MathHelper.sin(f7 + 0.0F) * 0.2F;
        float f23 = MathHelper.cos(f7 + ((float)Math.PI / 2F)) * 0.2F;
        float f24 = MathHelper.sin(f7 + ((float)Math.PI / 2F)) * 0.2F;
        float f25 = MathHelper.cos(f7 + ((float)Math.PI * 1.5F)) * 0.2F;
        float f26 = MathHelper.sin(f7 + ((float)Math.PI * 1.5F)) * 0.2F;
        float f29 = -1.0F + f2;
        float f30 = f4 * 2.5F + f29;
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(LASER_BEAM);
        MatrixStack.Entry matrixstack$entry = matrixStackIn.getLast();
        Matrix4f matrix4f = matrixstack$entry.getMatrix();
        Matrix3f matrix3f = matrixstack$entry.getNormal();

        draw(f4, r, g, b, f19, f20, f21, f22, f29, f30, ivertexbuilder, matrix4f, matrix3f);
        draw(f4, r, g, b, f23, f24, f25, f26, f29, f30, ivertexbuilder, matrix4f, matrix3f);
        float f31 = 0.0F;
        if (entityIn.ticksExisted % 2 == 0) {
            f31 = 0.5F;
        }
        drawLaser(ivertexbuilder, matrix4f, matrix3f, f11, f4, f12, r, g, b, 0.5F, f31 + 0.5F);
        drawLaser(ivertexbuilder, matrix4f, matrix3f, f13, f4, f14, r, g, b, 1.0F, f31 + 0.5F);
        drawLaser(ivertexbuilder, matrix4f, matrix3f, f17, f4, f18, r, g, b, 1.0F, f31);
        drawLaser(ivertexbuilder, matrix4f, matrix3f, f15, f4, f16, r, g, b, 0.5F, f31);
        matrixStackIn.pop();
    }

}
