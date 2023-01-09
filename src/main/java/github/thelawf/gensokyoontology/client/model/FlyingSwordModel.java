package github.thelawf.gensokyoontology.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.common.entity.projectile.FlyingSwordEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class FlyingSwordModel extends EntityModel<FlyingSwordEntity> {

    private final ModelRenderer body;

    public FlyingSwordModel() {
        body = new ModelRenderer(this);

    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {

    }

    @Override
    public void setRotationAngles(FlyingSwordEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        body.rotateAngleX = limbSwing;
        body.rotateAngleY = netHeadYaw;
        body.rotateAngleZ = headPitch;
    }
}
