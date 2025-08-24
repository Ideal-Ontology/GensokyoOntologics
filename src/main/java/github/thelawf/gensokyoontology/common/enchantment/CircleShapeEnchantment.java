package github.thelawf.gensokyoontology.common.enchantment;

import github.thelawf.gensokyoontology.core.EnchantRegistry;
import net.minecraft.enchantment.Enchantment;

public class CircleShapeEnchantment extends DanmakuShotEnchant {
    public CircleShapeEnchantment() {
        super(Rarity.UNCOMMON);
    }

    @Override
    public int getMaxLevel() {
        return 15;
    }
    public int getMinLevel() {
        return 5;
    }

    @Override
    protected boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench) && EnchantRegistry.CURVED_SHAPE.get() != ench &&
                EnchantRegistry.SPHERE_SHAPE.get() != ench &&
                EnchantRegistry.LINEAR_SHAPE.get() != ench;
    }
}
