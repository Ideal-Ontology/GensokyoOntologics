package github.thelawf.gensokyoontology.common.enchantment;

import github.thelawf.gensokyoontology.core.EnchantRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class SphereShapeEnchantment extends Enchantment {
    public SphereShapeEnchantment() {
        super(Rarity.COMMON, EnchantRegistry.DANMAKU, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 15;
    }

    @Override
    protected boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench) && EnchantRegistry.CURVED_SHAPE.get() != ench &&
                EnchantRegistry.CIRCLE_SHAPE.get() != ench &&
                EnchantRegistry.LINEAR_SHAPE.get() != ench;
    }
}
