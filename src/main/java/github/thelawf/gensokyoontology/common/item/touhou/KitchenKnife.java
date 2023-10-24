package github.thelawf.gensokyoontology.common.item.touhou;

import github.thelawf.gensokyoontology.core.init.itemtab.GSKOItemTab;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import org.jetbrains.annotations.NotNull;

public class KitchenKnife extends SwordItem {

    public KitchenKnife() {
        super(ItemTier.IRON, 3, -2.4F,
                new Properties().group(GSKOItemTab.GSKO_ITEM_TAB).maxDamage(40));
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack stack = itemStack.copy();
        if (stack.attemptDamageItem(1, random, null)) {
            return ItemStack.EMPTY;
        }
        return stack;
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack stack) {
        return false;
    }

    @Override
    public boolean hitEntity(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
        // 使用菜刀攻击牛可以掉落肥牛卷
        return super.hitEntity(stack, target, attacker);
    }
}
