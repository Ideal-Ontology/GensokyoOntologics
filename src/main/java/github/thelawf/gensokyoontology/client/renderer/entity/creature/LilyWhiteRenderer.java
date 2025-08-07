package github.thelawf.gensokyoontology.client.renderer.entity.creature;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.model.monster.LilyWhiteModel;
import github.thelawf.gensokyoontology.common.entity.monster.LilyWhiteEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class LilyWhiteRenderer extends LivingRenderer<LilyWhiteEntity, LilyWhiteModel> {

    public static final ResourceLocation LILY_WHITE_TEX = new ResourceLocation(
            GensokyoOntology.MODID, "textures/entity/lily_white.png");

    public LilyWhiteRenderer(EntityRendererManager rendererManager) {
        super(rendererManager, new LilyWhiteModel(0.6F), 0.6F);
    }

    @Override
    @NotNull
    public ResourceLocation getEntityTexture(LilyWhiteEntity entity) {
        return LILY_WHITE_TEX;
    }

    @Override
    public void render(LilyWhiteEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }
}
