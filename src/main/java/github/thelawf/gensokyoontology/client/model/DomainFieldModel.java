package github.thelawf.gensokyoontology.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.common.entity.DomainFieldEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class DomainFieldModel extends EntityModel<DomainFieldEntity> {

    private final ModelRenderer bone;

    public DomainFieldModel() {
        textureWidth = 512;
        textureHeight = 512;

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 24.0F, 0.0F);
        bone.setTextureOffset(28, 25).addBox(-64.0F, 0.0F, -64.0F, 128.0F, 0.0F, 128.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(DomainFieldEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        bone.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}
