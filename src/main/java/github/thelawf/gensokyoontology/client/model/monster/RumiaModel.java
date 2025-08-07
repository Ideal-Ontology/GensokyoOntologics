package github.thelawf.gensokyoontology.client.model.monster;// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.common.entity.monster.RumiaEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class RumiaModel extends EntityModel<RumiaEntity> {
	private final ModelRenderer head;
	private final ModelRenderer armRight;
	private final ModelRenderer armLeft;
	private final ModelRenderer body;
	private final ModelRenderer sittingRotationSkirt;
	private final ModelRenderer legLeft;
	private final ModelRenderer legRight;
	private final ModelRenderer hairtie;
	private final ModelRenderer tie2_r1;
	private final ModelRenderer tie1_r1;

	public RumiaModel() {
		textureWidth = 128;
		textureHeight = 128;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 6.0F, 0.0F);
		head.setTextureOffset(0, 37).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		armRight = new ModelRenderer(this);
		armRight.setRotationPoint(-3.0F, 8.5F, 0.0F);
		setRotationAngle(armRight, 0.0F, 0.0F, 1.6144F);
		armRight.setTextureOffset(60, 58).addBox(-2.0F, 1.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
		armRight.setTextureOffset(24, 58).addBox(-2.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);

		armLeft = new ModelRenderer(this);
		armLeft.setRotationPoint(3.0F, 8.5F, 0.0F);
		setRotationAngle(armLeft, 0.0F, 0.0F, -1.6144F);
		armLeft.setTextureOffset(36, 58).addBox(-0.5F, 0.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);
		armLeft.setTextureOffset(60, 58).addBox(0.0F, 1.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 13.5F, 0.0F);
		body.setTextureOffset(52, 0).addBox(-3.5F, -7.5F, -3.0F, 7.0F, 9.0F, 6.0F, 0.0F, false);

		sittingRotationSkirt = new ModelRenderer(this);
		sittingRotationSkirt.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(sittingRotationSkirt);
		sittingRotationSkirt.setTextureOffset(32, 48).addBox(-4.0F, 0.5F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);
		sittingRotationSkirt.setTextureOffset(32, 37).addBox(-4.5F, 2.5F, -4.5F, 9.0F, 2.0F, 9.0F, 0.0F, false);

		legLeft = new ModelRenderer(this);
		legLeft.setRotationPoint(2.0F, 15.0F, 0.0F);
		legLeft.setTextureOffset(0, 53).addBox(-1.501F, 0.0F, -1.5F, 3.0F, 9.0F, 3.0F, 0.0F, false);

		legRight = new ModelRenderer(this);
		legRight.setRotationPoint(-2.0F, 15.0F, 0.0F);
		legRight.setTextureOffset(12, 53).addBox(-1.499F, 0.0F, -1.5F, 3.0F, 9.0F, 3.0F, 0.0F, false);

		hairtie = new ModelRenderer(this);
		hairtie.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		tie2_r1 = new ModelRenderer(this);
		tie2_r1.setRotationPoint(4.0F, -24.0F, 0.5F);
		hairtie.addChild(tie2_r1);
		setRotationAngle(tie2_r1, 0.0879F, -0.151F, -0.5303F);
		tie2_r1.setTextureOffset(2, 16).addBox(-0.0152F, -1.0F, -0.1737F, 3.0F, 2.0F, 0.0F, 0.0F, false);

		tie1_r1 = new ModelRenderer(this);
		tie1_r1.setRotationPoint(4.0F, -24.0F, 0.0F);
		hairtie.addChild(tie1_r1);
		setRotationAngle(tie1_r1, 0.0692F, 0.2527F, 0.2706F);
		tie1_r1.setTextureOffset(2, 16).addBox(-0.2929F, -1.0F, 0.2247F, 3.0F, 2.0F, 0.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(RumiaEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		armRight.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		armLeft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		legLeft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		legRight.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		hairtie.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}