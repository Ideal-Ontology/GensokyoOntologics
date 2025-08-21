package github.thelawf.gensokyoontology.common.enchantment;

import github.thelawf.gensokyoontology.core.EnchantRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class CurvedShapeEnchantment extends Enchantment {
    public CurvedShapeEnchantment() {
        super(Rarity.UNCOMMON, EnchantRegistry.DANMAKU, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    protected boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench) && EnchantRegistry.SPHERE_SHAPE.get() != ench &&
                EnchantRegistry.CIRCLE_SHAPE.get() != ench;
    }
}

