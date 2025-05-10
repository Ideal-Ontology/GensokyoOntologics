package github.thelawf.gensokyoontology.common.item.tool;

import github.thelawf.gensokyoontology.common.item.touhou.GSKOItemTier;
import github.thelawf.gensokyoontology.common.util.GSKODamageSource;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class JadeSword extends SwordItem {
    public JadeSword(Properties properties) {
        super(GSKOItemTier.JADE, 6, 2f, properties);
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return super.hitEntity(stack, target, attacker);
    }
}
