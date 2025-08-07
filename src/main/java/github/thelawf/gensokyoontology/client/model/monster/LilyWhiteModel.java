package github.thelawf.gensokyoontology.client.model.monster;
// Made create Blockbench 4.8.3
// Exported for Minecraft version 1.15 - 1.16 create Mojang mappings
// Paste this class into your mod and generate all required imports

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.client.model.GSKOBipedModel;
import github.thelawf.gensokyoontology.common.entity.monster.LilyWhiteEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class LilyWhiteModel extends GSKOBipedModel<LilyWhiteEntity> {
    private final ModelRenderer lilywhite;
    private final ModelRenderer hand;
    private final ModelRenderer wing;
    private final ModelRenderer wingRight2;
    private final ModelRenderer wingRightEndR1;
    private final ModelRenderer wingRightUpR1;
    private final ModelRenderer wingLeft;
    private final ModelRenderer wingLeftEndR1;
    private final ModelRenderer wingLeftUpR1;

    public LilyWhiteModel(float modelSize) {
        super(modelSize);
        this.textureWidth = 64;
        this.textureHeight = 64;

        lilywhite = new ModelRenderer(this);
        lilywhite.setRotationPoint(0.0F, 24.0F, 0.0F);

        hand = new ModelRenderer(this);
        hand.setRotationPoint(0.0F, 0.0F, 0.0F);
        lilywhite.addChild(hand);
        hand.setTextureOffset(0, 0).addBox(-3.8F, -31.05F, -3.0F, 7.8F, 7.8F, 7.8F, 0.0F, false);
        hand.setTextureOffset(32, 24).addBox(-3.9F, -30.75F, -3.4F, 8.0F, 8.0F, 8.0F, 0.0F, false);

        leftArm = new ModelRenderer(this);
        leftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        lilywhite.addChild(leftArm);
        leftArm.setTextureOffset(45, 40).addBox(3.9F, -16.6F, -1.1F, 3.2F, 5.0F, 4.2F, 0.0F, false);
        leftArm.setTextureOffset(32, 0).addBox(4.0F, -23.2F, -1.0F, 3.0F, 11.6F, 4.0F, 0.0F, false);

        rightArm = new ModelRenderer(this);
        rightArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        lilywhite.addChild(rightArm);
        rightArm.setTextureOffset(29, 39).addBox(-7.1F, -16.6F, -1.1F, 3.2F, 5.0F, 4.2F, 0.0F, false);
        rightArm.setTextureOffset(16, 32).addBox(-7.0F, -23.2F, -1.0F, 3.0F, 11.6F, 4.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        lilywhite.addChild(body);
        body.setTextureOffset(33, 49).addBox(-3.0F, -21.6F, -1.2F, 6.0F, 3.0F, 0.0F, 0.0F, false);
        body.setTextureOffset(0, 16).addBox(-4.0F, -23.2F, -1.0F, 8.0F, 11.6F, 4.0F, 0.0F, false);

        wing = new ModelRenderer(this);
        wing.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.addChild(wing);

        wingRight2 = new ModelRenderer(this);
        wingRight2.setRotationPoint(-5.5929F, -16.8132F, 4.0102F);
        wing.addChild(wingRight2);
        setRotationAngle(wingRight2, 0.0F, -2.9671F, 0.0F);
        wingRight2.setTextureOffset(0, 48).addBox(-6.1571F, -3.1868F, -0.0102F, 13.0F, 7.0F, 0.0F, 0.0F, false);

        wingRightEndR1 = new ModelRenderer(this);
        wingRightEndR1.setRotationPoint(0.342F, 4.3132F, 0.0F);
        wingRight2.addChild(wingRightEndR1);
        setRotationAngle(wingRightEndR1, 0.0F, 0.0F, 0.3491F);
        wingRightEndR1.setTextureOffset(0, 48).addBox(-6.4991F, -2.5F, -0.0102F, 13.0F, 7.0F, 0.0F, 0.0F, false);

        wingRightUpR1 = new ModelRenderer(this);
        wingRightUpR1.setRotationPoint(0.342F, -3.6868F, 0.0F);
        wingRight2.addChild(wingRightUpR1);
        setRotationAngle(wingRightUpR1, 0.0F, 0.0F, -0.3491F);
        wingRightUpR1.setTextureOffset(0, 48).addBox(-6.4991F, -5.5F, -0.0102F, 13.0F, 7.0F, 0.0F, 0.0F, false);

        wingLeft = new ModelRenderer(this);
        wingLeft.setRotationPoint(5.8494F, -16.4825F, 4.0F);
        wing.addChild(wingLeft);
        setRotationAngle(wingLeft, 0.0F, -0.1745F, 0.0F);
        wingLeft.setTextureOffset(0, 48).addBox(-6.0994F, -3.5175F, 0.0F, 13.0F, 7.0F, 0.0F, 0.0F, false);

        wingLeftEndR1 = new ModelRenderer(this);
        wingLeftEndR1.setRotationPoint(0.4006F, 3.9825F, 0.0F);
        wingLeft.addChild(wingLeftEndR1);
        setRotationAngle(wingLeftEndR1, 0.0F, 0.0F, 0.3491F);
        wingLeftEndR1.setTextureOffset(0, 48).addBox(-6.5F, -2.5F, 0.0F, 13.0F, 7.0F, 0.0F, 0.0F, false);

        wingLeftUpR1 = new ModelRenderer(this);
        wingLeftUpR1.setRotationPoint(0.4006F, -4.0175F, 0.0F);
        wingLeft.addChild(wingLeftUpR1);
        setRotationAngle(wingLeftUpR1, 0.0F, 0.0F, -0.3491F);
        wingLeftUpR1.setTextureOffset(0, 48).addBox(-6.5F, -5.5F, 0.0F, 13.0F, 7.0F, 0.0F, 0.0F, false);

        rightLeg = new ModelRenderer(this);
        rightLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
        lilywhite.addChild(rightLeg);
        rightLeg.setTextureOffset(46, 0).addBox(-4.1F, -7.9F, -1.1F, 4.0F, 8.0F, 4.2F, 0.0F, false);
        rightLeg.setTextureOffset(24, 16).addBox(-4.0F, -11.6F, -1.0F, 4.0F, 11.6F, 4.0F, 0.0F, false);

        leftLeg = new ModelRenderer(this);
        leftLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
        lilywhite.addChild(leftLeg);
        leftLeg.setTextureOffset(46, 12).addBox(0.1F, -7.9F, -1.1F, 4.0F, 8.0F, 4.2F, 0.0F, false);
        leftLeg.setTextureOffset(0, 32).addBox(0.0F, -11.6F, -1.0F, 4.0F, 11.6F, 4.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(LilyWhiteEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void setLivingAnimations(LilyWhiteEntity entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        lilywhite.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public Iterable<ModelRenderer> getBodyParts() {
        return Iterables.concat(super.getBodyParts(), ImmutableList.of(this.body, this.rightArm, this.leftArm, this.rightLeg, this.leftLeg));
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}