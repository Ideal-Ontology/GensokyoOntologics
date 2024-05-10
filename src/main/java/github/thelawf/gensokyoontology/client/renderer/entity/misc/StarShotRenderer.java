package github.thelawf.gensokyoontology.client.renderer.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.math.vector.Vector3f;
import org.jetbrains.annotations.NotNull;

public class StarShotRenderer extends SpriteRenderer<AbstractDanmakuEntity> {

    private final ItemRenderer itemRenderer;
    private final float scale;

    public StarShotRenderer(EntityRendererManager manager, ItemRenderer itemRenderer, float scale, boolean p_i226035_4_) {
        super(manager, itemRenderer, scale, p_i226035_4_);
        this.itemRenderer = itemRenderer;
        this.scale = scale;
    }

    @Override
    public void render(@NotNull AbstractDanmakuEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.push();

        float speed = 5;
        float angle = GSKOMathUtil.lerpTicks(partialTicks, entityIn.getLifespan(), entityIn.ticksExisted, 0f, 360F * speed);

        matrixStackIn.scale(this.scale, this.scale, this.scale);
        matrixStackIn.rotate(this.renderManager.getCameraOrientation());
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F));
        matrixStackIn.rotate(Vector3f.XP.rotationDegrees(angle));
        //matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw)));
        //matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch)));

        itemRenderer.renderItem(entityIn.getItem(), ItemCameraTransforms.TransformType.GUI,
                packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
        matrixStackIn.pop();
    }

}
