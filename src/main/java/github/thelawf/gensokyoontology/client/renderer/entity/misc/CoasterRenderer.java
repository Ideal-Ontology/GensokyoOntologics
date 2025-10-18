package github.thelawf.gensokyoontology.client.renderer.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.client.model.CoasterModel;
import github.thelawf.gensokyoontology.common.entity.misc.CoasterVehicle;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

public class CoasterRenderer extends EntityRenderer<CoasterVehicle> {
    public static final ResourceLocation COASTER_TEXTURE = new ResourceLocation("textures/entity/coaster_texture.png");
    private final CoasterModel model;
    public CoasterRenderer(EntityRendererManager renderManager) {
        super(renderManager);
        this.model = new CoasterModel();
    }

    @Override
    public ResourceLocation getEntityTexture(CoasterVehicle entity) {
        return COASTER_TEXTURE;
    }

    @Override
    public void render(CoasterVehicle entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        this.model.render(matrixStackIn, bufferIn.getBuffer(RenderType.getEntityTranslucentCull(COASTER_TEXTURE)),
                packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}
