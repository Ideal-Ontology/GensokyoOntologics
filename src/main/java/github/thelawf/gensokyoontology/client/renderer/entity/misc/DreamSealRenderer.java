package github.thelawf.gensokyoontology.client.renderer.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.entity.misc.DreamSealEntity;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
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
    @SuppressWarnings("deprecation")
    public void render(@NotNull DreamSealEntity entityIn, float entityYaw, float partialTicks, @NotNull MatrixStack matrixStackIn, @NotNull IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.push();
        matrixStackIn.scale(2f, 2f, 2f);
        matrixStackIn.rotate(this.renderManager.getCameraOrientation());
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F));
        // RenderSystem.color4f(COLOR == 0 ? 1f : 0f, COLOR == 1 ? 1f : 0f, COLOR == 2 ? 1f : 0f, 0f);
        switch (entityIn.getColor()){
            case RED:
                RenderSystem.color4f(1f, 0f, 0f, 0f);
            case BLUE:
                RenderSystem.color4f(0f, 1f, 0f, 0f);
            case GREEN:
                RenderSystem.color4f(0f, 0f, 1f, 0f);
            case YELLOW:
                RenderSystem.color4f(1f, 0f, 1f, 0f);
            case MAGENTA:
                RenderSystem.color4f(1f, 1f, 0f, 0f);
            case AQUA:
                RenderSystem.color4f(0f, 1f, 1f, 0f);
            default:
            case NONE:
                RenderSystem.color4f(0f, 0f, 0f, 0f);
        }
        this.itemRenderer.renderItem(new ItemStack(ItemRegistry.DREAM_SEAL_ITEM.get()), ItemCameraTransforms.TransformType.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
        matrixStackIn.pop();
    }
}
