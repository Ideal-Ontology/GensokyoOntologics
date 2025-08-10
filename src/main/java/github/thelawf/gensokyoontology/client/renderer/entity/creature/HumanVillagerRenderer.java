package github.thelawf.gensokyoontology.client.renderer.entity.creature;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.model.FemaleVillagerModel;
import github.thelawf.gensokyoontology.client.model.HumanrResidentModel;
import github.thelawf.gensokyoontology.client.model.MaleVillagerModel;
import github.thelawf.gensokyoontology.client.model.VariantModelRenderer;
import github.thelawf.gensokyoontology.common.entity.passive.HumanResidentEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class HumanVillagerRenderer extends VariantModelRenderer<HumanResidentEntity> {
    public static final ResourceLocation MALE_TEX = GensokyoOntology.withRL("textures/entity/human_villager_male.png");
    public static final ResourceLocation FEMALE_TEX = GensokyoOntology.withRL("textures/entity/human_villager_female.png");
    public static final MaleVillagerModel MALE_MODEL = new MaleVillagerModel();
    public static final FemaleVillagerModel FEMALE_MODEL = new FemaleVillagerModel();
    public HumanVillagerRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public @NotNull ResourceLocation getEntityTexture(HumanResidentEntity entity) {
        return entity.getGender() == HumanResidentEntity.Gender.MALE ? MALE_TEX : FEMALE_TEX;
    }

    @Override
    public void render(HumanResidentEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {

        matrixStackIn.push();
        HumanrResidentModel entityModel = this.getEntityModel(entityIn);
        float f = MathHelper.interpolateAngle(partialTicks, entityIn.prevRenderYawOffset, entityIn.renderYawOffset);
        float f1 = MathHelper.interpolateAngle(partialTicks, entityIn.prevRotationYawHead, entityIn.rotationYawHead);
        float f6 = MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch);
        float f2 = f1 - f;
        boolean shouldSit = entityIn.isPassenger() && (entityIn.getRidingEntity() != null && entityIn.getRidingEntity().shouldRiderSit());

        this.renderHeadRot(entityIn, matrixStackIn, partialTicks, f, f1, f2, shouldSit);
        this.renderLimbSwingAndGlow(entityIn, entityModel, matrixStackIn, partialTicks, f, f2, f6, shouldSit);
        this.renderModelByGender(entityIn, entityModel, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.pop();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public HumanrResidentModel getEntityModel(HumanResidentEntity entity) {
        return entity.getGender() == HumanResidentEntity.Gender.MALE ? MALE_MODEL : FEMALE_MODEL;
    }

    public void renderModelByGender(HumanResidentEntity entityIn, HumanrResidentModel model, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn){
        model.render(matrixStackIn, bufferIn.getBuffer(model.getRenderType(this.getEntityTexture(entityIn))),
                packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
    }

}
