package github.thelawf.gensokyoontology.client.renderer.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.misc.MasterSparkEntity;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.common.util.math.GeometryUtil;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.*;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class MasterSparkRenderer extends EntityRenderer<MasterSparkEntity> {

    public static final ResourceLocation TEXTURE = GensokyoOntology.withRL("textures/entity/master_spark_overlay.png");
    public MasterSparkRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public void render(@NotNull MasterSparkEntity entityIn, float entityYaw, float partialTicks, @NotNull MatrixStack matrixStackIn, @NotNull IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        IVertexBuilder builder = bufferIn.getBuffer(RenderType.getLightning());
        float step = 360f / 60;
        float hue = step * GSKOMathUtil.wavyPeriod(entityIn.ticksExisted, 0, 360) / 360f;
        float r = Color.getHSBColor(hue, 0.9f, 0.9f).getRed() / 255f;
        float g = Color.getHSBColor(hue, 0.9f, 0.9f).getGreen() / 255f;
        float b = Color.getHSBColor(hue, 0.9f, 0.9f).getBlue() / 255f;

        matrixStackIn.push();
        Matrix4f matrix4f = matrixStackIn.getLast().getMatrix();
        GSKOMathUtil.rotateMatrixToLookVec(matrixStackIn, entityIn.getLookVec());
        renderSpark(builder, matrix4f, new Vector4f(1, 1, 1, 1), 2f);
        renderSpark(builder, matrix4f, new Vector4f(r, g, b, 1f), 2.5f);
        matrixStackIn.pop();
    }

    private void renderSpark(IVertexBuilder builder, Matrix4f matrix4f, Vector4f color, float size) {
        GeometryUtil.renderSphere(builder, matrix4f, 15, 15, size, color.getX(), color.getY(), color.getZ(), color.getW());
        GeometryUtil.renderCylinder(builder, matrix4f, 25, size, -MasterSparkEntity.DISTANCE, color.getX(), color.getY(), color.getZ(), color.getW());
    }


    @Override
    public boolean shouldRender(MasterSparkEntity livingEntityIn, ClippingHelper camera, double camX, double camY, double camZ) {
        return true;
    }

    @Override
    @NotNull
    public ResourceLocation getEntityTexture(@NotNull MasterSparkEntity entity) {
        return TEXTURE;
    }
}
