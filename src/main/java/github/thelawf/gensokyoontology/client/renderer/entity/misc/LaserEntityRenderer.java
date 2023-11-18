package github.thelawf.gensokyoontology.client.renderer.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.GSKORenderTypes;
import github.thelawf.gensokyoontology.common.entity.misc.LaserSourceEntity;
import github.thelawf.gensokyoontology.common.util.Vec3fConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.GuardianEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LaserEntityRenderer extends EntityRenderer<LaserSourceEntity> {

    public static final RenderType LASER_TYPE = RenderType.getEntityTranslucent(
            GensokyoOntology.withRL("textures/entity/laser_beam.png"));

    protected LaserEntityRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public ResourceLocation getEntityTexture(LaserSourceEntity entity) {
        return null;
    }

    private static void drawLaser(IVertexBuilder builder, Matrix4f matrix4f, int r, int g, int b) {
        builder.pos(matrix4f, 0,0,1).color(r, g, b, 255).endVertex();
        builder.pos(matrix4f, 1,0,1).color(r, g, b, 255).endVertex();
        builder.pos(matrix4f, 1,1,1).color(r, g, b, 255).endVertex();
    }

    private static void drawLaser(IVertexBuilder builder, Matrix4f matrix4f, Vector3f start, Vector3f end, float r, float g, float b, float alpha) {
        builder.pos(matrix4f, start.getX(), start.getY(), start.getZ()).color(r, g, b, alpha).endVertex();
        builder.pos(matrix4f, end.getX(), end.getY(), end.getZ()).color(r, g, b, alpha).endVertex();
    }

    private static void drawLaser(IVertexBuilder builder, Matrix4f matrix4f, Matrix3f matrix3f,
                                  float dx1, float dy1, float dz1, int r, int g, int b, float u, float v) {
        builder.pos(matrix4f, dx1, dy1, dz1).color(r, g, b, 255).tex(u, v).overlay(OverlayTexture.NO_OVERLAY).lightmap(15728880).normal(matrix3f, 0.0F, 1.0F, 0.0F).endVertex();
    }

    @Override
    public void render(LaserSourceEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        IRenderTypeBuffer.Impl buffer = Minecraft.getInstance().getRenderTypeBuffers().getBufferSource();
        IVertexBuilder laser = buffer.getBuffer(LASER_TYPE);
        // IVertexBuilder lineVertex = buffer.getBuffer(GSKORenderTypes.LASER_LINE);
        // IVertexBuilder beamVertex = buffer.getBuffer(GSKORenderTypes.LASER_BEAM);
        // IVertexBuilder diffuseVertex = buffer.getBuffer(GSKORenderTypes.LASER_DIFFUSE);

        Matrix4f matrix4f = matrixStackIn.getLast().getMatrix();
        Vector3d vector3d = entityIn.getLookVec().scale(20);
        Vector3f lookVec = new Vector3f(toVector3f(vector3d).getX(), 0F, toVector3f(vector3d).getZ());

    matrixStackIn.push();
        drawLaser(laser, matrix4f, Vec3fConstants.ZERO, lookVec, 1F, 1F, 1F, 1F);
        // drawLaser(beamVertex, matrix4f, Vec3fConstants.ZERO, lookVec, 0F, 0F, 0F, 1F);
        // drawLaser(diffuseVertex, matrix4f, Vec3fConstants.ZERO, lookVec, 1F, 0F, 0F, 0.5F);
        matrixStackIn.pop();
    }

    private static Vector3f toVector3f(Vector3d vector3d) {
        return new Vector3f((float) vector3d.x, (float) vector3d.y, (float) vector3d.z);
    }

    private Vector3d getPosition(LivingEntity entityLivingBaseIn, double p_177110_2_, float p_177110_4_) {
        double d0 = MathHelper.lerp(p_177110_4_, entityLivingBaseIn.lastTickPosX, entityLivingBaseIn.getPosX());
        double d1 = MathHelper.lerp(p_177110_4_, entityLivingBaseIn.lastTickPosY, entityLivingBaseIn.getPosY()) + p_177110_2_;
        double d2 = MathHelper.lerp(p_177110_4_, entityLivingBaseIn.lastTickPosZ, entityLivingBaseIn.getPosZ());
        return new Vector3d(d0, d1, d2);
    }

    /** Render laser using Mojang's shit. <br>
     * In GuardianRenderer, Mojang official use these codes below only for rendering
     * Guardian Entity's laser.
     */
    private void renderLaserUsingMojangsShit(GuardianEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn){
        LivingEntity livingentity = entityIn.getTargetedEntity();
        if (livingentity != null) {
            float scale = entityIn.getAttackAnimationScale(partialTicks);
            float f1 = (float)entityIn.world.getGameTime() + partialTicks;
            float f2 = f1 * 0.5F % 1.0F;
            float eyeHeight = entityIn.getEyeHeight();
            matrixStackIn.push();
            matrixStackIn.translate(0.0D, eyeHeight, 0.0D);
            Vector3d vector3d = this.getPosition(livingentity, (double)livingentity.getHeight() * 0.5D, partialTicks);
            Vector3d vector3d1 = this.getPosition(entityIn, eyeHeight, partialTicks);
            Vector3d vector3d2 = vector3d.subtract(vector3d1);
            float f4 = (float)(vector3d2.length() + 1.0D);
            vector3d2 = vector3d2.normalize();
            float f5 = (float)Math.acos(vector3d2.y);
            float f6 = (float)Math.atan2(vector3d2.z, vector3d2.x);
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees((((float)Math.PI / 2F) - f6) * (180F / (float)Math.PI)));
            matrixStackIn.rotate(Vector3f.XP.rotationDegrees(f5 * (180F / (float)Math.PI)));
            float f7 = f1 * 0.05F * -1.5F;
            float f8 = scale * scale;
            int j = 64 + (int)(f8 * 191.0F);
            int k = 32 + (int)(f8 * 191.0F);
            int l = 128 - (int)(f8 * 64.0F);
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
            IVertexBuilder ivertexbuilder = bufferIn.getBuffer(LASER_TYPE);
            MatrixStack.Entry matrixstack$entry = matrixStackIn.getLast();
            Matrix4f matrix4f = matrixstack$entry.getMatrix();
            Matrix3f matrix3f = matrixstack$entry.getNormal();

            // Idea都看不下去了，帮忙模块化成了下面的样子：
            draw(f4, j, k, l, f19, f20, f21, f22, f29, f30, ivertexbuilder, matrix4f, matrix3f);
            draw(f4, j, k, l, f23, f24, f25, f26, f29, f30, ivertexbuilder, matrix4f, matrix3f);
            float f31 = 0.0F;
            if (entityIn.ticksExisted % 2 == 0) {
                f31 = 0.5F;
            }

            drawLaser(ivertexbuilder, matrix4f, matrix3f, f11, f4, f12, j, k, l, 0.5F, f31 + 0.5F);
            drawLaser(ivertexbuilder, matrix4f, matrix3f, f13, f4, f14, j, k, l, 1.0F, f31 + 0.5F);
            drawLaser(ivertexbuilder, matrix4f, matrix3f, f17, f4, f18, j, k, l, 1.0F, f31);
            drawLaser(ivertexbuilder, matrix4f, matrix3f, f15, f4, f16, j, k, l, 0.5F, f31);
            matrixStackIn.pop();
        }
    }

    private void draw(float f4, int j, int k, int l, float f19, float f20, float f21, float f22, float f29, float f30, IVertexBuilder ivertexbuilder, Matrix4f matrix4f, Matrix3f matrix3f) {
        drawLaser(ivertexbuilder, matrix4f, matrix3f, f19, f4, f20, j, k, l, 0.4999F, f30);
        drawLaser(ivertexbuilder, matrix4f, matrix3f, f19, 0.0F, f20, j, k, l, 0.4999F, f29);
        drawLaser(ivertexbuilder, matrix4f, matrix3f, f21, 0.0F, f22, j, k, l, 0.0F, f29);
        drawLaser(ivertexbuilder, matrix4f, matrix3f, f21, f4, f22, j, k, l, 0.0F, f30);
    }
}
