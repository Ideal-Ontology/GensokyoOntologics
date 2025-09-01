package github.thelawf.gensokyoontology.common.enchantment;

import github.thelawf.gensokyoontology.core.EnchantRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

public abstract class DanmakuShotEnchant extends Enchantment {
    protected DanmakuShotEnchant(Rarity rarityIn) {
        super(rarityIn, EnchantRegistry.DANMAKU, new EquipmentSlotType[]{EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND});
    }

    /** 这里是用来禁止玩家通过原版附魔台的方式获取到该附魔*/
    @Override
    public int getMinEnchantability(int level) {
        return -114514;
    }

    /** 这里是用来禁止玩家通过原版附魔台的方式获取到该附魔*/
    @Override
    public int getMaxEnchantability(int level) {
        return -1919810;
    }
}
