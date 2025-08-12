package github.thelawf.gensokyoontology.client.renderer.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.common.entity.Danmaku;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
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
public class NormalVectorRenderer extends SpriteRenderer<Danmaku> {
    private final boolean isUp;
    private ItemRenderer itemRenderer;

    public NormalVectorRenderer(EntityRendererManager manager, ItemRenderer itemRenderer, float scale, boolean p_i226035_4_, boolean isUp) {
        super(manager, itemRenderer, scale, p_i226035_4_);
        this.itemRenderer = itemRenderer;
        this.isUp = isUp;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void render(@NotNull Danmaku entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        //if (entityIn.ticksExisted >= 2 || !(this.renderManager.info.getRenderViewEntity().getDistanceSq(entityIn) < 12.25D)) {
        Pair<Boolean, Float> renderInfo = Danmaku.NORMAL_DANMAKU.get(entityIn.getItem().getItem());
        if (renderInfo == null) {
            this.useSuperRenderer(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
            return;
        }

        float renderScale = renderInfo.getSecond();
        matrixStackIn.push();
        matrixStackIn.scale(renderScale, renderScale, renderScale);
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) - 90f));
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch) - 90f));

        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(90.f));
        if (Danmaku.NORMAL_DANMAKU.get(entityIn.getItem().getItem()).getFirst()) {
            matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(180.F));
        }
        this.itemRenderer.renderItem(entityIn.getItem(), ItemCameraTransforms.TransformType.GUI,
                packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
        matrixStackIn.pop();

    }

    private void useSuperRenderer(@NotNull Danmaku entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn){
        float renderScale = Danmaku.DANMAKU_SIZES.get(entityIn.getItem().getItem());

        if (entityIn.ticksExisted >= 2 || !(this.renderManager.info.getRenderViewEntity().getDistanceSq(entityIn) < 12.25D)) {
            matrixStackIn.push();
            matrixStackIn.scale(renderScale, renderScale, renderScale);
            matrixStackIn.rotate(this.renderManager.getCameraOrientation());
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F));
            this.itemRenderer.renderItem(entityIn.getItem(), ItemCameraTransforms.TransformType.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
            matrixStackIn.pop();
            super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        }
    }
}
