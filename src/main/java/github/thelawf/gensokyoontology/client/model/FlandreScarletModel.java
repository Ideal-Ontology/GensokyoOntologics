package github.thelawf.gensokyoontology.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import github.thelawf.gensokyoontology.common.entity.monster.FlandreScarletEntity;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class FlandreScarletModel extends BipedModel<FlandreScarletEntity> {

    private final ModelRenderer flandre;
    private final ModelRenderer head;
    private final ModelRenderer left_arm;
    private final ModelRenderer right_arm;
    private final ModelRenderer body;
    private final ModelRenderer skirt;
    private final ModelRenderer skirt_table_h_r1;
    private final ModelRenderer r1_8;
    private final ModelRenderer r1_7;
    private final ModelRenderer r1_6;
    private final ModelRenderer r1_5;
    private final ModelRenderer r1_4;
    private final ModelRenderer r1_3;
    private final ModelRenderer r1_2;
    private final ModelRenderer r1_1;
    private final ModelRenderer right_leg;
    private final ModelRenderer left_leg;
    private final ModelRenderer fly_left;
    private final ModelRenderer crystal;
    private final ModelRenderer l_2_r1;
    private final ModelRenderer crystal2;
    private final ModelRenderer l2_2_r1;
    private final ModelRenderer crystal3;
    private final ModelRenderer l3_2_r1;
    private final ModelRenderer crystal4;
    private final ModelRenderer l4_2_r1;
    private final ModelRenderer crystal5;
    private final ModelRenderer l5_2_r1;
    private final ModelRenderer crystal6;
    private final ModelRenderer l6_2_r1;
    private final ModelRenderer gan;
    private final ModelRenderer g2_r1;
    private final ModelRenderer g1_r1;
    private final ModelRenderer fly_right;
    private final ModelRenderer crystal7;
    private final ModelRenderer l7_2_r1;
    private final ModelRenderer crystal8;
    private final ModelRenderer l8_2_r1;
    private final ModelRenderer crystal9;
    private final ModelRenderer l9_2_r1;
    private final ModelRenderer crystal10;
    private final ModelRenderer l10_2_r1;
    private final ModelRenderer crystal11;
    private final ModelRenderer l11_2_r1;
    private final ModelRenderer crystal12;
    private final ModelRenderer l12_2_r1;
    private final ModelRenderer gan2;
    private final ModelRenderer gan2_2_r1;
    private final ModelRenderer gam2_1_r1;
    public FlandreScarletModel(float modelSize) {
        super(modelSize);
        textureWidth = 80;
        textureHeight = 80;

        flandre = new ModelRenderer(this);
        flandre.setRotationPoint(0.0F, 24.0F, 0.0F);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        flandre.addChild(head);
        head.setTextureOffset(0, 0).addBox(-4.0F, -31.25F, -3.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
        head.setTextureOffset(32, 0).addBox(-4.1F, -31.35F, -3.3F, 8.2F, 8.2F, 8.2F, 0.0F, false);

        left_arm = new ModelRenderer(this);
        left_arm.setRotationPoint(5.4F, -17.4F, 1.0F);
        flandre.addChild(left_arm);
        left_arm.setTextureOffset(51, 31).addBox(-1.6F, -6.0F, -2.1F, 3.2F, 12.0F, 4.2F, 0.0F, false);
        left_arm.setTextureOffset(40, 17).addBox(-1.5F, -5.8F, -2.0F, 3.0F, 11.6F, 4.0F, 0.0F, false);

        right_arm = new ModelRenderer(this);
        right_arm.setRotationPoint(-5.6F, -17.4F, 1.0F);
        flandre.addChild(right_arm);
        right_arm.setTextureOffset(51, 31).addBox(-1.6F, -6.0F, -2.1F, 3.2F, 12.0F, 4.2F, 0.0F, false);
        right_arm.setTextureOffset(33, 48).addBox(-1.5F, -5.8F, -2.0F, 3.0F, 11.6F, 4.0F, 0.0F, false);

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        flandre.addChild(body);
        body.setTextureOffset(20, 36).addBox(-4.0F, -23.0F, -1.2F, 8.0F, 5.0F, 0.0F, 0.0F, false);
        body.setTextureOffset(16, 17).addBox(-4.0F, -23.2F, -1.0F, 8.0F, 11.6F, 4.0F, 0.0F, false);

        skirt = new ModelRenderer(this);
        skirt.setRotationPoint(-0.0024F, -11.1567F, 1.0186F);
        body.addChild(skirt);

        skirt_table_h_r1 = new ModelRenderer(this);
        skirt_table_h_r1.setRotationPoint(0.0F, -2.5F, 3.0F);
        skirt.addChild(skirt_table_h_r1);
        setRotationAngle(skirt_table_h_r1, 0.2618F, 0.0F, 0.0F);
        skirt_table_h_r1.setTextureOffset(56, 17).addBox(-3.5F, -1.8F, -0.4F, 7.0F, 5.0F, 0.0F, 0.0F, false);

        r1_8 = new ModelRenderer(this);
        r1_8.setRotationPoint(3.9965F, 0.4301F, 2.0763F);
        skirt.addChild(r1_8);
        setRotationAngle(r1_8, 0.1309F, 0.0F, -0.1309F);
        r1_8.setTextureOffset(50, 66).addBox(-0.4F, -4.5F, -0.5F, 1.0F, 8.0F, 1.0F, 0.0F, false);

        r1_7 = new ModelRenderer(this);
        r1_7.setRotationPoint(-4.0291F, 0.429F, 2.0848F);
        skirt.addChild(r1_7);
        setRotationAngle(r1_7, 0.1309F, 0.0F, 0.1309F);
        r1_7.setTextureOffset(50, 66).addBox(-0.5F, -4.5F, -0.5F, 1.0F, 8.0F, 1.0F, 0.0F, false);

        r1_6 = new ModelRenderer(this);
        r1_6.setRotationPoint(3.9073F, 0.2412F, -1.9132F);
        skirt.addChild(r1_6);
        setRotationAngle(r1_6, -0.1309F, 0.0F, -0.1309F);
        r1_6.setTextureOffset(50, 66).addBox(-0.4F, -4.2F, -0.6F, 1.0F, 8.0F, 1.0F, 0.0F, false);

        r1_5 = new ModelRenderer(this);
        r1_5.setRotationPoint(-4.0121F, 0.2996F, -1.9237F);
        skirt.addChild(r1_5);
        setRotationAngle(r1_5, -0.1309F, 0.0F, 0.1309F);
        r1_5.setTextureOffset(50, 66).addBox(-0.5F, -4.2F, -0.6F, 1.0F, 8.0F, 1.0F, 0.0F, false);

        r1_4 = new ModelRenderer(this);
        r1_4.setRotationPoint(-4.4938F, 0.2324F, -0.0186F);
        skirt.addChild(r1_4);
        setRotationAngle(r1_4, 0.0F, 0.0F, 0.2618F);
        r1_4.setTextureOffset(46, 63).addBox(-0.7F, -4.3F, -2.0F, 2.0F, 8.0F, 4.0F, 0.0F, false);

        r1_3 = new ModelRenderer(this);
        r1_3.setRotationPoint(3.8882F, 1.2482F, -0.0186F);
        skirt.addChild(r1_3);
        setRotationAngle(r1_3, 0.0F, 0.0F, -0.2618F);
        r1_3.setTextureOffset(46, 63).addBox(-0.5F, -5.2F, -2.0F, 2.0F, 8.0F, 4.0F, 0.0F, false);

        r1_2 = new ModelRenderer(this);
        r1_2.setRotationPoint(0.0024F, 2.8126F, -2.6366F);
        skirt.addChild(r1_2);
        setRotationAngle(r1_2, 0.2618F, 0.0F, 0.0F);
        r1_2.setTextureOffset(25, 64).addBox(-4.0F, -5.5F, 4.3F, 8.0F, 8.0F, 2.0F, 0.0F, false);

        r1_1 = new ModelRenderer(this);
        r1_1.setRotationPoint(0.0024F, 0.0941F, -2.7364F);
        skirt.addChild(r1_1);
        setRotationAngle(r1_1, -0.2618F, 0.0F, 0.0F);
        r1_1.setTextureOffset(25, 64).addBox(-4.0F, -4.2F, -0.4F, 8.0F, 8.0F, 2.0F, 0.0F, false);

        right_leg = new ModelRenderer(this);
        right_leg.setRotationPoint(0.0F, 0.0F, 0.0F);
        flandre.addChild(right_leg);
        right_leg.setTextureOffset(0, 36).addBox(-4.1F, -7.9F, -1.1F, 4.0F, 8.0F, 4.2F, 0.0F, false);
        right_leg.setTextureOffset(0, 17).addBox(-4.0F, -11.6F, -1.0F, 4.0F, 11.6F, 4.0F, 0.0F, false);

        left_leg = new ModelRenderer(this);
        left_leg.setRotationPoint(0.0F, 0.0F, 0.0F);
        flandre.addChild(left_leg);
        left_leg.setTextureOffset(0, 51).addBox(0.1F, -7.9F, -1.1F, 4.0F, 8.0F, 4.2F, 0.0F, false);
        left_leg.setTextureOffset(17, 48).addBox(0.0F, -11.6F, -1.0F, 4.0F, 11.6F, 4.0F, 0.0F, false);

        fly_left = new ModelRenderer(this);
        fly_left.setRotationPoint(2.25F, 1.0F, 0.0F);
        flandre.addChild(fly_left);


        crystal = new ModelRenderer(this);
        crystal.setRotationPoint(-18.3489F, -18.0429F, 3.5071F);
        fly_left.addChild(crystal);
        crystal.setTextureOffset(0, 68).addBox(-0.2511F, -2.5571F, -0.2571F, 0.6F, 0.6F, 0.6F, 0.0F, false);

        l_2_r1 = new ModelRenderer(this);
        l_2_r1.setRotationPoint(0.0626F, -0.4571F, 0.0636F);
        crystal.addChild(l_2_r1);
        setRotationAngle(l_2_r1, 0.0F, -0.7854F, 0.0F);
        l_2_r1.setTextureOffset(0, 64).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        crystal2 = new ModelRenderer(this);
        crystal2.setRotationPoint(-18.6489F, -18.0429F, 3.5071F);
        fly_left.addChild(crystal2);
        crystal2.setTextureOffset(4, 68).addBox(2.7489F, -3.5571F, -0.2571F, 0.6F, 0.6F, 0.6F, 0.0F, false);

        l2_2_r1 = new ModelRenderer(this);
        l2_2_r1.setRotationPoint(3.0626F, -1.4571F, 0.0636F);
        crystal2.addChild(l2_2_r1);
        setRotationAngle(l2_2_r1, 0.0F, -0.7854F, 0.0F);
        l2_2_r1.setTextureOffset(4, 64).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        crystal3 = new ModelRenderer(this);
        crystal3.setRotationPoint(-18.9489F, -17.6429F, 3.5071F);
        fly_left.addChild(crystal3);
        crystal3.setTextureOffset(8, 68).addBox(5.7489F, -4.8571F, -0.2571F, 0.6F, 0.6F, 0.6F, 0.0F, false);

        l3_2_r1 = new ModelRenderer(this);
        l3_2_r1.setRotationPoint(6.0626F, -2.7571F, 0.0636F);
        crystal3.addChild(l3_2_r1);
        setRotationAngle(l3_2_r1, 0.0F, -0.7854F, 0.0F);
        l3_2_r1.setTextureOffset(8, 64).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        crystal4 = new ModelRenderer(this);
        crystal4.setRotationPoint(-18.9489F, -18.9429F, 3.5071F);
        fly_left.addChild(crystal4);
        crystal4.setTextureOffset(12, 68).addBox(8.5489F, -4.4571F, -0.2571F, 0.6F, 0.6F, 0.6F, 0.0F, false);

        l4_2_r1 = new ModelRenderer(this);
        l4_2_r1.setRotationPoint(8.8626F, -2.3571F, 0.0636F);
        crystal4.addChild(l4_2_r1);
        setRotationAngle(l4_2_r1, 0.0F, -0.7854F, 0.0F);
        l4_2_r1.setTextureOffset(12, 64).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        crystal5 = new ModelRenderer(this);
        crystal5.setRotationPoint(-18.7489F, -18.9429F, 3.5071F);
        fly_left.addChild(crystal5);
        crystal5.setTextureOffset(16, 68).addBox(11.0489F, -3.5571F, -0.2571F, 0.6F, 0.6F, 0.6F, 0.0F, false);

        l5_2_r1 = new ModelRenderer(this);
        l5_2_r1.setRotationPoint(11.3626F, -1.4571F, 0.0636F);
        crystal5.addChild(l5_2_r1);
        setRotationAngle(l5_2_r1, 0.0F, -0.7854F, 0.0F);
        l5_2_r1.setTextureOffset(16, 64).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        crystal6 = new ModelRenderer(this);
        crystal6.setRotationPoint(-17.3489F, -18.4429F, 3.5071F);
        fly_left.addChild(crystal6);
        crystal6.setTextureOffset(20, 68).addBox(12.3489F, -3.1571F, -0.2571F, 0.6F, 0.6F, 0.6F, 0.0F, false);

        l6_2_r1 = new ModelRenderer(this);
        l6_2_r1.setRotationPoint(12.6626F, -1.0571F, 0.0636F);
        crystal6.addChild(l6_2_r1);
        setRotationAngle(l6_2_r1, 0.0F, -0.7854F, 0.0F);
        l6_2_r1.setTextureOffset(20, 64).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        gan = new ModelRenderer(this);
        gan.setRotationPoint(0.0F, 0.0F, 0.0F);
        fly_left.addChild(gan);


        g2_r1 = new ModelRenderer(this);
        g2_r1.setRotationPoint(-14.5F, -22.5F, 3.5F);
        gan.addChild(g2_r1);
        setRotationAngle(g2_r1, 0.0F, 0.0F, -0.3491F);
        g2_r1.setTextureOffset(0, 71).addBox(-4.5F, -0.5F, -0.5F, 9.0F, 1.0F, 1.0F, 0.0F, false);

        g1_r1 = new ModelRenderer(this);
        g1_r1.setRotationPoint(-6.5F, -22.5F, 3.5F);
        gan.addChild(g1_r1);
        setRotationAngle(g1_r1, 0.0F, 0.0F, 0.3491F);
        g1_r1.setTextureOffset(0, 71).addBox(-4.5F, -0.5F, -0.5F, 9.0F, 1.0F, 1.0F, 0.0F, false);

        fly_right = new ModelRenderer(this);
        fly_right.setRotationPoint(18.75F, 1.0F, 7.0F);
        flandre.addChild(fly_right);
        setRotationAngle(fly_right, 0.0F, 3.1416F, 0.0F);


        crystal7 = new ModelRenderer(this);
        crystal7.setRotationPoint(2.7068F, -19.4F, 3.5604F);
        fly_right.addChild(crystal7);
        crystal7.setTextureOffset(0, 68).addBox(-0.3068F, -1.2F, -0.3104F, 0.6F, 0.6F, 0.6F, 0.0F, false);

        l7_2_r1 = new ModelRenderer(this);
        l7_2_r1.setRotationPoint(0.0068F, 0.9F, 0.0104F);
        crystal7.addChild(l7_2_r1);
        setRotationAngle(l7_2_r1, 0.0F, -0.7854F, 0.0F);
        l7_2_r1.setTextureOffset(0, 64).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        crystal8 = new ModelRenderer(this);
        crystal8.setRotationPoint(5.4068F, -20.4F, 3.5604F);
        fly_right.addChild(crystal8);
        crystal8.setTextureOffset(4, 68).addBox(-0.3068F, -1.2F, -0.3104F, 0.6F, 0.6F, 0.6F, 0.0F, false);

        l8_2_r1 = new ModelRenderer(this);
        l8_2_r1.setRotationPoint(0.0068F, 0.9F, 0.0104F);
        crystal8.addChild(l8_2_r1);
        setRotationAngle(l8_2_r1, 0.0F, -0.7854F, 0.0F);
        l8_2_r1.setTextureOffset(4, 64).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        crystal9 = new ModelRenderer(this);
        crystal9.setRotationPoint(8.1068F, -21.3F, 3.5604F);
        fly_right.addChild(crystal9);
        crystal9.setTextureOffset(8, 68).addBox(-0.3068F, -1.2F, -0.3104F, 0.6F, 0.6F, 0.6F, 0.0F, false);

        l9_2_r1 = new ModelRenderer(this);
        l9_2_r1.setRotationPoint(0.0068F, 0.9F, 0.0104F);
        crystal9.addChild(l9_2_r1);
        setRotationAngle(l9_2_r1, 0.0F, -0.7854F, 0.0F);
        l9_2_r1.setTextureOffset(8, 64).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        crystal10 = new ModelRenderer(this);
        crystal10.setRotationPoint(10.9068F, -22.2F, 3.5604F);
        fly_right.addChild(crystal10);
        crystal10.setTextureOffset(12, 68).addBox(-0.3068F, -1.2F, -0.3104F, 0.6F, 0.6F, 0.6F, 0.0F, false);

        l10_2_r1 = new ModelRenderer(this);
        l10_2_r1.setRotationPoint(0.0068F, 0.9F, 0.0104F);
        crystal10.addChild(l10_2_r1);
        setRotationAngle(l10_2_r1, 0.0F, -0.7854F, 0.0F);
        l10_2_r1.setTextureOffset(12, 64).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        crystal11 = new ModelRenderer(this);
        crystal11.setRotationPoint(13.6068F, -21.3F, 3.5604F);
        fly_right.addChild(crystal11);
        crystal11.setTextureOffset(16, 68).addBox(-0.3068F, -1.2F, -0.3104F, 0.6F, 0.6F, 0.6F, 0.0F, false);

        l11_2_r1 = new ModelRenderer(this);
        l11_2_r1.setRotationPoint(0.0068F, 0.9F, 0.0104F);
        crystal11.addChild(l11_2_r1);
        setRotationAngle(l11_2_r1, 0.0F, -0.7854F, 0.0F);
        l11_2_r1.setTextureOffset(16, 64).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        crystal12 = new ModelRenderer(this);
        crystal12.setRotationPoint(16.3068F, -20.4F, 3.5604F);
        fly_right.addChild(crystal12);
        crystal12.setTextureOffset(20, 68).addBox(-0.3068F, -1.2F, -0.3104F, 0.6F, 0.6F, 0.6F, 0.0F, false);

        l12_2_r1 = new ModelRenderer(this);
        l12_2_r1.setRotationPoint(0.0068F, 0.9F, 0.0104F);
        crystal12.addChild(l12_2_r1);
        setRotationAngle(l12_2_r1, 0.0F, -0.7854F, 0.0F);
        l12_2_r1.setTextureOffset(20, 64).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        gan2 = new ModelRenderer(this);
        gan2.setRotationPoint(10.5F, -22.5F, 3.5F);
        fly_right.addChild(gan2);


        gan2_2_r1 = new ModelRenderer(this);
        gan2_2_r1.setRotationPoint(-4.0F, 0.0F, 0.0F);
        gan2.addChild(gan2_2_r1);
        setRotationAngle(gan2_2_r1, 0.0F, 0.0F, -0.3491F);
        gan2_2_r1.setTextureOffset(0, 71).addBox(-4.5F, -0.5F, -0.5F, 9.0F, 1.0F, 1.0F, 0.0F, false);

        gam2_1_r1 = new ModelRenderer(this);
        gam2_1_r1.setRotationPoint(4.0F, 0.0F, 0.0F);
        gan2.addChild(gam2_1_r1);
        setRotationAngle(gam2_1_r1, 0.0F, 0.0F, 0.3491F);
        gam2_1_r1.setTextureOffset(0, 71).addBox(-4.5F, -0.5F, -0.5F, 9.0F, 1.0F, 1.0F, 0.0F, false);
    }

    @Override
    public void setLivingAnimations(FlandreScarletEntity entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        flandre.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
