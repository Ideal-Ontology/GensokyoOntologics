package github.thelawf.gensokyoontology.common.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.common.entity.FlyingSwordEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class FlyingSwordRenderer extends EntityRenderer<FlyingSwordEntity> {

    public static final ResourceLocation FLYING_SWORD_TEX = new ResourceLocation("textures/entity/metaphysics_sword.png");
    public static final RenderType FLYING_SWORD_RENDER = RenderType.getEntityCutoutNoCull(FLYING_SWORD_TEX);


    protected FlyingSwordRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public void render(FlyingSwordEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.push();
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) - 90.0F));
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch)));

        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getEntityTexture(FlyingSwordEntity entity) {
        return FLYING_SWORD_TEX;
    }
}
