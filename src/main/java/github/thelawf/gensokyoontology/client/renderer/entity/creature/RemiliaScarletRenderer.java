package github.thelawf.gensokyoontology.client.renderer.entity.creature;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.client.model.RemiliaScarletModel;
import github.thelawf.gensokyoontology.common.entity.monster.RemiliaScarletEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;

public class RemiliaScarletRenderer extends BipedRenderer<RemiliaScarletEntity, RemiliaScarletModel> {
    public RemiliaScarletRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new RemiliaScarletModel(0.8F), 0.6F);
    }

    @Override
    public void render(RemiliaScarletEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }
}
