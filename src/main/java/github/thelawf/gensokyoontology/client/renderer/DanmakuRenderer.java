package github.thelawf.gensokyoontology.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.projectile.AbstractDanmakuEntity;
import github.thelawf.gensokyoontology.common.entity.projectile.DanmakuShotEntity;
import github.thelawf.gensokyoontology.common.util.danmaku.DanmakuType;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Vector3f;

public class DanmakuRenderer extends SpriteRenderer<AbstractDanmakuEntity> {
    public static final String PATH = "textures/entity";
    private static final ResourceLocation DANMAKU_RED = new ResourceLocation(GensokyoOntology.MODID, PATH);
    // private static final RenderType RENDER_TYPE = RenderType.getItemEntityTranslucentCull(DANMAKU_TEX);

    private final ItemRenderer itemRenderer;
    private float scale;

    public DanmakuRenderer(EntityRendererManager renderManagerIn, ItemRenderer itemRendererIn, float scale) {
        super(renderManagerIn, itemRendererIn);
        this.itemRenderer = itemRendererIn;
        this.scale = scale;
    }

    public void render(AbstractDanmakuEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        if (entityIn.ticksExisted >= 2 || !(this.renderManager.info.getRenderViewEntity().getDistanceSq(entityIn) < 12.25D)) {
            matrixStackIn.push();
            matrixStackIn.translate(0.5, 0.5, 0.5);
            matrixStackIn.scale(this.scale, this.scale, this.scale);
            matrixStackIn.rotate(this.renderManager.getCameraOrientation());
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F));
            this.itemRenderer.renderItem(entityIn.getItem(), ItemCameraTransforms.TransformType.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
            matrixStackIn.translate(0, 0, 0);
            matrixStackIn.pop();
        }
    }

}
