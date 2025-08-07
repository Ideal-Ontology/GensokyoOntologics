package github.thelawf.gensokyoontology.client.renderer.entity.creature;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.model.monster.FairyModel;
import github.thelawf.gensokyoontology.common.entity.monster.FairyEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class FairyRenderer extends MobRenderer<FairyEntity, FairyModel> {

    private static final ResourceLocation TEXTURE_FAIRY = new ResourceLocation(
            GensokyoOntology.MODID, "textures/entity/fairy_texture_1.png");

    public FairyRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FairyModel(0.6f), 0.4f);
    }

    @Override
    @NotNull
    public ResourceLocation getEntityTexture(@NotNull FairyEntity entity) {
        return TEXTURE_FAIRY;
    }


    @Override
    public void render(FairyEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }
}
