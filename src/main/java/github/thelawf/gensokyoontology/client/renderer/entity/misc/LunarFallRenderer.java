package github.thelawf.gensokyoontology.client.renderer.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.api.util.Color4i;
import github.thelawf.gensokyoontology.common.entity.misc.LunarFallEntity;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.common.util.math.GeometryUtil;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;

public class LunarFallRenderer extends EntityRenderer<LunarFallEntity> {

    public static final ResourceLocation BEAM_TEX = GensokyoOntology.withRL("textures/entity/beam_overlay.png");
    public static final ResourceLocation IMPACT_TEX = GensokyoOntology.withRL("textures/entity/impact.png");

    public LunarFallRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public ResourceLocation getEntityTexture(LunarFallEntity entity) {
        return entity.ticksExisted > 450 ? BEAM_TEX : IMPACT_TEX;
    }

    @Override
    public void render(LunarFallEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);

        float height = GSKOMathUtil.lerpTicks(partialTicks, LunarFallEntity.PREPARATION,
                entityIn.ticksExisted, 100, 0);
        float radius = GSKOMathUtil.lerpTicks(partialTicks, LunarFallEntity.MAX_TICK - LunarFallEntity.PREPARATION,
                entityIn.ticksExisted, LunarFallEntity.MIN_RADIUS, LunarFallEntity.MAX_RADIUS);

        if (entityIn.ticksExisted <= LunarFallEntity.PREPARATION) {
            GSKOUtil.log(entityIn.ticksExisted + " ticks");
            IVertexBuilder beamVertex = bufferIn.getBuffer(RenderType.getEntityTranslucentCull(BEAM_TEX));
            matrixStackIn.push();
            matrixStackIn.translate(0.0D, 100.0D, 0.0D);
            GSKOMathUtil.rotateMatrixToLookVec(matrixStackIn, new Vector3d(0, -1, 0));
            GeometryUtil.texturedCylinder(beamVertex, matrixStackIn, packedLightIn,
                    0.2F, height, 6, Color4i.of(140, 90, 230, 150));
            matrixStackIn.pop();
            return;
        }


//        IVertexBuilder impactVertex = bufferIn.getBuffer(RenderType.getEntityTranslucent(IMPACT_TEX));
//        matrixStackIn.push();
//        GeometryUtil.renderCircle(impactVertex, matrixStackIn.getLast().getMatrix(), new Vector3f(0,0,0),
//                radius, 16, 255, 255, 255, 255, false);
//        matrixStackIn.pop();
    }
}
