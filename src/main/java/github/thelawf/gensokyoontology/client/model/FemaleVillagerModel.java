package github.thelawf.gensokyoontology.client.model;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.VindicatorRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class FemaleVillagerModel extends HumanrResidentModel {
	private final ModelRenderer ponytail;
	private final ModelRenderer hair;
	private final ModelRenderer hairLeft_r1;
	private final ModelRenderer hairRight_r1;
	private final ModelRenderer head;
	private final ModelRenderer armRight;
	private final ModelRenderer armLeft;
	private final ModelRenderer body;
	private final ModelRenderer legLeft;
	private final ModelRenderer legRight;
	private final ModelRenderer hairTie;
	private final ModelRenderer ropeLeft_r1;
	private final ModelRenderer tieLeft_r1;
	private final ModelRenderer ropeRight_r1;
	private final ModelRenderer tieRight_r1;
	private final ModelRenderer tail_r1;

	public FemaleVillagerModel() {
		textureWidth = 64;
		textureHeight = 64;

		ponytail = new ModelRenderer(this);
		ponytail.setRotationPoint(-1.0F, 21.0F, 13.0F);
		

		hair = new ModelRenderer(this);
		hair.setRotationPoint(-1.0F, 21.0F, 0.0F);
		

		hairLeft_r1 = new ModelRenderer(this);
		hairLeft_r1.setRotationPoint(-1.1F, -24.5F, -2.0F);
		hair.addChild(hairLeft_r1);
		setRotationAngle(hairLeft_r1, 0.0372F, -0.0774F, -0.0765F);
		hairLeft_r1.setTextureOffset(32, 47).addBox(-1.0F, -3.0F, -2.0F, 7.0F, 3.0F, 8.0F, 0.0F, false);

		hairRight_r1 = new ModelRenderer(this);
		hairRight_r1.setRotationPoint(-2.1F, -25.75F, -2.0F);
		hair.addChild(hairRight_r1);
		setRotationAngle(hairRight_r1, 0.0F, 0.0F, 0.2182F);
		hairRight_r1.setTextureOffset(32, 47).addBox(-1.0F, -3.0F, -2.0F, 7.0F, 3.0F, 8.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 4.0F, 0.0F);
		head.setTextureOffset(0, 27).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		armRight = new ModelRenderer(this);
		armRight.setRotationPoint(-5.0F, 3.5F, 0.0F);
		setRotationAngle(armRight, 0.0F, 0.0F, 0.0436F);
		armRight.setTextureOffset(30, 0).addBox(-2.5F, 0.0F, -1.5F, 3.0F, 10.0F, 3.0F, 0.0F, false);
		armRight.setTextureOffset(0, 42).addBox(-2.0F, 2.0F, -1.0F, 2.0F, 9.0F, 2.0F, 0.0F, false);

		armLeft = new ModelRenderer(this);
		armLeft.setRotationPoint(5.0F, 5.5F, 0.0F);
		setRotationAngle(armLeft, 0.0F, 0.0F, -0.0436F);
		armLeft.setTextureOffset(32, 13).addBox(-0.5F, -2.0F, -1.5F, 3.0F, 10.0F, 3.0F, 0.0F, false);
		armLeft.setTextureOffset(9, 43).addBox(0.0F, 0.0F, -1.0F, 2.0F, 9.0F, 2.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 13.5F, 0.0F);
		body.setTextureOffset(0, 0).addBox(-4.5F, -11.5F, -3.0F, 9.0F, 20.0F, 6.0F, 0.0F, false);

		legLeft = new ModelRenderer(this);
		legLeft.setRotationPoint(2.0F, 15.0F, 0.0F);
		legLeft.setTextureOffset(32, 26).addBox(-1.501F, 2.0F, -1.5F, 3.0F, 7.0F, 3.0F, 0.0F, false);

		legRight = new ModelRenderer(this);
		legRight.setRotationPoint(-2.0F, 15.0F, 0.0F);
		legRight.setTextureOffset(32, 36).addBox(-1.499F, 2.0F, -1.5F, 3.0F, 7.0F, 3.0F, 0.0F, false);

		hairTie = new ModelRenderer(this);
		hairTie.setRotationPoint(4.75F, -5.0F, 0.5F);
		setRotationAngle(hairTie, 0.0F, 1.5708F, 0.0F);
		hairTie.setTextureOffset(56, 2).addBox(-0.25F, -0.5F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);

		ropeLeft_r1 = new ModelRenderer(this);
		ropeLeft_r1.setRotationPoint(3.0F, 1.0F, 0.5F);
		hairTie.addChild(ropeLeft_r1);
		setRotationAngle(ropeLeft_r1, 0.0F, 0.0F, 2.618F);
		ropeLeft_r1.setTextureOffset(19, 55).addBox(2.0F, 0.5F, -1.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		tieLeft_r1 = new ModelRenderer(this);
		tieLeft_r1.setRotationPoint(2.0F, 2.25F, 0.5F);
		hairTie.addChild(tieLeft_r1);
		setRotationAngle(tieLeft_r1, 0.0F, 0.0F, -2.618F);
		tieLeft_r1.setTextureOffset(51, 22).addBox(2.0F, -0.5F, -1.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);

		ropeRight_r1 = new ModelRenderer(this);
		ropeRight_r1.setRotationPoint(2.0F, 1.5F, 0.5F);
		hairTie.addChild(ropeRight_r1);
		setRotationAngle(ropeRight_r1, 0.0F, 0.0F, 0.5236F);
		ropeRight_r1.setTextureOffset(0, 55).addBox(-1.0F, -0.5F, -1.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		tieRight_r1 = new ModelRenderer(this);
		tieRight_r1.setRotationPoint(3.0F, 1.5F, 0.5F);
		hairTie.addChild(tieRight_r1);
		setRotationAngle(tieRight_r1, 0.0F, 0.0F, -0.5236F);
		tieRight_r1.setTextureOffset(48, 17).addBox(-1.0F, -2.5F, -1.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);

		tail_r1 = new ModelRenderer(this);
		tail_r1.setRotationPoint(0.25F, 3.5F, 0.0F);
		hairTie.addChild(tail_r1);
		setRotationAngle(tail_r1, 0.1745F, 0.0F, 0.0F);
		tail_r1.setTextureOffset(44, 48).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		ponytail.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		hair.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		armRight.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		armLeft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		legLeft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		legRight.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		hairTie.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;

	}

}