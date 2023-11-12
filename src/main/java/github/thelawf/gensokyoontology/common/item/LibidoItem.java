package github.thelawf.gensokyoontology.common.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;

import java.util.Collection;

/**
 * 拥有“投射”nbt标签的该物品被称为：力比多·投射，其作用是在右键生物时将自身的状态效果转移给该生物
 */
public class LibidoItem extends Item {
    public LibidoItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand) {

        if (stack.getTag() != null && stack.getTag().contains("projection")) {
            Collection<EffectInstance> effects = playerIn.getActivePotionEffects();
            effects.forEach(target::addPotionEffect);
            stack.shrink(1);
        }
        return super.itemInteractionForEntity(stack, playerIn, target, hand);
    }

}
