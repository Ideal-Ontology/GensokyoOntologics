package github.thelawf.gensokyoontology.client.model.monster;
// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.common.entity.monster.CirnoEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class CirnoModel extends EntityModel<CirnoEntity> {
	private final ModelRenderer bone;
	private final ModelRenderer head;
	private final ModelRenderer armRight;
	private final ModelRenderer armLeft;
	private final ModelRenderer body;
	private final ModelRenderer sittingRotationSkirt;
	private final ModelRenderer wingRight;
	private final ModelRenderer crystalBottomR_r1;
	private final ModelRenderer crystalMidR_r1;
	private final ModelRenderer crystalTopR_r1;
	private final ModelRenderer wingLeft;
	private final ModelRenderer crystalBottomL_r1;
	private final ModelRenderer crystalMidL_r1;
	private final ModelRenderer crystalTopL_r1;
	private final ModelRenderer legLeft;
	private final ModelRenderer legRight;
	private final ModelRenderer hairTie;
	private final ModelRenderer ropeLeft_r1;
	private final ModelRenderer tieLeft_r1;
	private final ModelRenderer ropeRight_r1;
	private final ModelRenderer tieRight_r1;

	public CirnoModel() {
		textureWidth = 128;
		textureHeight = 128;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -18.0F, 0.0F);
		bone.addChild(head);
		head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		armRight = new ModelRenderer(this);
		armRight.setRotationPoint(-3.0F, -17.5F, 0.0F);
		bone.addChild(armRight);
		setRotationAngle(armRight, 0.0F, 0.0F, 0.4363F);
		armRight.setTextureOffset(0, 56).addBox(-2.0F, 1.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
		armRight.setTextureOffset(44, 52).addBox(-2.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);

		armLeft = new ModelRenderer(this);
		armLeft.setRotationPoint(3.0F, -17.5F, 0.0F);
		bone.addChild(armLeft);
		setRotationAngle(armLeft, 0.0F, 0.0F, -0.4363F);
		armLeft.setTextureOffset(32, 48).addBox(-0.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);
		armLeft.setTextureOffset(32, 55).addBox(0.0F, 1.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -10.5F, 0.0F);
		bone.addChild(body);
		body.setTextureOffset(32, 0).addBox(-3.0F, -7.499F, -3.0F, 6.0F, 9.0F, 6.0F, 0.0F, false);

		sittingRotationSkirt = new ModelRenderer(this);
		sittingRotationSkirt.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(sittingRotationSkirt);
		sittingRotationSkirt.setTextureOffset(0, 16).addBox(-4.5F, 2.5F, -4.5F, 9.0F, 2.0F, 9.0F, 0.0F, false);
		sittingRotationSkirt.setTextureOffset(0, 27).addBox(-4.0F, 0.5F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);
		sittingRotationSkirt.setTextureOffset(32, 27).addBox(-3.5F, -1.5F, -3.5F, 7.0F, 2.0F, 7.0F, 0.0F, false);

		wingRight = new ModelRenderer(this);
		wingRight.setRotationPoint(0.5F, -14.0F, 4.5F);
		bone.addChild(wingRight);
		setRotationAngle(wingRight, 0.2166F, -1.4388F, -0.2535F);
		

		crystalBottomR_r1 = new ModelRenderer(this);
		crystalBottomR_r1.setRotationPoint(1.0F, 1.0F, 2.25F);
		wingRight.addChild(crystalBottomR_r1);
		setRotationAngle(crystalBottomR_r1, -0.6728F, 0.4016F, -0.3019F);
		crystalBottomR_r1.setTextureOffset(0, 45).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 6.0F, 0.0F, false);

		crystalMidR_r1 = new ModelRenderer(this);
		crystalMidR_r1.setRotationPoint(1.0F, -3.0F, 2.25F);
		wingRight.addChild(crystalMidR_r1);
		setRotationAngle(crystalMidR_r1, 0.0F, 0.2182F, 0.0F);
		crystalMidR_r1.setTextureOffset(0, 37).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 6.0F, 0.0F, false);

		crystalTopR_r1 = new ModelRenderer(this);
		crystalTopR_r1.setRotationPoint(1.0F, -7.0F, 2.25F);
		wingRight.addChild(crystalTopR_r1);
		setRotationAngle(crystalTopR_r1, 0.5059F, 0.3082F, 0.1665F);
		crystalTopR_r1.setTextureOffset(16, 37).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 6.0F, 0.0F, false);

		wingLeft = new ModelRenderer(this);
		wingLeft.setRotationPoint(0.5F, -14.0F, 5.0F);
		bone.addChild(wingLeft);
		setRotationAngle(wingLeft, 0.036F, 0.7395F, -0.0187F);
		

		crystalBottomL_r1 = new ModelRenderer(this);
		crystalBottomL_r1.setRotationPoint(1.0F, 1.0F, 2.25F);
		wingLeft.addChild(crystalBottomL_r1);
		setRotationAngle(crystalBottomL_r1, -0.6728F, 0.4016F, -0.3019F);
		crystalBottomL_r1.setTextureOffset(44, 44).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 6.0F, 0.0F, false);

		crystalMidL_r1 = new ModelRenderer(this);
		crystalMidL_r1.setRotationPoint(1.0F, -3.0F, 2.25F);
		wingLeft.addChild(crystalMidL_r1);
		setRotationAngle(crystalMidL_r1, 0.0F, 0.2182F, 0.0F);
		crystalMidL_r1.setTextureOffset(44, 36).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 6.0F, 0.0F, false);

		crystalTopL_r1 = new ModelRenderer(this);
		crystalTopL_r1.setRotationPoint(1.0F, -7.0F, 2.25F);
		wingLeft.addChild(crystalTopL_r1);
		setRotationAngle(crystalTopL_r1, 0.5059F, 0.3082F, 0.1665F);
		crystalTopL_r1.setTextureOffset(16, 37).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 6.0F, 0.0F, false);

		legLeft = new ModelRenderer(this);
		legLeft.setRotationPoint(2.0F, -9.0F, 0.0F);
		bone.addChild(legLeft);
		legLeft.setTextureOffset(36, 15).addBox(-1.501F, 0.0F, -1.5F, 3.0F, 9.0F, 3.0F, 0.0F, false);

		legRight = new ModelRenderer(this);
		legRight.setRotationPoint(-2.0F, -9.0F, 0.0F);
		bone.addChild(legRight);
		legRight.setTextureOffset(32, 36).addBox(-1.499F, 0.0F, -1.5F, 3.0F, 9.0F, 3.0F, 0.0F, false);

		hairTie = new ModelRenderer(this);
		hairTie.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(hairTie);
		hairTie.setTextureOffset(56, 0).addBox(-2.0F, -26.0F, 4.0F, 3.0F, 4.0F, 1.0F, 0.0F, false);

		ropeLeft_r1 = new ModelRenderer(this);
		ropeLeft_r1.setRotationPoint(-1.0F, -22.5F, 5.0F);
		hairTie.addChild(ropeLeft_r1);
		setRotationAngle(ropeLeft_r1, 0.0F, 0.0F, 2.618F);
		ropeLeft_r1.setTextureOffset(16, 53).addBox(-1.0F, -0.5F, -1.0F, 7.0F, 2.0F, 1.0F, 0.0F, false);

		tieLeft_r1 = new ModelRenderer(this);
		tieLeft_r1.setRotationPoint(-2.0F, -24.5F, 5.0F);
		hairTie.addChild(tieLeft_r1);
		setRotationAngle(tieLeft_r1, 0.0F, 0.0F, -2.618F);
		tieLeft_r1.setTextureOffset(48, 20).addBox(-1.0F, -2.5F, -1.0F, 7.0F, 4.0F, 1.0F, 0.0F, false);

		ropeRight_r1 = new ModelRenderer(this);
		ropeRight_r1.setRotationPoint(1.0F, -23.5F, 5.0F);
		hairTie.addChild(ropeRight_r1);
		setRotationAngle(ropeRight_r1, 0.0F, 0.0F, 0.5236F);
		ropeRight_r1.setTextureOffset(0, 53).addBox(-1.0F, -0.5F, -1.0F, 7.0F, 2.0F, 1.0F, 0.0F, false);

		tieRight_r1 = new ModelRenderer(this);
		tieRight_r1.setRotationPoint(2.0F, -23.5F, 5.0F);
		hairTie.addChild(tieRight_r1);
		setRotationAngle(tieRight_r1, 0.0F, 0.0F, -0.5236F);
		tieRight_r1.setTextureOffset(48, 15).addBox(-1.0F, -2.5F, -1.0F, 7.0F, 4.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(CirnoEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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