package github.thelawf.gensokyoontology.client.model;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/** 用于渲染盔甲的图层渲染器中有一个获取装备模型的方法叫
 * {@link net.minecraft.client.renderer.entity.layers.BipedArmorLayer#getArmorModelHook(LivingEntity, ItemStack, EquipmentSlotType, BipedModel) getArmorModelHook}。 <br>
 * 所以我们在渲染自定义3D盔甲时就不需要自己写渲染器了，只需继承自盔甲物品并重写 {@link net.minecraft.item.ArmorItem#getArmorModel(LivingEntity, ItemStack, EquipmentSlotType, BipedModel) getArmorModel} 方法，
 * 然后写一个仅客户端可用的静态方法初始化模型，最后在客户端初始化事件中调用这个静态方法就行了。
 * */
@OnlyIn(Dist.CLIENT)
public class KoishiHatModel extends BipedModel<AbstractClientPlayerEntity> {


    private final ModelRenderer hat;
    private final ModelRenderer hatDown;
    private final ModelRenderer hatMiddle;

    public KoishiHatModel(float modelSize) {
        super(modelSize);

        hat = new ModelRenderer(this);
        hat.setRotationPoint(0.0F, 0.0F, 0.0F);
        hat.setTextureOffset(33, 67).addBox(-4.1F, -32.05F, -3.1F, 8.2F, 2.0F, 8.2F, 0.0F, false);

        hatMiddle = new ModelRenderer(this);
        hatMiddle.setRotationPoint(0.0F, -0.25F, 0.0F);
        hatMiddle.setTextureOffset(23, 69).addBox(-4.1F, -29.8F, -3.1F, 8.2F, 1.0F, 8.2F, 0.0F, false);
        hat.addChild(hatMiddle);

        hatDown = new ModelRenderer(this);
        hatDown.setRotationPoint(0.0F, -29.8F, 0.9167F);
        hatDown.setTextureOffset(27, 65).addBox(-5.5F, 0.5F, -5.6667F, 11.0F, 1.0F, 11.0F, 0.0F, false);
        hat.addChild(hatDown);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
