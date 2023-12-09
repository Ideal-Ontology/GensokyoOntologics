package github.thelawf.gensokyoontology.client.model;// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.common.entity.monster.KomeijiKoishiEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class KomeijiKoishiModel extends EntityModel<KomeijiKoishiEntity> {
	private final ModelRenderer koishi;
	private final ModelRenderer hand;
	private final ModelRenderer mao;
	private final ModelRenderer hat;
	private final ModelRenderer hE_r1;
	private final ModelRenderer hW_r1;
	private final ModelRenderer left_arm;
	private final ModelRenderer right_arm;
	private final ModelRenderer body;
	private final ModelRenderer skirt;
	private final ModelRenderer s8_r1;
	private final ModelRenderer s7_r1;
	private final ModelRenderer s6_r1;
	private final ModelRenderer s5_r1;
	private final ModelRenderer s4_r1;
	private final ModelRenderer s3_r1;
	private final ModelRenderer s2_r1;
	private final ModelRenderer s1_r1;
	private final ModelRenderer eye;
	private final ModelRenderer you;
	private final ModelRenderer wire_r1;
	private final ModelRenderer wire_r2;
	private final ModelRenderer wire_r3;
	private final ModelRenderer wire_r4;
	private final ModelRenderer wire_r5;
	private final ModelRenderer wire_r6;
	private final ModelRenderer wire_r7;
	private final ModelRenderer wire_r8;
	private final ModelRenderer wire_r9;
	private final ModelRenderer wire_r10;
	private final ModelRenderer wire_r11;
	private final ModelRenderer zuo;
	private final ModelRenderer wire_r12;
	private final ModelRenderer wire_r13;
	private final ModelRenderer wire_r14;
	private final ModelRenderer wire_r15;
	private final ModelRenderer wire_r16;
	private final ModelRenderer wire_r17;
	private final ModelRenderer wire_r18;
	private final ModelRenderer wire_r19;
	private final ModelRenderer wire_r20;
	private final ModelRenderer wire_r21;
	private final ModelRenderer wire_r22;
	private final ModelRenderer wire_r23;
	private final ModelRenderer wire_r24;
	private final ModelRenderer wire_r25;
	private final ModelRenderer right_leg;
	private final ModelRenderer xin;
	private final ModelRenderer wire_r26;
	private final ModelRenderer wire_r27;
	private final ModelRenderer left_leg;
	private final ModelRenderer xin2;
	private final ModelRenderer wire_r28;
	private final ModelRenderer wire_r29;

	public KomeijiKoishiModel() {
		textureWidth = 80;
		textureHeight = 80;

		koishi = new ModelRenderer(this);
		koishi.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		hand = new ModelRenderer(this);
		hand.setRotationPoint(0.0F, 0.0F, 0.0F);
		koishi.addChild(hand);
		hand.setTextureOffset(0, 0).addBox(-4.0F, -31.25F, -3.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		mao = new ModelRenderer(this);
		mao.setRotationPoint(0.0F, 0.0F, 0.0F);
		hand.addChild(mao);
		mao.setTextureOffset(23, 68).addBox(-4.15F, -32.0F, -3.25F, 8.2F, 3.0F, 8.3F, 0.0F, false);
		mao.setTextureOffset(22, 65).addBox(-6.0F, -29.0F, -5.0F, 12.0F, 1.0F, 12.0F, 0.0F, false);

		hat = new ModelRenderer(this);
		hat.setRotationPoint(0.0F, 0.0F, 0.0F);
		hand.addChild(hat);
		hat.setTextureOffset(64, 18).addBox(-4.0F, -31.3F, -3.2F, 8.0F, 8.0F, 0.0F, 0.0F, false);
		hat.setTextureOffset(56, 9).addBox(-4.0F, -31.5F, -3.0F, 8.0F, 0.0F, 8.0F, 0.0F, false);
		hat.setTextureOffset(64, 36).addBox(-4.0F, -31.0F, 5.25F, 8.0F, 8.0F, 0.0F, 0.0F, false);

		hE_r1 = new ModelRenderer(this);
		hE_r1.setRotationPoint(-3.0F, -26.0F, -1.0F);
		hat.addChild(hE_r1);
		setRotationAngle(hE_r1, 0.0F, -1.5708F, 0.0F);
		hE_r1.setTextureOffset(64, 0).addBox(-2.0F, -5.2F, -7.15F, 8.0F, 8.0F, 0.0F, 0.0F, false);

		hW_r1 = new ModelRenderer(this);
		hW_r1.setRotationPoint(-4.2F, -27.1F, 0.9F);
		hat.addChild(hW_r1);
		setRotationAngle(hW_r1, 0.0F, -1.5708F, 0.0F);
		hW_r1.setTextureOffset(64, 27).addBox(-4.0F, -4.0F, 0.0F, 8.0F, 8.0F, 0.0F, 0.0F, false);

		left_arm = new ModelRenderer(this);
		left_arm.setRotationPoint(5.4F, -17.4F, 1.0F);
		koishi.addChild(left_arm);
		left_arm.setTextureOffset(31, 48).addBox(-1.6F, -6.0F, -2.1F, 3.2F, 12.0F, 4.2F, 0.0F, false);
		left_arm.setTextureOffset(40, 16).addBox(-1.5F, -5.8F, -2.0F, 3.0F, 11.6F, 4.0F, 0.0F, false);

		right_arm = new ModelRenderer(this);
		right_arm.setRotationPoint(-5.6F, -17.4F, 1.0F);
		koishi.addChild(right_arm);
		right_arm.setTextureOffset(40, 32).addBox(-1.6F, -6.0F, -2.1F, 3.2F, 12.0F, 4.2F, 0.0F, false);
		right_arm.setTextureOffset(16, 48).addBox(-1.5F, -5.8F, -2.0F, 3.0F, 11.6F, 4.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		koishi.addChild(body);
		body.setTextureOffset(16, 16).addBox(-4.0F, -23.2F, -1.0F, 8.0F, 11.6F, 4.0F, 0.0F, false);
		body.setTextureOffset(20, 32).addBox(-4.0F, -24.0F, -1.1F, 8.0F, 7.0F, 0.0F, 0.0F, false);
		body.setTextureOffset(20, 40).addBox(-4.0F, -23.0F, 3.1F, 8.0F, 2.0F, 0.0F, 0.0F, false);

		skirt = new ModelRenderer(this);
		skirt.setRotationPoint(-0.0024F, -11.1567F, 1.0186F);
		body.addChild(skirt);
		

		s8_r1 = new ModelRenderer(this);
		s8_r1.setRotationPoint(3.9965F, 0.4301F, 2.0763F);
		skirt.addChild(s8_r1);
		setRotationAngle(s8_r1, 0.1309F, 0.0F, -0.1309F);
		s8_r1.setTextureOffset(4, 70).addBox(-0.9F, -4.5F, -1.0F, 1.5F, 8.0F, 1.5F, 0.0F, false);

		s7_r1 = new ModelRenderer(this);
		s7_r1.setRotationPoint(-4.0291F, 0.429F, 2.0848F);
		skirt.addChild(s7_r1);
		setRotationAngle(s7_r1, 0.1309F, 0.0F, 0.1309F);
		s7_r1.setTextureOffset(5, 70).addBox(-0.5F, -5.5F, -1.0F, 1.5F, 8.0F, 1.5F, 0.0F, false);

		s6_r1 = new ModelRenderer(this);
		s6_r1.setRotationPoint(3.9073F, 0.2412F, -1.9132F);
		skirt.addChild(s6_r1);
		setRotationAngle(s6_r1, -0.1309F, 0.0F, -0.1309F);
		s6_r1.setTextureOffset(10, 70).addBox(-0.9F, -4.2F, -0.6F, 1.5F, 8.0F, 1.5F, 0.0F, false);

		s5_r1 = new ModelRenderer(this);
		s5_r1.setRotationPoint(-4.0121F, 0.2996F, -1.9237F);
		skirt.addChild(s5_r1);
		setRotationAngle(s5_r1, -0.1309F, 0.0F, 0.1309F);
		s5_r1.setTextureOffset(6, 70).addBox(-0.75F, -4.3F, -0.6F, 1.5F, 8.0F, 1.5F, 0.0F, false);

		s4_r1 = new ModelRenderer(this);
		s4_r1.setRotationPoint(-4.4938F, 0.2324F, -0.0186F);
		skirt.addChild(s4_r1);
		setRotationAngle(s4_r1, 0.0F, 0.0F, 0.2618F);
		s4_r1.setTextureOffset(0, 68).addBox(-0.7F, -4.3F, -2.0F, 2.0F, 8.0F, 4.0F, 0.0F, false);

		s3_r1 = new ModelRenderer(this);
		s3_r1.setRotationPoint(3.8882F, 1.2482F, -0.0186F);
		skirt.addChild(s3_r1);
		setRotationAngle(s3_r1, 0.0F, 0.0F, -0.2618F);
		s3_r1.setTextureOffset(0, 68).addBox(-0.5F, -5.2F, -2.0F, 2.0F, 8.0F, 4.0F, 0.0F, false);

		s2_r1 = new ModelRenderer(this);
		s2_r1.setRotationPoint(0.0024F, 2.8126F, -2.6366F);
		skirt.addChild(s2_r1);
		setRotationAngle(s2_r1, 0.2618F, 0.0F, 0.0F);
		s2_r1.setTextureOffset(0, 70).addBox(-4.0F, -5.5F, 4.3F, 8.0F, 8.0F, 2.0F, 0.0F, false);

		s1_r1 = new ModelRenderer(this);
		s1_r1.setRotationPoint(0.0024F, 0.0941F, -2.7364F);
		skirt.addChild(s1_r1);
		setRotationAngle(s1_r1, -0.2618F, 0.0F, 0.0F);
		s1_r1.setTextureOffset(0, 70).addBox(-4.0F, -4.2F, -0.4F, 8.0F, 8.0F, 2.0F, 0.0F, false);

		eye = new ModelRenderer(this);
		eye.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(eye);
		eye.setTextureOffset(49, 49).addBox(1.0F, -21.0F, -3.75F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		you = new ModelRenderer(this);
		you.setRotationPoint(-2.3262F, -21.7904F, -1.6477F);
		eye.addChild(you);
		setRotationAngle(you, 0.0F, 0.0873F, -2.0071F);
		

		wire_r1 = new ModelRenderer(this);
		wire_r1.setRotationPoint(-20.304F, -2.3078F, -1.2725F);
		you.addChild(wire_r1);
		setRotationAngle(wire_r1, -1.3424F, 0.0545F, 1.4802F);
		wire_r1.setTextureOffset(49, 54).addBox(-1.3F, -0.25F, -0.25F, 2.0F, 0.5F, 0.5F, 0.0F, false);

		wire_r2 = new ModelRenderer(this);
		wire_r2.setRotationPoint(-20.2197F, -0.7262F, 1.299F);
		you.addChild(wire_r2);
		setRotationAngle(wire_r2, -0.9355F, -1.1974F, 1.4928F);
		wire_r2.setTextureOffset(49, 54).addBox(-3.0F, -0.25F, -0.25F, 4.5F, 0.5F, 0.5F, 0.0F, false);

		wire_r3 = new ModelRenderer(this);
		wire_r3.setRotationPoint(-18.8852F, 0.4318F, 3.7199F);
		you.addChild(wire_r3);
		setRotationAngle(wire_r3, 0.0F, -0.6545F, 0.48F);
		wire_r3.setTextureOffset(49, 54).addBox(-2.0F, -0.25F, -0.25F, 4.0F, 0.5F, 0.5F, 0.0F, false);

		wire_r4 = new ModelRenderer(this);
		wire_r4.setRotationPoint(-15.8747F, 1.0005F, 5.4906F);
		you.addChild(wire_r4);
		setRotationAngle(wire_r4, 0.0F, -0.3927F, -0.0873F);
		wire_r4.setTextureOffset(49, 54).addBox(-2.0F, -0.25F, -0.25F, 4.0F, 0.5F, 0.5F, 0.0F, false);

		wire_r5 = new ModelRenderer(this);
		wire_r5.setRotationPoint(-9.7114F, -2.0467F, 5.1887F);
		you.addChild(wire_r5);
		setRotationAngle(wire_r5, 0.0F, 0.1745F, -0.5672F);
		wire_r5.setTextureOffset(49, 54).addBox(-5.5F, -0.25F, -0.25F, 11.0F, 0.5F, 0.5F, 0.0F, false);

		wire_r6 = new ModelRenderer(this);
		wire_r6.setRotationPoint(-5.2446F, -4.9707F, 1.9046F);
		you.addChild(wire_r6);
		setRotationAngle(wire_r6, 0.0F, -1.6144F, -1.1345F);
		wire_r6.setTextureOffset(49, 54).addBox(-2.5F, -0.25F, -0.25F, 5.0F, 0.5F, 0.5F, 0.0F, false);

		wire_r7 = new ModelRenderer(this);
		wire_r7.setRotationPoint(-7.1512F, -0.8818F, -0.3429F);
		you.addChild(wire_r7);
		setRotationAngle(wire_r7, 0.0F, 0.0F, -1.1345F);
		wire_r7.setTextureOffset(49, 54).addBox(-4.5F, -0.25F, -0.25F, 9.0F, 0.5F, 0.5F, 0.0F, false);

		wire_r8 = new ModelRenderer(this);
		wire_r8.setRotationPoint(-8.9998F, 3.0827F, 1.9177F);
		you.addChild(wire_r8);
		setRotationAngle(wire_r8, 0.0436F, 1.5272F, -1.0908F);
		wire_r8.setTextureOffset(49, 54).addBox(-2.5F, -0.25F, -0.25F, 5.0F, 0.5F, 0.5F, 0.0F, false);

		wire_r9 = new ModelRenderer(this);
		wire_r9.setRotationPoint(-2.9161F, -0.0054F, 5.0789F);
		you.addChild(wire_r9);
		setRotationAngle(wire_r9, 0.0F, -0.1309F, -0.48F);
		wire_r9.setTextureOffset(49, 54).addBox(-7.0F, -0.25F, -0.25F, 14.5F, 0.5F, 0.5F, 0.0F, false);

		wire_r10 = new ModelRenderer(this);
		wire_r10.setRotationPoint(3.3877F, -3.1824F, 2.8143F);
		you.addChild(wire_r10);
		setRotationAngle(wire_r10, 0.0F, -1.5272F, -0.6545F);
		wire_r10.setTextureOffset(49, 54).addBox(-2.6F, -0.25F, -0.25F, 6.0F, 0.5F, 0.5F, 0.0F, false);

		wire_r11 = new ModelRenderer(this);
		wire_r11.setRotationPoint(-0.2185F, 0.4886F, -0.3612F);
		you.addChild(wire_r11);
		setRotationAngle(wire_r11, 0.0F, -0.2182F, -0.7418F);
		wire_r11.setTextureOffset(49, 54).addBox(-4.25F, -0.55F, -0.65F, 9.5F, 0.5F, 0.5F, 0.0F, false);

		zuo = new ModelRenderer(this);
		zuo.setRotationPoint(0.0F, 0.0F, 0.0F);
		eye.addChild(zuo);
		

		wire_r12 = new ModelRenderer(this);
		wire_r12.setRotationPoint(-4.4077F, -2.1596F, -1.3379F);
		zuo.addChild(wire_r12);
		setRotationAngle(wire_r12, 0.0F, 0.0F, 0.1745F);
		wire_r12.setTextureOffset(49, 54).addBox(-1.0F, -0.25F, -0.15F, 2.0F, 0.5F, 0.5F, 0.0F, false);

		wire_r13 = new ModelRenderer(this);
		wire_r13.setRotationPoint(-5.8871F, -2.8596F, -0.9538F);
		zuo.addChild(wire_r13);
		setRotationAngle(wire_r13, 0.0F, 0.3491F, 0.829F);
		wire_r13.setTextureOffset(49, 54).addBox(-1.0F, -0.25F, -0.25F, 2.0F, 0.5F, 0.5F, 0.0F, false);

		wire_r14 = new ModelRenderer(this);
		wire_r14.setRotationPoint(-6.8446F, -4.09F, -0.1034F);
		zuo.addChild(wire_r14);
		setRotationAngle(wire_r14, 0.0F, 0.7854F, 1.1345F);
		wire_r14.setTextureOffset(49, 54).addBox(-1.0F, -0.25F, -0.15F, 2.0F, 0.5F, 0.5F, 0.0F, false);

		wire_r15 = new ModelRenderer(this);
		wire_r15.setRotationPoint(-7.0568F, -5.979F, 2.0179F);
		zuo.addChild(wire_r15);
		setRotationAngle(wire_r15, 0.0F, 0.7854F, 1.6144F);
		wire_r15.setTextureOffset(49, 54).addBox(-2.0F, -0.25F, -0.25F, 4.0F, 0.5F, 0.5F, 0.0F, false);

		wire_r16 = new ModelRenderer(this);
		wire_r16.setRotationPoint(-6.0409F, -8.6339F, 4.2891F);
		zuo.addChild(wire_r16);
		setRotationAngle(wire_r16, 0.0F, 0.48F, 2.3126F);
		wire_r16.setTextureOffset(49, 54).addBox(-2.0F, -0.45F, -0.25F, 4.0F, 0.5F, 0.5F, 0.0F, false);

		wire_r17 = new ModelRenderer(this);
		wire_r17.setRotationPoint(-0.0864F, -13.2787F, 4.6516F);
		zuo.addChild(wire_r17);
		setRotationAngle(wire_r17, 0.0F, -0.0873F, 2.4871F);
		wire_r17.setTextureOffset(49, 53).addBox(-6.0F, -0.25F, -0.25F, 12.0F, 0.5F, 0.5F, 0.0F, false);

		wire_r18 = new ModelRenderer(this);
		wire_r18.setRotationPoint(5.3604F, -18.6217F, 4.1224F);
		zuo.addChild(wire_r18);
		setRotationAngle(wire_r18, 0.0F, 0.0F, 1.9635F);
		wire_r18.setTextureOffset(49, 54).addBox(-2.0F, -0.25F, -0.25F, 4.0F, 0.5F, 0.5F, 0.0F, false);

		wire_r19 = new ModelRenderer(this);
		wire_r19.setRotationPoint(6.3444F, -22.3962F, 4.1224F);
		zuo.addChild(wire_r19);
		setRotationAngle(wire_r19, 0.0F, 0.0F, 1.7017F);
		wire_r19.setTextureOffset(49, 54).addBox(-2.0F, -0.25F, -0.25F, 4.0F, 0.5F, 0.5F, 0.0F, false);

		wire_r20 = new ModelRenderer(this);
		wire_r20.setRotationPoint(5.9744F, -25.3397F, 3.5016F);
		zuo.addChild(wire_r20);
		setRotationAngle(wire_r20, 0.0F, -0.3665F, 1.0472F);
		wire_r20.setTextureOffset(49, 54).addBox(-1.5F, -0.25F, -0.25F, 3.0F, 0.5F, 0.5F, 0.0F, false);

		wire_r21 = new ModelRenderer(this);
		wire_r21.setRotationPoint(5.968F, -26.9586F, 2.5646F);
		zuo.addChild(wire_r21);
		setRotationAngle(wire_r21, 0.0F, 0.3927F, -0.7418F);
		wire_r21.setTextureOffset(49, 54).addBox(-1.0F, -0.25F, -0.25F, 2.0F, 0.5F, 0.5F, 0.0F, false);

		wire_r22 = new ModelRenderer(this);
		wire_r22.setRotationPoint(6.9201F, -26.7966F, 1.8413F);
		zuo.addChild(wire_r22);
		setRotationAngle(wire_r22, 0.0F, 0.6109F, 0.8727F);
		wire_r22.setTextureOffset(49, 54).addBox(-1.0F, -0.15F, -0.45F, 2.0F, 0.5F, 0.5F, 0.0F, false);

		wire_r23 = new ModelRenderer(this);
		wire_r23.setRotationPoint(7.7394F, -26.6662F, 0.8053F);
		zuo.addChild(wire_r23);
		setRotationAngle(wire_r23, 0.0F, 0.3927F, -0.7418F);
		wire_r23.setTextureOffset(49, 54).addBox(-1.0F, -0.25F, -0.25F, 2.0F, 0.5F, 0.5F, 0.0F, false);

		wire_r24 = new ModelRenderer(this);
		wire_r24.setRotationPoint(8.8992F, -26.0957F, 0.0148F);
		zuo.addChild(wire_r24);
		setRotationAngle(wire_r24, 0.0F, 0.3927F, 0.9599F);
		wire_r24.setTextureOffset(49, 54).addBox(-1.5F, -0.35F, -0.25F, 3.0F, 0.5F, 0.5F, 0.0F, false);

		wire_r25 = new ModelRenderer(this);
		wire_r25.setRotationPoint(6.0738F, -22.3904F, -1.6477F);
		zuo.addChild(wire_r25);
		setRotationAngle(wire_r25, 0.0F, -0.2618F, -0.6545F);
		wire_r25.setTextureOffset(49, 54).addBox(-4.75F, -0.25F, -0.25F, 9.5F, 0.5F, 0.5F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(0.0F, 0.0F, 0.0F);
		koishi.addChild(right_leg);
		right_leg.setTextureOffset(0, 16).addBox(-4.0F, -11.6F, -1.0F, 4.0F, 11.6F, 4.0F, 0.0F, false);

		xin = new ModelRenderer(this);
		xin.setRotationPoint(-2.7113F, -1.4532F, -1.25F);
		right_leg.addChild(xin);
		

		wire_r26 = new ModelRenderer(this);
		wire_r26.setRotationPoint(1.0607F, -0.3536F, 0.0F);
		xin.addChild(wire_r26);
		setRotationAngle(wire_r26, 0.0F, 0.0F, 0.7854F);
		wire_r26.setTextureOffset(52, 56).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		wire_r27 = new ModelRenderer(this);
		wire_r27.setRotationPoint(0.0F, 0.0F, 0.0F);
		xin.addChild(wire_r27);
		setRotationAngle(wire_r27, 0.0F, 0.0F, -0.7854F);
		wire_r27.setTextureOffset(51, 55).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(0.0F, 0.0F, 0.0F);
		koishi.addChild(left_leg);
		left_leg.setTextureOffset(0, 48).addBox(0.0F, -11.6F, -1.0F, 4.0F, 11.6F, 4.0F, 0.0F, false);

		xin2 = new ModelRenderer(this);
		xin2.setRotationPoint(-2.7113F, -1.4532F, -1.25F);
		left_leg.addChild(xin2);
		

		wire_r28 = new ModelRenderer(this);
		wire_r28.setRotationPoint(5.7607F, -0.3536F, 0.0F);
		xin2.addChild(wire_r28);
		setRotationAngle(wire_r28, 0.0F, 0.0F, 0.7854F);
		wire_r28.setTextureOffset(52, 56).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		wire_r29 = new ModelRenderer(this);
		wire_r29.setRotationPoint(4.7F, 0.0F, 0.0F);
		xin2.addChild(wire_r29);
		setRotationAngle(wire_r29, 0.0F, 0.0F, -0.7854F);
		wire_r29.setTextureOffset(51, 55).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(KomeijiKoishiEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}


	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		koishi.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
	}
}