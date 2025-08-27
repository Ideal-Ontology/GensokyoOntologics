package github.thelawf.gensokyoontology.common.enchantment;

import github.thelawf.gensokyoontology.core.EnchantRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class CooldownHasteEnchantment extends Enchantment {
    public CooldownHasteEnchantment() {
        super(Rarity.VERY_RARE, EnchantRegistry.COOLDOWN_MODIFIABLE, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getMinEnchantability(int level) {
        return 114514;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return 1919810;
    }
}
