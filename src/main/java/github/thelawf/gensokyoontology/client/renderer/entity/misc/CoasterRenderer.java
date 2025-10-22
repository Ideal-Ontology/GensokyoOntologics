package github.thelawf.gensokyoontology.client.renderer.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.client.model.CoasterModel;
import github.thelawf.gensokyoontology.common.entity.misc.CoasterVehicle;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class CoasterRenderer extends EntityRenderer<CoasterVehicle> {
    public static final ResourceLocation COASTER_TEXTURE = GSKOUtil.withRL("textures/entity/coaster_vehicle.png");
    private final CoasterModel model;
    public CoasterRenderer(EntityRendererManager renderManager) {
        super(renderManager);
        this.model = new CoasterModel();
    }

    @Nonnull
    @Override
    public ResourceLocation getEntityTexture(CoasterVehicle entity) {
        return COASTER_TEXTURE;
    }

    @Override
    public void render(CoasterVehicle entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        Minecraft minecraft = Minecraft.getInstance();
        ClientPlayerEntity player = minecraft.player;

        if (player != null && player.isRidingOrBeingRiddenBy(entityIn)) {
            matrixStackIn.push();
            matrixStackIn.translate(0.0D, -1.0D, 0.0D);
            minecraft.getRenderManager().getRenderer(player).render(player, player.rotationYaw, partialTicks,
                    matrixStackIn, bufferIn, OverlayTexture.NO_OVERLAY);
            matrixStackIn.pop();
        }

        matrixStackIn.push();
        matrixStackIn.translate(0.0D, -1.25D, 0.0D);
        this.model.render(matrixStackIn, bufferIn.getBuffer(RenderType.getEntityTranslucentCull(COASTER_TEXTURE)),
                packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.pop();
    }
}
