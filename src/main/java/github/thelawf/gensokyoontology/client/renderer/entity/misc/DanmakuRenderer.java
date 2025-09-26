package github.thelawf.gensokyoontology.client.renderer.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.common.entity.projectile.Danmaku;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class DanmakuRenderer extends SpriteRenderer<Danmaku> {
    private final boolean isUp;
    public static final Map<Item, String> MAPPED_TEXTURES = Util.make(() -> {
        Map<Item, String> map = new HashMap<>();
        map.put(ItemRegistry.SMALL_SHOT.get(),         "large_shot");
        map.put(ItemRegistry.SMALL_SHOT_RED.get(),     "large_shot_red");
        map.put(ItemRegistry.SMALL_SHOT_ORANGE.get(),  "large_shot_orange");
        map.put(ItemRegistry.SMALL_SHOT_YELLOW.get(),  "large_shot_yellow");
        map.put(ItemRegistry.SMALL_SHOT_GREEN.get(),   "large_shot_green");
        map.put(ItemRegistry.SMALL_SHOT_AQUA.get(),    "large_shot_aqua");
        map.put(ItemRegistry.SMALL_SHOT_BLUE.get(),    "large_shot_blue");
        map.put(ItemRegistry.SMALL_SHOT_PURPLE.get(),  "large_shot_purple");
        map.put(ItemRegistry.SMALL_SHOT_MAGENTA.get(), "large_shot_magenta");

        map.put(ItemRegistry.SMALL_STAR_SHOT.get(),        "star_shot");
        map.put(ItemRegistry.SMALL_STAR_SHOT_RED.get(),    "star_shot_red");
        map.put(ItemRegistry.SMALL_STAR_SHOT_YELLOW.get(), "star_shot_yellow");
        map.put(ItemRegistry.SMALL_STAR_SHOT_GREEN.get(),  "star_shot_green");
        map.put(ItemRegistry.SMALL_STAR_SHOT_AQUA.get(),   "star_shot_aqua");
        map.put(ItemRegistry.SMALL_STAR_SHOT_BLUE.get(),   "star_shot_blue");
        map.put(ItemRegistry.SMALL_STAR_SHOT_PURPLE.get(), "star_shot_purple");

        map.put(ItemRegistry.LARGE_STAR_SHOT.get(),        "star_shot");
        map.put(ItemRegistry.LARGE_STAR_SHOT_RED.get(),    "star_shot_red");
        map.put(ItemRegistry.LARGE_STAR_SHOT_YELLOW.get(), "star_shot_yellow");
        map.put(ItemRegistry.LARGE_STAR_SHOT_GREEN.get(),  "star_shot_green");
        map.put(ItemRegistry.LARGE_STAR_SHOT_AQUA.get(),   "star_shot_aqua");
        map.put(ItemRegistry.LARGE_STAR_SHOT_BLUE.get(),   "star_shot_blue");
        map.put(ItemRegistry.LARGE_STAR_SHOT_PURPLE.get(), "star_shot_purple");

        return map;
    });

    public DanmakuRenderer(EntityRendererManager manager, ItemRenderer itemRenderer, float scale, boolean p_i226035_4_, boolean isUp) {
        super(manager, itemRenderer, scale, p_i226035_4_);
        this.isUp = isUp;
    }

    /**
     * 使用：<br><br>
     * 世界空间位置 (x,y,z)<br>
     *     ↓<br>
     * 平移至旋转中心 (centerOffsetX, centerOffsetY, centerOffsetZ)<br>
     *     ↓<br>
     * 应用旋转 (rotate)<br>
     *     ↓<br>
     * 缩放 (scale)<br>
     *     ↓<br>
     * 平移至模型中心 (-0.5, -0.5, 0)<br><br>
     * 的矩阵变换顺序可以实现为模型设置任意旋转中心 + 旋转任意角度 + 缩放任意大小 + 平移至任意位置
     */
    @OnlyIn(Dist.CLIENT)
    @Override
    public void render(@NotNull Danmaku entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        //if (entityIn.ticksExisted >= 2 || !(this.renderManager.info.getRenderViewEntity().getDistanceSq(entityIn) < 12.25D)) {
        if (Danmaku.SPECIAL_RENDERER.containsKey(entityIn.getItem().getItem())){
            Danmaku.SPECIAL_RENDERER.get(entityIn.getItem().getItem())
                    .render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
            return;
        }

        Pair<Boolean, Float> renderInfo = Danmaku.NORMAL_DANMAKU.get(entityIn.getItem().getItem());
        if (renderInfo == null) {
            this.useSuperRenderer(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
            return;
        }

        float renderScale = renderInfo.getSecond();
        matrixStackIn.push();
        matrixStackIn.translate(0, 0.5F, 0);

        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) - 90f));
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch) - 90f));
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(90.f));

        matrixStackIn.scale(renderScale, renderScale, renderScale);

        if (Danmaku.NORMAL_DANMAKU.get(entityIn.getItem().getItem()).getFirst()) {
            matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(180.F));
        }
        this.renderSingleSprite(entityIn, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.pop();

    }

    private void useSuperRenderer(@NotNull Danmaku entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn){
        float renderScale = 0.5F;
        if (Danmaku.DANMAKU_SIZES.containsKey(entityIn.getItem().getItem())) {
            renderScale = Danmaku.DANMAKU_SIZES.get(entityIn.getItem().getItem());
        }

        if (entityIn.ticksExisted >= 2 || !(this.renderManager.info.getRenderViewEntity().getDistanceSq(entityIn) < 12.25D)) {
            matrixStackIn.push();
            matrixStackIn.translate(0, 0.5F, 0);
            matrixStackIn.rotate(this.renderManager.getCameraOrientation());
            matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F));
            matrixStackIn.scale(renderScale, renderScale, renderScale);

            this.renderSingleSprite(entityIn, matrixStackIn, bufferIn, packedLightIn);
            // this.itemRenderer.renderItem(entityIn.getItem(), ItemCameraTransforms.TransformType.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
            matrixStackIn.pop();
        }
    }

    public void renderSingleSprite(Danmaku danmaku, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        if (this.getTexture(danmaku) == null) return;
        IVertexBuilder builder = bufferIn.getBuffer(RenderType.getEntityTranslucent(this.getTexture(danmaku)));
        Matrix4f matrix = matrixStackIn.getLast().getMatrix();
        float uMin = 0;   // 纹理左边界
        float uMax = 1;  // 纹理右边界
        float vMin = 0;
        float vMax = 1;
        this.vertex(matrix, builder, -0.5f, -0.5f, 0, uMin, vMax, packedLightIn);
        this.vertex(matrix, builder, -0.5f,  0.5f, 0, uMin, vMin, packedLightIn);
        this.vertex(matrix, builder,  0.5f,  0.5f, 0, uMax, vMin, packedLightIn);
        this.vertex(matrix, builder,  0.5f, -0.5f, 0, uMax, vMax, packedLightIn);

        this.vertex(matrix, builder,  0.5f, -0.5f, 0, uMax, vMax, packedLightIn);
        this.vertex(matrix, builder,  0.5f,  0.5f, 0, uMax, vMin, packedLightIn);
        this.vertex(matrix, builder, -0.5f,  0.5f, 0, uMin, vMin, packedLightIn);
        this.vertex(matrix, builder, -0.5f, -0.5f, 0, uMin, vMax, packedLightIn);
    }

    public ResourceLocation getTexture(Danmaku danmaku) {
        if (danmaku.getItem().getItem().getRegistryName() == null) {
            return null;
        }
        if (MAPPED_TEXTURES.containsKey(danmaku.getItem().getItem())) {
            return GSKOUtil.withRL("textures/item/" + MAPPED_TEXTURES.get(danmaku.getItem().getItem()) + ".png");
        }
        return GSKOUtil.withRL("textures/item/" + danmaku.getItem().getItem().getRegistryName().getPath() + ".png");
    }

    public void vertex(Matrix4f matrix, IVertexBuilder builder, float x, float y, float z, float u, float v, int packedLight) {
        builder.pos(matrix, x, y, z)
                .color(255, 255, 255, 255) // 白色，无颜色变化
                .tex(u, v)
                .overlay(OverlayTexture.NO_OVERLAY)
                .lightmap(packedLight) // 光照信息
                .normal(0, 1, 0) // 法向量（不重要，因为是2D）
                .endVertex();
    }
}
