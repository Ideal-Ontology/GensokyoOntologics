package github.thelawf.gensokyoontology.client.renderer.entity.creature;

import com.mojang.blaze3d.matrix.MatrixStack;
import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.client.model.FlandreScarletModel;
import github.thelawf.gensokyoontology.common.entity.monster.FlandreScarletEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

public class FlandreScarletRenderer extends BipedRenderer<FlandreScarletEntity, FlandreScarletModel> {

    public static final ResourceLocation FLANDRE_TEXTURE = GensokyoOntology.withRL("textures/entity/flandre_scarlet.png");

    public FlandreScarletRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FlandreScarletModel(1.0f), 0.9f);
    }

    @Override
    @NotNull
    public ResourceLocation getEntityTexture(@NotNull FlandreScarletEntity entity) {
        return FLANDRE_TEXTURE;
    }

    @Override
    public void render(FlandreScarletEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    // public void onAnimate(FlandreScarletEntity entityIn, float partialTicks) {
    //     switch (entityIn.getAnimation()) {
    //         case IDLE:
    //             playIdleAnimation(entityIn, partialTicks);
    //             break;
    //         case WALKING:
    //             playWalkAnimation(entityIn, partialTicks);
    //             break;
    //     }
    // }

    // private void playIdleAnimation(FlandreScarletEntity entityIn, float partialTicks){
    //     this.entityModel.setRotationAngle(this.entityModel.getBodyParts().get(2), MathHelper.clamp(
    //             entityIn.limbSwingAmount, 0 + partialTicks, 1F + partialTicks), 0, 0);
    //     this.entityModel.setRotationAngle(this.entityModel.getBodyParts().get(3), MathHelper.clamp(
    //             entityIn.limbSwingAmount, 0 + partialTicks, 1F + partialTicks), 0, 0);
    // }

    // private void playWalkAnimation(FlandreScarletEntity entityIn, float partialTicks) {
    //     this.entityModel.setRotationAngle(this.entityModel.getBodyParts().get(0), 0, 0, MathHelper.clamp(
    //             entityIn.limbSwingAmount, 0 + partialTicks, 3F + partialTicks));
    //     this.entityModel.setRotationAngle(this.entityModel.getBodyParts().get(1), 0, 0, MathHelper.clamp(
    //             entityIn.limbSwingAmount, 0 + partialTicks, 3F + partialTicks));
    //     this.entityModel.setRotationAngle(this.entityModel.getBodyParts().get(2), 0, 0, MathHelper.clamp(
    //             entityIn.limbSwingAmount, 0 + partialTicks, 3F + partialTicks));
    //     this.entityModel.setRotationAngle(this.entityModel.getBodyParts().get(3), 0, 0, MathHelper.clamp(
    //             entityIn.limbSwingAmount, 0 + partialTicks, 3F + partialTicks));
    // }
}
