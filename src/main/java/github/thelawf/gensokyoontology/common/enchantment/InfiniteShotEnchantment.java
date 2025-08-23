package github.thelawf.gensokyoontology.common.enchantment;

import github.thelawf.gensokyoontology.core.EnchantRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;

public class InfiniteShotEnchantment extends DanmakuShotEnchant {
    public InfiniteShotEnchantment() {
        super(Rarity.VERY_RARE);
    }

    @Override
    protected boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench) && Enchantments.INFINITY != ench;
    }
}
