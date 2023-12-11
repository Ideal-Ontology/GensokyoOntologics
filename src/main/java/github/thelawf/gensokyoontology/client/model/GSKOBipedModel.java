package github.thelawf.gensokyoontology.client.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.common.entity.monster.RemiliaScarletEntity;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelHelper;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

public abstract class GSKOBipedModel<T extends LivingEntity> extends BipedModel<T> {
    public ModelRenderer body = new ModelRenderer(this);;

    public ModelRenderer leftArm = new ModelRenderer(this);;
    // bipedLeftArm
    public ModelRenderer rightArm = new ModelRenderer(this);;
    // private final ModelRenderer body;

    public ModelRenderer rightLeg = new ModelRenderer(this);;
    public ModelRenderer leftLeg = new ModelRenderer(this);

    public GSKOBipedModel(float modelSize) {
        super(modelSize);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean flag = entityIn.getTicksElytraFlying() > 4;
        boolean flag1 = entityIn.isActualySwimming();
        this.bipedHead.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        if (flag) {
            this.bipedHead.rotateAngleX = (-(float)Math.PI / 4F);
        } else if (this.swimAnimation > 0.0F) {
            if (flag1) {
                this.bipedHead.rotateAngleX = this.rotLerpRad(this.swimAnimation, this.bipedHead.rotateAngleX, (-(float)Math.PI / 4F));
            } else {
                this.bipedHead.rotateAngleX = this.rotLerpRad(this.swimAnimation, this.bipedHead.rotateAngleX, headPitch * ((float)Math.PI / 180F));
            }
        } else {
            this.bipedHead.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        }

        this.body.rotateAngleY = 0.0F;
        this.bipedRightArm.rotationPointZ = 0.0F;
        this.bipedRightArm.rotationPointX = -5.0F;
        this.bipedLeftArm.rotationPointZ = 0.0F;
        this.bipedLeftArm.rotationPointX = 5.0F;

        // this.bipedRightArm.rotationPointY = 5.0F;
        // this.bipedLeftArm.rotationPointY = 5.0F;
//
        // this.bipedRightLeg.rotationPointY = 5.0F;
        // this.bipedLeftLeg.rotationPointY = 5.0F;

        float f = 1.0F;
        if (flag) {
            f = (float)entityIn.getMotion().lengthSquared();
            f = f / 0.2F;
            f = f * f * f;
        }

        if (f < 1.0F) {
            f = 1.0F;
        }

        this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
        this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
        this.rightArm.rotateAngleZ = 0.0F;
        this.leftArm.rotateAngleZ = 0.0F;
        this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;
        this.rightLeg.rotateAngleY = 0.0F;
        this.leftLeg.rotateAngleY = 0.0F;
        this.rightLeg.rotateAngleZ = 0.0F;
        this.leftLeg.rotateAngleZ = 0.0F;
        if (this.isSitting) {
            this.rightArm.rotateAngleX += (-(float)Math.PI / 5F);
            this.leftArm.rotateAngleX += (-(float)Math.PI / 5F);
            this.rightLeg.rotateAngleX = -1.4137167F;
            this.rightLeg.rotateAngleY = ((float)Math.PI / 10F);
            this.rightLeg.rotateAngleZ = 0.07853982F;
            this.leftLeg.rotateAngleX = -1.4137167F;
            this.leftLeg.rotateAngleY = (-(float)Math.PI / 10F);
            this.leftLeg.rotateAngleZ = -0.07853982F;
        }

        this.rightArm.rotateAngleY = 0.0F;
        this.leftArm.rotateAngleY = 0.0F;
        boolean flag2 = entityIn.getPrimaryHand() == HandSide.RIGHT;
        boolean flag3 = flag2 ? this.leftArmPose.func_241657_a_() : this.rightArmPose.func_241657_a_();
        if (flag2 != flag3) {
            this.func_241655_c_(entityIn);
            this.func_241654_b_(entityIn);
        } else {
            this.func_241654_b_(entityIn);
            this.func_241655_c_(entityIn);
        }

        this.func_230486_a_(entityIn, ageInTicks);
        if (this.isSneak) {
            this.body.rotateAngleX = 0.5F;
            this.rightArm.rotateAngleX += 0.4F;
            this.leftArm.rotateAngleX += 0.4F;
            this.rightLeg.rotationPointZ = 4.0F;
            this.leftLeg.rotationPointZ = 4.0F;
            // this.rightLeg.rotationPointY = 12.2F;
            // this.leftLeg.rotationPointY = 12.2F;
            this.bipedHead.rotationPointY = 4.2F;
            this.body.rotationPointY = 3.2F;
            // this.leftArm.rotationPointY = 5.2F;
            // this.rightArm.rotationPointY = 5.2F;
        } else {
            this.body.rotateAngleX = 0.0F;
            this.rightLeg.rotationPointZ = 0.1F;
            this.leftLeg.rotationPointZ = 0.1F;
            // this.rightLeg.rotationPointY = 12.0F;
            // this.leftLeg.rotationPointY = 12.0F;
            this.bipedHead.rotationPointY = 0.0F;
            this.body.rotationPointY = 0.0F;
            // this.leftArm.rotationPointY = 2.0F;
            // this.rightArm.rotationPointY = 2.0F;
        }

        ModelHelper.func_239101_a_(this.rightArm, this.leftArm, ageInTicks);
        if (this.swimAnimation > 0.0F) {
            float f1 = limbSwing % 26.0F;
            HandSide handside = this.getMainHand(entityIn);
            float f2 = handside == HandSide.RIGHT && this.swingProgress > 0.0F ? 0.0F : this.swimAnimation;
            float f3 = handside == HandSide.LEFT && this.swingProgress > 0.0F ? 0.0F : this.swimAnimation;
            if (f1 < 14.0F) {
                this.leftArm.rotateAngleX = this.rotLerpRad(f3, this.leftArm.rotateAngleX, 0.0F);
                this.rightArm.rotateAngleX = MathHelper.lerp(f2, this.rightArm.rotateAngleX, 0.0F);
                this.leftArm.rotateAngleY = this.rotLerpRad(f3, this.leftArm.rotateAngleY, (float)Math.PI);
                this.rightArm.rotateAngleY = MathHelper.lerp(f2, this.rightArm.rotateAngleY, (float)Math.PI);
                this.leftArm.rotateAngleZ = this.rotLerpRad(f3, this.leftArm.rotateAngleZ, (float)Math.PI + 1.8707964F * this.getArmAngleSq(f1) / this.getArmAngleSq(14.0F));
                this.rightArm.rotateAngleZ = MathHelper.lerp(f2, this.rightArm.rotateAngleZ, (float)Math.PI - 1.8707964F * this.getArmAngleSq(f1) / this.getArmAngleSq(14.0F));
            } else if (f1 >= 14.0F && f1 < 22.0F) {
                float f6 = (f1 - 14.0F) / 8.0F;
                this.leftArm.rotateAngleX = this.rotLerpRad(f3, this.leftArm.rotateAngleX, ((float)Math.PI / 2F) * f6);
                this.rightArm.rotateAngleX = MathHelper.lerp(f2, this.rightArm.rotateAngleX, ((float)Math.PI / 2F) * f6);
                this.leftArm.rotateAngleY = this.rotLerpRad(f3, this.leftArm.rotateAngleY, (float)Math.PI);
                this.rightArm.rotateAngleY = MathHelper.lerp(f2, this.rightArm.rotateAngleY, (float)Math.PI);
                this.leftArm.rotateAngleZ = this.rotLerpRad(f3, this.leftArm.rotateAngleZ, 5.012389F - 1.8707964F * f6);
                this.rightArm.rotateAngleZ = MathHelper.lerp(f2, this.rightArm.rotateAngleZ, 1.2707963F + 1.8707964F * f6);
            } else if (f1 >= 22.0F && f1 < 26.0F) {
                float f4 = (f1 - 22.0F) / 4.0F;
                this.leftArm.rotateAngleX = this.rotLerpRad(f3, this.leftArm.rotateAngleX, ((float)Math.PI / 2F) - ((float)Math.PI / 2F) * f4);
                this.rightArm.rotateAngleX = MathHelper.lerp(f2, this.rightArm.rotateAngleX, ((float)Math.PI / 2F) - ((float)Math.PI / 2F) * f4);
                this.leftArm.rotateAngleY = this.rotLerpRad(f3, this.leftArm.rotateAngleY, (float)Math.PI);
                this.rightArm.rotateAngleY = MathHelper.lerp(f2, this.rightArm.rotateAngleY, (float)Math.PI);
                this.leftArm.rotateAngleZ = this.rotLerpRad(f3, this.leftArm.rotateAngleZ, (float)Math.PI);
                this.rightArm.rotateAngleZ = MathHelper.lerp(f2, this.rightArm.rotateAngleZ, (float)Math.PI);
            }

            float f7 = 0.3F;
            float f5 = 0.33333334F;
            this.leftLeg.rotateAngleX = MathHelper.lerp(this.swimAnimation, this.leftLeg.rotateAngleX, 0.3F * MathHelper.cos(limbSwing * 0.33333334F + (float)Math.PI));
            this.rightLeg.rotateAngleX = MathHelper.lerp(this.swimAnimation, this.rightLeg.rotateAngleX, 0.3F * MathHelper.cos(limbSwing * 0.33333334F));
        }

        this.bipedHeadwear.copyModelAngles(this.bipedHead);
    }

    protected Iterable<ModelRenderer> getBodyParts() {
        return Iterables.concat(super.getBodyParts(), ImmutableList.of(this.body, this.rightArm, this.leftArm, this.rightLeg, this.leftLeg));
    }

    @Override
    public void setLivingAnimations(@NotNull T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
        //previously the render function, render code was moved to a method below
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    protected void func_241654_b_(LivingEntity p_241654_1_) {
        switch(this.rightArmPose) {
            case EMPTY:
                this.rightArm.rotateAngleY = 0.0F;
                break;
            case BLOCK:
                this.rightArm.rotateAngleX = this.rightArm.rotateAngleX * 0.5F - 0.9424779F;
                this.rightArm.rotateAngleY = (-(float)Math.PI / 6F);
                break;
            case ITEM:
                this.rightArm.rotateAngleX = this.rightArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F);
                this.rightArm.rotateAngleY = 0.0F;
                break;
            case THROW_SPEAR:
                this.rightArm.rotateAngleX = this.rightArm.rotateAngleX * 0.5F - (float)Math.PI;
                this.rightArm.rotateAngleY = 0.0F;
                break;
            case BOW_AND_ARROW:
                this.rightArm.rotateAngleY = -0.1F + this.bipedHead.rotateAngleY;
                this.leftArm.rotateAngleY = 0.1F + this.bipedHead.rotateAngleY + 0.4F;
                this.rightArm.rotateAngleX = (-(float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
                this.leftArm.rotateAngleX = (-(float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
                break;
            case CROSSBOW_CHARGE:
                ModelHelper.func_239102_a_(this.rightArm, this.leftArm, p_241654_1_, true);
                break;
            case CROSSBOW_HOLD:
                ModelHelper.func_239104_a_(this.rightArm, this.leftArm, this.bipedHead, true);
        }

    }

    protected void func_241655_c_(LivingEntity p_241655_1_) {
        switch(this.leftArmPose) {
            case EMPTY:
                this.leftArm.rotateAngleY = 0.0F;
                break;
            case BLOCK:
                this.leftArm.rotateAngleX = this.leftArm.rotateAngleX * 0.5F - 0.9424779F;
                this.leftArm.rotateAngleY = ((float)Math.PI / 6F);
                break;
            case ITEM:
                this.leftArm.rotateAngleX = this.leftArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F);
                this.leftArm.rotateAngleY = 0.0F;
                break;
            case THROW_SPEAR:
                this.leftArm.rotateAngleX = this.leftArm.rotateAngleX * 0.5F - (float)Math.PI;
                this.leftArm.rotateAngleY = 0.0F;
                break;
            case BOW_AND_ARROW:
                this.rightArm.rotateAngleY = -0.1F + this.bipedHead.rotateAngleY - 0.4F;
                this.leftArm.rotateAngleY = 0.1F + this.bipedHead.rotateAngleY;
                this.rightArm.rotateAngleX = (-(float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
                this.leftArm.rotateAngleX = (-(float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
                break;
            case CROSSBOW_CHARGE:
                ModelHelper.func_239102_a_(this.rightArm, this.leftArm, p_241655_1_, false);
                break;
            case CROSSBOW_HOLD:
                ModelHelper.func_239104_a_(this.rightArm, this.leftArm, this.bipedHead, false);
        }

    }

    protected float getArmAngleSq(float limbSwing) {
        return -65.0F * limbSwing + limbSwing * limbSwing;
    }
}
