package github.thelawf.gensokyoontology.common.enchantment;

import github.thelawf.gensokyoontology.core.EnchantRegistry;
import net.minecraft.enchantment.Enchantment;

public class LinearShapeEnchantment extends DanmakuShotEnchant {
    public LinearShapeEnchantment() {
        super(Rarity.VERY_RARE);
    }

    @Override
    public int getMaxLevel() {
        return 15;
    }

    @Override
    protected boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench) && EnchantRegistry.CURVED_SHAPE.get() != ench &&
                EnchantRegistry.CIRCLE_SHAPE.get() != ench &&
                EnchantRegistry.SPHERE_SHAPE.get() != ench;
    }
}

