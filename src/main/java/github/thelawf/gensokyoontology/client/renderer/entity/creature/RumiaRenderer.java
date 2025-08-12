package github.thelawf.gensokyoontology.client.renderer.entity.creature;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.GSKORenderTypes;
import github.thelawf.gensokyoontology.client.model.monster.RumiaModel;
import github.thelawf.gensokyoontology.common.entity.monster.RumiaEntity;
import github.thelawf.gensokyoontology.common.util.math.GeometryUtil;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class RumiaRenderer extends MobRenderer<RumiaEntity, RumiaModel> {
    public static final ResourceLocation TEXTURE = GensokyoOntology.withRL("textures/entity/rumia_texture.png");

    public RumiaRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new RumiaModel(), 0.8f);
    }

    @Override
    public ResourceLocation getEntityTexture(RumiaEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(RumiaEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        int blockLight = this.getBlockLight(packedLightIn);
        if (blockLight < 10){
            matrixStackIn.push();
            GeometryUtil.renderSphere(bufferIn.getBuffer(GSKORenderTypes.MULTI_FACE_SOLID), matrixStackIn.getLast().getMatrix(),
                    16, 16, 5F, 0F, 0F, 0F, 1F);
            matrixStackIn.pop();
        }
    }

    private int getBlockLight(int packedLight) {
        return packedLight & 0xFFFF;
    }
}
