package github.thelawf.gensokyoontology.client.model;
// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.common.entity.passive.HumanResidentEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class MaleVillagerModel extends HumanrResidentModel {
	private final ModelRenderer head;
	private final ModelRenderer blink;
	private final ModelRenderer armRight;
	private final ModelRenderer armLeft;
	private final ModelRenderer body;
	private final ModelRenderer legLeft;
	private final ModelRenderer legRight;

	public MaleVillagerModel() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 4.0F, 0.0F);
		head.setTextureOffset(0, 27).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		blink = new ModelRenderer(this);
		blink.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(blink);
		blink.setTextureOffset(48, 24).addBox(-3.0F, -10.0F, -4.001F, 8.0F, 10.0F, 0.0F, 0.0F, false);

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
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		armRight.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		armLeft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		legLeft.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		legRight.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

}