package github.thelawf.gensokyoontology.client.renderer.entity.creature;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.model.GSKOBipedModel;
import github.thelawf.gensokyoontology.client.model.HumanrResidentModel;
import github.thelawf.gensokyoontology.client.model.VariantModelRenderer;
import github.thelawf.gensokyoontology.client.model.monster.FairyModel;
import github.thelawf.gensokyoontology.client.model.monster.SunflowerFairyModel;
import github.thelawf.gensokyoontology.common.entity.monster.FairyEntity;
import github.thelawf.gensokyoontology.common.entity.monster.SunflowerFairyEntity;
import github.thelawf.gensokyoontology.common.entity.passive.HumanResidentEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EggEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class FairyRenderer extends VariantModelRenderer<FairyEntity> {

    private static final ResourceLocation NORMAL_FAIRY = new ResourceLocation(GensokyoOntology.MODID, "textures/entity/fairy_texture_1.png");

    public static final ResourceLocation SUNFLOWER_FAIRY = GensokyoOntology.withRL("textures/entity/sunflower_fairy.png");

    public FairyRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    @NotNull
    public ResourceLocation getEntityTexture(@NotNull FairyEntity entity) {
        return entity instanceof SunflowerFairyEntity ? SUNFLOWER_FAIRY : NORMAL_FAIRY;
    }


    @Override
    public void render(FairyEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);

        matrixStackIn.push();
        this.invokeLivingRenderer(entityIn, matrixStackIn, bufferIn, partialTicks, packedLightIn);
        this.renderFairyVariants(entityIn, this.getEntityModel(entityIn), matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.pop();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <M extends EntityModel<?>> M getEntityModel(FairyEntity entity) {
        return entity instanceof SunflowerFairyEntity ? (M) new SunflowerFairyModel() : (M) new FairyModel(1f);
    }

    public void renderFairyVariants(FairyEntity entityIn, GSKOBipedModel<FairyEntity> model, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn){
        model.render(matrixStackIn, bufferIn.getBuffer(model.getRenderType(this.getEntityTexture(entityIn))),
                packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
    }
}
