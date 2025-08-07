package github.thelawf.gensokyoontology.client.model.monster;// Made create Blockbench 4.8.3
// Exported for Minecraft version 1.15 - 1.16 create Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.client.model.GSKOBipedModel;
import github.thelawf.gensokyoontology.common.entity.monster.SunflowerFairyEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class SunflowerFairyModel extends GSKOBipedModel<SunflowerFairyEntity> {
    private final ModelRenderer bone;
    private final ModelRenderer flower;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer head;
    private final ModelRenderer armRight;
    private final ModelRenderer armRight_r1;
    private final ModelRenderer armLeft;
    private final ModelRenderer armLeft_r1;
    private final ModelRenderer body;
    private final ModelRenderer sittingRotationSkirt;
    private final ModelRenderer wingLeft;
    private final ModelRenderer wingRight;
    private final ModelRenderer legLeft;
    private final ModelRenderer legRight;

    public SunflowerFairyModel() {
        super(0.4f);
        textureWidth = 64;
        textureHeight = 64;

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 24.0F, 0.0F);


        flower = new ModelRenderer(this);
        flower.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone.addChild(flower);


        cube_r1 = new ModelRenderer(this);
        cube_r1.setRotationPoint(-8.5699F, -16.6F, -7.1655F);
        flower.addChild(cube_r1);
        setRotationAngle(cube_r1, 0.0F, 0.4363F, 0.4363F);
        cube_r1.setTextureOffset(32, 0).addBox(-4.5F, -3.5F, 0.3F, 9.0F, 9.0F, 0.0F, 0.0F, false);

        cube_r2 = new ModelRenderer(this);
        cube_r2.setRotationPoint(-0.1946F, -14.5608F, -7.5F);
        flower.addChild(cube_r2);
        setRotationAngle(cube_r2, 0.0F, 0.0F, -1.3963F);
        cube_r2.setTextureOffset(12, 37).addBox(-1.5F, -8.5F, 0.5F, 1.0F, 17.0F, 1.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, -18.0F, 0.0F);
        bone.addChild(head);
        head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

        armRight = new ModelRenderer(this);
        armRight.setRotationPoint(-5.0967F, -14.2973F, -1.75F);
        bone.addChild(armRight);
        setRotationAngle(armRight, -0.9163F, 0.0F, 0.2618F);
        armRight.setTextureOffset(16, 37).addBox(-1.5F, -4.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);

        armRight_r1 = new ModelRenderer(this);
        armRight_r1.setRotationPoint(0.0F, 2.0F, 0.0F);
        armRight.addChild(armRight_r1);
        setRotationAngle(armRight_r1, -0.48F, 0.0F, 0.0F);
        armRight_r1.setTextureOffset(0, 0).addBox(-1.0F, -3.0F, -2.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

        armLeft = new ModelRenderer(this);
        armLeft.setRotationPoint(3.0F, -17.5F, 0.0F);
        bone.addChild(armLeft);
        setRotationAngle(armLeft, -0.5672F, 0.0F, -0.3054F);
        armLeft.setTextureOffset(16, 37).addBox(-0.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);

        armLeft_r1 = new ModelRenderer(this);
        armLeft_r1.setRotationPoint(1.0F, 6.0F, 0.0F);
        armLeft.addChild(armLeft_r1);
        setRotationAngle(armLeft_r1, -0.3491F, 0.0F, 0.0F);
        armLeft_r1.setTextureOffset(0, 0).addBox(-1.0F, -3.5F, -1.75F, 2.0F, 6.0F, 2.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, -10.5F, 0.0F);
        bone.addChild(body);
        body.setTextureOffset(27, 10).addBox(-3.0F, -7.499F, -3.0F, 6.0F, 9.0F, 6.0F, 0.0F, false);

        sittingRotationSkirt = new ModelRenderer(this);
        sittingRotationSkirt.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.addChild(sittingRotationSkirt);
        sittingRotationSkirt.setTextureOffset(0, 16).addBox(-4.5F, 2.5F, -4.5F, 9.0F, 2.0F, 9.0F, 0.0F, false);
        sittingRotationSkirt.setTextureOffset(0, 27).addBox(-4.0F, 0.5F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);
        sittingRotationSkirt.setTextureOffset(29, 25).addBox(-3.5F, -1.5F, -3.5F, 7.0F, 2.0F, 7.0F, 0.0F, false);

        wingLeft = new ModelRenderer(this);
        wingLeft.setRotationPoint(0.5F, -15.0F, 3.25F);
        bone.addChild(wingLeft);
        setRotationAngle(wingLeft, 0.0F, 1.0472F, 0.0F);
        wingLeft.setTextureOffset(32, 24).addBox(0.0F, -10.0F, 0.25F, 0.0F, 15.0F, 10.0F, 0.0F, false);

        wingRight = new ModelRenderer(this);
        wingRight.setRotationPoint(-0.5F, -15.0F, 3.75F);
        bone.addChild(wingRight);
        setRotationAngle(wingRight, 0.0F, -1.0472F, 0.0F);
        wingRight.setTextureOffset(32, 24).addBox(0.0F, -10.0F, -0.25F, 0.0F, 15.0F, 10.0F, 0.0F, false);

        legLeft = new ModelRenderer(this);
        legLeft.setRotationPoint(2.0F, -9.0F, 0.0F);
        bone.addChild(legLeft);
        legLeft.setTextureOffset(0, 37).addBox(-1.501F, 0.0F, -1.5F, 3.0F, 9.0F, 3.0F, 0.0F, false);

        legRight = new ModelRenderer(this);
        legRight.setRotationPoint(-2.0F, -9.0F, 0.0F);
        bone.addChild(legRight);
        legRight.setTextureOffset(0, 37).addBox(-1.499F, 0.0F, -1.5F, 3.0F, 9.0F, 3.0F, 0.0F, false);
    }

    // @Override
    // public void setLivingAnimations(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
    // 	//previously the render function, render code was moved to a method below
    // }

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