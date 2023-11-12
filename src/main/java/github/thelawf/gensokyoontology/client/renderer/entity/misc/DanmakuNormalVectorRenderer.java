package github.thelawf.gensokyoontology.client.renderer.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class DanmakuNormalVectorRenderer extends SpriteRenderer<AbstractDanmakuEntity> {

    private final ItemRenderer itemRenderer;
    private final float scale;

    public DanmakuNormalVectorRenderer(EntityRendererManager manager, ItemRenderer itemRenderer, float scale, boolean p_i226035_4_) {
        super(manager, itemRenderer, scale, p_i226035_4_);
        this.itemRenderer = itemRenderer;
        this.scale = scale;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void render(@NotNull AbstractDanmakuEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        //if (entityIn.ticksExisted >= 2 || !(this.renderManager.info.getRenderViewEntity().getDistanceSq(entityIn) < 12.25D)) {
        matrixStackIn.push();
        matrixStackIn.scale(scale, scale, scale);
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) - 90f));
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch) - 90f));

        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(90.f));
        itemRenderer.renderItem(entityIn.getItem(), ItemCameraTransforms.TransformType.GUI,
                packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
        matrixStackIn.pop();
        // super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);

    }
}
