package github.thelawf.gensokyoontology.client.renderer.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.misc.DreamSealEntity;
import github.thelawf.gensokyoontology.common.util.math.GeometryUtil;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class DreamSealRenderer extends EntityRenderer<DreamSealEntity> {

    private final ItemRenderer itemRenderer;
    public static final int COLOR = new Random().nextInt(3);
    public static final ResourceLocation TEXTURE = GensokyoOntology.withRL("textures/entity/dream_seal_overlay.png");

    public DreamSealRenderer(EntityRendererManager renderManager, ItemRenderer itemRenderer) {
        super(renderManager);
        this.itemRenderer = itemRenderer;
    }

    @Override
    public ResourceLocation getEntityTexture(DreamSealEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(@NotNull DreamSealEntity entityIn, float entityYaw, float partialTicks, @NotNull MatrixStack matrixStackIn, @NotNull IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.push();
        matrixStackIn.scale(4f, 4f, 4f);
        matrixStackIn.rotate(this.renderManager.getCameraOrientation());
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F));
        float r, g, b;

        switch (entityIn.getColor()){
            case RED:
                r = 1f; g = 0f; b = 0f;
                break;
            case BLUE:
                r = 0f; g = 0f; b = 1f;
                break;
            case GREEN:
                r = 0f; g = 1f; b = 0f;
                break;
            default:
            case NONE:
                r = 1f; g = 1f; b = 1f;
                break;
        }
        GeometryUtil.longitudeSphere(bufferIn.getBuffer(RenderType.getLightning()), matrixStackIn.getLast().getMatrix(),
                10, 10, 0.3f, r, g, b, 0.5f);
        GeometryUtil.longitudeSphere(bufferIn.getBuffer(RenderType.getLightning()), matrixStackIn.getLast().getMatrix(),
                10, 10, 0.2f, 1f, 1f, 1f, 1f);
        // this.itemRenderer.renderItem(new ItemStack(ItemRegistry.DREAM_SEAL_ITEM.get()), ItemCameraTransforms.TransformType.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
        matrixStackIn.pop();
    }
}
