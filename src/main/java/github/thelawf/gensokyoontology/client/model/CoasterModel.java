package github.thelawf.gensokyoontology.client.model;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class CoasterModel extends EntityModel<Entity> {
	private final ModelRenderer bone;
	private final ModelRenderer front;
	private final ModelRenderer frontLeft_r1;
	private final ModelRenderer frontRight_r1;
	private final ModelRenderer left;
	private final ModelRenderer right;
	private final ModelRenderer down;
	private final ModelRenderer downRight_r1;
	private final ModelRenderer downLeft_r1;
	private final ModelRenderer back;
	private final ModelRenderer seat;
	private final ModelRenderer handles;
	private final ModelRenderer right_r1;
	private final ModelRenderer wheels;

	public CoasterModel() {
        textureWidth = 128;
        textureHeight = 128;

        bone = new ModelRenderer(this);
        bone.setRotationPoint(0.0F, 24.0F, 0.0F);
        setRotationAngle(bone, 0.0F, 0.0F, -3.1416F);


        front = new ModelRenderer(this);
        front.setRotationPoint(0.0F, 0.0F, -2.0F);
        bone.addChild(front);
        front.setTextureOffset(58, 65).addBox(-4.0F, -8.0F, -11.0F, 8.0F, 8.0F, 1.0F, 0.0F, false);

        frontLeft_r1 = new ModelRenderer(this);
        frontLeft_r1.setRotationPoint(4.0F, 0.0F, -11.0F);
        front.addChild(frontLeft_r1);
        setRotationAngle(frontLeft_r1, 0.0F, 0.6109F, 0.0F);
        frontLeft_r1.setTextureOffset(26, 65).addBox(-1.0F, -8.0F, 0.0F, 1.0F, 8.0F, 7.0F, 0.0F, false);

        frontRight_r1 = new ModelRenderer(this);
        frontRight_r1.setRotationPoint(-4.0F, 0.0F, -11.0F);
        front.addChild(frontRight_r1);
        setRotationAngle(frontRight_r1, 0.0F, -0.6109F, 0.0F);
        frontRight_r1.setTextureOffset(42, 65).addBox(0.0F, -8.0F, 0.0F, 1.0F, 8.0F, 7.0F, 0.0F, false);

        left = new ModelRenderer(this);
        left.setRotationPoint(7.0F, 0.0F, -1.25F);
        bone.addChild(left);
        left.setTextureOffset(0, 39).addBox(0.0F, -8.0F, -6.0F, 1.0F, 8.0F, 18.0F, 0.0F, false);

        right = new ModelRenderer(this);
        right.setRotationPoint(-8.0F, 0.0F, -1.25F);
        bone.addChild(right);
        right.setTextureOffset(38, 39).addBox(0.0F, -8.0F, -6.0F, 1.0F, 8.0F, 18.0F, 0.0F, false);

        down = new ModelRenderer(this);
        down.setRotationPoint(-8.0F, 0.0F, -1.25F);
        bone.addChild(down);
        down.setTextureOffset(0, 17).addBox(5.0F, -1.0F, -10.75F, 6.0F, 1.0F, 21.0F, 0.0F, false);
        down.setTextureOffset(1, 0).addBox(1.0F, -1.0F, -4.75F, 14.0F, 1.0F, 16.0F, 0.0F, false);

        downRight_r1 = new ModelRenderer(this);
        downRight_r1.setRotationPoint(7.65F, 0.0F, -10.0F);
        down.addChild(downRight_r1);
        setRotationAngle(downRight_r1, 0.0F, -0.6109F, 0.0F);
        downRight_r1.setTextureOffset(0, 65).addBox(-3.0F, -1.0F, 0.0F, 4.0F, 1.0F, 9.0F, 0.0F, false);

        downLeft_r1 = new ModelRenderer(this);
        downLeft_r1.setRotationPoint(9.15F, 0.0F, -10.6F);
        down.addChild(downLeft_r1);
        setRotationAngle(downLeft_r1, 0.0F, 0.6109F, 0.0F);
        downLeft_r1.setTextureOffset(62, 0).addBox(-3.0F, -1.0F, 0.0F, 5.0F, 1.0F, 9.0F, 0.0F, false);

        back = new ModelRenderer(this);
        back.setRotationPoint(-8.0F, 0.0F, -1.25F);
        bone.addChild(back);
        back.setTextureOffset(54, 29).addBox(0.0F, -8.0F, 11.25F, 16.0F, 8.0F, 1.0F, 0.0F, false);

        seat = new ModelRenderer(this);
        seat.setRotationPoint(-8.0F, 0.0F, -1.25F);
        bone.addChild(seat);
        seat.setTextureOffset(54, 17).addBox(1.0F, -4.0F, 2.25F, 14.0F, 3.0F, 9.0F, 0.0F, false);

        handles = new ModelRenderer(this);
        handles.setRotationPoint(0.0F, -1.4F, 5.0F);
        bone.addChild(handles);
        handles.setTextureOffset(62, 10).addBox(-7.0F, -6.0F, -2.0F, 14.0F, 1.0F, 1.0F, 0.0F, false);

        right_r1 = new ModelRenderer(this);
        right_r1.setRotationPoint(-6.0F, -6.0F, -2.0F);
        handles.addChild(right_r1);
        setRotationAngle(right_r1, -1.0908F, 0.0F, 0.0F);
        right_r1.setTextureOffset(62, 74).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 13.0F, 1.0F, 0.0F, false);
        right_r1.setTextureOffset(58, 74).addBox(12.0F, 0.0F, 0.0F, 1.0F, 13.0F, 1.0F, 0.0F, false);

        wheels = new ModelRenderer(this);
        wheels.setRotationPoint(0.0F, 0.0F, 0.0F);
        bone.addChild(wheels);
        wheels.setTextureOffset(66, 74).addBox(8.0F, -2.0F, -7.0F, 1.0F, 3.0F, 3.0F, 0.0F, false);
        wheels.setTextureOffset(74, 74).addBox(-9.0F, -2.0F, -7.0F, 1.0F, 3.0F, 3.0F, 0.0F, false);
        wheels.setTextureOffset(8, 75).addBox(-9.0F, -2.0F, 6.0F, 1.0F, 3.0F, 3.0F, 0.0F, false);
        wheels.setTextureOffset(0, 75).addBox(8.0F, -2.0F, 6.0F, 1.0F, 3.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}


	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        bone.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}