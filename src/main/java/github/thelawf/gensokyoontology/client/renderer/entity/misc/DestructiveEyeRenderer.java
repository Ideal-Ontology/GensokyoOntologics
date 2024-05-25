package github.thelawf.gensokyoontology.client.renderer.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.misc.DestructiveEyeEntity;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.common.util.math.GeometryUtil;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;

public class DestructiveEyeRenderer extends EntityRenderer<DestructiveEyeEntity> {

    private final ItemRenderer itemRenderer;
    public static final ResourceLocation TEXTURE = GensokyoOntology.withRL("textures/entity/sphere_dark");

    public DestructiveEyeRenderer(EntityRendererManager renderManager, ItemRenderer itemRenderer) {
        super(renderManager);
        this.itemRenderer = itemRenderer;
    }

    @Override
    public @NotNull ResourceLocation getEntityTexture(@NotNull DestructiveEyeEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(@NotNull DestructiveEyeEntity entityIn, float entityYaw, float partialTicks, @NotNull MatrixStack matrixStackIn, @NotNull IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        float circumstance = 360F;
        float speed = 5;
        float scale = GSKOMathUtil.lerpTicks(partialTicks, entityIn.MAX_LIVING_TICK, entityIn.ticksExisted, 0.1f, 3f);
        float angle = GSKOMathUtil.lerpTicks(partialTicks, entityIn.MAX_LIVING_TICK, entityIn.ticksExisted, 0f, circumstance * speed);

        matrixStackIn.push();

        matrixStackIn.translate(0.12, 0.2, 0);
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(45f));
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(angle));
        matrixStackIn.scale(scale, scale, scale);

        this.itemRenderer.renderItem(new ItemStack(ItemRegistry.SPHERE_EFFECT_ITEM.get()), ItemCameraTransforms.TransformType.GROUND, 0, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
        matrixStackIn.pop();

        matrixStackIn.push();
        matrixStackIn.translate(-0.8, 0.2, 0);
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(-45f));
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(angle));
        matrixStackIn.scale(scale, scale, scale);

        // GeometryUtil.renderSphere(bufferIn.getBuffer(RenderType.getLightning()), matrixStackIn.getLast().getMatrix(),
        //         10, 10, 1f, 1.f, 0.1f, 0.1f, 0.7f);
        this.itemRenderer.renderItem(new ItemStack(ItemRegistry.SPHERE_EFFECT_ITEM.get()), ItemCameraTransforms.TransformType.GROUND, 0, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);

        matrixStackIn.pop();
    }

    public Vector3f toVec3f(Vector3d vector3d) {
        return new Vector3f((float) vector3d.x, (float) vector3d.y, (float) vector3d.z);
    }
}
