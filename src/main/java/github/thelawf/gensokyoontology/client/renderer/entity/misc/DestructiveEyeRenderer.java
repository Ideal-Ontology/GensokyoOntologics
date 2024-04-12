package github.thelawf.gensokyoontology.client.renderer.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.misc.DestructiveEyeEntity;
import github.thelawf.gensokyoontology.common.util.math.GSKOMathUtil;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
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
        //matrixStackIn.translate(.5, 1.5, .5);
        matrixStackIn.scale(scale, scale, scale);
        //matrixStackIn.rotate(Vector3f.YP.rotationDegrees(angle));
        //matrixStackIn.rotate(Vector3f.XP.rotationDegrees(45f));
        //matrixStackIn.translate(-.5, 1, -.5);
        this.itemRenderer.renderItem(new ItemStack(ItemRegistry.SPHERE_EFFECT_ITEM.get()), ItemCameraTransforms.TransformType.GROUND, 0, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
        matrixStackIn.pop();
    }
}
