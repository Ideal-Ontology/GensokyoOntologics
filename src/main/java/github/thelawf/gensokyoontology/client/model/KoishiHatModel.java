package github.thelawf.gensokyoontology.client.model;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
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
    public KoishiHatModel(float modelSize) {
        super(modelSize);
    }
}
