package github.thelawf.gensokyoontology.client.renderer.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.misc.MasterSparkEntity;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.common.util.math.GeometryUtil;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import org.jetbrains.annotations.NotNull;

public class MasterSparkRenderer extends EntityRenderer<MasterSparkEntity> {

    public static final ResourceLocation TEXTURE = GensokyoOntology.withRL("textures/entity/master_spark_overlay.png");
    public MasterSparkRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public void render(MasterSparkEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        IVertexBuilder builder = bufferIn.getBuffer(RenderType.getLightning());
        matrixStackIn.push();
        Matrix4f matrix4f = matrixStackIn.getLast().getMatrix();
        GSKOMathUtil.rotateMatrixToLookVec(matrixStackIn, entityIn.getLookVec());
        GeometryUtil.renderSphere(builder, matrix4f, 12, 12, 10, 1.f, 0.f, 0.f, 0.6f);
        GeometryUtil.renderCylinder(builder, matrix4f, 1.f, 10, 16, 1.f, 1.f, 0.f, 0.6f);
        matrixStackIn.pop();
    }

    @Override
    @NotNull
    public ResourceLocation getEntityTexture(@NotNull MasterSparkEntity entity) {
        return TEXTURE;
    }
}
