package github.thelawf.gensokyoontology.client.model;// Made with Blockbench 4.7.0
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.common.entity.FairyEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class FairyModel extends EntityModel<FairyEntity> {
	private final ModelRenderer bone;
	private final ModelRenderer head;
	// private final ModelRenderer blink;
	private final ModelRenderer armRight;
	private final ModelRenderer armLeft;
	private final ModelRenderer body;
	private final ModelRenderer sittingRotationSkirt;
	private final ModelRenderer wingLeft;
	private final ModelRenderer wingRight;
	private final ModelRenderer legLeft;
	private final ModelRenderer legRight;

	public FairyModel() {
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

		armRight = new ModelRenderer(this);
		armRight.setRotationPoint(-3.0F, -17.5F, 0.0F);
		bone.addChild(armRight);
		setRotationAngle(armRight, 0.0F, 0.0F, 0.4363F);
		armRight.setTextureOffset(46, 22).addBox(-2.0F, 1.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
		armRight.setTextureOffset(0, 52).addBox(-2.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);

		armLeft = new ModelRenderer(this);
		armLeft.setRotationPoint(3.0F, -17.5F, 0.0F);
		bone.addChild(armLeft);
		setRotationAngle(armLeft, 0.0F, 0.0F, -0.4363F);
		armLeft.setTextureOffset(12, 52).addBox(-0.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);
		armLeft.setTextureOffset(46, 22).addBox(0.0F, 1.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

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

		legLeft = new ModelRenderer(this);
		legLeft.setRotationPoint(2.0F, -9.0F, 0.0F);
		bone.addChild(legLeft);
		legLeft.setTextureOffset(33, 18).addBox(-1.501F, 0.0F, -1.5F, 3.0F, 9.0F, 3.0F, 0.0F, false);

		legRight = new ModelRenderer(this);
		legRight.setRotationPoint(-2.0F, -9.0F, 0.0F);
		bone.addChild(legRight);
		legRight.setTextureOffset(32, 6).addBox(-1.499F, 0.0F, -1.5F, 3.0F, 9.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(FairyEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		bone.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}