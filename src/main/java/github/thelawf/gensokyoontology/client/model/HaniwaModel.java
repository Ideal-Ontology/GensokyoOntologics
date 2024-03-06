package github.thelawf.gensokyoontology.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.common.entity.HaniwaEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class HaniwaModel extends EntityModel<Entity> {
    private final ModelRenderer haniwa;
    private final ModelRenderer body;
    private final ModelRenderer left_from_eyes;
    private final ModelRenderer right_from_eyes;

    public HaniwaModel() {
        textureWidth = 64;
        textureHeight = 64;

        haniwa = new ModelRenderer(this);
        haniwa.setRotationPoint(0.0F, 24.0F, 0.0F);


        body = new ModelRenderer(this);
        body.setRotationPoint(-8.0F, -6.0F, 8.0F);
        haniwa.addChild(body);
        body.setTextureOffset(0, 0).addBox(3.0F, -6.0F, -13.0F, 10.0F, 12.0F, 10.0F, 0.0F, false);
        body.setTextureOffset(0, 22).addBox(4.0F, -8.0F, -12.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);

        left_from_eyes = new ModelRenderer(this);
        left_from_eyes.setRotationPoint(-0.4718F, -7.1164F, 8.25F);
        haniwa.addChild(left_from_eyes);
        left_from_eyes.setTextureOffset(30, 0).addBox(-5.5282F, -0.8836F, -9.25F, 1.0F, 3.0F, 3.0F, 0.0F, false);
        left_from_eyes.setTextureOffset(0, 0).addBox(-7.5282F, -0.8836F, -8.25F, 2.0F, 5.0F, 2.0F, 0.0F, false);

        right_from_eyes = new ModelRenderer(this);
        right_from_eyes.setRotationPoint(-0.4718F, -7.1164F, 8.25F);
        haniwa.addChild(right_from_eyes);
        right_from_eyes.setTextureOffset(29, 29).addBox(5.4718F, -0.8836F, -9.25F, 1.0F, 3.0F, 3.0F, 0.0F, false);
        right_from_eyes.setTextureOffset(24, 22).addBox(6.4718F, -0.8836F, -9.25F, 4.0F, 2.0F, 2.0F, 0.0F, false);
        right_from_eyes.setTextureOffset(0, 22).addBox(8.4718F, -4.8836F, -9.25F, 2.0F, 4.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        haniwa.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}