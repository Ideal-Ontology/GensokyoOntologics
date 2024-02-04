package github.thelawf.gensokyoontology.client.model;
// Made with Blockbench 4.7.0
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.common.entity.monster.FairyEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class FairyModel extends BipedModel<FairyEntity> {
    private final ModelRenderer bone;
    private final ModelRenderer head;
    // private final ModelRenderer blink;
    private final ModelRenderer body;
    private final ModelRenderer sittingRotationSkirt;
    private final ModelRenderer wingLeft;
    private final ModelRenderer wingRight;

    public FairyModel(float modelSize) {
        super(RenderType::getEntityTranslucent, modelSize, 0f, 64, 64);
        textureWidth = 64;
        textureHeight = 64;

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 24.0F, 0.0F);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, -18.0F, 0.0F);
        bone.addChild(head);
        head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

        // blink = new ModelRenderer(this);
        // blink.setRotationPoint(0.0F, 0.0F, 0.0F);
        // head.addChild(blink);
        // blink.setTextureOffset(24, 0).addBox(-4.0F, -8.0F, -4.001F, 8.0F, 8.0F, 0.0F, 0.0F, false);

        bipedRightArm = new ModelRenderer(this);
        bipedRightArm.setRotationPoint(-3.0F, -17.5F, 0.0F);
        bone.addChild(bipedRightArm);
        setRotationAngle(bipedRightArm , 0.0F, 0.0F, 0.4363F);
        bipedRightArm.setTextureOffset(46, 22).addBox(-2.0F, 1.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
        bipedRightArm.setTextureOffset(0, 52).addBox(-2.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);

        bipedLeftArm = new ModelRenderer(this);
        bipedLeftArm.setRotationPoint(3.0F, -17.5F, 0.0F);
        bone.addChild(bipedLeftArm);
        setRotationAngle(bipedLeftArm, 0.0F, 0.0F, -0.4363F);
        bipedLeftArm.setTextureOffset(12, 52).addBox(-0.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);
        bipedLeftArm.setTextureOffset(46, 22).addBox(0.0F, 1.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, -10.5F, 0.0F);
        bone.addChild(body);
        body.setTextureOffset(5, 35).addBox(-3.0F, -7.499F, -3.0F, 6.0F, 9.0F, 6.0F, 0.0F, false);

        sittingRotationSkirt = new ModelRenderer(this);
        sittingRotationSkirt.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.addChild(sittingRotationSkirt);
        sittingRotationSkirt.setTextureOffset(0, 21).addBox(-4.5F, 2.5F, -4.5F, 9.0F, 2.0F, 9.0F, 0.0F, false);
        sittingRotationSkirt.setTextureOffset(0, 21).addBox(-4.0F, 0.5F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);
        sittingRotationSkirt.setTextureOffset(0, 22).addBox(-3.5F, -1.5F, -3.5F, 7.0F, 2.0F, 7.0F, 0.0F, false);

        wingLeft = new ModelRenderer(this);
        wingLeft.setRotationPoint(0.5F, -15.0F, 3.25F);
        bone.addChild(wingLeft);
        setRotationAngle(wingLeft, 0.0F, 1.0472F, 0.0F);
        wingLeft.setTextureOffset(33, 35).addBox(0.0F, -6.0F, 0.25F, 0.0F, 11.0F, 13.0F, 0.0F, false);

        wingRight = new ModelRenderer(this);
        wingRight.setRotationPoint(-0.5F, -15.0F, 3.75F);
        bone.addChild(wingRight);
        setRotationAngle(wingRight, 0.0F, -1.0472F, 0.0F);
        wingRight.setTextureOffset(33, 35).addBox(0.0F, -6.0F, -0.25F, 0.0F, 11.0F, 13.0F, 0.0F, false);

        bipedLeftLeg = new ModelRenderer(this);
        bipedLeftLeg.setRotationPoint(2.0F, -9.0F, 0.0F);
        bone.addChild(bipedLeftLeg);
        bipedLeftLeg.setTextureOffset(33, 18).addBox(-1.501F, 0.0F, -1.5F, 3.0F, 9.0F, 3.0F, 0.0F, false);

        bipedRightLeg = new ModelRenderer(this);
        bipedRightLeg.setRotationPoint(-2.0F, -9.0F, 0.0F);
        bone.addChild(bipedRightLeg);
        bipedRightLeg.setTextureOffset(33, 18).addBox(-1.499F, 0.0F, -1.5F, 3.0F, 9.0F, 3.0F, 0.0F, false);

    }

    @Override
    public void setRotationAngles(FairyEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void setLivingAnimations(FairyEntity entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        bone.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}